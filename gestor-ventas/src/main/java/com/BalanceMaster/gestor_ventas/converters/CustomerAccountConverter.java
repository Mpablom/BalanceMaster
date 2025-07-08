package com.BalanceMaster.gestor_ventas.converters;

import java.util.stream.Collectors;

import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.CustomerAccount;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerAccountConverter {

  private final MovementConverter movementConverter;

  public CustomerAccountResponseDTO toDTO(CustomerAccount customerAccount) {
    return CustomerAccountResponseDTO.builder()
        .id(customerAccount.getId())
        .customerId(customerAccount.getCustomer().getId())
        .customerName(customerAccount.getCustomer().getName())
        .balance(customerAccount.getBalance())
        .dueDate(customerAccount.getDueDate())
        .movements(customerAccount.getMovements().stream()
            .map(movementConverter::toDTO)
            .collect(Collectors.toList()))
        .build();
  }
}
