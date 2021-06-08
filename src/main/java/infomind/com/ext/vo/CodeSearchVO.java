package infomind.com.ext.vo;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeSearchVO {

    private String code="";
    private String codeNm="";

    private String clCode ="";
    private String codeId ="";

    private Integer pId;

}
