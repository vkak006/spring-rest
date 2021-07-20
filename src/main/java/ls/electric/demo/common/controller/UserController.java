package ls.electric.demo.common.controller;

import ls.electric.demo.common.domain.User;
import ls.electric.demo.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public User findByUser(String email){
        return userService.findByUser(email);
    }

    @PostMapping()
    public void registerUser(User user){
        userService.registerUser(user);
    }

    @PutMapping()
    public void modifyUser(String id, String password){
        userService.modifyUser(id, password);
    }

    @DeleteMapping()
    public void removeUser(String id){
        userService.removeUser(id);
    }
}
