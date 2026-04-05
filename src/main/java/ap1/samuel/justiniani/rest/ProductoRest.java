package ap1.samuel.justiniani.rest;

import ap1.samuel.justiniani.model.Producto;
import ap1.samuel.justiniani.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/producto")
public class ProductoRest {

    private final ProductoService productoService;

    @Autowired
    public ProductoRest(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public Flux<Producto> findAll() {
        return productoService.findAll();
    }

    @GetMapping("/activos")
    public Flux<Producto> findAllActivos() {
        return productoService.findAllActivos();
    }

    @GetMapping("/inactivos")
    public Flux<Producto> findAllInactivos() {
        return productoService.findAllInactivos();
    }

    @GetMapping("/{id}")
    public Mono<Producto> findById(@PathVariable Long id) {
        return productoService.findById(id);
    }

    @PostMapping("/save")
    public Mono<Producto> save(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PutMapping("/{id}")
    public Mono<Producto> update(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.update(id, producto);
    }

    @PutMapping("/delete/{id}")
    public Mono<Producto> deleteLogico(@PathVariable Long id) {
        return productoService.deleteLogico(id);
    }

    @PutMapping("/restore/{id}")
    public Mono<Producto> restore(@PathVariable Long id) {
        return productoService.restore(id);
    }

}