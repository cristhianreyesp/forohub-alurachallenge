package cristhianrp.forohub.api.domain.topicos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoCreacion(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,

        @NotNull
        Long idUsuario,

        @NotNull
        Long idCurso
) {
}
