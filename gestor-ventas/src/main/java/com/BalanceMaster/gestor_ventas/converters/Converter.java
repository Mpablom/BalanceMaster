package com.BalanceMaster.gestor_ventas.converters;

public interface Converter<E, ReqDTO, ResDTO> {

  ResDTO toDTO(E entity);

  E toEntity(ReqDTO dto);
}
