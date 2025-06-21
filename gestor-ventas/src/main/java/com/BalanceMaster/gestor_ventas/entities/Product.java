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

  @Column(name = "barcode", unique = true, nullable = true)
  private String barcode;

  @Column(nullable = false)
  private String name;

  @Column(length = 255)
  private String description;

  @Column(name = "purchase_price", nullable = false)
  private double purchasePrice;

  @Column(name = "sale_price", nullable = false)
  private double salePrice;

  @Column(name = "min_stock", nullable = false)
  private int minStock;

  @Column(nullable = false)
  @Builder.Default
  private Boolean deleted = false;

  public double calculateProfit() {
    return salePrice - purchasePrice;
  }
}
