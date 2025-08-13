package com.forohub.forohub.infra.security.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank
        String email,
        @NotBlank
        String contrasena
) {}