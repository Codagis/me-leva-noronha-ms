package com.melevanoronha.dto.response;

/**
 * DTO utilizado para devolver os dados de pontos de interesse para os consumidores da API.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record PontoInteresseResponse(
        Long id,
        String titulo,
        String categoria,
        String tag,
        String linkGoogleMaps
) {
}
