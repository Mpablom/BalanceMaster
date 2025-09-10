package com.BalanceMaster.gestor_ventas.dtos.productsDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStockDTO {
  private String name;
  private Integer stock;
}
