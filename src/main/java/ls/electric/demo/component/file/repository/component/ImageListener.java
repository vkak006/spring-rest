package ls.electric.demo.component.file.repository.component;

import lombok.RequiredArgsConstructor;
import ls.electric.demo.component.file.domain.Image;
import ls.electric.demo.component.file.repository.SequenceGeneratorService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ImageListener extends AbstractMongoEventListener<Image> {
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Image> event){
        event.getSource().setFileNo(sequenceGenerator.generateSequence(Image.SEQUENCE_NAME));
    }
}
