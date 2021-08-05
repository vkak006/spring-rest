package ls.electric.demo.config.security;

import ls.electric.demo.common.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

public class AuthenticationManager implements ReactiveAuthenticationManager {
    @Autowired
    private LoginService loginService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return null;
    }
}
