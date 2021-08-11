package ls.electric.demo.common.users.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.users.domain.User;
import ls.electric.demo.common.users.repository.UserRepository;
import ls.electric.demo.common.users.service.dto.UserResponse;
import ls.electric.demo.config.security.PBKDF2Encoder;
import ls.electric.demo.config.security.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class UserService {
    final UserRepository userRepository;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    public Flux<UserResponse> findAll(){
        return userRepository.findAll()
                .flatMap(user -> Flux.just(UserResponse.of(user)));
    }

    public Mono<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Mono<UserResponse> createUsers(String email, String username, String password){
        return userRepository
                .save(User.newInstance(email, username, passwordEncoder.encode(password),true, Arrays.asList(Role.ROLE_USER)))
                .flatMap(user -> Mono.just(UserResponse.of(user)));
    }

    public Mono deleteUser(String id){
        return userRepository.deleteById(id);
    }

}
