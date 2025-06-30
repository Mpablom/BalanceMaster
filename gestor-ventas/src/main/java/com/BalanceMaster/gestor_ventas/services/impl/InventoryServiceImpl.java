package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.converters.InventoryConverter;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Inventory;
import com.BalanceMaster.gestor_ventas.repositories.InventoryRepository;
import com.BalanceMaster.gestor_ventas.services.InventoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
  private final InventoryRepository inventoryRepository;
  private final InventoryConverter inventoryConverter;

  @Override
  @Transactional
  public InventoryResponseDTO createInventory(InventoryRequestDTO request) {
    Inventory inventory = inventoryConverter.toEntity(request);
    return inventoryConverter.toDTO(inventoryRepository.save(inventory));
  }

  @Override
  @Transactional
  public InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO request) {
    Inventory inventory = inventoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    inventory.setQuantity(request.getQuantity());
    inventory.setLocation(request.getLocation());
    inventory.setLastUpdated(java.time.LocalDateTime.now());
    return inventoryConverter.toDTO(inventoryRepository.save(inventory));
  }

  @Override
  @Transactional
  public void deleteInventory(Long id) {
    Inventory inventory = inventoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    inventoryRepository.delete(inventory);
  }

  @Override
  @Transactional(readOnly = true)
  public InventoryResponseDTO getInventoryById(Long id) {
    Inventory inventory = inventoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    return inventoryConverter.toDTO(inventory);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<InventoryResponseDTO> getAllInventories(Pageable pageable) {
    return inventoryRepository.findAll(pageable).map(inventoryConverter::toDTO);
  }
}
