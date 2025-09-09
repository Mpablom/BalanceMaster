package com.BalanceMaster.gestor_ventas.services.impl;

import java.util.List;

import com.BalanceMaster.gestor_ventas.converters.CategoryConverter;
import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Category;
import com.BalanceMaster.gestor_ventas.repositories.CategoryRepository;
import com.BalanceMaster.gestor_ventas.services.CategoryService;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
  private final CategoryRepository categoryRepository;
  private final CategoryConverter categoryConverter;

  @Override
  @Transactional
  public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
    Category category = categoryConverter.toEntity(request);
    category = categoryRepository.save(category);
    return categoryConverter.toDTO(category);
  }

  @Override
  public CategoryResponseDTO getCategoryById(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    return categoryConverter.toDTO(category);
  }

  @Override
  public List<CategoryResponseDTO> getAllCategories() {
    return categoryRepository.findAll()
        .stream()
        .map(categoryConverter::toDTO)
        .toList();
  }

  @Override
  @Transactional
  public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));

    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setMarginPercentage(
        request.getMarginPercentage() != null ? request.getMarginPercentage() : category.getMarginPercentage());

    return categoryConverter.toDTO(category);
  }

  @Override
  @Transactional
  public void deleteCategory(Long id) {
    Category category = categoryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Category not found with id " + id));
    categoryRepository.delete(category);
  }
}
