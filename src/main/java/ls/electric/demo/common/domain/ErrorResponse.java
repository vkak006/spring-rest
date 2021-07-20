package ls.electric.demo.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String errorMessage;
    private String errorCode;

    public ErrorResponse(String errorMessage){
        this.errorMessage = errorMessage;
        this.errorCode = "404";
    }
}
