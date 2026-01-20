package com.BalanceMaster.gestor_ventas.services.impl;

import com.BalanceMaster.gestor_ventas.config.JwtTokenUtil;
import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationRequest;
import com.BalanceMaster.gestor_ventas.dtos.auth.AuthenticationResponse;
import com.BalanceMaster.gestor_ventas.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${JWT_EXPIRATION:86400000}")
    private Long jwtExpiration;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.username(),
                    request.password()
                )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtTokenUtil.generateToken(userDetails);

            return new AuthenticationResponse(
                token,
                "Bearer",
                jwtExpiration,
                userDetails.getUsername()
            );

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public AuthenticationResponse refreshToken(String token) {
        try {
            String username = jwtTokenUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(token, userDetails)) {
                String newToken = jwtTokenUtil.generateToken(userDetails);
                return new AuthenticationResponse(
                    newToken,
                    "Bearer",
                    jwtExpiration,
                    userDetails.getUsername()
                );
            }
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid token");
        }
        
        throw new BadCredentialsException("Invalid token");
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            String username = jwtTokenUtil.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtTokenUtil.validateToken(token, userDetails);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String extractUsername(String token) {
        try {
            return jwtTokenUtil.extractUsername(token);
        } catch (Exception e) {
            return null;
        }
    }
}