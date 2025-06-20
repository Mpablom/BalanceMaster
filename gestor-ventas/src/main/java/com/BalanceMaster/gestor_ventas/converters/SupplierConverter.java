package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersDtos.SupplierResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Supplier;

public class SupplierConverter {

  public static SupplierResponseDTO toDTO(Supplier supplier) {
    return SupplierResponseDTO.builder()
        .id(supplier.getId())
        .name(supplier.getName())
        .contactInfo(supplier.getContactInfo())
        .build();
  }

  public static Supplier toEntity(SupplierRequestDTO dto) {
    return Supplier.builder()
        .name(dto.getName())
        .contactInfo(dto.getContactInfo())
        .build();
  }
}
