package com.melevanoronha.controller;

import com.melevanoronha.dto.request.DicaRequest;
import com.melevanoronha.dto.response.DicaResponse;
import com.melevanoronha.service.DicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dicas")
@RequiredArgsConstructor
public class DicaController {

    private final DicaService dicaService;

    @GetMapping
    public ResponseEntity<List<DicaResponse>> listar() {
        List<DicaResponse> respostas = dicaService.listarTodos().stream()
                .map(this::completarLinks)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DicaResponse> buscarPorId(@PathVariable Long id) {
        DicaResponse response = completarLinks(dicaService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DicaResponse> cadastrar(@Valid @ModelAttribute DicaRequest request) {
        DicaResponse response = completarLinks(dicaService.cadastrar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/{id}/imagem")
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        DicaService.MidiaResponse midia = dicaService.obterImagem(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=imagem-" + id)
                .contentType(MediaType.parseMediaType(midia.contentType()))
                .body(midia.dados());
    }

    @GetMapping(value = "/{id}/icone")
    public ResponseEntity<byte[]> obterIcone(@PathVariable Long id) {
        DicaService.MidiaResponse midia = dicaService.obterIcone(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=icone-" + id)
                .contentType(MediaType.parseMediaType(midia.contentType()))
                .body(midia.dados());
    }

    private DicaResponse completarLinks(DicaResponse response) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return response
                .withLinkImagem(baseUrl + response.linkImagem())
                .withLinkIcone(baseUrl + response.linkIcone());
    }
}