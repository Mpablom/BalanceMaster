package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {
  SupplierResponseDTO createSupplier(SupplierRequestDTO request);

  SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO request);

  void deleteSupplier(Long id);

  SupplierResponseDTO getSupplierById(Long id);

  Page<SupplierResponseDTO> getAllSuppliers(Pageable pageable);
}
