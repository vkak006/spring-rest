package ls.electric.demo.component.sample.repository;

import ls.electric.demo.component.sample.domain.Sample;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SampleRepository extends ReactiveCrudRepository<Sample, String>{
}
