package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationRequest;
import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationResponse;

public interface AuthenticationService {
  AuthenticationResponse authenticate(AuthenticationRequest request);

  AuthenticationResponse refreshToken(String token);

  Boolean validateToken(String token);

  String extractUsername(String token);
}
