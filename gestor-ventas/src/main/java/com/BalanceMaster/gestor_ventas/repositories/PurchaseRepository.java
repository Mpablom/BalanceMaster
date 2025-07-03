package com.BalanceMaster.gestor_ventas.repositories;

import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
  Optional<Purchase> findById(String id);
}
