package ls.electric.demo.common.login.controller;

import ls.electric.demo.common.login.service.LoginService;
import ls.electric.demo.common.login.service.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity retrieveUsers() throws Exception{
        Flux<UserResponse> userFlux = loginService.retrieveUsers();
        if(userFlux == null){
            return new ResponseEntity("조회 된 결과가 없습니다.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Flux<UserResponse>>(userFlux,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(String email, String password) throws Exception{
        Mono<UserResponse> userMono = loginService.createUsers(email,password);
        return new ResponseEntity<Mono<UserResponse>>(userMono,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        Mono mono = loginService.deleteUser(id);
        if(mono == null){
            return new ResponseEntity("계정을 찾을 수 없습니다.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mono>(mono,HttpStatus.OK);
    }
}