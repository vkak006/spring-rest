package ls.electric.demo.sample.repository;

import ls.electric.demo.sample.domain.Sample;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SampleRepository extends ReactiveCrudRepository<Sample, String>{
}
