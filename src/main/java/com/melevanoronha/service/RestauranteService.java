package com.melevanoronha.service;

import com.melevanoronha.dto.request.RestauranteRequest;
import com.melevanoronha.dto.response.RestauranteResponse;
import com.melevanoronha.enumerator.CategoriaRestaurante;
import com.melevanoronha.model.Restaurante;
import com.melevanoronha.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela orquestração das operações relacionadas a restaurantes,
 * incluindo persistência, montagem das respostas e fornecimento de mídias.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public List<RestauranteResponse> listarTodos() {
        return restauranteRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RestauranteResponse> listarPorCategoria(CategoriaRestaurante categoria) {
        return restauranteRepository.findByCategoria(categoria).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RestauranteResponse buscarPorId(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));
        return toResponse(restaurante);
    }

    @Transactional(readOnly = true)
    public MidiaResponse obterImagem(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));
        return new MidiaResponse(restaurante.getImagemDados(), restaurante.getImagemContentType());
    }

    @Transactional
    public RestauranteResponse cadastrar(RestauranteRequest request) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(request.nome());
        restaurante.setDescricao(request.descricao());
        restaurante.setNumeroWhatsapp(request.numeroWhatsapp());
        restaurante.setCategoria(request.categoria());
        restaurante.setImagemContentType(definirContentType(request.imagem()));
        restaurante.setImagemDados(obterBytes(request.imagem()));
        restaurante.setLinkImagem("");

        Restaurante saved = restauranteRepository.save(restaurante);
        saved.setLinkImagem("/api/restaurantes/" + saved.getId() + "/imagem");
        return toResponse(saved);
    }

    private RestauranteResponse toResponse(Restaurante restaurante) {
        return new RestauranteResponse(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getDescricao(),
                restaurante.getNumeroWhatsapp(),
                restaurante.getCategoria(),
                restaurante.getCategoria() != null ? restaurante.getCategoria().getCifroes() : null,
                restaurante.getLinkImagem()
        );
    }

    private byte[] obterBytes(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Arquivo inválido");
        }
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao processar arquivo enviado", e);
        }
    }

    private String definirContentType(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && !contentType.isBlank() ? contentType : "application/octet-stream";
    }

    public record MidiaResponse(byte[] dados, String contentType) {
    }
}

