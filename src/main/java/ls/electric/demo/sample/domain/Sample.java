package ls.electric.demo.sample.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "sample")
public class Sample extends BaseTimeEntity{
    private String id;
    private String prefix;

    private Sample(String prefix){
        this.prefix = prefix;
    }

    public static Sample newInstance(String prefix){
        return new Sample(prefix);
    }
}
