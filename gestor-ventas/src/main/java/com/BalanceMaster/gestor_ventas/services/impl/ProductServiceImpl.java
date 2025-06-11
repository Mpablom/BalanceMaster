package com.BalanceMaster.gestor_ventas.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;
import com.BalanceMaster.gestor_ventas.services.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  public List<Product> findAll() {
    return productRepository.findAll();
  }

  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  public Product save(Product product) {
    return productRepository.save(product);
  }

  public void delete(Long id) {
    productRepository.deleteById(id);
  }

}
