package ls.electric.demo.component.users.service;

import ls.electric.demo.component.users.domain.User;
import ls.electric.demo.config.security.role.Role;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    //test data
    private Map<String, User> data;

    @PostConstruct
    public void init(){
        data = new HashMap<>();

        data.put("user", User.newInstance("user","user@gmail.com","cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Arrays.asList(Role.ROLE_USER)));
        data.put("admin", User.newInstance("admin","admin@gmail.com", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ROLE_ADMIN)));
    }

    public Mono<User> findByUsername(String username){
        return Mono.justOrEmpty(data.get(username));
    }
}
