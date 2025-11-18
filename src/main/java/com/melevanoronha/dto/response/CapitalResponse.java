package com.melevanoronha.dto.response;

/**
 * DTO de resposta para informações de uma capital brasileira.
 *
 * @param nomeCompleto nome completo no formato "Cidade - UF"
 * @param codigoIATA código IATA do aeroporto
 */
public record CapitalResponse(
        String nomeCompleto,
        String codigoIATA
) {
}

