package ls.electric.demo.common.controller;

//HATEOAS Sample

import ls.electric.demo.common.domain.Sample;
import ls.electric.demo.config.hateoas.EntityToModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {
    @Autowired
    private EntityToModelConverter entityToModelConverter;

    @GetMapping("/sample")
    public EntityModel<Sample> test(){
        return entityToModelConverter.toModel(new Sample());
    }

//    @GetMapping("/web-flux")
//    public Mono<Sample> testWebFlux(){
//        Sample sample = new Sample();
//        sample.setName("test");
//        sample.setPrefix("webFlux");
//
//        return Mono.just(sample);
//    }
}
