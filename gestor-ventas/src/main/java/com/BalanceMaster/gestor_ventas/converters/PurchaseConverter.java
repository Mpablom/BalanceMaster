package com.BalanceMaster.gestor_ventas.converters;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Purchase;
import com.BalanceMaster.gestor_ventas.entities.Supplier;
import com.BalanceMaster.gestor_ventas.entities.TransactionItem;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PurchaseConverter {
  private final SupplierRepository supplierRepository;
  private final TransactionItemConverter transactionItemConverter;

  public Purchase toEntity(PurchaseRequestDTO request) {
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
        .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

    Purchase purchase = new Purchase();
    purchase.setDate(LocalDateTime.now());
    purchase.setSupplier(supplier);
    purchase.setInvoiceNumber(request.getInvoiceNumber());
    purchase.setId("PUR-" + UUID.randomUUID());

    List<TransactionItem> items = request.getItems().stream()
        .map(itemdto -> transactionItemConverter.toEntity(itemdto, purchase))
        .toList();

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
        .items(transactionItemConverter.toDTOList(purchase.getItems()))
        .build();
  }
}
