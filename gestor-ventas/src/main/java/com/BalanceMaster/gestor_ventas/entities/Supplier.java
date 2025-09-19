package com.BalanceMaster.gestor_ventas.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

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

  @Column(name = "contact_info")
  private String phone;

  @Column(name = "email")
  private String email;

  @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
  private SupplierAccount account;

  @OneToMany(mappedBy = "supplier")
  @JsonManagedReference
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private List<Purchase> purchases;

  @Column(nullable = false)
  @Builder.Default
  private Boolean deleted = false;
}
