package com.melevanoronha.enumerator;

/**
 * Enum que representa os tipos de hospedagem disponíveis em Fernando de Noronha.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum TipoHospedagem {
    MOCHILEIRO(150.0),
    ECONOMICA(300.0),
    INTERMEDIARIA(600.0),
    LUXO(1500.0);

    private final double valorDiario;

    TipoHospedagem(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    public double getValorDiario() {
        return valorDiario;
    }
}

