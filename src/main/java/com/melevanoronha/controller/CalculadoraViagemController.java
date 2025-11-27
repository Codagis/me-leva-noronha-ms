package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.CalculadoraViagemControllerInterface;
import com.melevanoronha.dto.request.CalculadoraPassagensRequest;
import com.melevanoronha.dto.request.CalculadoraViagemRequest;
import com.melevanoronha.dto.response.AeroportoResponse;
import com.melevanoronha.dto.response.CalculadoraPassagensResponse;
import com.melevanoronha.dto.response.CalculadoraViagemResponse;
import com.melevanoronha.dto.response.PasseioCalculadoraResponse;
import com.melevanoronha.enumerator.TipoPasseio;
import com.melevanoronha.service.AeroportoService;
import com.melevanoronha.service.CalculadoraViagemService;
import com.melevanoronha.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Controller REST responsável pelo endpoint de cálculo de custos de viagem.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class CalculadoraViagemController implements CalculadoraViagemControllerInterface {

    private final CalculadoraViagemService calculadoraViagemService;
    private final FlightService flightService;
    private final AeroportoService aeroportoService;

    @Override
    public ResponseEntity<CalculadoraPassagensResponse> calcularPassagens(
            @Valid @RequestBody CalculadoraPassagensRequest request
    ) {
        BigDecimal passagensAereas = flightService.buscarPrecoPassagens(
                request.origem(),
                request.numeroPessoas(),
                request.duracaoDias()
        );
        CalculadoraPassagensResponse response = new CalculadoraPassagensResponse(passagensAereas);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CalculadoraViagemResponse> calcularCustos(
            @Valid @RequestBody CalculadoraViagemRequest request
    ) {
        CalculadoraViagemResponse response = calculadoraViagemService.calcularCustos(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CalculadoraViagemResponse> calcularCustosOutros(
            @Valid @RequestBody CalculadoraViagemRequest request
    ) {
        CalculadoraViagemResponse response = calculadoraViagemService.calcularCustosOutros(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CalculadoraViagemResponse> calcularCustosCompleto(
            @Valid @RequestBody CalculadoraViagemRequest request
    ) {
        CalculadoraViagemResponse response = calculadoraViagemService.calcularCustosCompleto(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<AeroportoResponse>> listarAeroportos() {
        List<AeroportoResponse> aeroportos = aeroportoService.listarTodos();
        return ResponseEntity.ok(aeroportos);
    }

    @Override
    public ResponseEntity<List<PasseioCalculadoraResponse>> listarPasseios() {
        List<PasseioCalculadoraResponse> passeios = mapearPasseios();
        return ResponseEntity.ok(passeios);
    }


    private List<PasseioCalculadoraResponse> mapearPasseios() {
        return Arrays.stream(TipoPasseio.values())
                .map(this::criarPasseioResponse)
                .toList();
    }

    private PasseioCalculadoraResponse criarPasseioResponse(TipoPasseio passeio) {
        return new PasseioCalculadoraResponse(
                passeio.name(),
                formatarNomePasseio(passeio),
                BigDecimal.valueOf(passeio.getValorPorPessoa())
        );
    }

    private String formatarNomePasseio(TipoPasseio passeio) {
        return switch (passeio) {
            case PASSEIO_BARCO_ENTARDECER_VIP -> "Passeio de Barco Entardecer VIP";
            case MERGULHO_CILINDRO -> "Mergulho de Cilindro";
            case ILHA_TOUR_PRIVATIVO -> "Ilha Tour Privativo";
            case PASSEIO_LANCHA_PRIVATIVO -> "Passeio de Lancha Privativo";
            case CANOA_HAVAIANA -> "Canoa Havaiana";
            case TRILHAS_GUIADAS -> "Trilhas Guiadas";
            case PLANASUB -> "Planasub";
            case AQUASUB -> "Aquasub";
        };
    }
}
