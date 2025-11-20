package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.PontoInteresseControllerInterface;
import com.melevanoronha.dto.request.PontoInteresseRequest;
import com.melevanoronha.dto.response.PontoInteresseResponse;
import com.melevanoronha.service.PontoInteresseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller REST responsável pelos endpoints de consulta e cadastro de pontos de interesse.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class PontoInteresseController implements PontoInteresseControllerInterface {

    private final PontoInteresseService pontoInteresseService;

    @Override
    public ResponseEntity<List<PontoInteresseResponse>> listar() {
        List<PontoInteresseResponse> respostas = pontoInteresseService.listarTodos();
        return ResponseEntity.ok(respostas);
    }

    @Override
    public ResponseEntity<PontoInteresseResponse> cadastrar(@Valid @RequestBody PontoInteresseRequest request) {
        PontoInteresseResponse response = pontoInteresseService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
