package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InventoryService {
  InventoryResponseDTO createInventory(InventoryRequestDTO request);

  InventoryResponseDTO updateInventory(Long id, InventoryRequestDTO request);

  void deleteInventory(Long id);

  InventoryResponseDTO getInventoryById(Long id);

  Page<InventoryResponseDTO> getAllInventories(Pageable pageable);
}
