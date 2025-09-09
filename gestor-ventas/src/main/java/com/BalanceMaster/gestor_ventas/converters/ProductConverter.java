package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Category;
import com.BalanceMaster.gestor_ventas.entities.Inventory;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.repositories.CategoryRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductConverter implements Converter<Product, ProductRequestDTO, ProductResponseDTO> {

  private final CategoryRepository categoryRepository;

  @Override
  public ProductResponseDTO toDTO(Product product) {
    if (product == null)
      return null;
    Inventory inventory = product.getInventory();
    InventoryResponseDTO inventoryDTO = null;
    if (inventory != null) {
      inventoryDTO = InventoryResponseDTO.builder()
          .quantity(inventory.getQuantity())
          .productName(product.getName())
          .lastUpdated(inventory.getLastUpdated())
          .build();
    }

    Category category = product.getCategory();
    CategoryResponseDTO categoryDTO = null;
    if (category != null) {
      categoryDTO = CategoryResponseDTO.builder()
          .id(category.getId())
          .name(category.getName())
          .description(category.getDescription())
          .marginPercentage(category.getMarginPercentage())
          .build();
    }

    return ProductResponseDTO.builder()
        .id(product.getId())
        .barcode(product.getBarcode())
        .name(product.getName())
        .description(product.getDescription())
        .purchasePrice(product.getPurchasePrice())
        .salePrice(product.getSalePrice())
        .minStock(product.getMinStock())
        .inventory(inventoryDTO)
        .category(categoryDTO)
        .build();
  }

  @Override
  public Product toEntity(ProductRequestDTO dto) {
    if (dto == null)
      return null;

    Category category = null;
    if (dto.getCategoryId() != null) {
      category = categoryRepository.findById(dto.getCategoryId())
          .orElseThrow(() -> new EntityNotFoundException(
              "Category not found with id " + dto.getCategoryId()));
    }

    return Product.builder()
        .barcode(dto.getBarcode())
        .name(dto.getName())
        .description(dto.getDescription())
        .purchasePrice(dto.getPurchasePrice() != null ? dto.getPurchasePrice() : 0.0)
        .minStock(dto.getMinStock() != null ? dto.getMinStock() : 0)
        .category(category)
        .deleted(false)
        .build();
  }
}
