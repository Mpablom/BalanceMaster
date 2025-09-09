package com.BalanceMaster.gestor_ventas.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.BalanceMaster.gestor_ventas.converters.PurchaseConverter;
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
}
