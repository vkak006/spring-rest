package ls.electric.demo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@Document(collection = "sample")
public class Sample extends BaseTimeEntity{
    private String prefix;
    private String name;

    public static Sample newInstance(String prefix, String name){
        return new Sample(prefix, name);
    }
}
