package com.BalanceMaster.gestor_ventas.dtos.productsDtos;

import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

  private Long id;
  private String barcode;
  private String name;
  private String description;
  private Double purchasePrice;
  private Double salePrice;
  private Integer minStock;
  private InventoryResponseDTO inventory;
  private CategoryResponseDTO category;
}
