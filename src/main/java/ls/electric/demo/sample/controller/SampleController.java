package ls.electric.demo.sample.controller;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.common.users.domain.Message;
import ls.electric.demo.sample.service.SampleService;
import ls.electric.demo.sample.service.dto.SampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    @Autowired
    private SampleService sampleService;

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
}
