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
  
  @Pattern(regexp = "^[A-Z0-9-_]{3,50}$", message = "Barcode must be 3-50 characters (letters, numbers, hyphen, underscore)")
  private String barcode;

  @NotBlank(message = "Name is required")
  @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
  private String name;

  @Size(max = 500, message = "Description must not exceed 500 characters")
  private String description;

  @NotNull(message = "Purchase price is required")
  @DecimalMin(value = "0.0", inclusive = true, message = "Purchase price cannot be negative")
  @Digits(integer = 10, fraction = 2, message = "Purchase price must have maximum 10 integer and 2 decimal digits")
  private Double purchasePrice;

  @NotNull(message = "Minimum stock is required")
  @Min(value = 0, message = "Minimum stock cannot be negative")
  @Max(value = 999999, message = "Minimum stock cannot exceed 999,999")
  private Integer minStock;

  @Min(value = 0, message = "Initial stock cannot be negative")
  @Max(value = 999999, message = "Initial stock cannot exceed 999,999")
  private Integer initialStock;
  
  @NotNull(message = "Category is required")
  @Positive(message = "Category ID must be positive")
  private Long categoryId;
}
