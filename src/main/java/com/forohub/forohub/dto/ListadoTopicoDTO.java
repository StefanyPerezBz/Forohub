package com.forohub.forohub.dto;

import java.time.LocalDateTime;

import com.forohub.forohub.model.Topico;

public record ListadoTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        String autor,
        String curso
) {
    public ListadoTopicoDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado().toString(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}