package ls.electric.demo.common.login.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ls.electric.demo.sample.domain.BaseTimeEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class User extends BaseTimeEntity {
    private String id;
    private String email;
    private String name;
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public static User newInstance(String email, String password){
        return new User(email, password);
    }
}
