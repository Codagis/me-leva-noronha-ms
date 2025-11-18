package com.melevanoronha.enumerator;

/**
 * Enumeração que representa as categorias disponíveis para os passeios,
 * acompanhadas de descrições amigáveis para exibição ao usuário final.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum PasseioCategoria {

    AQUATICOS("Aquáticos"),
    TERRESTRES("Terrestres"),
    EXCLUSIVOS("Exclusivos"),
    AVENTURA("Aventura");

    private final String descricao;

    PasseioCategoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


