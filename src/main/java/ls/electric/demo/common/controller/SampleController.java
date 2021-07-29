package ls.electric.demo.common.controller;

import ls.electric.demo.common.domain.Sample;
import ls.electric.demo.config.hateoas.EntityToModelConverter;
import ls.electric.demo.config.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SampleController {

    @GetMapping("/sample")
    public ResponseEntity test(String prefix){
        Sample sample = Sample.newInstance(prefix,"");

        if(sample.getPrefix() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 회원정보가 없습니다."));
        }

        return ResponseEntity.ok().body(
                EntityModel.of(Mono.just(sample), linkTo(methodOn(SampleController.class).test(prefix)).withSelfRel())
        );
    }
}
