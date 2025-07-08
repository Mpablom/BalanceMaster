package com.BalanceMaster.gestor_ventas.dtos.movementsDtos;

import java.time.LocalDateTime;

import com.BalanceMaster.gestor_ventas.enums.MovementType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementResponseDTO {
  private Long id;
  private LocalDateTime date;
  private String description;
  private double amount;
  private MovementType movementType;
}
