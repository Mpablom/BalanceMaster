package com.BalanceMaster.gestor_ventas.repositories;

import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.CustomerAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
  Optional<CustomerAccount> findByCustomerId(Long customerId);

  @Modifying
  @Query(nativeQuery = true, value = "DELETE FROM customer_account WHERE customer_id = ?1")
  void hardDeleteByCustomerId(Long customerId);
}
