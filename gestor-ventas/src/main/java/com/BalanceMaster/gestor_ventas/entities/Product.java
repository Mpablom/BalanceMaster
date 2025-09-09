package com.BalanceMaster.gestor_ventas.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "barcode", unique = true)
  private String barcode;

  @Column(nullable = false)
  private String name;

  @Column(length = 255)
  private String description;

  @Column(name = "purchase_price", nullable = false)
  private double purchasePrice;

  @Column(nullable = false)
  private Integer minStock;

  @Column(nullable = false)
  @Builder.Default
  private Boolean deleted = false;

  @OneToOne(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonManagedReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Inventory inventory;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Category category;

  @Transient
  public double getSalePrice() {
    if (category == null)
      return purchasePrice;
    return purchasePrice * (1 + category.getMarginPercentage());
  }

  @Transient
  public double calculateProfit() {
    return getSalePrice() - purchasePrice;
  }
}
