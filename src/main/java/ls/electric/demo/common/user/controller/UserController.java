package ls.electric.demo.common.user.controller;

import ls.electric.demo.common.user.domain.User;
import ls.electric.demo.common.user.service.UserService;
import ls.electric.demo.common.user.service.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity retrieveUsers() throws Exception{
        Flux<UserResponse> userFlux = userService.retrieveUsers();
        return new ResponseEntity<Flux<UserResponse>>(userFlux,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(String email, String password) throws Exception{
        Mono<UserResponse> userMono = userService.createUsers(email,password);
        return new ResponseEntity<Mono<UserResponse>>(userMono,HttpStatus.OK);
    }
}
