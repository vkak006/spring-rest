package ls.electric.demo.common.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    private String id;
    private String email;
    private String password;

    private String firstName;
    private String lastName;

    //name String 확장
    public void setName(String name){
        String[] names = name.split(" ");
        firstName = names[0];
        lastName = names[1];
    }

    public String getName(){
        return firstName + " " + lastName;
    }
}
