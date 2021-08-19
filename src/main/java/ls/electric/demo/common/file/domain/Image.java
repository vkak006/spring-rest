package ls.electric.demo.common.file.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "image")
@AllArgsConstructor
public class Image {
    private String id;
    private String fileNo;
    private String origFileName;
    private String tempFileName;
    private String filePath;
    private long fileSize;

    public Image(String origFileName, String filePath){
        this.origFileName = origFileName;
        this.filePath = filePath;
    }

    public static Image newInstance(String origFileName, String filePath){
        return new Image(origFileName, filePath);
    }
}

