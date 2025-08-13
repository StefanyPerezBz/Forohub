package com.forohub.forohub.infra.exceptions;

public class ValidacionDeIntegridad extends RuntimeException {
    public ValidacionDeIntegridad(String message) {
        super(message);
    }
}