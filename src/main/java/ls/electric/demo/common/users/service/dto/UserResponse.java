package ls.electric.demo.common.users.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.users.domain.User;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private final String email;
    private final String username;
    private final String password;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public static UserResponse of(User user){
        return new UserResponse(user.getEmail(),user.getUsername(),user.getPassword(),user.getCreatedDateTime(),user.getUpdateDateTime());
    }

}
