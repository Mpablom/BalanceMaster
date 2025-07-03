package com.BalanceMaster.gestor_ventas.controllers;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseResponseDTO;
import com.BalanceMaster.gestor_ventas.services.PurchaseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

  private final PurchaseService purchaseService;

  @PostMapping
  public ResponseEntity<PurchaseResponseDTO> createPurchase(@Valid @RequestBody PurchaseRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.createPurchase(request));
  }

  @GetMapping
  public ResponseEntity<List<PurchaseResponseDTO>> getAllPuchases() {
    return ResponseEntity.ok(purchaseService.getAllPurchases());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PurchaseResponseDTO> getById(@PathVariable String id) {
    return ResponseEntity.ok(purchaseService.findPurchaseById(id));
  }
}
