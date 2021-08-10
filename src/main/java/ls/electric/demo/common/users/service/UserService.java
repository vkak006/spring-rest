package ls.electric.demo.common.users.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.users.domain.User;
import ls.electric.demo.common.users.repository.UserRepository;
import ls.electric.demo.common.users.service.dto.UserResponse;
import ls.electric.demo.config.security.role.Role;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    public Flux<UserResponse> retrieveUsers(){
        Flux<User> userFlux = userRepository.findAll();
        return userFlux.flatMap(user -> Flux.just(UserResponse.of(user)));
    }

    public Mono<UserResponse> createUsers(String email, String password){
        Mono<User> userMono = userRepository.save(User.newInstance(email, password,true, Arrays.asList(Role.USER)));
        return userMono.flatMap(user -> Mono.just(UserResponse.of(user)));
    }

    public Mono deleteUser(String id){
        return userRepository.deleteById(id);
    }

}
