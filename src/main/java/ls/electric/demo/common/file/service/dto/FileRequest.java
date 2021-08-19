package ls.electric.demo.common.file.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileRequest {
    private String originalFileName;
    private String filePath;
    private Long fileSize;
}
