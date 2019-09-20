package com.example.backapi.utils.exceptions;

public class ObjetoNaoEncontrado extends Exception {

    public ObjetoNaoEncontrado() {
    }

    public ObjetoNaoEncontrado(String message) {
        super(message);
    }

    public ObjetoNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjetoNaoEncontrado(Throwable cause) {
        super(cause);
    }

    public ObjetoNaoEncontrado(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
