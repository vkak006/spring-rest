package ls.electric.demo.common.repository;

import ls.electric.demo.common.domain.Sample;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SampleRepository extends ReactiveCrudRepository<Sample, String> {
}
