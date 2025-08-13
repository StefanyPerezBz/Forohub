package com.forohub.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable paginacion);

    @Query("""
            SELECT t FROM Topico t
            WHERE t.curso.nombre = :nombreCurso
            AND YEAR(t.fechaCreacion) = :anio
            """)
    Page<Topico> findByCursoNombreAndAnio(String nombreCurso, Integer anio, Pageable paginacion);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}