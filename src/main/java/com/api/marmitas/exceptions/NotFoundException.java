package com.api.marmitas.exceptions;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException(Long id) {
       super("Registro não encontrado com id: " + id);
    }
}
