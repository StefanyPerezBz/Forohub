package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.curso.Curso;
import com.forohub.forohub.domain.curso.CursoRepository;
import com.forohub.forohub.domain.topico.dto.DatosActualizarTopico;
import com.forohub.forohub.domain.topico.dto.DatosCrearTopico;
import com.forohub.forohub.domain.topico.dto.DatosListadoTopico;
import com.forohub.forohub.domain.topico.dto.DatosRespuestaTopico;
import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import com.forohub.forohub.infra.exceptions.ValidacionDeIntegridad;
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

    public DatosRespuestaTopico crearTopico(DatosCrearTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionDeIntegridad("Ya existe un tópico con el mismo título y mensaje");
        }

        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new ValidacionDeIntegridad("Usuario no encontrado"));

        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new ValidacionDeIntegridad("Curso no encontrado"));

        Topico topico = new Topico(datos.titulo(), datos.mensaje(), autor, curso);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(topico);
    }

    public Page<DatosListadoTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    public Page<DatosListadoTopico> listarTopicosPorCursoYAnio(String nombreCurso, Integer anio, Pageable paginacion) {
        return topicoRepository.findByCursoNombreAndAnio(nombreCurso, anio, paginacion)
                .map(DatosListadoTopico::new);
    }

    public DatosRespuestaTopico obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionDeIntegridad("Tópico no encontrado"));
        return new DatosRespuestaTopico(topico);
    }

    public DatosRespuestaTopico actualizarTopico(DatosActualizarTopico datos) {
        Topico topico = topicoRepository.findById(datos.id())
                .orElseThrow(() -> new ValidacionDeIntegridad("Tópico no encontrado"));

        topico.actualizarDatos(datos);
        return new DatosRespuestaTopico(topico);
    }

    public void eliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }
}