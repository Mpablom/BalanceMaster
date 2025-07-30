package com.BalanceMaster.gestor_ventas.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.BalanceMaster.gestor_ventas.converters.PurchaseConverter;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.*;
import com.BalanceMaster.gestor_ventas.repositories.InventoryRepository;
import com.BalanceMaster.gestor_ventas.repositories.PurchaseRepository;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;
import com.BalanceMaster.gestor_ventas.services.PurchaseService;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
  private final PurchaseRepository purchaseRepository;
  private final PurchaseConverter purchaseConverter;
  private final InventoryRepository inventoryRepository;
  private final SupplierRepository supplierRepository;

  @Override
  public PurchaseResponseDTO createPurchase(PurchaseRequestDTO request) {
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
        .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));
    if (supplier.getDeleted()) {
      throw new IllegalStateException("Cannot register purchase with deleted supplier");
    }
    Purchase purchase = purchaseConverter.toEntity(request);
    purchase.setId(UUID.randomUUID().toString());
    Purchase saved = purchaseRepository.save(purchase);

    for (TransactionItem item : purchase.getItems()) {
      Product product = item.getProduct();
      Inventory inventory = inventoryRepository.findByProduct(product)
          .orElseGet(() -> Inventory.builder()
              .product(product)
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
  public List<PurchaseResponseDTO> getAllPurchases() {
    return purchaseRepository.findAll().stream()
        .map(purchaseConverter::toDTO)
        .toList();
  }

  @Override
  public PurchaseResponseDTO findPurchaseById(String id) {
    Purchase purchase = purchaseRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Purchase not found"));
    return purchaseConverter.toDTO(purchase);
  }
}
