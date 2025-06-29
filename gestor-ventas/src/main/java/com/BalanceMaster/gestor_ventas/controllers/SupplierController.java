package com.BalanceMaster.gestor_ventas.controllers;

import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierResponseDTO;
import com.BalanceMaster.gestor_ventas.services.SupplierService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierController {

  private final SupplierService supplierService;

  @PostMapping
  public ResponseEntity<SupplierResponseDTO> createSupplier(@Valid @RequestBody SupplierRequestDTO request) {
    return new ResponseEntity<>(
        supplierService.createSupplier(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id,
      @Valid @RequestBody SupplierRequestDTO request) {
    return ResponseEntity.ok(supplierService.updateSupplier(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
    supplierService.deleteSupplier(id);
    return ResponseEntity.ok("El proveedor fue eliminado correctamente.");
  }

  @GetMapping("/{id}")
  public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id) {
    return ResponseEntity.ok(supplierService.getSupplierById(id));
  }

  @GetMapping
  public ResponseEntity<Page<SupplierResponseDTO>> getAllSuppliers(Pageable pageable) {
    return ResponseEntity.ok(supplierService.getAllSuppliers(pageable));
  }
}
