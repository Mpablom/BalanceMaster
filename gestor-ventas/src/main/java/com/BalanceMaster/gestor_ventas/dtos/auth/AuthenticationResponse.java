package com.BalanceMaster.gestor_ventas.dtos.auth;

public record AuthenticationResponse(
    String token,
    String type,
    Long expiresIn,
    String username
) {}