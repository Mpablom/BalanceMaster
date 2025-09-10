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

import com.BalanceMaster.gestor_ventas.converters.PurchaseConverter;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.DailyPurchaseDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.*;
import com.BalanceMaster.gestor_ventas.repositories.InventoryRepository;
import com.BalanceMaster.gestor_ventas.repositories.PurchaseRepository;
import com.BalanceMaster.gestor_ventas.services.PurchaseService;
import com.BalanceMaster.gestor_ventas.services.ValidationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final PurchaseConverter purchaseConverter;
  private final InventoryRepository inventoryRepository;
  private final ValidationService validationService;

  @Override
  @Transactional
  public PurchaseResponseDTO createPurchase(PurchaseRequestDTO request) {
    Supplier supplier = validationService.validateActiveSupplier(request.getSupplierId());

    Purchase purchase = purchaseConverter.toEntity(request);

    if (purchase.getSupplier() == null) {
      purchase.setSupplier(supplier);
    }

    for (TransactionItem item : purchase.getItems()) {
      Product validatedProduct = validationService.validateActiveProduct(item.getProduct().getId());
      item.setProduct(validatedProduct);

      if (item.getUnitPrice() == null || item.getUnitPrice() <= 0) {
        item.setUnitPrice(validatedProduct.getSalePrice());
      }
      if (item.getUnitCost() == null) {
        item.setUnitCost(validatedProduct.getPurchasePrice());
      }
      if (item.getAmount() <= 0) {
        throw new IllegalArgumentException("Item amount must be greater than zero");
      }
    }

    Purchase saved = purchaseRepository.save(purchase);

    for (TransactionItem item : saved.getItems()) {
      Inventory inventory = inventoryRepository.findByProduct(item.getProduct())
          .orElseGet(() -> Inventory.builder()
              .product(item.getProduct())
              .quantity(0)
              .lastUpdated(LocalDateTime.now())
              .build());

      inventory.setQuantity(inventory.getQuantity() + item.getAmount());
      inventory.setLastUpdated(LocalDateTime.now());
      inventoryRepository.save(inventory);
    }

    return purchaseConverter.toDTO(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public List<PurchaseResponseDTO> getAllPurchases() {
    return purchaseRepository.findAll().stream()
        .map(purchaseConverter::toDTO)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public PurchaseResponseDTO findPurchaseById(String id) {
    Purchase purchase = purchaseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));
    return purchaseConverter.toDTO(purchase);
  }

  @Override
  @Transactional(readOnly = true)
  public List<DailyPurchaseDTO> getDailyPurchases() {
    LocalDate today = LocalDate.now();
    LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
    LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

    List<Purchase> purchases = purchaseRepository.findByDateBetween(
        startOfWeek.atStartOfDay(),
        endOfWeek.atTime(23, 59, 59));

    Map<DayOfWeek, Double> grouped = purchases.stream()
        .collect(Collectors.groupingBy(
            p -> p.getDate().getDayOfWeek(),
            Collectors.summingDouble(Purchase::getTotal)));

    List<DailyPurchaseDTO> daily = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      if (day.getValue() >= DayOfWeek.MONDAY.getValue() &&
          day.getValue() <= DayOfWeek.SUNDAY.getValue()) {
        daily.add(DailyPurchaseDTO.builder()
            .day(day.getDisplayName(TextStyle.SHORT, Locale.getDefault()))
            .amount(grouped.getOrDefault(day, 0.0))
            .build());
      }
    }

    return daily;
  }

  @Override
  @Transactional(readOnly = true)
  public double getTotalPurchases() {
    return purchaseRepository.findAll().stream()
        .mapToDouble(Purchase::getTotal)
        .sum();
  }
}
