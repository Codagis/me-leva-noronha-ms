package com.melevanoronha.enumerator;

/**
 * Enumeração que representa o ranking dos top 3 passeios.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum TopRanking {
    TOP_1("TOP 1"),
    TOP_2("TOP 2"),
    TOP_3("TOP 3");

    private final String descricao;

    TopRanking(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

