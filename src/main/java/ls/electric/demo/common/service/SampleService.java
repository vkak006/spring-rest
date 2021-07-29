package ls.electric.demo.common.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.domain.Sample;
import ls.electric.demo.common.repository.SampleRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class SampleService {
    SampleRepository sampleRepository;

    public Mono<Sample> registerSample(String prefix, String name){
        Mono<Sample> sampleMono = sampleRepository.save(Sample.newInstance(prefix,name));
        return sampleMono;
    }
}
