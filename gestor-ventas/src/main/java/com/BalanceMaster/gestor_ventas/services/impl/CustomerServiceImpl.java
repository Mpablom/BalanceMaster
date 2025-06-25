package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.converters.CustomerConverter;
import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersDtos.CustomerResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Customer;
import com.BalanceMaster.gestor_ventas.exceptions.ResourceNotFoundException;
import com.BalanceMaster.gestor_ventas.repositories.CustomerRepository;
import com.BalanceMaster.gestor_ventas.services.CustomerService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerConverter customerConverter;

  @Override
  @Transactional
  public CustomerResponseDTO createCustomer(CustomerRequestDTO request) {
    Customer customer = customerConverter.toEntity(request);
    customer.setDeleted(false);
    return customerConverter.toDTO(customerRepository.save(customer));
  }

  @Override
  @Transactional
  public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {
    Customer customer = customerRepository.findById(id)
        .filter(c -> !c.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

    customer.setName(dto.getName());
    customer.setContactInfo(dto.getContactInfo());
    customer.setCreditLimit(dto.getCreditLimit());
    return customerConverter.toDTO(customer);
  }

  @Override
  @Transactional
  public void deleteCustomer(Long id) {
    Customer customer = customerRepository.findById(id)
        .filter(c -> !c.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    customer.setDeleted(true);
  }

  @Override
  @Transactional(readOnly = true)
  public CustomerResponseDTO getCustomerById(Long id) {
    Customer customer = customerRepository.findById(id)
        .filter(c -> !c.getDeleted())
        .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    return customerConverter.toDTO(customer);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable) {
    return customerRepository.findAllByDeletedFalse(pageable)
        .map(customerConverter::toDTO);
  }

}
