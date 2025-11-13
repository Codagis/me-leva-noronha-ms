package com.melevanoronha.service;

import com.melevanoronha.dto.request.DicaRequest;
import com.melevanoronha.dto.response.DicaResponse;
import com.melevanoronha.model.Dica;
import com.melevanoronha.repository.DicaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DicaService {

    private final DicaRepository dicaRepository;

    @Transactional(readOnly = true)
    public List<DicaResponse> listarTodos() {
        return dicaRepository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DicaResponse buscarPorId(Long id) {
        Dica dica = dicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dica não encontrada"));
        return toResponse(dica);
    }

    @Transactional(readOnly = true)
    public MidiaResponse obterImagem(Long id) {
        Dica dica = dicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dica não encontrada"));
        return new MidiaResponse(dica.getImagemDados(), dica.getImagemContentType());
    }

    @Transactional(readOnly = true)
    public MidiaResponse obterIcone(Long id) {
        Dica dica = dicaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dica não encontrada"));
        return new MidiaResponse(dica.getIconeDados(), dica.getIconeContentType());
    }

    @Transactional
    public DicaResponse cadastrar(DicaRequest request) {
        Dica dica = new Dica();
        dica.setTag(request.tag());
        dica.setTitulo(request.titulo());
        dica.setDescricao(request.descricao());
        dica.setLinkWhatsapp(request.linkWhatsapp());
        dica.setImagemContentType(definirContentType(request.imagem()));
        dica.setIconeContentType(definirContentType(request.icone()));
        dica.setImagemDados(obterBytes(request.imagem()));
        dica.setIconeDados(obterBytes(request.icone()));
        dica.setLinkImagem("");
        dica.setLinkIcone("");

        Dica saved = dicaRepository.save(dica);
        saved.setLinkImagem("/api/dicas/" + saved.getId() + "/imagem");
        saved.setLinkIcone("/api/dicas/" + saved.getId() + "/icone");
        return toResponse(saved);
    }

    private DicaResponse toResponse(Dica dica) {
        return new DicaResponse(
                dica.getId(),
                dica.getTag(),
                dica.getTitulo(),
                dica.getDescricao(),
                dica.getLinkWhatsapp(),
                dica.getLinkImagem(),
                dica.getLinkIcone()
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

    public record MidiaResponse(byte[] dados, String contentType) {}
}

