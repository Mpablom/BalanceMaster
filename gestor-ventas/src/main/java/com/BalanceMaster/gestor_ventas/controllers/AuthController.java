package com.BalanceMaster.gestor_ventas.controllers;

import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationRequest;
import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationResponse;
import com.BalanceMaster.gestor_ventas.services.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthenticationService authenticationService;

  @Autowired
  public AuthController(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @Valid @RequestBody AuthenticationRequest request) {
    try {
      AuthenticationResponse response = authenticationService.authenticate(request);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(new AuthenticationResponse(null, null, null, null));
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<AuthenticationResponse> refreshToken(
      @RequestHeader("Authorization") String authorization) {
    try {
      if (authorization != null && authorization.startsWith("Bearer ")) {
        String token = authorization.substring(7);
        AuthenticationResponse response = authenticationService.refreshToken(token);
        return ResponseEntity.ok(response);
      }
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @GetMapping("/validate")
  public ResponseEntity<Boolean> validateToken(
      @RequestHeader("Authorization") String authorization) {
    try {
      if (authorization != null && authorization.startsWith("Bearer ")) {
        String token = authorization.substring(7);
        Boolean isValid = authenticationService.validateToken(token);
        return ResponseEntity.ok(isValid);
      }
      return ResponseEntity.ok(false);
    } catch (Exception e) {
      return ResponseEntity.ok(false);
    }
  }
}
