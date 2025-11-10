package com.melevanoronha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DicaResponse {

    private Long id;
    private String tag;
    private String titulo;
    private String descricao;
    private String linkWhatsapp;
    private String linkImagem;
    private String linkIcone;
}

