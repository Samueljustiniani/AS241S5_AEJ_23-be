package ap1.samuel.justiniani.service;

import ap1.samuel.justiniani.model.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoService {

    Flux<Producto> findAll();

    Flux<Producto> findAllActivos();

    Flux<Producto> findAllInactivos();

    Mono<Producto> findById(Long id);

    Mono<Producto> save(Producto producto);

    Mono<Producto> update(Long id, Producto producto);

    Mono<Producto> deleteLogico(Long id);

    Mono<Producto> restore(Long id);

}