package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier_account")
@Builder
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
