package com.forohub.forohub.exceptions;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String message) {
        super(message);
    }
}