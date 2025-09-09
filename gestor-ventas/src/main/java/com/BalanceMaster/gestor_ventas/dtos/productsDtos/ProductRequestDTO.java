package com.BalanceMaster.gestor_ventas.dtos.productsDtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {
  private String barcode;

  @NotBlank(message = "Name must not be blank")
  private String name;

  @Size(max = 255, message = "Description too long")
  private String description;

  @NotNull(message = "Purchase price is required")
  @PositiveOrZero(message = "Purchase price cannot be negative")
  private Double purchasePrice;

  @NotNull(message = "Minimum stock is required")
  @Min(value = 0, message = "Minimum stock cannot be negative")
  private Integer minStock;

  private Integer initialStock;
  private Long categoryId;
}
