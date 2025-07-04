package com.BalanceMaster.gestor_ventas.dtos.customersDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {

  private Long id;
  private String name;
  private String contactInfo;
  private Double creditLimit;
}
