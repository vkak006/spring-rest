package ls.electric.demo.sample.controller;

import ls.electric.demo.sample.service.SampleService;
import ls.electric.demo.sample.service.dto.SampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;


    @PostMapping
    public Mono<SampleResponse> insertSample(String title){
        return sampleService.registerSample(title);
    }
}
