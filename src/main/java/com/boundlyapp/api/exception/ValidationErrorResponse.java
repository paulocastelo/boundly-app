package com.boundlyapp.api.exception;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Classe que representa o JSON de erro retornado na validação.
 */
public class ValidationErrorResponse {
    private int status;
    private List<String> errors;
    private String path;
    private ZonedDateTime timestamp;

    public ValidationErrorResponse(int status, List<String> errors, String path, ZonedDateTime timestamp) {
        this.status = status;
        this.errors = errors;
        this.path = path;
        this.timestamp = timestamp;
    }

    // Getters e Setters

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
