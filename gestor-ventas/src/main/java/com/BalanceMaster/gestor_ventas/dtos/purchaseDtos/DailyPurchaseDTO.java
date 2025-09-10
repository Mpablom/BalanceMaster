package com.BalanceMaster.gestor_ventas.dtos.purchaseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyPurchaseDTO {
  private String day;
  private double amount;
}
