package com.BalanceMaster.gestor_ventas.dtos.salesItemsDtos;

import jakarta.validation.constraints.Min;
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
public class SaleItemDTO {
  @NotNull
  private Long productId;

  @NotNull
  @Min(1)
  private Integer quantity;

  @NotNull
  @Positive
  private Double price;
}
