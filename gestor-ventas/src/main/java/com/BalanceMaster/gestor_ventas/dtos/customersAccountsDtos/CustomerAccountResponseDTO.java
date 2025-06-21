package com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAccountResponseDTO {

  private Long id;
  private Long customerId;
  private Double balance;
  private LocalDate dueDate;
}
