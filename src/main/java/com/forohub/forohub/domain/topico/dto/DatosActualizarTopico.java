package com.forohub.forohub.domain.topico.dto;

import com.forohub.forohub.domain.topico.EstadoTopico;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        EstadoTopico estado
) {}