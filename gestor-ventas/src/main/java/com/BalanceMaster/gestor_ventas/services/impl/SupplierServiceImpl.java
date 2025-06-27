package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.converters.SupplierConverter;
import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Supplier;
import com.BalanceMaster.gestor_ventas.exceptions.ResourceNotFoundException;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;
import com.BalanceMaster.gestor_ventas.services.SupplierService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
  private final SupplierRepository supplierRepository;
  private final SupplierConverter supplierConverter;

  @Override
  @Transactional
  public SupplierResponseDTO createSupplier(SupplierRequestDTO dto) {
    Supplier supplier = supplierConverter.toEntity(dto);
    supplier.setDeleted(false);
    return supplierConverter.toDTO(supplierRepository.save(supplier));
  }

  @Override
  @Transactional
  public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO dto) {
    Supplier supplier = supplierRepository.findById(id)
        .filter(s -> !s.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    supplier.setName(dto.getName());
    supplier.setContactInfo(dto.getContactInfo());
    return supplierConverter.toDTO(supplier);
  }

  @Override
  @Transactional
  public void deleteSupplier(Long id) {
    Supplier supplier = supplierRepository.findById(id)
        .filter(s -> !s.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    supplier.setDeleted(true);
  }

  @Override
  @Transactional(readOnly = true)
  public SupplierResponseDTO getSupplierById(Long id) {
    Supplier supplier = supplierRepository.findById(id)
        .filter(s -> !s.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Supplier not found "));
    return supplierConverter.toDTO(supplier);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<SupplierResponseDTO> getAllSuppliers(Pageable pageable) {
    return supplierRepository.findAllByDeletedFalse(pageable)
        .map(supplierConverter::toDTO);
  }

}
