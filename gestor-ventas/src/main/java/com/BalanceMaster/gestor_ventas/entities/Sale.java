package com.BalanceMaster.gestor_ventas.entities;

import com.BalanceMaster.gestor_ventas.enums.PaymentMethod;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sale")
public class Sale extends Transaction {
  @ManyToOne(fetch = FetchType.LAZY, optional = true)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method", nullable = false)
  private PaymentMethod paymentMethod;

  @Column(name = "amountPaid", nullable = false)
  private double amountPaid;

  @Column(nullable = false)
  private boolean completed;
}
