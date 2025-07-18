package com.BalanceMaster.gestor_ventas.repositories;

import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.Inventory;
import com.BalanceMaster.gestor_ventas.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  Optional<Inventory> findByProductId(Long productId);

  Optional<Inventory> findByProduct(Product product);
}
