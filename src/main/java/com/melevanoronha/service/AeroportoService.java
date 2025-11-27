package com.melevanoronha.service;

import com.melevanoronha.dto.response.AeroportoResponse;
import com.melevanoronha.model.Aeroporto;
import com.melevanoronha.repository.AeroportoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável por operações relacionadas a aeroportos.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class AeroportoService {

    private final AeroportoRepository aeroportoRepository;

    @Transactional(readOnly = true)
    public List<AeroportoResponse> listarTodos() {
        return aeroportoRepository.findAll().stream()
                .map(this::toAeroportoResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<Aeroporto> buscarPorCodigoIATA(String codigoIATA) {
        if (codigoIATA == null || codigoIATA.isBlank()) {
            return Optional.empty();
        }
        return aeroportoRepository.findByCodigoIATA(codigoIATA.toUpperCase().trim());
    }

    private AeroportoResponse toAeroportoResponse(Aeroporto aeroporto) {
        return new AeroportoResponse(
                aeroporto.getCidade(),
                aeroporto.getNomeAeroporto(),
                aeroporto.getCodigoIATA()
        );
    }
}

