package com.BalanceMaster.gestor_ventas.converters;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.salesDtos.SaleResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Customer;
import com.BalanceMaster.gestor_ventas.entities.Sale;
import com.BalanceMaster.gestor_ventas.entities.TransactionItem;
import com.BalanceMaster.gestor_ventas.repositories.CustomerRepository;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaleConverter {
  private final CustomerRepository customerRepository;
  private final TransactionItemConverter transactionItemConverter;

  public Sale toEntity(SaleRequestDTO request) {
    Customer customer = customerRepository.findById(request.getCustomerId())
        .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

    Sale sale = new Sale();
    sale.setCustomer(customer);
    sale.setDate(LocalDateTime.now());
    sale.setPaymentMethod(request.getPaymentMethod());
    sale.setAmountPaid(request.getAmountPaid());
    sale.setId("SALE-" + UUID.randomUUID());

    List<TransactionItem> items = request.getItems().stream()
        .map(itemsdto -> transactionItemConverter.toEntity(itemsdto, sale))
        .toList();

    sale.setItems(items);

    double total = items.stream()
        .mapToDouble(item -> item.getAmount() * item.getUnitPrice())
        .sum();
    sale.setTotal(total);
    sale.setCompleted(request.getAmountPaid() >= total);

    return sale;
  }

  public SaleResponseDTO toDTO(Sale sale) {
    return SaleResponseDTO.builder()
        .id(sale.getId())
        .date(sale.getDate())
        .customerId(sale.getCustomer().getId())
        .items(transactionItemConverter.toDTOList(sale.getItems()))
        .paymentMethod(sale.getPaymentMethod().name())
        .amountPaid(sale.getAmountPaid())
        .completed(sale.isCompleted())
        .total(sale.getTotal())
        .build();
  }
}
