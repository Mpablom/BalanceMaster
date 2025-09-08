package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.producsDtos.ProductRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.producsDtos.ProductResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Product;

import org.springframework.stereotype.Component;

@Component
public class ProductConverter implements Converter<Product, ProductRequestDTO, ProductResponseDTO> {
  @Override
  public ProductResponseDTO toDTO(Product product) {
    if (product == null)
      return null;
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

  public ProductResponseDTO toDTO(Product product, int currentStock) {
    ProductResponseDTO dto = toDTO(product);
    dto.setCurrentStock(currentStock);
    return dto;
  }

  @Override
  public Product toEntity(ProductRequestDTO dto) {
    if (dto == null)
      return null;
    return Product.builder()
        .barcode(dto.getBarcode())
        .name(dto.getName())
        .description(dto.getDescription())
        .purchasePrice(dto.getPurchasePrice())
        .salePrice(dto.getSalePrice())
        .minStock(dto.getMinStock())
        .deleted(false)
        .build();
  }
}
