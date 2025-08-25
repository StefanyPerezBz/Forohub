package com.forohub.forohub.dto;

import com.forohub.forohub.domain.topico.EstadoTopico;
import jakarta.validation.constraints.NotNull;

public record ActualizarTopicoDTO(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        EstadoTopico estado
) {}