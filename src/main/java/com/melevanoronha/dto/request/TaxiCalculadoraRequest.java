package com.melevanoronha.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para requisição de cálculo de corrida de táxi em Fernando de Noronha.
 *
 * @param origem local de partida da corrida
 * @param destino local de destino da corrida
 */
public record TaxiCalculadoraRequest(
        @NotBlank(message = "Origem é obrigatória")
        @Size(max = 150, message = "Origem deve ter no máximo 150 caracteres")
        String origem,

        @NotBlank(message = "Destino é obrigatório")
        @Size(max = 150, message = "Destino deve ter no máximo 150 caracteres")
        String destino
) {
}

