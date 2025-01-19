package cristhianrp.forohub.api.domain.topicos.dto;

import cristhianrp.forohub.api.domain.perfil.Perfil;
import cristhianrp.forohub.api.domain.usuarios.Usuario;

public record TopicoYUsuario (
    Long id,
    String nombre,
    String correoElectronico,
    Perfil perfil
) {
    public TopicoYUsuario(Usuario datos){
            this(datos.getId(), datos.getNombre(), datos.getCorreoElectronico(), datos.getPerfil());
        }
}
