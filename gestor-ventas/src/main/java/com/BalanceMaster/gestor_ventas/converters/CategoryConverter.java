package com.BalanceMaster.gestor_ventas.converters;

import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Category;

import org.springframework.stereotype.Component;

@Component
public class CategoryConverter implements Converter<Category, CategoryRequestDTO, CategoryResponseDTO> {

  @Override
  public CategoryResponseDTO toDTO(Category category) {
    if (category == null)
      return null;
    return CategoryResponseDTO.builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .marginPercentage(category.getMarginPercentage())
        .build();
  }

  @Override
  public Category toEntity(CategoryRequestDTO dto) {
    if (dto == null)
      return null;
    return Category.builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .marginPercentage(dto.getMarginPercentage() != null ? dto.getMarginPercentage() : 0.0)
        .build();
  }
}
