package ls.electric.demo.sample;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.sample.controller.SampleController;
import ls.electric.demo.sample.domain.Sample;
import ls.electric.demo.sample.repository.SampleRepository;
import ls.electric.demo.sample.service.SampleService;
import ls.electric.demo.sample.service.dto.SampleResponse;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleControllerTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleRepository sampleRepository;

    @AfterEach
    void cleanUp(){
        sampleRepository.deleteAll().subscribe();
    }

    @Test
    public void registerSample(){
        String title = "junit4 test";

        //when
        sampleService.registerSample(title).subscribe();

        //then
        Flux<Sample> sampleFlux = sampleRepository.findAll();
        StepVerifier.create(sampleFlux)
                .assertNext(sample -> assertThat(sample.getTitle()).isEqualTo(title))
                .verifyComplete();
    }

    @Test
    public void retrieveSamples(){
        //data
        String title1 = "Junit-test-01";
        Sample sample1 = Sample.newInstance(title1);

        sampleRepository.save(sample1).subscribe();

        //when
        Flux<SampleResponse> sampleResponseFlux = sampleService.retrieveSamples();

        //then
        StepVerifier.create(sampleResponseFlux)
                .assertNext(sample -> assertSampleResponse(sample, sample1.getId(), title1))
                .verifyComplete();
    }

    private void assertSampleResponse(SampleResponse sampleResponse, String id, String title){
        assertThat(sampleResponse.getId()).isEqualTo(id);
        assertThat(sampleResponse.getTitle()).isEqualTo(title);
    }

}
