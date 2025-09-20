package com.BalanceMaster.gestor_ventas.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.BalanceMaster.gestor_ventas.converters.CustomerAccountConverter;
import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.customersAccountsDtos.CustomerAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementRequestDTO;
import com.BalanceMaster.gestor_ventas.entities.Customer;
import com.BalanceMaster.gestor_ventas.entities.CustomerAccount;
import com.BalanceMaster.gestor_ventas.entities.Movement;
import com.BalanceMaster.gestor_ventas.enums.MovementType;
import com.BalanceMaster.gestor_ventas.repositories.CustomerAccountRepository;
import com.BalanceMaster.gestor_ventas.repositories.MovementsRepository;
import com.BalanceMaster.gestor_ventas.services.CustomerAccountService;
import com.BalanceMaster.gestor_ventas.services.ValidationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerAccountServiceImpl implements CustomerAccountService {

  private final CustomerAccountRepository customerAccountRepository;
  private final CustomerAccountConverter customerAccountConverter;
  private final MovementsRepository movementsRepository;
  private final ValidationService validationService;

  @Override
  @Transactional
  public CustomerAccountResponseDTO createAccount(CustomerAccountRequestDTO request) {
    Customer customer = validationService.validateActiveCustomer(request.getCustomerId());

    if (customerAccountRepository.findByCustomerId(customer.getId()).isPresent()) {
      throw new IllegalStateException("Customer already has an account");
    }

    CustomerAccount account = CustomerAccount.builder()
        .customer(customer)
        .balance(request.getBalance())
        .creditLimit(request.getCreditLimit())
        .dueDate(LocalDate.now().plusDays(30))
        .movements(new ArrayList<>())
        .build();

    CustomerAccount saved = customerAccountRepository.save(account);
    return customerAccountConverter.toDTO(saved);
  }

  @Override
  public CustomerAccountResponseDTO updateAccount(Long customerId, CustomerAccountRequestDTO request) {
    validationService.validateActiveCustomer(customerId);

    CustomerAccount account = customerAccountRepository.findByCustomerId(customerId)
        .orElseThrow(() -> new EntityNotFoundException("Customer account not found"));

    if (request.getCreditLimit() < account.getBalance()) {
      throw new IllegalArgumentException("New credit limit cannot be less than current balance");
    }

    account.setBalance(request.getBalance());
    account.setCreditLimit(request.getCreditLimit());
    CustomerAccount saved = customerAccountRepository.save(account);
    return customerAccountConverter.toDTO(saved);
  }

  @Override
  @Transactional
  public void deleteAccount(Long customerId) {
    validationService.validateActiveCustomer(customerId);

    CustomerAccount account = customerAccountRepository.findByCustomerId(customerId)
        .orElseThrow(() -> new EntityNotFoundException("Customer account not found"));

    if (Math.abs(account.getBalance()) > 0.001) {
      throw new IllegalStateException("Cannot delete account with non-zero balance");
    }
    movementsRepository.deleteByCustomerAccountId(account.getId());
    customerAccountRepository.hardDeleteByCustomerId(customerId);
  }

  @Override
  @Transactional(readOnly = true)
  public CustomerAccountResponseDTO getByCustomerId(Long customerId) {
    validationService.validateActiveCustomer(customerId);

    CustomerAccount customerAccount = customerAccountRepository.findByCustomerId(customerId)
        .orElseThrow(() -> new EntityNotFoundException("Customer account not found"));
    return customerAccountConverter.toDTO(customerAccount);
  }

  @Override
  @Transactional
  public CustomerAccountResponseDTO addMovement(Long customerId, MovementRequestDTO movementRequestDTO) {
    validationService.validateActiveProduct(customerId);

    CustomerAccount customerAccount = customerAccountRepository.findByCustomerId(customerId)
        .orElseThrow(() -> new EntityNotFoundException("Customer account not found"));

    Movement movement = Movement.builder()
        .amount(movementRequestDTO.getAmount())
        .description(movementRequestDTO.getDescription())
        .date(movementRequestDTO.getDate() != null ? movementRequestDTO.getDate() : LocalDateTime.now())
        .movementType(movementRequestDTO.getMovementType())
        .customerAccount(customerAccount)
        .build();

    if (movementRequestDTO.getMovementType() == MovementType.CREDIT) {
      double newBalance = customerAccount.getBalance() + movementRequestDTO.getAmount();
      if (newBalance > customerAccount.getCreditLimit()) {
        throw new IllegalArgumentException("Debit would exceed credit limit");
      }
      customerAccount.setBalance(newBalance);
    } else if (movementRequestDTO.getMovementType() == MovementType.DEBIT) {
      double newBalance = customerAccount.getBalance() - movementRequestDTO.getAmount();
      if (newBalance < 0) {
        newBalance = 0;
      }
      customerAccount.setBalance(newBalance);
    }

    customerAccount.setDueDate(movement.getDate().toLocalDate().plusDays(30));
    customerAccount.getMovements().add(movement);

    customerAccountRepository.save(customerAccount);
    movementsRepository.save(movement);

    return customerAccountConverter.toDTO(customerAccount);
  }

  @Override
  public List<CustomerAccountResponseDTO> findAll() {
    return customerAccountRepository.findAll()
        .stream()
        .map(customerAccountConverter::toDTO)
        .toList();
  }
}
