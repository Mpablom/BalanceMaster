package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDateTime;

import com.BalanceMaster.gestor_ventas.enums.MovementType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movement")
@Builder
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

  @PrePersist
  public void prePersist() {
    if (this.date == null) {
      this.date = LocalDateTime.now();
    }
  }
}
