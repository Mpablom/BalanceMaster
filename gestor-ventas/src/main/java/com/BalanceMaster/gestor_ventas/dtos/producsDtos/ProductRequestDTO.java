package com.BalanceMaster.gestor_ventas.dtos.producsDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

  private String barcode;
  private String name;
  private String description;
  private Double purchasePrice;
  private Double salePrice;
  private Integer minStock;

}
