package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDateTime;

import com.BalanceMaster.gestor_ventas.enums.MovementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movement")
public class Movement {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime date;

  @Column(nullable = false, length = 200)
  private String description;

  @Column(nullable = false)
  private double amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "movement_type", nullable = false)
  private MovementType movementType;

  @ManyToOne
  @JoinColumn(name = "customer_account_id")
  private CustomerAccount customerAccount;

  @ManyToOne
  @JoinColumn(name = "supplier_account_id")
  private SupplierAccount supplierAccount;
}
