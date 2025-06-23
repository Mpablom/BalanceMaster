package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.converters.ProductConverter;
import com.BalanceMaster.gestor_ventas.dtos.producsDtos.ProductRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.producsDtos.ProductResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Product;
import com.BalanceMaster.gestor_ventas.exceptions.ResourceNotFoundException;
import com.BalanceMaster.gestor_ventas.repositories.ProductRepository;
import com.BalanceMaster.gestor_ventas.services.ProductService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ProductConverter productConverter;

  @Override
  @Transactional
  public ProductResponseDTO createProduct(ProductRequestDTO request) {
    Product product = productConverter.toEntity(request);
    product.setDeleted(false);
    return productConverter.toDTO(productRepository.save(product));
  }

  @Override
  @Transactional
  public ProductResponseDTO updateProduct(Long id, ProductRequestDTO request) {
    Product product = productRepository.findById(id)
        .filter(p -> !p.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

    product.setBarcode(request.getBarcode());
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPurchasePrice(request.getPurchasePrice());
    product.setSalePrice(request.getSalePrice());
    product.setMinStock(request.getMinStock());
    return productConverter.toDTO(product);
  }

  @Override
  @Transactional
  public void deleteProduct(Long id) {
    Product product = productRepository.findById(id)
        .filter(p -> !p.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    product.setDeleted(true);
  }

  @Override
  @Transactional(readOnly = true)
  public ProductResponseDTO getProductById(Long id) {
    Product product = productRepository.findById(id)
        .filter(p -> !p.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    return productConverter.toDTO(product);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {
    return productRepository.findAllByDeletedFalse(pageable)
        .map(productConverter::toDTO);
  }

}
