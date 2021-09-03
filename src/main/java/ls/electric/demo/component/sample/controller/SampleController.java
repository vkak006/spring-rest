package ls.electric.demo.component.sample.controller;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.component.users.domain.Message;
import ls.electric.demo.component.sample.service.SampleService;
import ls.electric.demo.component.sample.service.dto.SampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private WebClient webClient;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Mono<SampleResponse> registerSample(String title){
        return sampleService.registerSample(title);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Flux<SampleResponse> retrieveSamples() {
        return sampleService.retrieveSamples();
    }

    @GetMapping("/security/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<Message>> user(){
        return Mono.just(ResponseEntity.ok(new Message("Content for user")));
    }

    @GetMapping("/security/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<Message>> admin(){
        return Mono.just(ResponseEntity.ok(new Message("Content for admin")));
    }

    @GetMapping("/webClient/test")
    public Mono<String> test(String fileName){
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/file/{fileName}").build(fileName))
                .retrieve()
                .bodyToMono(String.class);
    }
}
