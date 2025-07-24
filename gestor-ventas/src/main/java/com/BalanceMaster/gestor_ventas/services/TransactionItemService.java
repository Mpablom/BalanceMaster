package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionItemService {

  TransactionItemResponseDTO getTransactionItemById(Long id);

  void delete(Long id);

  Page<TransactionItemResponseDTO> getAllFiltered(Long productId, Long transactionId, Pageable pageable);
}
