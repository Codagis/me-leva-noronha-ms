package com.melevanoronha.service;

import com.melevanoronha.dto.request.TabelaMareRequest;
import com.melevanoronha.dto.response.TabelaMareResponse;
import com.melevanoronha.model.TabuaMare;
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
        List<TabuaMare> registros = tabelaMareRepository.findByData(data);
        return registros.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TabelaMareResponse cadastrar(TabelaMareRequest request) {
        if (tabelaMareRepository.existsByDataAndHorario(request.data(), request.horario())) {
            throw new RuntimeException("Já existe um registro para esta data e horário");
        }

        TabuaMare tabuaMare = new TabuaMare();
        tabuaMare.setData(request.data());
        tabuaMare.setHorario(request.horario());
        tabuaMare.setMetro(request.metro());

        TabuaMare saved = tabelaMareRepository.save(tabuaMare);
        return toResponse(saved);
    }

    private TabelaMareResponse toResponse(TabuaMare tabuaMare) {
        return TabelaMareResponse.builder()
                .id(tabuaMare.getId())
                .data(tabuaMare.getData())
                .horario(tabuaMare.getHorario())
                .metro(tabuaMare.getMetro())
                .build();
    }
}

