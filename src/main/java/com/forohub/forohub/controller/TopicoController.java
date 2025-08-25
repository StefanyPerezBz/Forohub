package com.forohub.forohub.controller;

import com.forohub.forohub.dto.ActualizarTopicoDTO;
import com.forohub.forohub.dto.CrearTopicoDTO;
import com.forohub.forohub.dto.ListadoTopicoDTO;
import com.forohub.forohub.dto.RespuestaTopicoDTO;
import com.forohub.forohub.service.TopicoService;

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
    public ResponseEntity<RespuestaTopicoDTO> crearTopico(
            @RequestBody @Valid CrearTopicoDTO datos,
            UriComponentsBuilder uriComponentsBuilder) {
        RespuestaTopicoDTO respuesta = topicoService.crearTopico(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopicoDTO>> listarTopicos(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoDTO> obtenerTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtenerTopicoPorId(id));
    }

    @GetMapping("/curso")
    public ResponseEntity<Page<ListadoTopicoDTO>> listarTopicosPorCursoYAnio(
            @RequestParam String nombreCurso,
            @RequestParam Integer anio,
            @PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicosPorCursoYAnio(nombreCurso, anio, paginacion));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizarTopico(
            @RequestBody @Valid ActualizarTopicoDTO datos) {
        return ResponseEntity.ok(topicoService.actualizarTopico(datos));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}