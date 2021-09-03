package ls.electric.demo.component.sample.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ls.electric.demo.component.sample.domain.Sample;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleResponse {

    private final String id;
    private final String title;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public static SampleResponse of(Sample sample){
        return new SampleResponse(sample.getId(), sample.getTitle(), sample.getCreatedDateTime(), sample.getUpdateDateTime());
    }

}
