package com.melevanoronha.dto.response;

import java.math.BigDecimal;

/**
 * DTO para resposta de cálculo de passagens aéreas para Fernando de Noronha.
 *
 * @param passagensAereas valor total das passagens aéreas
 */
public record CalculadoraPassagensResponse(
        BigDecimal passagensAereas
) {
}

