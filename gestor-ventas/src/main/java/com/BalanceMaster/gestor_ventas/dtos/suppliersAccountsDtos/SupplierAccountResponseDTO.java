package com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierAccountResponseDTO {

  private Long id;
  private Long supplierId;
  private Double balance;
  private LocalDate dueDate;
}
