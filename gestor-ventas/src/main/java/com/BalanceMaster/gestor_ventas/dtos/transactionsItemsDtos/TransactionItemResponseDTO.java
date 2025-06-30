package com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionItemResponseDTO {
  private Long id;
  private Long productId;
  private String productName;
  private Integer amount;
  private Double unitPrice;
  private Double subtotal;
}
