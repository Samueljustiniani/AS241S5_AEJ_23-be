package ap1.samuel.justiniani.service;

import ap1.samuel.justiniani.dto.ChatMessage;
import ap1.samuel.justiniani.dto.ChatRequest;
import ap1.samuel.justiniani.dto.ChatResponse;
import ap1.samuel.justiniani.dto.ConsultaRequest;
import ap1.samuel.justiniani.model.Consulta;
import ap1.samuel.justiniani.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Value("${ia.llama.url}")
    private String llamaUrl;

    @Value("${ia.llama.key}")
    private String apiKey;

    private final WebClient webClient = WebClient.builder().build();
    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Flux<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    public Flux<Consulta> findAllActivas() {
        return consultaRepository.findByStatus(1);
    }

    public Flux<Consulta> findAllInactivas() {
        return consultaRepository.findByStatus(0);
    }

    public Mono<Consulta> findById(Long id) {
        return consultaRepository.findById(id);
    }

    public Mono<Consulta> create(ConsultaRequest request) {
        return askAi(request.getPregunta())
                .flatMap(respuesta -> {
                    Consulta consulta = new Consulta();
                    consulta.setPregunta(request.getPregunta());
                    consulta.setRespuesta(respuesta);
                    consulta.setFechaRegistro(LocalDateTime.now());
                    consulta.setStatus(1);
                    return consultaRepository.save(consulta);
                });
    }

    public Mono<Consulta> update(Long id, ConsultaRequest request) {
        return consultaRepository.findById(id)
                .flatMap(existing -> askAi(request.getPregunta())
                        .flatMap(respuesta -> {
                            existing.setPregunta(request.getPregunta());
                            existing.setRespuesta(respuesta);
                            existing.setFechaRegistro(LocalDateTime.now());
                            if (existing.getStatus() == null) {
                                existing.setStatus(1);
                            }
                            return consultaRepository.save(existing);
                        }));
    }

    public Mono<Consulta> deleteLogico(Long id) {
        return consultaRepository.findById(id)
                .flatMap(consulta -> {
                    consulta.setStatus(0);
                    return consultaRepository.save(consulta);
                });
    }

    public Mono<Consulta> restore(Long id) {
        return consultaRepository.findById(id)
                .flatMap(consulta -> {
                    consulta.setStatus(1);
                    return consultaRepository.save(consulta);
                });
    }

    private Mono<String> askAi(String pregunta) {
        ChatRequest chatRequest = new ChatRequest(List.of(new ChatMessage("user", pregunta)));
        return webClient.post()
                .uri(llamaUrl)
                .header("content-type", "application/json")
                .header("X-RapidAPI-Key", apiKey)
                .header("X-RapidAPI-Host", "open-ai21.p.rapidapi.com")
                .bodyValue(chatRequest)
                .retrieve()
                .bodyToMono(ChatResponse.class)
                .map(ChatResponse::getResult);
    }
}