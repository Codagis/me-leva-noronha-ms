package com.melevanoronha.dto.request;

import com.melevanoronha.enumerator.CategoriaRestaurante;
import com.melevanoronha.enumerator.TipoHospedagem;
import com.melevanoronha.enumerator.TipoPasseio;
import com.melevanoronha.enumerator.TipoTransporte;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * DTO para requisição de cálculo de viagem para Fernando de Noronha.
 *
 * @param origem cidade de origem do viajante
 * @param duracaoDias duração da viagem em dias
 * @param numeroPessoas número de pessoas na viagem
 * @param tipoHospedagem tipo de hospedagem escolhida
 * @param categoriaRestaurante categoria de restaurantes escolhida
 * @param tipoTransporte tipo de transporte na ilha
 * @param passeios lista de passeios selecionados
 * @param jaTemPassagens indica se o usuário já possui passagens aéreas (se true, o cálculo de passagens será desconsiderado)
 */
public record CalculadoraViagemRequest(
        @NotBlank(message = "Origem é obrigatória")
        @Size(max = 100, message = "Origem deve ter no máximo 100 caracteres")
        String origem,

        @NotNull(message = "Duração em dias é obrigatória")
        @Min(value = 1, message = "Duração deve ser no mínimo 1 dia")
        Integer duracaoDias,

        @NotNull(message = "Número de pessoas é obrigatório")
        @Min(value = 1, message = "Número de pessoas deve ser no mínimo 1")
        Integer numeroPessoas,

        @NotNull(message = "Tipo de hospedagem é obrigatório")
        TipoHospedagem tipoHospedagem,

        @NotNull(message = "Categoria de restaurante é obrigatória")
        CategoriaRestaurante categoriaRestaurante,

        @NotNull(message = "Tipo de transporte é obrigatório")
        TipoTransporte tipoTransporte,

        List<TipoPasseio> passeios,

        Boolean jaTemPassagens
) {
}

