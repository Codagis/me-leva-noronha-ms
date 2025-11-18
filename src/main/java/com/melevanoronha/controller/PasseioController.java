package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.PasseioControllerInterface;
import com.melevanoronha.dto.PasseioRequest;
import com.melevanoronha.dto.PasseioResponse;
import com.melevanoronha.service.PasseioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller REST responsável pelos endpoints de consulta e cadastro de passeios,
 * incluindo o fornecimento da imagem armazenada no banco de dados.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class PasseioController implements PasseioControllerInterface {

    private final PasseioService passeioService;

    @Override
    public ResponseEntity<List<PasseioResponse>> listar() {
        List<PasseioResponse> respostas = passeioService.listarTodos().stream()
                .map(this::completarLinks)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respostas);
    }

    @Override
    public ResponseEntity<PasseioResponse> buscarPorId(@PathVariable Long id) {
        PasseioResponse response = completarLinks(passeioService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PasseioResponse> cadastrar(@Valid @ModelAttribute PasseioRequest request) {
        PasseioResponse response = completarLinks(passeioService.cadastrar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        PasseioService.MidiaResponse midia = passeioService.obterImagem(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=passeio-imagem-" + id)
                .contentType(MediaType.parseMediaType(midia.contentType()))
                .body(midia.dados());
    }

    private PasseioResponse completarLinks(PasseioResponse response) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return response.withLinkImagem(baseUrl + response.linkImagem());
    }
}


