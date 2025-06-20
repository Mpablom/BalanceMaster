package com.BalanceMaster.gestor_ventas.dtos.suppliersDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequestDTO {

  private String name;
  private String contactInfo;
}
