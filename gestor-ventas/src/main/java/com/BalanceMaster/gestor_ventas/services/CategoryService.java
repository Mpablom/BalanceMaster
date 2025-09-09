package com.BalanceMaster.gestor_ventas.services;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;

public interface CategoryService {
  List<CategoryResponseDTO> getAllCategories();

  CategoryResponseDTO getCategoryById(Long id);

  CategoryResponseDTO createCategory(CategoryRequestDTO dto);

  CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO dto);

  void deleteCategory(Long id);
}
