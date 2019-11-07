package com.example.backapi.utils.exceptions;

public class AcessoNegado extends Exception {

    public AcessoNegado() {
        super();
    }

    public AcessoNegado(String message) {
        super(message);
    }
}
