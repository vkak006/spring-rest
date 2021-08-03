package ls.electric.demo.common.user.repository;

import ls.electric.demo.common.user.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<User,String> {
}
