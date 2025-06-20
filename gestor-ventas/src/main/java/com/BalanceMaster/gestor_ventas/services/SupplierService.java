package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.entities.Supplier;

public interface SupplierService {

  List<Supplier> findAll();

  Supplier save(Supplier supplier);
}
