package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "transaction")
public abstract class Transaction {
  @Id
  @Column(length = 50)
  private String id;

  @Column(nullable = false)
  private LocalDateTime date;

  @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<TransactionItem> items;

  @Column(nullable = false)
  private double total;

  @PrePersist
  public void prePersist() {
    if (this.date == null) {
      this.date = LocalDateTime.now();
    }
  }
}
