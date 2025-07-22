package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.transactionsItemsDtos.TransactionItemResponseDTO;

public interface TransactionItemService {

  TransactionItemResponseDTO getTransactionItemById(Long id);

  void delete(Long id);
}
