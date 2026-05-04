package ap1.samuel.justiniani.rest;

import ap1.samuel.justiniani.service.TextToSpeechService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ap1.samuel.justiniani.dto.TextToSpeechRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tts")
public class TextToSpeechController {

    private final TextToSpeechService ttsService;

    public TextToSpeechController(TextToSpeechService ttsService) {
        this.ttsService = ttsService;
    }

        @PostMapping
        public Mono<ResponseEntity<byte[]>> generarVoz(@RequestBody TextToSpeechRequest req) {
        return ttsService.textToSpeech(req)
            .map(audio -> ResponseEntity
                .ok()
                .header("Content-Type", "audio/mpeg")
                .body(audio));
        }
}
