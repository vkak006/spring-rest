package ls.electric.demo.sample;

import ls.electric.demo.sample.domain.Sample;
import ls.electric.demo.sample.repository.SampleRepository;
import ls.electric.demo.sample.service.SampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebFluxTest()
public class SampleControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleRepository sampleRepository;

    @Test
    public void registerSample(){
        String prefix = "junit4 test";

        //when
        sampleService.registerSample(prefix).subscribe();

        //then
        Flux<Sample> sampleFlux = sampleRepository.findAll();
        StepVerifier.create(sampleFlux)
                .assertNext(sample -> assertThat(sample.getTitle()).isEqualTo(prefix))
                .verifyComplete();
    }

}
