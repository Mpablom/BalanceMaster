package com.BalanceMaster.gestor_ventas.services;

import java.util.List;
import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.Product;

public interface ProductService {
  List<Product> findAll();

  Optional<Product> findById(Long id);

  Product save(Product product);

  void delete(Long id);
}
