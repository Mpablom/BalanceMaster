package com.BalanceMaster.gestor_ventas.controllers;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.salesDtos.DailySalesDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleResponseDTO;
import com.BalanceMaster.gestor_ventas.services.SaleService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {
  private final SaleService saleService;

  @PostMapping
  public ResponseEntity<SaleResponseDTO> createSale(@Valid @RequestBody SaleRequestDTO request) {
    return new ResponseEntity<>(saleService.createSale(request), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SaleResponseDTO> getSaleById(@PathVariable String id) {
    return ResponseEntity.ok(saleService.getSaleById(id));
  }

  @GetMapping
  public ResponseEntity<Page<SaleResponseDTO>> getAllSales(Pageable pageable) {
    return ResponseEntity.ok(saleService.getAllSales(pageable));
  }

  @GetMapping("/daily")
  public ResponseEntity<List<DailySalesDTO>> getDailySales() {
    List<DailySalesDTO> data = saleService.getDailySales();
    return ResponseEntity.ok(data);
  }

  @GetMapping("/total")
  public ResponseEntity<Double> getTotalSales() {
    double total = saleService.getTotalSales();
    return ResponseEntity.ok(total);
  }
}
