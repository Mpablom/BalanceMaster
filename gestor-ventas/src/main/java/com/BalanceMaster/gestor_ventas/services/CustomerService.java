package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
  CustomerResponseDTO createCustomer(CustomerRequestDTO request);

  CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto);

  void deleteCustomer(Long id);

  CustomerResponseDTO getCustomerById(Long id);

  Page<CustomerResponseDTO> getAllCustomers(Pageable pageable);
}
