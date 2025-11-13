package com.melevanoronha.dto.request;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO para requisição de renovação de token usando refresh token.
 *
 * @param refreshToken token de renovação enviado pelo cliente
 */
public record RefreshTokenRequest(
        @NotBlank(message = "Refresh token é obrigatório") String refreshToken
) {
}


