package cristhianrp.forohub.api.domain.topicos.dto;

import cristhianrp.forohub.api.domain.curso.Curso;
import cristhianrp.forohub.api.domain.topicos.Estado;
import cristhianrp.forohub.api.domain.topicos.Topico;

import java.time.LocalDateTime;

public record TopicoMostar(
        Long id,

        String titulo,

        String mensaje,

        LocalDateTime fechaCreacion,

        Estado status,

        TopicoYUsuario autor,

        Curso curso
) {
    public TopicoMostar(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                new TopicoYUsuario(topico.getAutor().getId(), topico.getAutor().getNombre(), topico.getAutor().getCorreoElectronico(),
                        topico.getAutor().getPerfil()),topico.getCurso());
    }
}
