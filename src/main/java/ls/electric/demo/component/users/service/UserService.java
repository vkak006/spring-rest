package ls.electric.demo.component.users.service;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.component.users.domain.User;
import ls.electric.demo.component.users.repository.UserRepository;
import ls.electric.demo.component.users.service.dto.UserResponse;
import ls.electric.demo.config.security.PBKDF2Encoder;
import ls.electric.demo.config.utils.AesUtil;
import ls.electric.demo.config.constants.Role;
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
    private AesUtil aesUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    public Flux<UserResponse> findAll(){
        return userRepository.findAll()
                .flatMap(user -> Flux.just(UserResponse.of(user)));
    }

    public Mono<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Mono<UserResponse> createUsers(String email, String username, String password) throws Exception{
        return userRepository
                .save(User.newInstance(email, username, aesUtil.encrypt(password),true, Arrays.asList(Role.ROLE_USER)))
                .flatMap(user -> Mono.just(UserResponse.of(user)));
    }

    public Mono deleteUser(String id){
        return userRepository.deleteById(id);
    }

}
