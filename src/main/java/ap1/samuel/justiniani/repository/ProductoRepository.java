package ap1.samuel.justiniani.repository;

import ap1.samuel.justiniani.model.Producto;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<Producto, Long> {
    Flux<Producto> findByStatus(Integer status);
}