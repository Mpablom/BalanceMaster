package com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionItemDTO {
  private Long productId;
  private Integer amount;
  private Double unitPrice;
}
