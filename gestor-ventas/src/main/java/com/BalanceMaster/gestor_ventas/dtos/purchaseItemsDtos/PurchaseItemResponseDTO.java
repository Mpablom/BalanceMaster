package com.BalanceMaster.gestor_ventas.dtos.purchaseItemsDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseItemResponseDTO {
  private Long productId;
  private String productName;
  private Integer quantity;
  private Double price;
  private Double subtotal;
}
