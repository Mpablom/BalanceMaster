package com.BalanceMaster.gestor_ventas.controllers;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.services.SupplierAccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/supplier-account")
@RequiredArgsConstructor
public class SupplierAccountController {
  private final SupplierAccountService supplierAccountService;

  @PostMapping
  public ResponseEntity<SupplierAccountResponseDTO> createSupplierAccount(
      @RequestBody @Valid SupplierAccountRequestDTO request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(supplierAccountService.createSupplierAccount(request));
  }

  @GetMapping
  public ResponseEntity<List<SupplierAccountResponseDTO>> listAll() {
    List<SupplierAccountResponseDTO> accounts = supplierAccountService.getAll();
    return ResponseEntity.ok(accounts);
  }

  @GetMapping("/{supplierId}")
  public ResponseEntity<SupplierAccountResponseDTO> getSupplierAccount(@PathVariable Long supplierId) {
    return ResponseEntity.ok(supplierAccountService.getAccountBySupplierId(supplierId));
  }

  @PutMapping("/{supplierId}")
  public ResponseEntity<SupplierAccountResponseDTO> updateSupplierAccount(@PathVariable Long supplierId,
      @RequestBody @Valid SupplierAccountRequestDTO request) {
    return ResponseEntity.ok(supplierAccountService.updateSupplierAccount(supplierId, request));
  }

  @PostMapping("/{supplierId}/movements")
  public ResponseEntity<SupplierAccountResponseDTO> addMovement(
      @PathVariable Long supplierId,
      @RequestBody @Valid MovementRequestDTO movementRequestDTO) {
    return ResponseEntity.ok(supplierAccountService.addMovement(supplierId, movementRequestDTO));
  }

  @DeleteMapping("/{supplierId}")
  public ResponseEntity<Void> deleteSupplierAccount(@PathVariable Long supplierId) {
    supplierAccountService.deleteSupplierAccount(supplierId);
    return ResponseEntity.noContent().build();
  }
}
