package com.BalanceMaster.gestor_ventas.repositories;

import java.util.Optional;

import com.BalanceMaster.gestor_ventas.entities.Customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Page<Customer> findAllByDeletedFalse(Pageable pageable);

  Optional<Customer> findByIdAndDeletedFalse(Long id);
}
