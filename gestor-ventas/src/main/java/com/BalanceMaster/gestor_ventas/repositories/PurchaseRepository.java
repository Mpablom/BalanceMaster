package com.BalanceMaster.gestor_ventas.repositories;

import java.util.List;
import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.Purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String> {

  @Query("SELECT p FROM Purchase p " +
      "LEFT JOIN FETCH p.items i " +
      "LEFT JOIN FETCH i.product pr " +
      "LEFT JOIN FETCH pr.inventory " +
      "LEFT JOIN FETCH pr.category " +
      "LEFT JOIN FETCH p.supplier " +
      "WHERE p.id = :id")
  Optional<Purchase> findByIdWithItems(@Param("id") Long id);

  @Query("SELECT DISTINCT p FROM Purchase p " +
      "LEFT JOIN FETCH p.items i " +
      "LEFT JOIN FETCH i.product pr " +
      "LEFT JOIN FETCH pr.inventory " +
      "LEFT JOIN FETCH pr.category " +
      "LEFT JOIN FETCH p.supplier")
  List<Purchase> findAllWithItems();
}
