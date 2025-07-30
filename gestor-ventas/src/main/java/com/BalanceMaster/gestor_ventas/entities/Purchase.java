package com.BalanceMaster.gestor_ventas.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "purchase")
@Builder
public class Purchase extends Transaction {
  @ManyToOne(optional = false)
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  private String invoiceNumber;
  @Column(nullable = false)
  @Builder.Default
  private boolean deleted = false;
}
