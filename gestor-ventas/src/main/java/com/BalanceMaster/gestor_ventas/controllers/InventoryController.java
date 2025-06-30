package com.BalanceMaster.gestor_ventas.controllers;

import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos.InventoryResponseDTO;
import com.BalanceMaster.gestor_ventas.services.InventoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;

  @PostMapping
  public InventoryResponseDTO createInventory(@RequestBody @Valid InventoryRequestDTO request) {
    return inventoryService.createInventory(request);
  }

  @PutMapping("/{id}")
  public InventoryResponseDTO updateInventory(@PathVariable Long id, @RequestBody @Valid InventoryRequestDTO request) {
    return inventoryService.updateInventory(id, request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    inventoryService.deleteInventory(id);
  }

  @GetMapping("/{id}")
  public InventoryResponseDTO getById(@PathVariable Long id) {
    return inventoryService.getInventoryById(id);
  }

  @GetMapping
  public Page<InventoryResponseDTO> getAll(Pageable pageable) {
    return inventoryService.getAllInventories(pageable);
  }
}
