package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.AuthenticationControllerInterface;
import com.melevanoronha.dto.request.LoginRequest;
import com.melevanoronha.dto.request.RefreshTokenRequest;
import com.melevanoronha.dto.request.RegisterRequest;
import com.melevanoronha.dto.response.AuthResponse;
import com.melevanoronha.model.Usuario;
import com.melevanoronha.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável por gerenciar endpoints de autenticação.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerInterface {

    private final AuthenticationService authenticationService;

    @Override
    public ResponseEntity<Usuario> register(@Valid @RequestBody RegisterRequest request) {
        Usuario usuario = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Override
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthResponse response = authenticationService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequest request) {
        authenticationService.revokeToken(request.refreshToken());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


