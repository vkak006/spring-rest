package ls.electric.demo.common.controller;

import ls.electric.demo.config.response.CommonResponse;
import ls.electric.demo.config.response.ErrorResponse;
import ls.electric.demo.common.domain.User;
import ls.electric.demo.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity findByUser(String email){
        User user = userService.findByUser(email);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 회원정보가 없습니다."));
        }

        return ResponseEntity.ok().body(new CommonResponse<User>(user));
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
