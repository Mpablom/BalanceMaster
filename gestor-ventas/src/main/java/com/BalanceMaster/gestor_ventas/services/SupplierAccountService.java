package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountResponseDTO;

public interface SupplierAccountService {

  SupplierAccountResponseDTO createSupplierAccount(SupplierAccountRequestDTO request);

  SupplierAccountResponseDTO updateSupplierAccount(Long supplierId, SupplierAccountRequestDTO request);

  SupplierAccountResponseDTO getAccountBySupplierId(Long supplierId);

  SupplierAccountResponseDTO addMovement(Long supplierId, MovementRequestDTO request);

  void deleteSupplierAccount(Long supplierId);
}
