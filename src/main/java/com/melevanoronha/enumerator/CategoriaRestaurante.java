package com.melevanoronha.enumerator;

/**
 * Enum que representa as categorias de restaurantes disponíveis.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum CategoriaRestaurante {
    ECONOMICO("$ Econômico", 80.0),
    MODERADO("$$ Moderado", 150.0),
    SOFISTICADO("$$$ Sofisticado", 250.0),
    PREMIUM("$$$$ Premium", 400.0);

    private final String descricao;
    private final double valorDiario;

    CategoriaRestaurante(String descricao, double valorDiario) {
        this.descricao = descricao;
        this.valorDiario = valorDiario;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCifroes() {
        return descricao.split(" ")[0];
    }

    public double getValorDiario() {
        return valorDiario;
    }
}

