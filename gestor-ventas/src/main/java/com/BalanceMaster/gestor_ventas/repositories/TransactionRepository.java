package com.BalanceMaster.gestor_ventas.repositories;

import com.BalanceMaster.gestor_ventas.entities.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
