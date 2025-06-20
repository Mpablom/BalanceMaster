package com.BalanceMaster.gestor_ventas.services.impl;

import java.util.List;

import com.BalanceMaster.gestor_ventas.entities.Supplier;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;
import com.BalanceMaster.gestor_ventas.services.SupplierService;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

  private final SupplierRepository supplierRepository;

  public List<Supplier> findAll() {
    return supplierRepository.findAll();
  }

  public Supplier save(Supplier supplier) {
    return supplierRepository.save(supplier);
  }
}
