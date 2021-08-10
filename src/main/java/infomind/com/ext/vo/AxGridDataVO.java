package infomind.com.ext.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AxGridDataVO<T> {

    private List<T> list;
    private AxGridPageVO page = new AxGridPageVO();


}
