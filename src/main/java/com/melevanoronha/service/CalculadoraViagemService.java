package com.melevanoronha.service;

import com.melevanoronha.dto.request.CalculadoraViagemRequest;
import com.melevanoronha.dto.response.CalculadoraViagemResponse;
import com.melevanoronha.enumerator.CategoriaRestaurante;
import com.melevanoronha.enumerator.TipoHospedagem;
import com.melevanoronha.enumerator.TipoPasseio;
import com.melevanoronha.enumerator.TipoTransporte;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Serviço responsável por calcular os custos totais de uma viagem para Fernando de Noronha.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CalculadoraViagemService {

    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private static final double TUP_VALOR_DIARIO = 79.20;
    private static final double PARNAMAR_VALOR_FIXO = 222.00;

    private final FlightService flightService;

    public CalculadoraViagemResponse calcularCustos(CalculadoraViagemRequest request) {
        return calcularCustos(request, false);
    }

    public CalculadoraViagemResponse calcularCustosOutros(CalculadoraViagemRequest request) {
        log.info("Calculando custos (sem passagens): duracao={} dias, pessoas={}", 
                request.duracaoDias(), request.numeroPessoas());
        return calcularCustos(request, false);
    }

    public CalculadoraViagemResponse calcularCustosCompleto(CalculadoraViagemRequest request) {
        log.info("Calculando custos completos: origem={}, duracao={} dias, pessoas={}", 
                request.origem(), request.duracaoDias(), request.numeroPessoas());
        return calcularCustos(request, true);
    }

    private CalculadoraViagemResponse calcularCustos(CalculadoraViagemRequest request, boolean incluirPassagens) {
        BigDecimal passagensAereas = incluirPassagens ? calcularPassagensAereas(request) : BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);

        BigDecimal hospedagem = calcularHospedagem(
                request.tipoHospedagem(),
                request.duracaoDias(),
                request.numeroPessoas()
        );

        BigDecimal alimentacao = calcularAlimentacao(
                request.categoriaRestaurante(),
                request.duracaoDias(),
                request.numeroPessoas()
        );

        BigDecimal passeios = calcularPasseios(
                request.passeios(),
                request.numeroPessoas()
        );

        BigDecimal transporteLocal = calcularTransporteLocal(
                request.tipoTransporte(),
                request.duracaoDias(),
                request.numeroPessoas()
        );

        BigDecimal taxas = calcularTaxas(request.duracaoDias(), request.numeroPessoas());

        BigDecimal total = calcularTotal(passagensAereas, hospedagem, alimentacao, passeios, transporteLocal, taxas);

        log.info("Cálculo concluído: Total = R$ {}", total);

        return new CalculadoraViagemResponse(
                passagensAereas,
                hospedagem,
                alimentacao,
                passeios,
                transporteLocal,
                taxas,
                total
        );
    }

    private BigDecimal calcularHospedagem(TipoHospedagem tipoHospedagem, Integer duracaoDias, Integer numeroPessoas) {
        double valorDiario = tipoHospedagem.getValorDiario();
        return calcularCustoDiarioPorPessoa(valorDiario, duracaoDias, numeroPessoas);
    }

    private BigDecimal calcularAlimentacao(CategoriaRestaurante categoriaRestaurante, Integer duracaoDias, Integer numeroPessoas) {
        double valorDiario = categoriaRestaurante.getValorDiario();
        return calcularCustoDiarioPorPessoa(valorDiario, duracaoDias, numeroPessoas);
    }

    private BigDecimal calcularPasseios(List<TipoPasseio> passeios, Integer numeroPessoas) {
        if (passeios == null || passeios.isEmpty()) {
            return BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
        }

        double totalPorPessoa = passeios.stream()
                .mapToDouble(TipoPasseio::getValorPorPessoa)
                .sum();

        return calcularCustoPorPessoa(totalPorPessoa, numeroPessoas);
    }

    private BigDecimal calcularTransporteLocal(TipoTransporte tipoTransporte, Integer duracaoDias, Integer numeroPessoas) {
        double valorDiario = tipoTransporte.getValorDiario();
        return calcularCustoDiarioPorPessoa(valorDiario, duracaoDias, numeroPessoas);
    }

    private BigDecimal calcularCustoDiarioPorPessoa(double valorDiario, Integer duracaoDias, Integer numeroPessoas) {
        double total = valorDiario * duracaoDias * numeroPessoas;
        return toBigDecimal(total);
    }

    private BigDecimal calcularCustoPorPessoa(double valorPorPessoa, Integer numeroPessoas) {
        double total = valorPorPessoa * numeroPessoas;
        return toBigDecimal(total);
    }

    private BigDecimal calcularTaxas(Integer duracaoDias, Integer numeroPessoas) {
        BigDecimal tup = calcularTUP(duracaoDias, numeroPessoas);
        BigDecimal parnamar = calcularPARNAMAR(numeroPessoas);
        return tup.add(parnamar).setScale(SCALE, ROUNDING_MODE);
    }

    private BigDecimal calcularTUP(Integer duracaoDias, Integer numeroPessoas) {
        double total = TUP_VALOR_DIARIO * duracaoDias * numeroPessoas;
        return toBigDecimal(total);
    }

    private BigDecimal calcularPARNAMAR(Integer numeroPessoas) {
        double total = PARNAMAR_VALOR_FIXO * numeroPessoas;
        return toBigDecimal(total);
    }

    private BigDecimal calcularTotal(BigDecimal passagensAereas, BigDecimal hospedagem, 
                                     BigDecimal alimentacao, BigDecimal passeios, 
                                     BigDecimal transporteLocal, BigDecimal taxas) {
        return passagensAereas
                .add(hospedagem)
                .add(alimentacao)
                .add(passeios)
                .add(transporteLocal)
                .add(taxas)
                .setScale(SCALE, ROUNDING_MODE);
    }

    private BigDecimal calcularPassagensAereas(CalculadoraViagemRequest request) {
        if (Boolean.TRUE.equals(request.jaTemPassagens())) {
            log.info("Usuário já possui passagens aéreas. Desconsiderando cálculo de passagens.");
            return BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
        }

        return flightService.buscarPrecoPassagens(
                request.origem(), 
                request.numeroPessoas(),
                request.duracaoDias()
        );
    }

    private BigDecimal toBigDecimal(double value) {
        return BigDecimal.valueOf(value)
                .setScale(SCALE, ROUNDING_MODE);
    }
}
