package com.BalanceMaster.gestor_ventas.dtos.suppliersDtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequestDTO {
  @NotBlank(message = "Name must not be blank")
  private String name;

  private String contactInfo;
}
