package com.BalanceMaster.gestor_ventas.controllers;

import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementRequestDTO;
import com.BalanceMaster.gestor_ventas.services.CustomerAccountService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer-accounts")
@RequiredArgsConstructor
public class CustomerAccountController {

  private final CustomerAccountService customerAccountService;

  @PostMapping
  public ResponseEntity<CustomerAccountResponseDTO> createCustomerAccount(
      @Valid @RequestBody CustomerAccountRequestDTO request) {
    return ResponseEntity.ok(customerAccountService.createAccount(request));
  }

  @PutMapping("/{customerId}")
  public ResponseEntity<CustomerAccountResponseDTO> updateCustomerAccount(
      @PathVariable Long customerId,
      @Valid @RequestBody CustomerAccountRequestDTO request) {
    return ResponseEntity.ok(customerAccountService.updateAccount(customerId, request));
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> deleteCustomerAccount(@PathVariable Long customerId) {
    customerAccountService.deleteAccount(customerId);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerAccountResponseDTO> getCustomerAccount(@PathVariable Long customerId) {
    return ResponseEntity.ok(customerAccountService.getByCustomerId(customerId));
  }

  @PostMapping("/{customerId}/movements")
  public ResponseEntity<CustomerAccountResponseDTO> addMovement(@PathVariable Long customerId,
      @Valid @RequestBody MovementRequestDTO request) {
    return ResponseEntity.ok(customerAccountService.addMovement(customerId, request));
  }
}
