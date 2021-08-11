package ls.electric.demo.common.users.controller;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.common.users.domain.User;
import ls.electric.demo.common.users.service.AuthenticationService;
import ls.electric.demo.common.users.service.UserService;
import ls.electric.demo.common.users.service.dto.AuthRequest;
import ls.electric.demo.common.users.service.dto.AuthResponse;
import ls.electric.demo.config.security.PBKDF2Encoder;
import ls.electric.demo.config.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@Slf4j
public class AuthenticationController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest){

        return userService.findByEmail(authRequest.getEmail())
                .filter(userDetails -> passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
