package com.example.backapi.utils.exceptions;

public class TamanhoDeCampoExcedente extends Exception {
    public TamanhoDeCampoExcedente() {
    }

    public TamanhoDeCampoExcedente(String message) {
        super(message);
    }

    public TamanhoDeCampoExcedente(String message, Throwable cause) {
        super(message, cause);
    }

    public TamanhoDeCampoExcedente(Throwable cause) {
        super(cause);
    }

    public TamanhoDeCampoExcedente(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
