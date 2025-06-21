package com.BalanceMaster.gestor_ventas.dtos.movementsDtos;

import java.time.LocalDateTime;

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
  private Double amount;
  private String movementType;
  private Long customerAccountId;
  private Long supplierAccountId;
}
