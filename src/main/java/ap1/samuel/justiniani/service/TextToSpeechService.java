package ap1.samuel.justiniani.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ap1.samuel.justiniani.dto.TextToSpeechRequest;
import ap1.samuel.justiniani.dto.TtsHistoryItem;
import ap1.samuel.justiniani.model.TtsResult;
import ap1.samuel.justiniani.repository.TtsResultRepository;
import java.util.HashMap;
import reactor.core.publisher.Flux;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

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

    public Flux<TtsHistoryItem> findAll() {
        return ttsResultRepository.findAll().map(this::toHistoryItem);
    }

    public Flux<TtsHistoryItem> findAllActivas() {
        return ttsResultRepository.findByStatus(1).map(this::toHistoryItem);
    }

    public Flux<TtsHistoryItem> findAllInactivas() {
        return ttsResultRepository.findByStatus(0).map(this::toHistoryItem);
    }

    public Mono<TtsHistoryItem> findById(Long id) {
        return ttsResultRepository.findById(id).map(this::toHistoryItem);
    }

    public Mono<ResponseEntity<byte[]>> findAudioById(Long id) {
        return ttsResultRepository.findById(id)
                .map(result -> ResponseEntity.ok()
                        .contentType(MediaType.valueOf("audio/mpeg"))
                .body(result.getAudio()))
            .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    public Mono<TtsHistoryItem> deleteLogico(Long id) {
        return ttsResultRepository.findById(id)
                .flatMap(result -> {
                    result.setStatus(0);
                    return ttsResultRepository.save(result);
                })
                .map(this::toHistoryItem);
    }

    public Mono<TtsHistoryItem> restore(Long id) {
        return ttsResultRepository.findById(id)
                .flatMap(result -> {
                    result.setStatus(1);
                    return ttsResultRepository.save(result);
                })
                .map(this::toHistoryItem);
    }

    private TtsHistoryItem toHistoryItem(TtsResult result) {
        return new TtsHistoryItem(result.getId(), result.getInputText(), result.getCreatedAt(), result.getStatus());
    }
}
