package ls.electric.demo.component.sample.repository;

import ls.electric.demo.component.sample.domain.Sample;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SampleRepository extends ReactiveCrudRepository<Sample, String>{
    @Aggregation({
            "{ $match : {title : 'testMessage'}}"
    })
    Flux<Sample> getSampleData();
}
