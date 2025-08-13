package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.topico.dto.DatosActualizarTopico;
import com.forohub.forohub.domain.topico.dto.DatosCrearTopico;
import com.forohub.forohub.domain.topico.dto.DatosListadoTopico;
import com.forohub.forohub.domain.topico.dto.DatosRespuestaTopico;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearTopico(
            @RequestBody @Valid DatosCrearTopico datos,
            UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaTopico respuesta = topicoService.crearTopico(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicos(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> obtenerTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtenerTopicoPorId(id));
    }

    @GetMapping("/curso")
    public ResponseEntity<Page<DatosListadoTopico>> listarTopicosPorCursoYAnio(
            @RequestParam String nombreCurso,
            @RequestParam Integer anio,
            @PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicosPorCursoYAnio(nombreCurso, anio, paginacion));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(
            @RequestBody @Valid DatosActualizarTopico datos) {
        return ResponseEntity.ok(topicoService.actualizarTopico(datos));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}