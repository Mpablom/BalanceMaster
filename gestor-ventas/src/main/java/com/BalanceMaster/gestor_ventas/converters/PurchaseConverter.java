package com.BalanceMaster.gestor_ventas.converters;

import java.time.LocalDateTime;
import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.entities.Purchase;
import com.BalanceMaster.gestor_ventas.entities.Supplier;
import com.BalanceMaster.gestor_ventas.entities.TransactionItem;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseConverter {
  private final SupplierRepository supplierRepository;
  private final ProductRepository productRepository;

  public Purchase toEntity(PurchaseRequestDTO request) {
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
        .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

    Purchase purchase = new Purchase();
    purchase.setDate(LocalDateTime.now());
    purchase.setSupplier(supplier);
    purchase.setInvoiceNumber(request.getInvoiceNumber());

    List<TransactionItem> items = request.getItems().stream().map(itemDTO -> {
      Product product = productRepository.findById(itemDTO.getProductId())
          .orElseThrow(() -> new EntityNotFoundException("Product not found"));
      TransactionItem item = TransactionItem.builder()
          .product(product)
          .amount(itemDTO.getAmount())
          .unitPrice(itemDTO.getUnitPrice())
          .transaction(purchase)
          .build();
      return item;
    }).toList();

    purchase.setItems(items);
    purchase.setTotal(items.stream()
        .mapToDouble(i -> i.getAmount() * i.getUnitPrice())
        .sum());

    return purchase;
  }

  public PurchaseResponseDTO toDTO(Purchase purchase) {
    return PurchaseResponseDTO.builder()
        .id(purchase.getId())
        .date(purchase.getDate())
        .total(purchase.getTotal())
        .supplierId(purchase.getSupplier().getId())
        .supplierName(purchase.getSupplier().getName())
        .invoiceNumber(purchase.getInvoiceNumber())
        .items(purchase.getItems().stream().map(item -> TransactionItemResponseDTO.builder()
            .id(item.getId())
            .productId(item.getProduct().getId())
            .productName(item.getProduct().getName())
            .amount(item.getAmount())
            .unitPrice(item.getUnitPrice())
            .subtotal(item.getAmount() * item.getUnitPrice())
            .build()).toList())
        .build();
  }
}
