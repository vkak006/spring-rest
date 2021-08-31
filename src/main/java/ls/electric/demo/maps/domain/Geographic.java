package ls.electric.demo.maps.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "geographic")
public class Geographic {
    @Transient
    public static final String SEQUENCE_NAME = "geo_sequence";

    private String id;
    private long geoNo;
    private Float lat;
    private Float lng;
    private String address;

    public Geographic(Float lat, Float lng, String address){
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public static Geographic newInstance(Float lat, Float lng, String address){
        return new Geographic(lat,lng, address);
    }
}
