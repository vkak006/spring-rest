package ls.electric.demo.common.domain;

import lombok.Data;

import java.util.List;

@Data
public class CommonResponse<T> {
    private int count;
    private T data;

    public CommonResponse(T data){
        this.data = data;

        if(data instanceof List){
            this.count = ((List<T>)data).size();
        }else{
            this.count = 1;
        }
    }
}
