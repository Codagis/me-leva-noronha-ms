package com.melevanoronha.dto.response;

import java.math.BigDecimal;

/**
 * DTO de resposta para informações de um passeio disponível na calculadora.
 *
 * @param codigo código do passeio (enum name)
 * @param nome nome amigável do passeio
 * @param valorPorPessoa valor por pessoa em reais
 */
public record PasseioCalculadoraResponse(
        String codigo,
        String nome,
        BigDecimal valorPorPessoa
) {
}

