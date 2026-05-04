package ap1.samuel.justiniani.rest;

import ap1.samuel.justiniani.dto.ConsultaRequest;
import ap1.samuel.justiniani.model.Consulta;
import ap1.samuel.justiniani.service.ConsultaService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @GetMapping
    public Flux<Consulta> findAll() {
        return consultaService.findAll();
    }

    @GetMapping("/activas")
    public Flux<Consulta> findAllActivas() {
        return consultaService.findAllActivas();
    }

    @GetMapping("/inactivas")
    public Flux<Consulta> findAllInactivas() {
        return consultaService.findAllInactivas();
    }

    @GetMapping("/{id}")
    public Mono<Consulta> findById(@PathVariable Long id) {
        return consultaService.findById(id);
    }

    @PostMapping
    public Mono<Consulta> create(@RequestBody ConsultaRequest request) {
        return consultaService.create(request);
    }

    @PutMapping("/{id}")
    public Mono<Consulta> update(@PathVariable Long id, @RequestBody ConsultaRequest request) {
        return consultaService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public Mono<Consulta> deleteLogico(@PathVariable Long id) {
        return consultaService.deleteLogico(id);
    }

    @PutMapping("/restore/{id}")
    public Mono<Consulta> restore(@PathVariable Long id) {
        return consultaService.restore(id);
    }
}