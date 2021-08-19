package ls.electric.demo.common.file.repository;

import ls.electric.demo.common.file.domain.Image;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface FileRepository extends ReactiveCrudRepository<Image,String> {
    Mono<Void> deleteByOrigFileName(String fileName);
}
