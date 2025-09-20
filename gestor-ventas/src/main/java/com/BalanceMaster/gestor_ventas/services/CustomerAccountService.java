package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementRequestDTO;

public interface CustomerAccountService {

  CustomerAccountResponseDTO getByCustomerId(Long customerId);

  CustomerAccountResponseDTO addMovement(Long customerId, MovementRequestDTO movementRequestDTO);

  CustomerAccountResponseDTO createAccount(CustomerAccountRequestDTO request);

  CustomerAccountResponseDTO updateAccount(Long customerId, CustomerAccountRequestDTO request);

  void deleteAccount(Long customerId);

  List<CustomerAccountResponseDTO> findAll();
}
