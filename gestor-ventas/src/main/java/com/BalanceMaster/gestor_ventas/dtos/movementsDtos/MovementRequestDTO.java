package com.BalanceMaster.gestor_ventas.dtos.movementsDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovementRequestDTO {
  @NotNull
  private Long productId;

  @NotNull
  private Integer quantity;

  @NotBlank
  private String reason;
}
