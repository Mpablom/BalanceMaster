package com.BalanceMaster.gestor_ventas.services;

import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationRequest;
import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    
    AuthenticationResponse refreshToken(String token);
    
    Boolean validateToken(String token);
    
    String extractUsername(String token);
}