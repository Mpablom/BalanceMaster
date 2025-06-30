package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory")
@Builder
public class Inventory {

  @Id
  private Long id;
  @OneToOne
  @MapsId
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false)
  private int quantity;

  @Column(nullable = true)
  private String location;

  @Column(name = "last_updated", nullable = false)
  private LocalDateTime lastUpdated;

}
