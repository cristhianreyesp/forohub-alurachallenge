package cristhianrp.forohub.api.domain.topicos;

import cristhianrp.forohub.api.domain.curso.Curso;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoCreacion;
import cristhianrp.forohub.api.domain.usuarios.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private Estado status;
    @ManyToOne // Relación muchos a uno con Usuario
    @JoinColumn(name = "autor_id", nullable = false) // Nombre de la columna para la clave foránea
    private Usuario autor;

    @ManyToOne // Relación muchos a uno con Curso
    @JoinColumn(name = "curso_id", nullable = false) // Nombre de la columna para la clave foránea
    private Curso curso;


    public Topico(TopicoCreacion crearTopico, Usuario autor, Curso curso) {
        this.titulo = crearTopico.titulo();
        this.mensaje = crearTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Estado.ACTIVO ;
        this.autor = autor;
        this.curso = curso;
    }
}
