package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Inventory;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryConverter implements Converter<Inventory, InventoryRequestDTO, InventoryResponseDTO> {

  private final ProductRepository productRepository;

  @Override
  public Inventory toEntity(InventoryRequestDTO dto) {
    Product product = productRepository.findById(dto.getProductId())
        .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + dto.getProductId()));

    return Inventory.builder()
        .product(product)
        .quantity(dto.getQuantity())
        .lastUpdated(java.time.LocalDateTime.now())
        .location(dto.getLocation())
        .build();
  }

  @Override
  public InventoryResponseDTO toDTO(Inventory inventory) {
    return InventoryResponseDTO.builder()
        .productId(inventory.getProduct().getId())
        .productName(inventory.getProduct().getName())
        .quantity(inventory.getQuantity())
        .location(inventory.getLocation())
        .lastUpdated(inventory.getLastUpdated())
        .build();
  }

}
