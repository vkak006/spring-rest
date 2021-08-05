package ls.electric.demo.common.users.controller;

import ls.electric.demo.common.users.service.AuthenticationService;
import ls.electric.demo.common.users.service.dto.AuthRequest;
import ls.electric.demo.common.users.service.dto.AuthResponse;
import ls.electric.demo.config.security.PBKDF2Encoder;
import ls.electric.demo.config.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class AuthenticationController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/login")
    public Mono<ResponseEntity<AuthResponse>> login(AuthRequest authRequest){
        return authenticationService.findByUsername(authRequest.getUsername())
                .filter(userDetails -> passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
