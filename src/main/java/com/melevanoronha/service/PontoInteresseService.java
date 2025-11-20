package com.melevanoronha.service;

import com.melevanoronha.dto.request.PontoInteresseRequest;
import com.melevanoronha.dto.response.PontoInteresseResponse;
import com.melevanoronha.model.PontoInteresse;
import com.melevanoronha.repository.PontoInteresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela orquestração das operações relacionadas a pontos de interesse,
 * incluindo persistência e montagem das respostas.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class PontoInteresseService {

    private final PontoInteresseRepository pontoInteresseRepository;

    @Transactional(readOnly = true)
    public List<PontoInteresseResponse> listarTodos() {
        return pontoInteresseRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PontoInteresseResponse cadastrar(PontoInteresseRequest request) {
        PontoInteresse pontoInteresse = new PontoInteresse();
        pontoInteresse.setTitulo(request.titulo());
        pontoInteresse.setCategoria(request.categoria());
        pontoInteresse.setTag(request.tag());
        pontoInteresse.setLinkGoogleMaps(request.linkGoogleMaps());

        PontoInteresse saved = pontoInteresseRepository.save(pontoInteresse);
        return toResponse(saved);
    }

    private PontoInteresseResponse toResponse(PontoInteresse pontoInteresse) {
        return new PontoInteresseResponse(
                pontoInteresse.getId(),
                pontoInteresse.getTitulo(),
                pontoInteresse.getCategoria(),
                pontoInteresse.getTag(),
                pontoInteresse.getLinkGoogleMaps()
        );
    }
}
