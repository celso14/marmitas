package com.api.marmitas.exceptions;

import java.io.Serial;

public class GeoCodeException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public GeoCodeException(String message) {
        super("Erro ao buscar geocodificação: " + message);
    }
}
