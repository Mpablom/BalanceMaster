package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductStockDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.DailySalesDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SaleService {
  SaleResponseDTO createSale(SaleRequestDTO request);

  SaleResponseDTO getSaleById(String id);

  Page<SaleResponseDTO> getAllSales(Pageable pageable);

  List<DailySalesDTO> getDailySales();

  Double getTotalSales();

  List<ProductStockDTO> getLowStockProducts();

}
