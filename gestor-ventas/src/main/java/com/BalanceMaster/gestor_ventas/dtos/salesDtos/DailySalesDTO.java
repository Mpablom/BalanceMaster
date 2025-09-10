package com.BalanceMaster.gestor_ventas.dtos.salesDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailySalesDTO {
  private String day;
  private Double sales;
}
