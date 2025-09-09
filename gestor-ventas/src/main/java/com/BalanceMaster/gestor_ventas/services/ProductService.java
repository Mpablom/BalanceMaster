package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.productsDtos.ProductResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  ProductResponseDTO createProduct(ProductRequestDTO request);

  ProductResponseDTO updateProduct(Long id, ProductRequestDTO request);

  void deleteProduct(Long id);

  ProductResponseDTO getProductById(Long id);

  Page<ProductResponseDTO> getAllProducts(Pageable pageable);
}
