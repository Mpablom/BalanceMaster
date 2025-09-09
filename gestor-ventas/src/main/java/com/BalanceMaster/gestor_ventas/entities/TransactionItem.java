package com.BalanceMaster.gestor_ventas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "item_transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "transaction_id", nullable = false)
  @JsonBackReference("transaction-items")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Transaction transaction;

  @ManyToOne(optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private int amount;

  @Column(name = "unit_cost", nullable = false)
  private Double unitCost;

  @Column(name = "unitPrice", nullable = false)
  private Double unitPrice;

  @Transient
  public double getSubtotal() {
    return unitPrice * amount;
  }
}
