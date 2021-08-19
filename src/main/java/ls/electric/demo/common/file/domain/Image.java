package ls.electric.demo.common.file.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "image")
@AllArgsConstructor
public class Image {
    private String id;
    private String fileName;
    private String originalFileName;
    private String filePath;

    public Image(String originalFileName, String filePath)
    {
        this.originalFileName = originalFileName;
        this.filePath = filePath;
    }

    public static Image newInstance(String originalFileName, String filePath){
        return new Image(originalFileName, filePath);
    }
}

