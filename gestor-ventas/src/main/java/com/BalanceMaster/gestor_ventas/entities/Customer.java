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
@Table(name = "customer")
@Builder
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(name = "contact_info", nullable = true)
  private String contactInfo;

  @Column(name = "credit_limit", nullable = false)
  private double creditLimit;

  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
  private CustomerAccount account;

  @OneToMany(mappedBy = "customer")
  private List<Sale> sales;

  @Column(nullable = false)
  @Builder.Default
  private Boolean deleted = false;

}
