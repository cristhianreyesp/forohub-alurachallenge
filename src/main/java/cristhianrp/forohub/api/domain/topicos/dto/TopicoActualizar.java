package cristhianrp.forohub.api.domain.topicos.dto;

import cristhianrp.forohub.api.domain.topicos.Estado;

public record TopicoActualizar(
        String titulo,
        String mensaje,
        Estado estado,
        Long autor,
        Long curso
) {
}
