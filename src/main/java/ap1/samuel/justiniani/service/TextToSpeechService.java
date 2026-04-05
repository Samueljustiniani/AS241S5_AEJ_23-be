package ap1.samuel.justiniani.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ap1.samuel.justiniani.dto.TextToSpeechRequest;
import ap1.samuel.justiniani.model.TtsResult;
import ap1.samuel.justiniani.repository.TtsResultRepository;
import java.util.HashMap;
import java.time.LocalDateTime;

@Service
public class TextToSpeechService {

    @Value("${ia.tts.url}")
    private String ttsUrl;

    @Value("${ia.tts.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder().build();
    private final TtsResultRepository ttsResultRepository;

    public TextToSpeechService(TtsResultRepository ttsResultRepository) {
        this.ttsResultRepository = ttsResultRepository;
    }

    public Mono<byte[]> textToSpeech(TextToSpeechRequest req) {
        HashMap<String, Object> body = new HashMap<>();
        body.put("model", req.getModel());
        body.put("input", req.getInput());
        body.put("instructions", req.getInstructions());
        body.put("voice", req.getVoice());
        return webClient.post()
                .uri(ttsUrl)
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", apiKey)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(byte[].class)
                .flatMap(audio -> {
                    TtsResult result = new TtsResult(req.getInput(), LocalDateTime.now(), audio);
                    return ttsResultRepository.save(result).thenReturn(audio);
                });
    }
}
