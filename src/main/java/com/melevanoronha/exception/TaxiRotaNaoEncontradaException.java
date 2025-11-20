package com.melevanoronha.exception;

/**
 * Exceção lançada quando uma rota de táxi não é encontrada no sistema.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
public class TaxiRotaNaoEncontradaException extends RuntimeException {

    public TaxiRotaNaoEncontradaException(String message) {
        super(message);
    }
}

