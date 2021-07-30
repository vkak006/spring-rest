package ls.electric.demo.common.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.domain.Sample;
import ls.electric.demo.common.repository.SampleRepository;
import ls.electric.demo.common.service.dto.SampleResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SampleService {
    final SampleRepository sampleRepository;

    public Mono<SampleResponse> registerSample(String prefix){
        Mono<Sample> sampleMono = sampleRepository.save(Sample.newInstance(prefix));
        return sampleMono.flatMap(sample -> Mono.just(SampleResponse.of(sample)));
    }
}
