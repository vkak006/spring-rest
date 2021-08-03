package ls.electric.demo.common.user.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.user.domain.User;
import ls.electric.demo.common.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    public Flux<User> retrieveUsers(){
        return userRepository.findAll();
    }

    public Mono<User> createUsers(String email, String password){
        return userRepository.save(User.newInstance(email, password));
    }

}
