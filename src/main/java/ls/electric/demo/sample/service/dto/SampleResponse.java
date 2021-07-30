package ls.electric.demo.sample.service.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ls.electric.demo.sample.domain.Sample;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleResponse {

    private final String id;
    private final String prefix;
    private final LocalDateTime createDateTime;
    private final LocalDateTime updateDateTime;

    public static SampleResponse of(Sample sample){
        return new SampleResponse(sample.getId(), sample.getPrefix(), sample.getCreatedDateTime(), sample.getUpdateDateTime());
    }

}
