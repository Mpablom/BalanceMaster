package com.BalanceMaster.gestor_ventas.converters;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.SupplierAccount;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SupplierAccountConverter {

  private final MovementConverter movementConverter;

  public SupplierAccountResponseDTO toDTO(SupplierAccount account) {
    return SupplierAccountResponseDTO.builder()
        .id(account.getId())
        .supplierId(account.getSupplier().getId())
        .supplierName(account.getSupplier().getName())
        .balance(account.getBalance())
        .dueDate(account.getDueDate())
        .movements(account.getMovements() != null ? account.getMovements().stream()
            .map(movementConverter::toDTO)
            .collect(Collectors.toList())
            : new ArrayList<>())
        .build();
  }
}
