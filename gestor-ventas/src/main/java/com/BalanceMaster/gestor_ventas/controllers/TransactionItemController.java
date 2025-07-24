package com.BalanceMaster.gestor_ventas.controllers;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;
import com.BalanceMaster.gestor_ventas.services.TransactionItemService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transaction-items")
@RequiredArgsConstructor
public class TransactionItemController {

  private final TransactionItemService transactionItemService;

  @GetMapping("/{id}")
  public ResponseEntity<TransactionItemResponseDTO> getById(@PathVariable Long id) {
    TransactionItemResponseDTO dto = transactionItemService.getTransactionItemById(id);
    return ResponseEntity.ok(dto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    transactionItemService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Page<TransactionItemResponseDTO>> getAll(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(required = false) Long productId,
      @RequestParam(required = false) Long transactionId) {
    Pageable pageable = PageRequest.of(page, size);
    Page<TransactionItemResponseDTO> result = transactionItemService.getAllFiltered(productId, transactionId, pageable);
    return ResponseEntity.ok(result);
  }
}
