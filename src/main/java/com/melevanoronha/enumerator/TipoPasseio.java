package com.melevanoronha.enumerator;

/**
 * Enum que representa os tipos de passeios disponíveis em Fernando de Noronha.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public enum TipoPasseio {
    PASSEIO_BARCO_ENTARDECER_VIP(600.0),
    MERGULHO_CILINDRO(450.0),
    ILHA_TOUR_PRIVATIVO(800.0),
    PASSEIO_LANCHA_PRIVATIVO(1500.0),
    TRILHAS_GUIADAS(120.0),
    CANOA_HAVAIANA(200.0),
    PLANASUB(280.0),
    AQUASUB(350.0);

    private final double valorPorPessoa;

    TipoPasseio(double valorPorPessoa) {
        this.valorPorPessoa = valorPorPessoa;
    }

    public double getValorPorPessoa() {
        return valorPorPessoa;
    }
}

