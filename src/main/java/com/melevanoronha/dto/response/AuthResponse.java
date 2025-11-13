package com.melevanoronha.dto.response;

/**
 * DTO para resposta de autenticação contendo tokens JWT.
 *
 * @param accessToken token JWT de acesso
 * @param refreshToken token JWT de renovação
 * @param tokenType tipo do token, padrão Bearer
 * @param expiresIn tempo de expiração em segundos
 */
public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long expiresIn
) {

    public AuthResponse {
        tokenType = (tokenType == null || tokenType.isBlank()) ? "Bearer" : tokenType;
    }
}


