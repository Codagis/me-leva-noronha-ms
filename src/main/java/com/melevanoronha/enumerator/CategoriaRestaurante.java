package com.melevanoronha.enumerator;

/**
 * Enum que representa as categorias de restaurantes disponíveis.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum CategoriaRestaurante {
    ECONOMICOS(80.0),
    MEDIO(150.0),
    PREMIUM(300.0);

    private final double valorDiario;

    CategoriaRestaurante(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public double getValorDiario() {
        return valorDiario;
    }
}

