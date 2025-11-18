package com.melevanoronha.service;

import com.melevanoronha.dto.request.VidaNoturnaRequest;
import com.melevanoronha.dto.response.VidaNoturnaResponse;
import com.melevanoronha.model.VidaNoturna;
import com.melevanoronha.repository.VidaNoturnaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela orquestração das operações de vida noturna,
 * incluindo persistência, montagem de respostas e fornecimento da imagem.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class VidaNoturnaService {

    private final VidaNoturnaRepository vidaNoturnaRepository;

    @Transactional(readOnly = true)
    public List<VidaNoturnaResponse> listarTodos() {
        return vidaNoturnaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VidaNoturnaResponse buscarPorId(Long id) {
        VidaNoturna vidaNoturna = vidaNoturnaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vida Noturna não encontrada"));
        return toResponse(vidaNoturna);
    }

    @Transactional(readOnly = true)
    public MidiaResponse obterImagem(Long id) {
        VidaNoturna vidaNoturna = vidaNoturnaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vida Noturna não encontrada"));
        return new MidiaResponse(vidaNoturna.getImagemDados(), vidaNoturna.getImagemContentType());
    }

    @Transactional
    public VidaNoturnaResponse cadastrar(VidaNoturnaRequest request) {
        VidaNoturna vidaNoturna = new VidaNoturna();
        vidaNoturna.setTitulo(request.titulo());
        vidaNoturna.setHorarioFuncionamento(request.horarioFuncionamento());
        vidaNoturna.setNumeroWhatsapp(request.numeroWhatsapp());
        vidaNoturna.setLinkGoogleMaps(request.linkGoogleMaps());
        vidaNoturna.setImagemContentType(definirContentType(request.imagem()));
        vidaNoturna.setImagemDados(obterBytes(request.imagem()));
        vidaNoturna.setLinkImagem("");

        VidaNoturna saved = vidaNoturnaRepository.save(vidaNoturna);
        saved.setLinkImagem("/api/vida-noturna/" + saved.getId() + "/imagem");
        return toResponse(saved);
    }

    private VidaNoturnaResponse toResponse(VidaNoturna vidaNoturna) {
        return new VidaNoturnaResponse(
                vidaNoturna.getId(),
                vidaNoturna.getTitulo(),
                vidaNoturna.getHorarioFuncionamento(),
                vidaNoturna.getNumeroWhatsapp(),
                vidaNoturna.getLinkGoogleMaps(),
                vidaNoturna.getLinkImagem()
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


