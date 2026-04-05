package ap1.samuel.justiniani.service.impl;

import ap1.samuel.justiniani.model.Producto;
import ap1.samuel.justiniani.repository.ProductoRepository;
import ap1.samuel.justiniani.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Flux<Producto> findAll() {
        log.info("Mostrando productos");
        return productoRepository.findAll();
    }

    @Override
    public Flux<Producto> findAllActivos() {
        log.info("Listando productos activos");
        return productoRepository.findByStatus(1);
    }

    @Override
    public Flux<Producto> findAllInactivos() {
        log.info("Listando productos inactivos");
        return productoRepository.findByStatus(0);
    }

    @Override
    public Mono<Producto> findById(Long id) {
        log.info("Mostrando producto por ID");
        return productoRepository.findById(id);
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        log.info("Registrando producto " + producto.toString());
        if (producto.getStatus() == null) producto.setStatus(1); // Activo por defecto
        return productoRepository.save(producto);
    }

    @Override
    public Mono<Producto> update(Long id, Producto producto) {
        log.info("Actualizando producto id=" + id);
        return productoRepository.findById(id)
                .flatMap(existing -> {
                    // Copiar los campos editables
                    existing.setNombre(producto.getNombre());
                    existing.setDescripcion(producto.getDescripcion());
                    existing.setPrecio(producto.getPrecio());
                    existing.setStock(producto.getStock());
                    existing.setCategoria(producto.getCategoria());
                    existing.setMarca(producto.getMarca());
                    existing.setCodigo(producto.getCodigo());
                    existing.setFechaRegistro(producto.getFechaRegistro());
                    // Solo actualiza status si no es null
                    if (producto.getStatus() != null) {
                        existing.setStatus(producto.getStatus());
                    }
                    return productoRepository.save(existing);
                });
    }

    @Override
    public Mono<Producto> deleteLogico(Long id) {
        log.info("Eliminando lógicamente producto id=" + id);
        return productoRepository.findById(id)
                .flatMap(producto -> {
                    producto.setStatus(0);
                    return productoRepository.save(producto);
                });
    }

    @Override
    public Mono<Producto> restore(Long id) {
        log.info("Restaurando producto id=" + id);
        return productoRepository.findById(id)
                .flatMap(producto -> {
                    producto.setStatus(1);
                    return productoRepository.save(producto);
                });
    }

}