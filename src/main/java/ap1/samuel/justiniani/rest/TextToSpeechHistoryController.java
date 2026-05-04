package ap1.samuel.justiniani.rest;

import ap1.samuel.justiniani.dto.TtsHistoryItem;
import ap1.samuel.justiniani.service.TextToSpeechService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tts/history")
public class TextToSpeechHistoryController {

    private final TextToSpeechService ttsService;

    public TextToSpeechHistoryController(TextToSpeechService ttsService) {
        this.ttsService = ttsService;
    }

    @GetMapping
    public Flux<TtsHistoryItem> findAll() {
        return ttsService.findAll();
    }

    @GetMapping("/activas")
    public Flux<TtsHistoryItem> findAllActivas() {
        return ttsService.findAllActivas();
    }

    @GetMapping("/inactivas")
    public Flux<TtsHistoryItem> findAllInactivas() {
        return ttsService.findAllInactivas();
    }

    @GetMapping("/{id}")
    public Mono<TtsHistoryItem> findById(@PathVariable Long id) {
        return ttsService.findById(id);
    }

    @GetMapping("/{id}/audio")
    public Mono<ResponseEntity<byte[]>> findAudioById(@PathVariable Long id) {
        return ttsService.findAudioById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<TtsHistoryItem> deleteLogico(@PathVariable Long id) {
        return ttsService.deleteLogico(id);
    }

    @PutMapping("/restore/{id}")
    public Mono<TtsHistoryItem> restore(@PathVariable Long id) {
        return ttsService.restore(id);
    }
}