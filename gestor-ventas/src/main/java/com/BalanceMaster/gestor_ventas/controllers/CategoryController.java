package com.BalanceMaster.gestor_ventas.controllers;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.categoryDtos.CategoryResponseDTO;
import com.BalanceMaster.gestor_ventas.services.CategoryService;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @GetMapping
  public List<CategoryResponseDTO> getAll() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/{id}")
  public CategoryResponseDTO getById(@PathVariable Long id) {
    return categoryService.getCategoryById(id);
  }

  @PostMapping
  public CategoryResponseDTO create(@RequestBody CategoryRequestDTO request) {
    return categoryService.createCategory(request);
  }

  @PutMapping("/{id}")
  public CategoryResponseDTO update(@PathVariable Long id, @RequestBody CategoryRequestDTO request) {
    return categoryService.updateCategory(id, request);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    categoryService.deleteCategory(id);
  }
}
