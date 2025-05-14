package com.boundlyapp.api.exception;

import java.util.List;

/**
 * Exceção usada para erros específicos no PATCH.
 */
public class PatchValidationException extends RuntimeException {

    private final List<String> errors;

    public PatchValidationException(List<String> errors) {
        super("Erro de validação no PATCH");
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
