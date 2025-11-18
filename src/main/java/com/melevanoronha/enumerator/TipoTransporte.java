package com.melevanoronha.enumerator;

/**
 * Enum que representa os tipos de transporte disponíveis na ilha.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum TipoTransporte {
    ALUGUEL_CARRO_BUGGY(200.0),
    TAXI(80.0),
    ONIBUS(10.0),
    NENHUM(0.0);

    private final double valorDiario;

    TipoTransporte(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public double getValorDiario() {
        return valorDiario;
    }
}

