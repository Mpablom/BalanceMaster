package com.BalanceMaster.gestor_ventas.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "barcode", length = 50, unique = true, nullable = false)
  private String barcode;

  @Column(nullable = false)
  private String name;

  private String description;

  @Column(name = "purchasePrice", nullable = false)
  private double purchasePrice;

  @Column(name = "salePrice", nullable = false)
  private double salePrice;

  @Column(name = "minStock", nullable = false)
  private int minStock;

  public double calculateProfit() {
    return salePrice - purchasePrice;
  }
}
