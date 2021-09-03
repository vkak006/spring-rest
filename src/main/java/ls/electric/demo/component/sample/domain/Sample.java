package ls.electric.demo.component.sample.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "sample")
public class Sample extends BaseTimeEntity{

    private String id;
    private String title;

    public Sample(String title){
        this.title = title;
    }

    public static Sample newInstance(String title){
        return new Sample(title);
    }
}
