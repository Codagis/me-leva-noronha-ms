package com.melevanoronha.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para requisição de autenticação/login.
 *
 * @param username nome de usuário
 * @param senha senha do usuário
 */
public record LoginRequest(
        @NotBlank(message = "Username é obrigatório") String username,
        @NotBlank(message = "Senha é obrigatória") String senha
) {
}


