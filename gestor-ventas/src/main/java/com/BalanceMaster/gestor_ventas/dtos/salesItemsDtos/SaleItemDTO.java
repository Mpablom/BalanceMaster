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
  @NotNull(message = "Product ID is required")
  private Long productId;

  @NotNull(message = "Amount is required")
  @Min(value = 1, message = "Amount must be at least 1")
  private Integer amount;

  @NotNull(message = "Unit price is required")
  @Positive(message = "Unit price must be greater than 0")
  private Double unitPrice;
}
