package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.producsDtos.ProductRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.producsDtos.ProductResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Product;

public class ProductConverter {

  public static ProductResponseDTO toDTO(Product product) {
    return ProductResponseDTO.builder()
        .id(product.getId())
        .barcode(product.getBarcode())
        .name(product.getName())
        .description(product.getDescription())
        .purchasePrice(product.getPurchasePrice())
        .salePrice(product.getSalePrice())
        .minStock(product.getMinStock())
        .build();
  }

  public static Product toEntity(ProductRequestDTO dto) {
    return Product.builder()
        .barcode(dto.getBarcode())
        .name(dto.getName())
        .description(dto.getDescription())
        .purchasePrice(dto.getPurchasePrice())
        .salePrice(dto.getSalePrice())
        .minStock(dto.getMinStock())
        .build();
  }
}
