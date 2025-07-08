package com.BalanceMaster.gestor_ventas.dtos.movementsDtos;

import java.time.LocalDateTime;

import com.BalanceMaster.gestor_ventas.enums.MovementType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementRequestDTO {
  @NotNull(message = "Amount is required")
  @Positive(message = "Amount must be positive")
  private double amount;

  @NotNull(message = "Movement type is required")
  private MovementType movementType;

  @NotBlank(message = "Description is required")
  private String description;

  private LocalDateTime date;
}
