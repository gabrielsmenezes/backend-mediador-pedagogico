package com.example.backapi.utils.exceptions;

public class CampoObrigatorio extends Exception{

    public CampoObrigatorio() {
    }

    public CampoObrigatorio(String message) {
        super(message);
    }

    public CampoObrigatorio(String message, Throwable cause) {
        super(message, cause);
    }

    public CampoObrigatorio(Throwable cause) {
        super(cause);
    }

    public CampoObrigatorio(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
