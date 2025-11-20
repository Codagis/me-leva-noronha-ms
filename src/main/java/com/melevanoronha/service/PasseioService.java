package com.melevanoronha.service;

import com.melevanoronha.dto.PasseioRequest;
import com.melevanoronha.dto.PasseioResponse;
import com.melevanoronha.enumerator.TopRanking;
import com.melevanoronha.model.Passeio;
import com.melevanoronha.repository.PasseioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela orquestração das operações relacionadas a passeios,
 * incluindo persistência, montagem das respostas e fornecimento de mídias.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class PasseioService {

    private final PasseioRepository passeioRepository;

    @Transactional(readOnly = true)
    public List<PasseioResponse> listarTodos() {
        return passeioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PasseioResponse buscarPorId(Long id) {
        Passeio passeio = passeioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passeio não encontrado"));
        return toResponse(passeio);
    }

    @Transactional(readOnly = true)
    public MidiaResponse obterImagem(Long id) {
        Passeio passeio = passeioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Passeio não encontrado"));
        return new MidiaResponse(passeio.getImagemDados(), passeio.getImagemContentType());
    }

    @Transactional
    public PasseioResponse cadastrar(PasseioRequest request) {
        Passeio passeio = new Passeio();
        passeio.setTag(request.tag());
        passeio.setTitulo(request.titulo());
        passeio.setDescricao(request.descricao());
        passeio.setDuracao(request.duracao());
        passeio.setValor(request.valor());
        passeio.getItensIncluidos().clear();
        passeio.getItensIncluidos().addAll(request.itensIncluidos());
        passeio.setLinkWhatsapp(request.linkWhatsapp());
        passeio.setCategoria(request.categoria());
        passeio.setTopRanking(request.topRanking());
        passeio.setImagemContentType(definirContentType(request.imagem()));
        passeio.setImagemDados(obterBytes(request.imagem()));
        passeio.setLinkImagem("");

        Passeio saved = passeioRepository.save(passeio);
        saved.setLinkImagem("/api/passeios/" + saved.getId() + "/imagem");
        return toResponse(saved);
    }

    private PasseioResponse toResponse(Passeio passeio) {
        return new PasseioResponse(
                passeio.getId(),
                passeio.getTag(),
                passeio.getTitulo(),
                passeio.getDescricao(),
                passeio.getDuracao(),
                passeio.getValor(),
                passeio.getItensIncluidos() == null ? List.of() : List.copyOf(passeio.getItensIncluidos()),
                passeio.getLinkWhatsapp(),
                passeio.getCategoria(),
                passeio.getCategoria() != null ? passeio.getCategoria().getDescricao() : null,
                passeio.getLinkImagem(),
                passeio.getTopRanking()
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


