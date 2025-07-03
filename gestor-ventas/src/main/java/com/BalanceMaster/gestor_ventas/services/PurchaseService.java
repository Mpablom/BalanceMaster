package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.purchaseDtos.PurchaseResponseDTO;

public interface PurchaseService {
  PurchaseResponseDTO createPurchase(PurchaseRequestDTO request);

  List<PurchaseResponseDTO> getAllPurchases();

  PurchaseResponseDTO findPurchaseById(String id);
}
