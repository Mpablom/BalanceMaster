package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier_account")
public class SupplierAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(optional = false)
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  @Column(nullable = false)
  private double balance;

  private LocalDate dueDate;

  @OneToMany(mappedBy = "supplierAccount", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Movement> movements;
}
