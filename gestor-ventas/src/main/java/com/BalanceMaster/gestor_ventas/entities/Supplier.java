package com.BalanceMaster.gestor_ventas.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
@Builder
public class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(name = "contact_info", nullable = true)
  private String contactInfo;

  @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
  private SupplierAccount account;

  @OneToMany(mappedBy = "supplier")
  private List<Purchase> purchases;

  @Column(nullable = false)
  @Builder.Default
  private Boolean deleted = false;
}
