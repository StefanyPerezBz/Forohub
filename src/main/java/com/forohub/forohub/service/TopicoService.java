package com.forohub.forohub.service;

import com.forohub.forohub.dto.ActualizarTopicoDTO;
import com.forohub.forohub.dto.CrearTopicoDTO;
import com.forohub.forohub.dto.ListadoTopicoDTO;
import com.forohub.forohub.dto.RespuestaTopicoDTO;
import com.forohub.forohub.exceptions.IntegrityValidation;
import com.forohub.forohub.model.Curso;
import com.forohub.forohub.model.Topico;
import com.forohub.forohub.model.Usuario;
import com.forohub.forohub.repository.CursoRepository;
import com.forohub.forohub.repository.TopicoRepository;
import com.forohub.forohub.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public RespuestaTopicoDTO crearTopico(CrearTopicoDTO datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IntegrityValidation("Ya existe un tópico con el mismo título y mensaje");
        }

        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new IntegrityValidation("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new IntegrityValidation("Curso no encontrado"));

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);
        topicoRepository.save(topico);

        return new RespuestaTopicoDTO(topico);
    }

    public Page<ListadoTopicoDTO> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(ListadoTopicoDTO::new);
    }

    public Page<ListadoTopicoDTO> listarTopicosPorCursoYAnio(String nombreCurso, Integer anio, Pageable paginacion) {
        return topicoRepository.findByCursoNombreAndAnio(nombreCurso, anio, paginacion)
                .map(ListadoTopicoDTO::new);
    }

    public RespuestaTopicoDTO obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new IntegrityValidation("Tópico no encontrado"));
        return new RespuestaTopicoDTO(topico);
    }

    public RespuestaTopicoDTO actualizarTopico(ActualizarTopicoDTO datos) {
        Topico topico = topicoRepository.findById(datos.id())
                .orElseThrow(() -> new IntegrityValidation("Tópico no encontrado"));

        topico.actualizarDatos(datos);
        return new RespuestaTopicoDTO(topico);
    }

    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new IntegrityValidation("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}