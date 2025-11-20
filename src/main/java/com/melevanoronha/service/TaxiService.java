package com.melevanoronha.service;

import com.melevanoronha.dto.request.TaxiCalculadoraRequest;
import com.melevanoronha.dto.response.TaxiCalculadoraResponse;
import com.melevanoronha.exception.TaxiRotaNaoEncontradaException;
import com.melevanoronha.model.TaxiPreco;
import com.melevanoronha.model.TaxiTabela;
import com.melevanoronha.repository.TaxiPrecoRepository;
import com.melevanoronha.repository.TaxiTabelaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável por calcular valores de corridas de táxi em Fernando de Noronha.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TaxiService {

    private final TaxiPrecoRepository taxiPrecoRepository;
    private final TaxiTabelaRepository taxiTabelaRepository;

    @Transactional(readOnly = true)
    public TaxiCalculadoraResponse calcularCorrida(TaxiCalculadoraRequest request) {
        log.info("Calculando corrida de táxi: origem={}, destino={}", request.origem(), request.destino());

        TaxiPreco taxiPreco = taxiPrecoRepository
                .findByOrigemIgnoreCaseAndDestinoIgnoreCase(request.origem(), request.destino())
                .orElseThrow(() -> {
                    log.warn("Preço de táxi não encontrado: origem={}, destino={}", request.origem(), request.destino());
                    return new TaxiRotaNaoEncontradaException(
                            String.format("Preço de táxi não encontrado para a rota: %s -> %s", 
                                    request.origem(), request.destino())
                    );
                });

        // Buscar números de WhatsApp das tabelas
        TaxiTabela tabela1 = taxiTabelaRepository.findByNomeIgnoreCase("Tabela 1")
                .orElseThrow(() -> new RuntimeException("Tabela 1 não encontrada. Execute a inicialização dos dados."));
        
        TaxiTabela tabela2 = taxiTabelaRepository.findByNomeIgnoreCase("Tabela 2")
                .orElseThrow(() -> new RuntimeException("Tabela 2 não encontrada. Execute a inicialização dos dados."));

        log.info("Corrida calculada: Tabela 1 = R$ {}, Tabela 2 = R$ {}", 
                taxiPreco.getValorTabela1(), taxiPreco.getValorTabela2());

        return new TaxiCalculadoraResponse(
                taxiPreco.getOrigem(),
                taxiPreco.getDestino(),
                taxiPreco.getValorTabela1(),
                taxiPreco.getValorTabela2(),
                tabela1.getNumeroWhatsapp(),
                tabela2.getNumeroWhatsapp()
        );
    }
}

