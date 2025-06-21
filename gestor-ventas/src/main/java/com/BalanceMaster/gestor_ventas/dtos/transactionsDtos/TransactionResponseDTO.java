package com.BalanceMaster.gestor_ventas.dtos.transactionsDtos;

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
public class TransactionResponseDTO {
  private Long id;
  private String type;
  private LocalDateTime date;
  private Double total;
  private List<TransactionItemDTO> items;
}
