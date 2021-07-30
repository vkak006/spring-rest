package ls.electric.demo.common.controller;

import ls.electric.demo.common.service.SampleService;
import ls.electric.demo.common.service.dto.SampleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;


    @GetMapping("/web-flux")
    public Mono<SampleResponse> test2(String prefix){
        return sampleService.registerSample(prefix);
    }
}
