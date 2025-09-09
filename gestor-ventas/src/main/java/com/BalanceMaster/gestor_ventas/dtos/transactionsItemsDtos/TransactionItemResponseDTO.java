package com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos;

import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductResponseDTO;

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
  private ProductResponseDTO product;
  private Integer amount;
  private Double unitCost;
  private Double unitPrice;
  private Double subtotal;
}
