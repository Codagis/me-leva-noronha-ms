package com.melevanoronha.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO utilizado para receber os dados de cadastro de pontos de interesse.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record PontoInteresseRequest(
        @NotBlank
        @Size(max = 150)
        String titulo,

        @NotBlank
        @Size(max = 100)
        String categoria,

        @NotBlank
        @Size(max = 100)
        String tag,

        @NotBlank
        @Size(max = 512)
        String linkGoogleMaps
) {
}
