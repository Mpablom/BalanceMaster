package com.BalanceMaster.gestor_ventas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
  @JsonIgnoreProperties({ "purchases", "account" })
  private Supplier supplier;

  @Column(nullable = false)
  @Builder.Default
  private boolean deleted = false;
}
