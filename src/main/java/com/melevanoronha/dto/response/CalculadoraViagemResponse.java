package com.melevanoronha.dto.response;

import java.math.BigDecimal;

/**
 * DTO de resposta com o cálculo detalhado da viagem para Fernando de Noronha.
 *
 * @param passagensAereas valor total das passagens aéreas
 * @param hospedagem valor total da hospedagem
 * @param alimentacao valor total da alimentação
 * @param passeios valor total dos passeios
 * @param transporteLocal valor total do transporte local
 * @param taxas valor total das taxas obrigatórias (TUP + PARNAMAR)
 * @param total valor total da viagem
 */
public record CalculadoraViagemResponse(
        BigDecimal passagensAereas,
        BigDecimal hospedagem,
        BigDecimal alimentacao,
        BigDecimal passeios,
        BigDecimal transporteLocal,
        BigDecimal taxas,
        BigDecimal total
) {
}

