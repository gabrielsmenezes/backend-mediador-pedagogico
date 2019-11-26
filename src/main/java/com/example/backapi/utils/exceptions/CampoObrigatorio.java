package com.example.backapi.utils.exceptions;

public class CampoObrigatorio extends Exception{
    public CampoObrigatorio() {
    }
    public CampoObrigatorio(String message) {
        super(message);
    }
}
