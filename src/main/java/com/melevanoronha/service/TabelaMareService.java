package com.melevanoronha.service;

import com.melevanoronha.dto.TabelaMareRequest;
import com.melevanoronha.dto.TabelaMareResponse;
import com.melevanoronha.model.TabelaMare;
import com.melevanoronha.repository.TabelaMareRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável por gerenciar operações relacionadas à tabela de marés.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class TabelaMareService {

    private final TabelaMareRepository tabelaMareRepository;

    @Transactional(readOnly = true)
    public List<TabelaMareResponse> listarPorData(LocalDate data) {
        List<TabelaMare> registros = tabelaMareRepository.findByData(data);
        return registros.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TabelaMareResponse cadastrar(TabelaMareRequest request) {
        if (tabelaMareRepository.existsByDataAndHorario(request.getData(), request.getHorario())) {
            throw new RuntimeException("Já existe um registro para esta data e horário");
        }

        TabelaMare tabelaMare = new TabelaMare();
        tabelaMare.setData(request.getData());
        tabelaMare.setHorario(request.getHorario());
        tabelaMare.setMetro(request.getMetro());

        TabelaMare saved = tabelaMareRepository.save(tabelaMare);
        return toResponse(saved);
    }

    private TabelaMareResponse toResponse(TabelaMare tabelaMare) {
        return TabelaMareResponse.builder()
                .id(tabelaMare.getId())
                .data(tabelaMare.getData())
                .horario(tabelaMare.getHorario())
                .metro(tabelaMare.getMetro())
                .build();
    }
}

