package ap1.samuel.justiniani.rest;

import ap1.samuel.justiniani.dto.ChatRequest;
import ap1.samuel.justiniani.dto.ChatResponse;
import ap1.samuel.justiniani.service.ChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Mono<ChatResponse> ask(@RequestBody ChatRequest req) {
        return chatService.ask(req);
    }
}
