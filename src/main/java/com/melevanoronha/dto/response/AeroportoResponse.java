package com.melevanoronha.dto.response;

/**
 * DTO de resposta para informações completas de um aeroporto brasileiro.
 *
 * @param cidade nome da cidade no formato "Cidade (UF)" ou "Cidade"
 * @param nomeAeroporto nome completo do aeroporto
 * @param codigoIATA código IATA do aeroporto
 */
public record AeroportoResponse(
        String cidade,
        String nomeAeroporto,
        String codigoIATA
) {
}

