package ls.electric.demo.maps.controller;

import ls.electric.demo.maps.domain.Geographic;
import ls.electric.demo.maps.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/maps")
public class GeoController {

    @Autowired
    private GeoService mapsService;

    @GetMapping()
    public Mono<Geographic> getGeographic(String address){
        return mapsService.convertGeoCoding(address);
    }
}
