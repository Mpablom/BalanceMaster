package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.converters.SaleConverter;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Inventory;
import com.BalanceMaster.gestor_ventas.entities.Sale;
import com.BalanceMaster.gestor_ventas.entities.TransactionItem;
import com.BalanceMaster.gestor_ventas.repositories.InventoryRepository;
import com.BalanceMaster.gestor_ventas.repositories.SaleRepository;
import com.BalanceMaster.gestor_ventas.services.SaleService;

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

  @Override
  @Transactional
  public SaleResponseDTO createSale(SaleRequestDTO request) {
    Sale sale = saleConverter.toEntity(request);

    for (TransactionItem item : sale.getItems()) {
      Inventory inventory = inventoryRepository.findByProduct(item.getProduct())
          .orElseThrow(() -> new EntityNotFoundException("No inventory for product"));

      if (inventory.getQuantity() < item.getAmount()) {
        throw new IllegalArgumentException("Not enough stock for product");
      }

      inventory.setQuantity(inventory.getQuantity() - item.getAmount());
      inventory.setLastUpdated(sale.getDate());
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
}
