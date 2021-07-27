package ls.electric.demo.common.controller;

import ls.electric.demo.config.response.CommonResponse;
import ls.electric.demo.config.response.ErrorResponse;
import ls.electric.demo.common.domain.User;
import ls.electric.demo.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //1. 회원목록조회
    @GetMapping("")
    public ResponseEntity findAll(){
        return ResponseEntity.ok().body(new CommonResponse<List<User>>(userService.findAll()));
    }

    //2. 회원조회
    @GetMapping("/{id}")
    public ResponseEntity findByUser(String email){
        User user = userService.findByUser(email);

        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 회원정보가 없습니다."));
        }

        return ResponseEntity.ok().body(new CommonResponse<User>(user));
    }

    //3. 신규회원
    @PostMapping("")
    public void registerUser(User user){
        userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){
        return null;
    }

    //기타 CRUD-----------------------------------------

    @PutMapping("")
    public void modifyUser(String id, String password){
        userService.modifyUser(id, password);
    }

    @DeleteMapping("/{id}")
    public void removeUser(String id){
        userService.removeUser(id);
    }
}
