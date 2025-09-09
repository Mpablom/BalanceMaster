package com.BalanceMaster.gestor_ventas.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, unique = true)
  private String name;
  private String description;

  @Column(nullable = false)
  private Double marginPercentage;
}
