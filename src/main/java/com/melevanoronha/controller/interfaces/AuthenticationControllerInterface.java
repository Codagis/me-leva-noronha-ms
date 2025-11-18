package com.melevanoronha.controller.interfaces;

import com.melevanoronha.dto.request.LoginRequest;
import com.melevanoronha.dto.request.RefreshTokenRequest;
import com.melevanoronha.dto.request.RegisterRequest;
import com.melevanoronha.dto.response.AuthResponse;
import com.melevanoronha.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface do controller responsável por gerenciar endpoints de autenticação.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Tag(name = "Autenticação", description = "Endpoints públicos para autenticação de usuários")
@RequestMapping("/api/auth")
public interface AuthenticationControllerInterface {

    @Operation(
            summary = "Registrar novo usuário",
            description = "Cria uma nova conta de usuário no sistema. Retorna os dados do usuário criado."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = Usuario.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos ou usuário/email já existente",
                    content = @Content
            )
    })
    @PostMapping("/register")
    ResponseEntity<Usuario> register(@Valid @RequestBody RegisterRequest request);

    @Operation(
            summary = "Login de usuário",
            description = "Autentica um usuário e retorna tokens JWT (access token e refresh token)."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidas",
                    content = @Content
            )
    })
    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request);

    @Operation(
            summary = "Renovar token de acesso",
            description = "Gera um novo access token usando um refresh token válido."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Token renovado com sucesso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Refresh token inválido ou expirado",
                    content = @Content
            )
    })
    @PostMapping("/refresh")
    ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request);

    @Operation(
            summary = "Logout de usuário",
            description = "Revoga um refresh token, invalidando-o para futuras renovações."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Logout realizado com sucesso",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Refresh token inválido",
                    content = @Content
            )
    })
    @PostMapping("/logout")
    ResponseEntity<Void> logout(@Valid @RequestBody RefreshTokenRequest request);
}

