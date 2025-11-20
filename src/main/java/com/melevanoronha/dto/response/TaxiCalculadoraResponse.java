package com.melevanoronha.dto.response;

import java.math.BigDecimal;

/**
 * DTO para resposta de cálculo de corrida de táxi em Fernando de Noronha.
 *
 * @param origem local de partida da corrida
 * @param destino local de destino da corrida
 * @param valorTabela1 valor da corrida na Tabela 1
 * @param valorTabela2 valor da corrida na Tabela 2
 * @param whatsappTabela1 número de WhatsApp da Tabela 1
 * @param whatsappTabela2 número de WhatsApp da Tabela 2
 */
public record TaxiCalculadoraResponse(
        String origem,
        String destino,
        BigDecimal valorTabela1,
        BigDecimal valorTabela2,
        String whatsappTabela1,
        String whatsappTabela2
) {
}

