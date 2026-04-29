package com.limpiezait.api.controller;

import com.limpiezait.api.dto.AuthResponse;
import com.limpiezait.api.dto.LoginRequest;
import com.limpiezait.api.security.JwtService;
import com.limpiezait.api.service.CustomUserDetailsService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          CustomUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        // 1. Autenticar con usuario y contraseña
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. Cargar los detalles del usuario
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        // 3. Generar el token JWT
        String token = jwtService.generateToken(userDetails);

        // 4. Devolver el token
        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        userDetailsService.register(request.getUsername(), request.getPassword());
        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}