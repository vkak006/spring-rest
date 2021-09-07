package ls.electric.demo.component.users.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.component.users.service.AuthenticationService;
import ls.electric.demo.component.users.service.UserService;
import ls.electric.demo.component.users.service.dto.AuthRequest;
import ls.electric.demo.component.users.service.dto.AuthResponse;
import ls.electric.demo.config.security.PBKDF2Encoder;
import ls.electric.demo.config.security.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
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

    /**
     * RefreshToken 발급
     * @param id
     * @return AuthResponse
     */
    @PostMapping("/token/refresh")
    public Mono<ResponseEntity<AuthResponse>> refreshToken(String id){
        return null;
    }

    /**
     * 유효 Token 요청
     * @param authRequest
     * @return AuthResponse
     */
    @GetMapping("/token")
    public Mono<ResponseEntity<AuthResponse>> getToken(@RequestBody AuthRequest authRequest){
        return userService.findByEmail(authRequest.getEmail())
                .filter(userDetails -> passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

    /**
     * 로그인
     * @param authRequest
     * @return AuthResponse
     */
    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest){
        return userService.findByEmail(authRequest.getEmail())
                .filter(userDetails -> passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

//    @PreAuthorize("hasRole('USER')")
//    @ApiResponses({ @ApiResponse(code = 204, message = "success")})
//    @GetMapping("/logout")
//    public Mono<String> logout(ServerHttpRequest request){
//        return authenticationService.logout(request);
//    }
}
