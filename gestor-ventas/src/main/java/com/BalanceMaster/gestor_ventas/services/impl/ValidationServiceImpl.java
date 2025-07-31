package com.BalanceMaster.gestor_ventas.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.BalanceMaster.gestor_ventas.entities.Customer;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.entities.Supplier;
import com.BalanceMaster.gestor_ventas.exceptions.ResourceNotFoundException;
import com.BalanceMaster.gestor_ventas.repositories.CustomerRepository;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;
import com.BalanceMaster.gestor_ventas.services.ValidationService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
  private final SupplierRepository supplierRepository;
  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;

  @Override
  public Supplier validateActiveSupplier(Long id) {
    return supplierRepository.findById(id)
        .filter(s -> !s.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Supplier not found or deleted"));
  }

  @Override
  public Customer validateActiveCustomer(Long id) {
    return customerRepository.findById(id)
        .filter(c -> !c.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found or deleted"));
  }

  @Override
  public Product validateActiveProduct(Long id) {
    return productRepository.findById(id)
        .filter(p -> !p.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found or deleted"));
  }

  @Override
  public List<Product> validateActiveProducts(List<Long> ids) {
    List<Product> products = productRepository.findAll();

    List<Long> notFoundOrDeleted = ids.stream()
        .filter(id -> products.stream().noneMatch(p -> p.getId().equals(id) && !p.getDeleted()))
        .collect(Collectors.toList());

    if (!notFoundOrDeleted.isEmpty()) {
      throw new ResourceNotFoundException("Some products are deleted or not found: " + notFoundOrDeleted);
    }

    return products;
  }
}
