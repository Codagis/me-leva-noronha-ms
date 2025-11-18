package com.melevanoronha.dto.response;

import com.melevanoronha.enumerator.PasseioCategoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO utilizado para devolver os dados de passeios para os consumidores da API.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasseioResponse {

    private Long id;
    private String tag;
    private String titulo;
    private String descricao;
    private String duracao;
    private BigDecimal valor;
    private List<String> itensIncluidos;
    private String linkWhatsapp;
    private PasseioCategoria categoria;
    private String categoriaDescricao;
    private String linkImagem;
}


