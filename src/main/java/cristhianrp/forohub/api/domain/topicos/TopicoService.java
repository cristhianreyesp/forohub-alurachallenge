package cristhianrp.forohub.api.domain.topicos;

import cristhianrp.forohub.api.domain.curso.Curso;
import cristhianrp.forohub.api.domain.curso.CursoRepository;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoActualizar;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoCreacion;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoMostar;
import cristhianrp.forohub.api.domain.usuarios.Usuario;
import cristhianrp.forohub.api.domain.usuarios.UsuarioRepository;
import cristhianrp.forohub.api.infra.errores.ValidacionException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoMostar registrar(TopicoCreacion datosCreacionTopico){
        boolean existeDuplicado = topicoRepository.existsByTitulo(datosCreacionTopico.titulo());
        if(existeDuplicado){
            throw new IllegalArgumentException("Ya existe un tópico con el mismo titulo");
        }

        Usuario autor = usuarioRepository.findById(datosCreacionTopico.idUsuario())
                .orElseThrow(() -> new ValidacionException("Autor No encontrado"));

        Curso curso = cursoRepository.findById(datosCreacionTopico.idCurso())
                .orElseThrow(() -> new ValidacionException("Curso no encontrado"));

        Topico topico = new Topico(datosCreacionTopico, autor, curso);
        topicoRepository.save(topico);

        return new TopicoMostar(topico);
    }

    @Transactional
    public TopicoMostar actualizar(Long id, TopicoActualizar datos){

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró un tópico con el id proporcionado"));
        if(datos.titulo() != null){
            topico.setTitulo(datos.titulo());
        }
        if(datos.mensaje() != null){
            topico.setMensaje(datos.mensaje());
        }
        if(datos.estado() != null){
            topico.setStatus(datos.estado());
        }

        if(datos.autor() != null){
            var autor = usuarioRepository.findById(datos.autor()).orElseThrow(()-> new RuntimeException("Autor no encontrado"));
            topico.setAutor(autor);
        }
        if(datos.curso() != null){
            var curso = cursoRepository.findById(datos.curso()).orElseThrow(
                    () -> new RuntimeException("Curso no encontrado")
            );
            topico.setCurso(curso);
        }

        return new TopicoMostar(topico);

    }
}
