package ap1.samuel.justiniani.repository;

import ap1.samuel.justiniani.model.TtsResult;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface TtsResultRepository extends ReactiveCrudRepository<TtsResult, Long> {

	Flux<TtsResult> findByStatus(Integer status);
}
