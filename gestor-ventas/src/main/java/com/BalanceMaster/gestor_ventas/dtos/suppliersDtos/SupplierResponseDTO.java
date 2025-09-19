package com.BalanceMaster.gestor_ventas.dtos.suppliersDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponseDTO {

  private Long id;
  private String name;
  private String phone;
  private String email;
}
