package com.melevanoronha.dto;

import com.melevanoronha.enumerator.PasseioCategoria;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO utilizado para devolver os dados de passeios para os consumidores da API.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record PasseioResponse(
        Long id,
        String tag,
        String titulo,
        String descricao,
        String duracao,
        BigDecimal valor,
        List<String> itensIncluidos,
        String linkWhatsapp,
        PasseioCategoria categoria,
        String categoriaDescricao,
        String linkImagem
) {

    public PasseioResponse {
        itensIncluidos = itensIncluidos == null ? List.of() : List.copyOf(itensIncluidos);
    }

    public PasseioResponse withLinkImagem(String linkImagem) {
        return new PasseioResponse(id, tag, titulo, descricao, duracao, valor, itensIncluidos, linkWhatsapp, categoria, categoriaDescricao, linkImagem);
    }
}


