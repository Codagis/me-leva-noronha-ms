package com.melevanoronha.dto.response;

import com.melevanoronha.enumerator.CategoriaRestaurante;

/**
 * DTO utilizado para devolver os dados de restaurantes para os consumidores da API.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public record RestauranteResponse(
        Long id,
        String nome,
        String descricao,
        String numeroWhatsapp,
        CategoriaRestaurante categoria,
        String categoriaCifroes,
        String linkImagem
) {
    public RestauranteResponse withLinkImagem(String linkImagem) {
        return new RestauranteResponse(id, nome, descricao, numeroWhatsapp, categoria, categoriaCifroes, linkImagem);
    }
}

