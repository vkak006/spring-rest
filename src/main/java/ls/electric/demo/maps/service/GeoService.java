package ls.electric.demo.maps.service;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.*;
import ls.electric.demo.maps.domain.Geographic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class GeoService {

    @Value("${google.map.api.key}")
    private String apiKey;

    public Mono<Geographic> convertGeoCoding(String address){
        return Mono.just(address)
                .flatMap(addr -> Mono.just(getGeoCording(addr)));
    }

    public Geographic getGeoCording(String addr){
        if(addr == null) {
            throw new IllegalArgumentException();
        }

        try{
            Geocoder geocoder = new Geocoder();
            GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(addr).setLanguage("ko").getGeocoderRequest();
            GeocodeResponse geocodeResponse;

            geocodeResponse = geocoder.geocode(geocoderRequest);
            if(geocodeResponse.getStatus() == GeocoderStatus.OK & !geocodeResponse.getResults().isEmpty()) {
                GeocoderResult geocoderResult = geocodeResponse.getResults().iterator().next();
                LatLng latLng = geocoderResult.getGeometry().getLocation();
                Float lat = latLng.getLat().floatValue();
                Float lng = latLng.getLng().floatValue();

                return Geographic.newInstance(lat,lng,addr);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public String getGeoCording2(String address){
        Map<String,Object> map = new HashMap<>();

        try{
            String apiURL = "https://maps.googleapis.com/maps/api/geocode/json?";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
