package com.melevanoronha.controller;

import com.melevanoronha.controller.interfaces.RestauranteControllerInterface;
import com.melevanoronha.dto.request.RestauranteRequest;
import com.melevanoronha.dto.response.RestauranteResponse;
import com.melevanoronha.enumerator.CategoriaRestaurante;
import com.melevanoronha.service.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller REST responsável pelos endpoints de consulta e cadastro de restaurantes,
 * incluindo o fornecimento da imagem armazenada no banco de dados.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
public class RestauranteController implements RestauranteControllerInterface {

    private final RestauranteService restauranteService;

    @Override
    public ResponseEntity<List<RestauranteResponse>> listar(@RequestParam(required = false) CategoriaRestaurante categoria) {
        List<RestauranteResponse> respostas;
        
        if (categoria != null) {
            respostas = restauranteService.listarPorCategoria(categoria).stream()
                    .map(this::completarLinks)
                    .collect(Collectors.toList());
        } else {
            respostas = restauranteService.listarTodos().stream()
                    .map(this::completarLinks)
                    .collect(Collectors.toList());
        }
        
        return ResponseEntity.ok(respostas);
    }

    @Override
    public ResponseEntity<RestauranteResponse> buscarPorId(@PathVariable Long id) {
        RestauranteResponse response = completarLinks(restauranteService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<RestauranteResponse> cadastrar(@Valid @ModelAttribute RestauranteRequest request) {
        RestauranteResponse response = completarLinks(restauranteService.cadastrar(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<byte[]> obterImagem(@PathVariable Long id) {
        RestauranteService.MidiaResponse midia = restauranteService.obterImagem(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=restaurante-imagem-" + id)
                .contentType(MediaType.parseMediaType(midia.contentType()))
                .body(midia.dados());
    }

    private RestauranteResponse completarLinks(RestauranteResponse response) {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return response.withLinkImagem(baseUrl + response.linkImagem());
    }
}

