package ls.electric.demo.common.controller;

//HATEOAS Sample

import ls.electric.demo.common.domain.Sample;
import ls.electric.demo.config.hateoas.EntityToModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class SampleController {
    @Autowired
    private EntityToModelConverter entityToModelConverter;

    @GetMapping("/sample")
    public EntityModel<Sample> test(){
        return entityToModelConverter.toModel(new Sample());
    }
}
