package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleService {
  SaleResponseDTO createSale(SaleRequestDTO request);

  SaleResponseDTO getSaleById(String id);

  Page<SaleResponseDTO> getAllSales(Pageable pageable);
}
