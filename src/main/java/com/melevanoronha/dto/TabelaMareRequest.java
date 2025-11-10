package com.melevanoronha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO para requisições de cadastro de registros de maré.
 * 
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TabelaMareRequest {

    @NotNull(message = "Data é obrigatória")
    private LocalDate data;

    @NotBlank(message = "Horário é obrigatório")
    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Horário deve estar no formato HH:mm (ex: 09:30, 03:45)")
    @Size(max = 5, message = "Horário deve ter no máximo 5 caracteres")
    private String horario;

    @NotBlank(message = "Metro é obrigatório")
    @Size(max = 10, message = "Metro deve ter no máximo 10 caracteres")
    private String metro;
}

