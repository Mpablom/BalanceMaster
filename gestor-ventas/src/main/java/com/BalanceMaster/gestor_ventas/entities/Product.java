package com.BalanceMaster.gestor_ventas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
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

  @Column(name = "minimumStock", nullable = false)
  private int minimumStock;

  public double calculateProfit() {
    return salePrice - purchasePrice;
  }
}
