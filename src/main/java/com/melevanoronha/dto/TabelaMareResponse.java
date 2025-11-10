package com.melevanoronha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para respostas de registros de maré.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TabelaMareResponse {

    private Long id;
    private LocalDate data;
    private String horario;
    private String metro;
}

