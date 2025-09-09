package com.BalanceMaster.gestor_ventas.converters;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.*;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionItemConverter {

  private final ProductRepository productRepository;

  public TransactionItem toEntity(TransactionItemRequestDTO dto, Transaction transaction) {
    TransactionItem item = new TransactionItem();
    item.setAmount(dto.getAmount());
    item.setUnitPrice(dto.getUnitPrice());

    Product product = productRepository.findById(dto.getProductId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    item.setProduct(product);

    if (transaction instanceof Purchase) {
      item.setUnitCost(dto.getUnitCost());
    } else if (transaction instanceof Sale) {
      item.setUnitCost(product.getPurchasePrice());
    }

    item.setTransaction(transaction);
    return item;
  }

  public TransactionItemResponseDTO toDTO(TransactionItem item) {
    if (item == null)
      return null;

    Product p = item.getProduct();

    ProductResponseDTO productDTO = null;
    if (p != null) {
      InventoryResponseDTO inventoryDTO = null;
      if (p.getInventory() != null) {
        inventoryDTO = InventoryResponseDTO.builder()
            .quantity(p.getInventory().getQuantity())
            .productName(p.getName())
            .lastUpdated(p.getInventory().getLastUpdated())
            .build();
      }

      CategoryResponseDTO categoryDTO = null;
      if (p.getCategory() != null) {
        categoryDTO = CategoryResponseDTO.builder()
            .id(p.getCategory().getId())
            .name(p.getCategory().getName())
            .description(p.getCategory().getDescription())
            .marginPercentage(p.getCategory().getMarginPercentage())
            .build();
      }

      productDTO = ProductResponseDTO.builder()
          .id(p.getId())
          .barcode(p.getBarcode())
          .name(p.getName())
          .description(p.getDescription())
          .purchasePrice(p.getPurchasePrice())
          .salePrice(p.getSalePrice())
          .minStock(p.getMinStock())
          .inventory(inventoryDTO)
          .category(categoryDTO)
          .build();
    }

    return TransactionItemResponseDTO.builder()
        .id(item.getId())
        .product(productDTO)
        .amount(item.getAmount())
        .unitCost(item.getUnitCost())
        .unitPrice(item.getUnitPrice())
        .subtotal(item.getSubtotal())
        .build();
  }

  public List<TransactionItemResponseDTO> toDTOList(List<TransactionItem> items) {
    return items == null ? List.of() : items.stream().map(this::toDTO).toList();
  }
}
