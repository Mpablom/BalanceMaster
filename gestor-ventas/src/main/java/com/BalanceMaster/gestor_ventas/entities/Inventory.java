package com.BalanceMaster.gestor_ventas.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "inventory")
public class Inventory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @MapsId
  @JoinColumn(name = "product_id", nullable = false)
  @JsonBackReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Product product;

  @Column(nullable = false)
  private int quantity;

  @Column
  private String location;

  @Column(name = "last_updated", nullable = false)
  private LocalDateTime lastUpdated;

  @PrePersist
  public void prePersist() {
    this.lastUpdated = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    this.lastUpdated = LocalDateTime.now();
  }
}
