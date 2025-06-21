package com.BalanceMaster.gestor_ventas.dtos.purchaseItemsDtos;

import java.time.LocalDateTime;
import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDTO {
  private Long id;
  private Long supplierId;
  private LocalDateTime timestamp;
  private List<TransactionItemDTO> items;
}
