package ls.electric.demo.common.user.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ls.electric.demo.common.user.domain.User;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {
    private final String email;
    private final String password;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public static UserResponse of(User user){
        return new UserResponse(user.getEmail(),user.getPassword(),user.getCreatedDateTime(),user.getUpdateDateTime());
    }

}
