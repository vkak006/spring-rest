package ls.electric.demo.sample.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.sample.domain.Sample;
import ls.electric.demo.sample.repository.SampleRepository;
import ls.electric.demo.sample.service.dto.SampleResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SampleService {
    final SampleRepository sampleRepository;

    public Mono<SampleResponse> registerSample(String title){
        Mono<Sample> sampleMono = sampleRepository.save(Sample.newInstance(title));
        return sampleMono.flatMap(sample -> Mono.just(SampleResponse.of(sample)));
    }
}
