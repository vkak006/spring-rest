package ls.electric.demo.common.login.repository;

import ls.electric.demo.common.login.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoginRepository extends ReactiveCrudRepository<User,String> {
}
