package com.forohub.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record AutenticacionUsuarioDTO(
        @NotBlank
        String email,
        @NotBlank
        String contrasena
) {}