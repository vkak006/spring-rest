package ls.electric.demo.common.user.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.user.domain.User;
import ls.electric.demo.common.user.repository.UserRepository;
import ls.electric.demo.common.user.service.dto.UserResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    public Flux<UserResponse> retrieveUsers(){
        Flux<User> userFlux = userRepository.findAll();
        return userFlux.flatMap(user -> Flux.just(UserResponse.of(user)));
    }

    public Mono<UserResponse> createUsers(String email, String password){
        Mono<User> userMono = userRepository.save(User.newInstance(email, password));
        return userMono.flatMap(user -> Mono.just(UserResponse.of(user)));
    }

}
