package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.TaxiControllerInterface;
import com.melevanoronha.dto.request.TaxiCalculadoraRequest;
import com.melevanoronha.dto.response.TaxiCalculadoraResponse;
import com.melevanoronha.service.TaxiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller REST responsável pelo endpoint de cálculo de corridas de táxi.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class TaxiController implements TaxiControllerInterface {

    private final TaxiService taxiService;

    @Override
    public ResponseEntity<TaxiCalculadoraResponse> calcularCorrida(
            @Valid @RequestBody TaxiCalculadoraRequest request
    ) {
        TaxiCalculadoraResponse response = taxiService.calcularCorrida(request);
        return ResponseEntity.ok(response);
    }
}

