package ls.electric.demo.common.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.domain.Sample;
import ls.electric.demo.common.repository.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class SampleService {
    private SampleRepository sampleRepository;

    @Transactional
    public Mono<Sample> registerSample(String prefix, String name){
        Mono<Sample> sampleMono = sampleRepository.save(Sample.newInstance(prefix,name));
        return sampleMono;
    }
}
