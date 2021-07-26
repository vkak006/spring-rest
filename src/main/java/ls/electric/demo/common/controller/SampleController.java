package ls.electric.demo.common.controller;

//HATEOAS Sample

import ls.electric.demo.common.domain.Sample;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SampleController {
    @GetMapping("/sample")
    public EntityModel<Sample> test(){
        Sample sample = new Sample();

        sample.setPrefix("hello, ");
        sample.setName("Ban");

        EntityModel<Sample> sampleModel = EntityModel.of(sample);
        sampleModel.add(linkTo(methodOn(SampleController.class).test()).withSelfRel());
        sampleModel.add(linkTo(methodOn(SampleController.class).test()).withRel("custom"));

        return sampleModel;
    }
}
