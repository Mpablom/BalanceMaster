package com.BalanceMaster.gestor_ventas.dtos.categoryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDTO {

  private String name;
  private String description;
  private Double marginPercentage;
}
