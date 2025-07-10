package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Movement;

import org.springframework.stereotype.Component;

@Component
public class MovementConverter {

  public MovementResponseDTO toDTO(Movement movement) {
    return MovementResponseDTO.builder()
        .id(movement.getId())
        .date(movement.getDate())
        .description(movement.getDescription())
        .amount(movement.getAmount())
        .movementType(movement.getMovementType())
        .build();
  }
}
