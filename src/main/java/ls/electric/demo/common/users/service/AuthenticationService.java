package ls.electric.demo.common.users.service;

import ls.electric.demo.common.users.domain.User;
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

        data.put("user", User.newInstance("user","JQ7sS6oFMxpDg/WvmH3eX7KU/yAXR6phq44jUoFU69s=", true, Arrays.asList(Role.USER)));
        data.put("admin", User.newInstance("admin", "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ADMIN)));
    }

    public Mono<User> findByUsername(String username){
        return Mono.justOrEmpty(data.get(username));
    }
}
