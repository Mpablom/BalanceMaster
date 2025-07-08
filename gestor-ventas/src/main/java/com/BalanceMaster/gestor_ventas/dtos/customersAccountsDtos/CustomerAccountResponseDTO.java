package com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos;

import java.time.LocalDate;
import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementResponseDTO;

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
  private String customerName;
  private Double balance;
  private LocalDate dueDate;
  private List<MovementResponseDTO> movements;
}
