package com.BalanceMaster.gestor_ventas.controllers;

import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerResponseDTO;
import com.BalanceMaster.gestor_ventas.services.CustomerService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<CustomerResponseDTO> createCustomer(@Valid @RequestBody CustomerRequestDTO request) {
    return new ResponseEntity<>(
        customerService.createCustomer(request), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id,
      @Valid @RequestBody CustomerRequestDTO request) {
    return ResponseEntity.ok(customerService.updateCustomer(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return ResponseEntity.ok("El cliente fue eliminado correctamente.");
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
    return ResponseEntity.ok(customerService.getCustomerById(id));
  }

  @GetMapping
  public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(Pageable pageable) {
    return ResponseEntity.ok(customerService.getAllCustomers(pageable));
  }
}
