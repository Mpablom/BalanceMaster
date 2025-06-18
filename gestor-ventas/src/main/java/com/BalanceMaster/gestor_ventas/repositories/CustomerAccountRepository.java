package com.BalanceMaster.gestor_ventas.repositories;

import com.BalanceMaster.gestor_ventas.entities.CustomerAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {

}
