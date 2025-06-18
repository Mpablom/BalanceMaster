package com.BalanceMaster.gestor_ventas.repositories;

import com.BalanceMaster.gestor_ventas.entities.SupplierAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierAccountRepository extends JpaRepository<SupplierAccount, Long> {

}
