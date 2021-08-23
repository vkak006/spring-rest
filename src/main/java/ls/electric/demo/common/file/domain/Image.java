package ls.electric.demo.common.file.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "image")
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Transient
    public static final String SEQUENCE_NAME = "file_sequence";

    private String id;
    private long fileNo;
    private String origFileName;
    private String tempFileName;
    private String filePath;
    private long fileSize;

    public Image(String origFileName, String filePath){
        this.origFileName = origFileName;
        this.tempFileName = UUID.randomUUID().toString().replace("-","")
                + origFileName.substring(origFileName.lastIndexOf("."));
        this.filePath = filePath;
    }

    public static Image newInstance(String origFileName, String filePath){
        return new Image(origFileName, filePath);
    }
}

