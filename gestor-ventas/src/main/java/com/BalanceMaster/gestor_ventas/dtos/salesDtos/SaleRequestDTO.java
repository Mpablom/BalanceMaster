package com.BalanceMaster.gestor_ventas.dtos.salesDtos;

import java.util.List;

import com.BalanceMaster.gestor_ventas.dtos.salesItemsDtos.SaleItemDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequestDTO {
    @NotNull
    private Long customerId;

    @NotEmpty
    private List<@Valid SaleItemDTO> items;
}
