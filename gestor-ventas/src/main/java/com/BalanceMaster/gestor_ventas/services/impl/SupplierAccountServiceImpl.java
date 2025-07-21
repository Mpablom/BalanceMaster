package com.BalanceMaster.gestor_ventas.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.BalanceMaster.gestor_ventas.converters.SupplierAccountConverter;
import com.BalanceMaster.gestor_ventas.dtos.movementsDtos.MovementRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountRequestDTO;
import com.BalanceMaster.gestor_ventas.dtos.suppliersAccountsDtos.SupplierAccountResponseDTO;
import com.BalanceMaster.gestor_ventas.entities.Movement;
import com.BalanceMaster.gestor_ventas.entities.Supplier;
import com.BalanceMaster.gestor_ventas.entities.SupplierAccount;
import com.BalanceMaster.gestor_ventas.enums.MovementType;
import com.BalanceMaster.gestor_ventas.repositories.SupplierAccountRepository;
import com.BalanceMaster.gestor_ventas.repositories.SupplierRepository;
import com.BalanceMaster.gestor_ventas.services.SupplierAccountService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierAccountServiceImpl implements SupplierAccountService {

  private final SupplierRepository supplierRepository;
  private final SupplierAccountRepository supplierAccountRepository;
  private final SupplierAccountConverter supplierAccountConverter;

  @Override
  public SupplierAccountResponseDTO createSupplierAccount(SupplierAccountRequestDTO request) {
    Supplier supplier = supplierRepository.findById(request.getSupplierId())
        .orElseThrow(() -> new RuntimeException("Supplier not found"));

    if (supplierAccountRepository.findBySupplierId(supplier.getId()).isPresent()) {
      throw new IllegalStateException("Supplier already has an account");
    }

    SupplierAccount account = SupplierAccount.builder()
        .supplier(supplier)
        .balance(request.getBalance())
        .dueDate(LocalDate.now().plusDays(30))
        .movements(new ArrayList<>())
        .build();

    SupplierAccount saved = supplierAccountRepository.save(account);
    return supplierAccountConverter.toDTO(saved);
  }

  @Override
  public SupplierAccountResponseDTO getAccountBySupplierId(Long supplierId) {
    SupplierAccount account = supplierAccountRepository.findBySupplierId(supplierId)
        .orElseThrow(() -> new EntityNotFoundException("Supplier account not found"));

    return supplierAccountConverter.toDTO(account);
  }

  @Override
  public SupplierAccountResponseDTO addMovement(Long supplierId, MovementRequestDTO movementRequestDTO) {
    SupplierAccount account = supplierAccountRepository.findBySupplierId(supplierId)
        .orElseThrow(() -> new EntityNotFoundException("Supplier account not found"));

    Movement movement = Movement.builder()
        .amount(movementRequestDTO.getAmount())
        .description(movementRequestDTO.getDescription())
        .date(movementRequestDTO.getDate() != null ? movementRequestDTO.getDate() : LocalDateTime.now())
        .movementType(movementRequestDTO.getMovementType())
        .supplierAccount(account)
        .build();

    if (movement.getMovementType() == MovementType.CREDIT) {
      account.setBalance(account.getBalance() + movement.getAmount());
    } else if (movement.getMovementType() == MovementType.DEBIT) {
      double newBalance = account.getBalance() - movement.getAmount();
      account.setBalance(Math.max(newBalance, 0));
    }

    account.setDueDate(movement.getDate().toLocalDate().plusDays(30));
    account.getMovements().add(movement);

    supplierAccountRepository.save(account);

    return supplierAccountConverter.toDTO(account);
  }

  @Override
  public SupplierAccountResponseDTO updateSupplierAccount(Long supplierId, SupplierAccountRequestDTO request) {
    SupplierAccount account = supplierAccountRepository.findBySupplierId(supplierId)
        .orElseThrow(() -> new EntityNotFoundException("Supplier account not found"));

    account.setBalance(request.getBalance());
    SupplierAccount saved = supplierAccountRepository.save(account);
    return supplierAccountConverter.toDTO(saved);
  }

  @Override
  @Transactional
  public void deleteSupplierAccount(Long supplierId) {
    SupplierAccount account = supplierAccountRepository.findBySupplierId(supplierId)
        .orElseThrow(() -> new EntityNotFoundException("Supplier account not found"));

    if (account.getBalance() != 0.0) {
      throw new IllegalStateException("Cannot delete account with non-zero balance");
    }

    supplierAccountRepository.delete(account);
  }
}
