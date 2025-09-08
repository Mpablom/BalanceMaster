package com.BalanceMaster.gestor_ventas.converters;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.entities.Transaction;
import com.BalanceMaster.gestor_ventas.entities.TransactionItem;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionItemConverter {

  private final ProductRepository productRepository;

  public TransactionItem toEntity(TransactionItemRequestDTO request, Transaction transaction) {
    Product product = productRepository.findById(request.getProductId())
        .orElseThrow(() -> new EntityNotFoundException("Product not found"));

    return TransactionItem.builder()
        .transaction(transaction)
        .product(product)
        .amount(request.getAmount())
        .unitPrice(request.getUnitPrice())
        .build();
  }

  public TransactionItemResponseDTO toDTO(TransactionItem transactionItem) {
    return TransactionItemResponseDTO.builder()
        .id(transactionItem.getId())
        .productId(transactionItem.getProduct().getId())
        .productName(transactionItem.getProduct().getName())
        .amount(transactionItem.getAmount())
        .unitPrice(transactionItem.getUnitPrice())
        .subtotal(transactionItem.getAmount() * transactionItem.getUnitPrice())
        .build();
  }

  public List<TransactionItemResponseDTO> toDTOList(List<TransactionItem> items) {
    return items.stream()
        .map(this::toDTO)
        .toList();
  }
}
