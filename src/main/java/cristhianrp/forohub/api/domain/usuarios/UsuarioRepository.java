package cristhianrp.forohub.api.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByNombre(String nombre);

    UserDetails findByCorreoElectronico(String correoElectronico);
}
