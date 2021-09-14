package ls.electric.demo.component.users.controller;

import lombok.extern.slf4j.Slf4j;
import ls.electric.demo.component.users.service.AuthenticationService;
import ls.electric.demo.component.users.service.UserService;
import ls.electric.demo.component.users.service.dto.AuthRequest;
import ls.electric.demo.component.users.service.dto.AuthResponse;
import ls.electric.demo.config.security.PBKDF2Encoder;
import ls.electric.demo.config.utils.AesUtil;
import ls.electric.demo.config.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AesUtil aesUtil;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    //AES-128 방식을 사용하는 경우, 삭제필요.
    @Autowired
    private PBKDF2Encoder passwordEncoder;

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
     * 로그인 (Base64 -> AES-128)
     * @param authRequest
     * @return AuthResponse
     */
    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest){
        return userService.findByEmail(authRequest.getEmail())
                .filter(userDetails -> {
                    try {
                        return authRequest.getPassword().equals(aesUtil.decrypt(userDetails.getPassword()));
                    } catch (Exception e) {
                        log.error("AES-128 decrypt ERROR : ", e);
                        throw new IllegalStateException(e);
                    }
                })
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
