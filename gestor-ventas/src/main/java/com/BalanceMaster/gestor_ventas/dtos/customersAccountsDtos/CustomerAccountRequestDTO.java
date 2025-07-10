package com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAccountRequestDTO {
  private Long customerId;

  @NotNull(message = "Initial balance is required")
  @PositiveOrZero(message = "Balance must be 0 or more")
  private Double balance;

  @NotNull(message = "Credit limit is required")
  @Positive(message = "Credit limit must be greater than 0")
  private Double creditLimit;
}
