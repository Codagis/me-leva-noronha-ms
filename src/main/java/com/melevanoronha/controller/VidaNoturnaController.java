package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.VidaNoturnaControllerInterface;
import com.melevanoronha.dto.request.VidaNoturnaRequest;
import com.melevanoronha.dto.response.VidaNoturnaResponse;
import com.melevanoronha.service.VidaNoturnaService;
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
 * Controller REST que expõe os endpoints de consulta e cadastro de vida noturna,
 * incluindo o fornecimento da imagem armazenada no banco.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class VidaNoturnaController implements VidaNoturnaControllerInterface {

    private final VidaNoturnaService vidaNoturnaService;

    @Override
    public ResponseEntity<List<VidaNoturnaResponse>> listar() {
        List<VidaNoturnaResponse> respostas = vidaNoturnaService.listarTodos().stream()
                .map(this::completarLinks)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respostas);
    }

    @Override
    public ResponseEntity<VidaNoturnaResponse> buscarPorId(@PathVariable Long id) {
        VidaNoturnaResponse response = completarLinks(vidaNoturnaService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<VidaNoturnaResponse> cadastrar(@Valid @ModelAttribute VidaNoturnaRequest request) {
        VidaNoturnaResponse response = completarLinks(vidaNoturnaService.cadastrar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        VidaNoturnaService.MidiaResponse midia = vidaNoturnaService.obterImagem(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=vida-noturna-imagem-" + id)
                .contentType(MediaType.parseMediaType(midia.contentType()))
                .body(midia.dados());
    }

    private VidaNoturnaResponse completarLinks(VidaNoturnaResponse response) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return response.withLinkImagem(baseUrl + response.linkImagem());
    }
}


