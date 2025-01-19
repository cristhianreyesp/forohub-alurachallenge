package cristhianrp.forohub.api.controller;

import cristhianrp.forohub.api.domain.topicos.*;
import cristhianrp.forohub.api.domain.topicos.dto.TopicoActualizar;
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

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizar(@RequestBody @Valid TopicoActualizar datos , @PathVariable Long id){

        try{
            var detalleTopico = topicoService.actualizar(id, datos);
            return ResponseEntity.ok(detalleTopico);
        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity mostarTopicoPorId(@PathVariable Long id){

        var topicoOptional = topicoRepository.findById(id);

        if(topicoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay topico con es ID:" + id);
        }
        var topico = topicoOptional.get();
        return ResponseEntity.ok(new TopicoMostar(topico));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){

        var topico = topicoRepository.findById(id);
        if(topico.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró ningún tópico con id: " + id);
        }

        topicoRepository.delete(topico.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Tópico eliminado correctamente");
    }



}
