package com.BalanceMaster.gestor_ventas.repositories;

import com.BalanceMaster.gestor_ventas.entities.TransactionItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {

}
