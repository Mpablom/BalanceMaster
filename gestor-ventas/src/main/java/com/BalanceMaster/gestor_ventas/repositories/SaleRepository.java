package com.BalanceMaster.gestor_ventas.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.Sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, String> {

  @Query("SELECT s FROM Sale s " +
      "LEFT JOIN FETCH s.items i " +
      "LEFT JOIN FETCH i.product p " +
      "LEFT JOIN FETCH p.inventory " +
      "LEFT JOIN FETCH p.category " +
      "LEFT JOIN FETCH s.customer c " +
      "WHERE s.id = :id")
  Optional<Sale> findByIdWithItems(@Param("id") String id);

  @Query("SELECT DISTINCT s FROM Sale s " +
      "LEFT JOIN FETCH s.items i " +
      "LEFT JOIN FETCH i.product p " +
      "LEFT JOIN FETCH p.inventory " +
      "LEFT JOIN FETCH p.category " +
      "LEFT JOIN FETCH s.customer c")
  List<Sale> findAllWithItems();

  List<Sale> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
