package com.BalanceMaster.gestor_ventas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "purchase")
public class Purchase extends Transaction {
  @ManyToOne(optional = false)
  @JoinColumn(name = "supplier_id", nullable = false)
  private Supplier supplier;

  private String invoiceNumber;
}
