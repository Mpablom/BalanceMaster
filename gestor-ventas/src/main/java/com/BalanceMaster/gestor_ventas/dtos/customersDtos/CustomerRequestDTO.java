package com.BalanceMaster.gestor_ventas.dtos.customersDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {

  private String name;
  private String contact;
  private Double creditLimit;
}
