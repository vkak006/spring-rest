package ls.electric.demo.common.controller;

import ls.electric.demo.common.domain.User;
import ls.electric.demo.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public User findByUser(String email){
        return userService.findByUser(email);
    }

    @PostMapping("")
    public Map<String,Object> save(User user){
        return userService.registerUser(user);
    }
}
