package com.BalanceMaster.gestor_ventas.dtos.purchaseDtos;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemRequestDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseRequestDTO {

  @NotNull
  private Long supplierId;

  private String invoiceNumber;

  @NotEmpty
  private List<@Valid TransactionItemRequestDTO> items;
}
