package ls.electric.demo.config.hateoas;

import ls.electric.demo.common.controller.SampleController;
import ls.electric.demo.common.domain.Sample;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EntityToModelConverter implements RepresentationModelAssembler<Sample, EntityModel<Sample>> {

    @Override
    public EntityModel<Sample> toModel(Sample entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(SampleController.class).test("test")).withSelfRel());
    }

    @Override
    public CollectionModel<EntityModel<Sample>> toCollectionModel(Iterable<? extends Sample> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
