package com.BalanceMaster.gestor_ventas.dtos.purchaseDtos;

import java.time.LocalDateTime;
import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseResponseDTO {
  private String id;
  private LocalDateTime date;
  private double total;
  private Long supplierId;
  private String supplierName;
  private List<TransactionItemResponseDTO> items;
}
