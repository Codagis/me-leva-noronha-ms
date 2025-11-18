package com.melevanoronha.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para requisição de cálculo de passagens aéreas para Fernando de Noronha.
 *
 * @param origem cidade de origem do viajante (formato: "Cidade - UF", ex: "Fortaleza - CE")
 * @param duracaoDias duração da viagem em dias
 * @param numeroPessoas número de pessoas na viagem
 */
public record CalculadoraPassagensRequest(
        @NotBlank(message = "Origem é obrigatória")
        @Size(max = 100, message = "Origem deve ter no máximo 100 caracteres")
        String origem,

        @NotNull(message = "Duração em dias é obrigatória")
        @Min(value = 1, message = "Duração deve ser no mínimo 1 dia")
        Integer duracaoDias,

        @NotNull(message = "Número de pessoas é obrigatório")
        @Min(value = 1, message = "Número de pessoas deve ser no mínimo 1")
        Integer numeroPessoas
) {
}

