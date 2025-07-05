package com.BalanceMaster.gestor_ventas.dtos.salesDtos;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemRequestDTO;
import com.BalanceMaster.gestor_ventas.enums.PaymentMethod;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {
  @NotNull(message = "Customer ID is required")
  private Long customerId;

  @NotEmpty(message = "Items must not be empty")
  private List<@Valid TransactionItemRequestDTO> items;

  @NotNull(message = "Payment method is required")
  private PaymentMethod paymentMethod;

  @NotNull(message = "Amount paid is required")
  @PositiveOrZero(message = "Amount paid must be 0 or more")
  private Double amountPaid;
}
