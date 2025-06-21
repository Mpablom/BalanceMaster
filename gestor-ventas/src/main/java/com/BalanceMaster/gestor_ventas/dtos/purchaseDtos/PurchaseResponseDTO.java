package com.BalanceMaster.gestor_ventas.dtos.purchaseDtos;

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
  private String id;
  private Long supplierId;
  private LocalDateTime date;
  private List<TransactionItemDTO> items;
  private String invoiceNumber;
  private Double total;
}
