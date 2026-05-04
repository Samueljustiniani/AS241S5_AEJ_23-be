package ap1.samuel.justiniani.repository;

import ap1.samuel.justiniani.model.Consulta;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ConsultaRepository extends ReactiveCrudRepository<Consulta, Long> {

    Flux<Consulta> findByStatus(Integer status);
}