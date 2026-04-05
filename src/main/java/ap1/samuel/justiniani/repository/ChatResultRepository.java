package ap1.samuel.justiniani.repository;

import ap1.samuel.justiniani.model.ChatResult;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChatResultRepository extends ReactiveCrudRepository<ChatResult, Long> {
}
