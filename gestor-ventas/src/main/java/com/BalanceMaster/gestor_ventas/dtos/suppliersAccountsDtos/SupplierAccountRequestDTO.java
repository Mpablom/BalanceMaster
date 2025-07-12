package com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos;

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
public class SupplierAccountRequestDTO {
  @NotNull(message = "Supplier ID is required")
  private Long supplierId;

  @NotNull(message = "Initial balance is required")
  @PositiveOrZero(message = "Balance must be 0 or more")
  private Double balance;
}
