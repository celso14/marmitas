package com.api.marmitas.exceptions;

public class GeoCodeException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public GeoCodeException(String message) {
        super("Erro ao buscar geocodificação: " + message);
    }
}
