package ls.electric.demo.common.users.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class LoginController {

//    @GetMapping("/api/login")
//    public String login() {
//        return null;
//    }
//
//    @GetMapping("/api/logout")
//    public String logout(){
//        return null;
//    }


    @GetMapping("/api/test/login")
    public Mono<String> greet(Mono<Principal> principal){
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Hello %s",name));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/api/test/login/admin")
    public Mono<String> greetAdmin(Mono<Principal> principal){
        return principal
                .map(Principal::getName)
                .map(name -> String.format("Admin access: %s", name));
    }

}
