package com.BalanceMaster.gestor_ventas.dtos.salesDtos;

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
public class SaleResponseDTO {
  private String id;
  private Long customerId;
  private LocalDateTime date;
  private List<TransactionItemResponseDTO> items;
  private String paymentMethod;
  private Double amountPaid;
  private boolean completed;
  private Double total;
}
