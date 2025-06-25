package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerConverter implements Converter<Customer, CustomerRequestDTO, CustomerResponseDTO> {
  @Override
  public CustomerResponseDTO toDTO(Customer customer) {
    if (customer == null)
      return null;
    return CustomerResponseDTO.builder()
        .id(customer.getId())
        .name(customer.getName())
        .contactInfo(customer.getContactInfo())
        .creditLimit(customer.getCreditLimit())
        .build();
  }

  @Override
  public Customer toEntity(CustomerRequestDTO dto) {
    if (dto == null)
      return null;
    return Customer.builder()
        .name(dto.getName())
        .contactInfo(dto.getContactInfo())
        .creditLimit(dto.getCreditLimit())
        .deleted(false)
        .build();
  }
}
