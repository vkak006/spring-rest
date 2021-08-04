package ls.electric.demo.common.login.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.login.domain.User;
import ls.electric.demo.common.login.repository.LoginRepository;
import ls.electric.demo.common.login.service.dto.UserResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LoginService {
    final LoginRepository loginRepository;

    public Flux<UserResponse> retrieveUsers(){
        Flux<User> userFlux = loginRepository.findAll();
        return userFlux.flatMap(user -> Flux.just(UserResponse.of(user)));
    }

    public Mono<UserResponse> createUsers(String email, String password){
        Mono<User> userMono = loginRepository.save(User.newInstance(email, password));
        return userMono.flatMap(user -> Mono.just(UserResponse.of(user)));
    }

    public Mono deleteUser(String id){
        return loginRepository.deleteById(id);
    }

}
