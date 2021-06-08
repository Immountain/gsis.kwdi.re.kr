package wj.com.cms.wj.program.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@Getter
@Setter
@ToString
public class WjProgramInfoLangVO extends CmsSearchVO {

    private String langCode="";   //not null comment '언어코드',
    private String festivityId="";   //not null comment '대회아이디',
    private String programSno="";   //not null comment '주요프로그램자동증가',
    private String programNm="";   //null comment '프로그램명칭',
    private String programPlace="";   //null comment '프로그램 장소',
    private String programEtc="";   //null comment '프로그램 기타',
    private String contentInfo="";   //null comment '내용',
    private String pageId; //페이지아이디

    //code name
    private String langCodeNm;

    public String getContentInfoDecode(){

        String tempString =this.contentInfo;
        tempString=ReplaceTag(tempString,"decode");

        return tempString;
    }

}
