package com.BalanceMaster.gestor_ventas.dtos.inventoriesDtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponseDTO {

  private Long productId;
  private Integer quantity;
  private LocalDateTime lastUpdate;

}
