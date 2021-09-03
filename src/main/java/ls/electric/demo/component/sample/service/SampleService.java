package ls.electric.demo.component.sample.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.component.sample.domain.Sample;
import ls.electric.demo.component.sample.repository.SampleRepository;
import ls.electric.demo.component.sample.service.dto.SampleResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SampleService {
    final SampleRepository sampleRepository;

    @Transactional
    public Mono<SampleResponse> registerSample(String title){
        Mono<Sample> sampleMono = sampleRepository.save(Sample.newInstance(title));
        return sampleMono.flatMap(sample -> Mono.just(SampleResponse.of(sample)));
    }

    @Transactional(readOnly = true)
    public Flux<SampleResponse> retrieveSamples(){
        return sampleRepository.findAll()
                .flatMap(sample -> Flux.just(SampleResponse.of(sample)));
    }
}
