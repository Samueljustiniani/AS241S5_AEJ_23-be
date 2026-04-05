package ap1.samuel.justiniani.service;

import ap1.samuel.justiniani.dto.ChatRequest;
import ap1.samuel.justiniani.dto.ChatResponse;
import ap1.samuel.justiniani.model.ChatResult;
import ap1.samuel.justiniani.repository.ChatResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
public class ChatService {
    @Value("${ia.llama.url}")
    private String llamaUrl;
    @Value("${ia.llama.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder().build();
    private final ChatResultRepository chatResultRepository;

    public ChatService(ChatResultRepository chatResultRepository) {
        this.chatResultRepository = chatResultRepository;
    }

    public Mono<ChatResponse> ask(ChatRequest req) {
        return webClient.post()
                .uri(llamaUrl)
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "open-ai21.p.rapidapi.com")
                .bodyValue(req)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .flatMap(resp -> {
                    String question = req.getMessages().get(req.getMessages().size() - 1).getContent();
                    ChatResult result = new ChatResult(question, resp.getResult(), LocalDateTime.now());
                    return chatResultRepository.save(result).thenReturn(resp);
                });
    }
}
