package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.entities.Customer;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.entities.Supplier;

public interface ValidationService {
  Supplier validateActiveSupplier(Long id);

  Customer validateActiveCustomer(Long id);

  Product validateActiveProduct(Long id);

  List<Product> validateActiveProducts(List<Long> productIds);
}
