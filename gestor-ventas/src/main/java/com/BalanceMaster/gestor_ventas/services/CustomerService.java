package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.entities.Customer;

public interface CustomerService {

  List<Customer> findAll();

  Customer save(Customer customer);
}
