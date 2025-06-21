package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Supplier;

public class SupplierConverter implements Converter<Supplier, SupplierRequestDTO, SupplierResponseDTO> {
  @Override
  public SupplierResponseDTO toDTO(Supplier supplier) {
    if (supplier == null)
      return null;
    return SupplierResponseDTO.builder()
        .id(supplier.getId())
        .name(supplier.getName())
        .contactInfo(supplier.getContactInfo())
        .build();
  }

  @Override
  public Supplier toEntity(SupplierRequestDTO dto) {
    if (dto == null)
      return null;
    return Supplier.builder()
        .name(dto.getName())
        .contactInfo(dto.getContactInfo())
        .deleted(false)
        .build();
  }
}
