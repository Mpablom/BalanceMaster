package com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos;

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
public class TransactionItemRequestDTO {
  @NotNull(message = "Product ID is required")
  private Long productId;

  @NotNull(message = "Amount is required")
  @Min(value = 1, message = "Amount must be at least 1")
  private Integer amount;

  @NotNull(message = "Unit price is required")
  @Positive(message = "Unit price must be positive")
  private Double unitPrice;
}
