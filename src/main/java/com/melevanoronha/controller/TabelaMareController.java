package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.TabelaMareControllerInterface;
import com.melevanoronha.dto.request.TabelaMareRequest;
import com.melevanoronha.dto.response.TabelaMareResponse;
import com.melevanoronha.service.TabelaMareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller responsável por gerenciar endpoints relacionados à tabela de marés.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class TabelaMareController implements TabelaMareControllerInterface {

    private final TabelaMareService tabelaMareService;

    @Override
    public ResponseEntity<List<TabelaMareResponse>> listarPorData(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<TabelaMareResponse> registros = tabelaMareService.listarPorData(data);
        return ResponseEntity.ok(registros);
    }

    @Override
    public ResponseEntity<TabelaMareResponse> cadastrar(@Valid @RequestBody TabelaMareRequest request) {
        TabelaMareResponse response = tabelaMareService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

