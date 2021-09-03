package ls.electric.demo.component.users.controller;

import ls.electric.demo.component.users.service.UserService;
import ls.electric.demo.component.users.service.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity findAll() throws Exception{
        Flux<UserResponse> userFlux = userService.findAll();
        if(userFlux == null){
            return new ResponseEntity("조회 된 결과가 없습니다.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Flux<UserResponse>>(userFlux,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(String email, String username, String password) throws Exception{
        Mono<UserResponse> userMono = userService.createUsers(email,username,password);
        return new ResponseEntity<Mono<UserResponse>>(userMono,HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        Mono mono = userService.deleteUser(id);
        if(mono == null){
            return new ResponseEntity("계정을 찾을 수 없습니다.",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mono>(mono,HttpStatus.OK);
    }
}
