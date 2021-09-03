package ls.electric.demo.component.file.repository;

import ls.electric.demo.component.file.domain.Image;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FileRepository extends ReactiveCrudRepository<Image,String> {
    Mono<Void> deleteByTempFileName(String fileName);
    Mono<Image> findByTempFileName(String fileName);
}
