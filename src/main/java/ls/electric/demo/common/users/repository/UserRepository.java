package ls.electric.demo.common.users.repository;

import ls.electric.demo.common.users.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User,String> {
    Mono<User> findByEmail(String email);
}
