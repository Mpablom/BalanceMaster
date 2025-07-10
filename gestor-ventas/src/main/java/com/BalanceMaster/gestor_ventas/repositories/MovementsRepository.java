package com.BalanceMaster.gestor_ventas.repositories;

import com.BalanceMaster.gestor_ventas.entities.Movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MovementsRepository extends JpaRepository<Movement, Long> {
  @Transactional
  @Modifying
  @Query("DELETE FROM Movement m WHERE m.customerAccount.id = :accountId")
  void deleteByCustomerAccountId(@Param("accountId") Long accountId);
}
