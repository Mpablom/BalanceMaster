package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.converters.TransactionItemConverter;
import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.TransactionItem;
import com.BalanceMaster.gestor_ventas.repositories.TransactionItemRepository;
import com.BalanceMaster.gestor_ventas.services.TransactionItemService;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionItemServiceImpl implements TransactionItemService {

  private final TransactionItemRepository transactionItemRepository;
  private final TransactionItemConverter transactionItemConverter;

  @Override
  public TransactionItemResponseDTO getTransactionItemById(Long id) {
    TransactionItem transactionItem = transactionItemRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Transaction item not found"));

    return transactionItemConverter.toDTO(transactionItem);
  }

  @Override
  public void delete(Long id) {
    if (!transactionItemRepository.existsById(id)) {
      throw new EntityNotFoundException("Transaction item not found");
    }
    transactionItemRepository.deleteById(id);
  }
}
