package ls.electric.demo.common.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import ls.electric.demo.sample.domain.BaseTimeEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "user")
public class User extends BaseTimeEntity {
    private String id;
    private String name;
    private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public static User newInstance(String name, String password){
        return new User(name, password);
    }
}
