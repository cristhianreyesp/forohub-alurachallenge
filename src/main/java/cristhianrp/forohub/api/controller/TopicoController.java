package cristhianrp.forohub.api.controller;

import cristhianrp.forohub.api.domain.topicos.*;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoCreacion;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoMostar;
import cristhianrp.forohub.api.infra.errores.ValidacionException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TopicoService topicoService;


    @PostMapping
    @Transactional
    public ResponseEntity crearTopico(@RequestBody @Valid TopicoCreacion creacionTopico,
                                      UriComponentsBuilder uriComponentsBuilder){

        try {
            var topicoNuevo = topicoService.registrar(creacionTopico);
            return ResponseEntity.ok(topicoNuevo);
        }catch(ValidacionException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // Nuevo método para listar todos los tópicos
    @GetMapping
    public ResponseEntity<Page<TopicoMostar>> listarTopicos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(TopicoMostar::new));
    }
}
