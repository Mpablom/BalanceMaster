package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Customer;

public class CustomerConverter {

  public static CustomerResponseDTO toDTO(Customer customer) {
    return CustomerResponseDTO.builder()
        .id(customer.getId())
        .name(customer.getName())
        .contactInfo(customer.getContactInfo())
        .creditLimit(customer.getCreditLimit())
        .build();
  }

  public static Customer toEntity(CustomerRequestDTO dto) {
    return Customer.builder()
        .name(dto.getName())
        .contactInfo(dto.getContactInfo())
        .creditLimit(dto.getCreditLimit())
        .build();
  }
}
