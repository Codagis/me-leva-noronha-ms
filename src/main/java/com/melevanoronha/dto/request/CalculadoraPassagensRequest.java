package com.melevanoronha.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO para requisição de cálculo de passagens aéreas para Fernando de Noronha.
 *
 * @param origem código IATA do aeroporto de origem (ex: "FOR", "GRU", "REC")
 * @param duracaoDias duração da viagem em dias
 * @param numeroPessoas número de pessoas na viagem
 */
public record CalculadoraPassagensRequest(
        @NotBlank(message = "Código IATA de origem é obrigatório")
        @Size(min = 3, max = 3, message = "Código IATA deve ter exatamente 3 caracteres")
        String origem,

        @NotNull(message = "Duração em dias é obrigatória")
        @Min(value = 1, message = "Duração deve ser no mínimo 1 dia")
        Integer duracaoDias,

        @NotNull(message = "Número de pessoas é obrigatório")
        @Min(value = 1, message = "Número de pessoas deve ser no mínimo 1")
        Integer numeroPessoas
) {
}

