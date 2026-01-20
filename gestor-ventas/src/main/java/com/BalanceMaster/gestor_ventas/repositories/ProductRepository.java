package com.BalanceMaster.gestor_ventas.repositories;

import com.BalanceMaster.gestor_ventas.entities.Product;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  @Cacheable("products")
  Page<Product> findAllByDeletedFalse(Pageable pageable);

  @Cacheable("productCounts")
  Long countByCategoryId(Long categoryId);
  
  @Cacheable("products")
  Product findByBarcode(String barcode);
  
  @Query("SELECT p FROM Product p WHERE p.deleted = false AND p.category.id = :categoryId")
  @Cacheable("productsByCategory")
  Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
}
