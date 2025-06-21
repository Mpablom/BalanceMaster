package com.BalanceMaster.gestor_ventas.dtos.customersDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequestDTO {
  @NotBlank(message = "Name must not be blank")
  private String name;

  private String contactInfo;

  @NotNull(message = "Credit limit is required")
  @PositiveOrZero(message = "Credit limit cannot be negative")
  private Double creditLimit;
}
