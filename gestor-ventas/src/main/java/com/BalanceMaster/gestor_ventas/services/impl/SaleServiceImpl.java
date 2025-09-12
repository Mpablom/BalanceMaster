package com.BalanceMaster.gestor_ventas.services.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import com.BalanceMaster.gestor_ventas.converters.SaleConverter;
import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductStockDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.DailySalesDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.*;
import com.BalanceMaster.gestor_ventas.repositories.InventoryRepository;
import com.BalanceMaster.gestor_ventas.repositories.SaleRepository;
import com.BalanceMaster.gestor_ventas.services.SaleService;
import com.BalanceMaster.gestor_ventas.services.ValidationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
  private final SaleRepository saleRepository;
  private final InventoryRepository inventoryRepository;
  private final SaleConverter saleConverter;
  private final ValidationService validationService;

  @Override
  @Transactional
  public SaleResponseDTO createSale(SaleRequestDTO request) {
    Customer customer = null;
    if (request.getCustomerId() != null) {
      customer = validationService.validateActiveCustomer(request.getCustomerId());
    }

    Sale sale = saleConverter.toEntity(request);
    sale.setCustomer(customer);

    for (TransactionItem item : sale.getItems()) {
      Product validatedProduct = validationService.validateActiveProduct(item.getProduct().getId());
      item.setProduct(validatedProduct);

      Inventory inventory = inventoryRepository.findByProduct(validatedProduct)
          .orElseThrow(() -> new EntityNotFoundException("No inventory for product"));

      if (inventory.getQuantity() < item.getAmount()) {
        throw new IllegalArgumentException("Not enough stock for product: " + validatedProduct.getName());
      }

      inventory.setQuantity(inventory.getQuantity() - item.getAmount());
      inventory.setLastUpdated(LocalDateTime.now());
      inventoryRepository.save(inventory);
    }

    Sale saved = saleRepository.save(sale);
    return saleConverter.toDTO(saved);
  }

  @Override
  public SaleResponseDTO getSaleById(String id) {
    Sale sale = saleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Sale not found"));
    return saleConverter.toDTO(sale);
  }

  @Override
  public Page<SaleResponseDTO> getAllSales(Pageable pageable) {
    return saleRepository.findAll(pageable).map(saleConverter::toDTO);
  }

  @Override
  @Transactional(readOnly = true)
  public List<DailySalesDTO> getDailySales() {
    LocalDate today = LocalDate.now();
    LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

    List<Sale> sales = saleRepository.findByDateBetween(
        startOfWeek.atStartOfDay(),
        endOfWeek.atTime(23, 59, 59));

    Map<DayOfWeek, Double> grouped = sales.stream()
        .collect(Collectors.groupingBy(
            s -> s.getDate().getDayOfWeek(),
            Collectors.summingDouble(Sale::getTotal)));

    List<DailySalesDTO> daily = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      if (day.getValue() >= DayOfWeek.MONDAY.getValue() &&
          day.getValue() <= DayOfWeek.SUNDAY.getValue()) {
        daily.add(DailySalesDTO.builder()
            .day(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
            .sales(grouped.getOrDefault(day, 0.0))
            .build());
      }
    }

    return daily;
  }

  @Override
  public Double getTotalSales() {
    return saleRepository.findAll()
        .stream()
        .mapToDouble(Sale::getTotal)
        .sum();
  }

  @Override
  public List<ProductStockDTO> getLowStockProducts() {
    return inventoryRepository.findAll()
        .stream()
        .filter(inv -> inv.getQuantity() <= inv.getProduct().getMinStock())
        .map(inv -> new ProductStockDTO(inv.getProduct().getName(), inv.getQuantity()))
        .toList();
  }
}
