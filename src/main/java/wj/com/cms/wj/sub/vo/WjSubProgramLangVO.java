package wj.com.cms.wj.sub.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static infomind.com.utils.web.ReplaceTag.ReplaceTag;

@ToString
@Getter
@Setter

public class WjSubProgramLangVO extends CmsSearchVO {

    private String festivityId;  //varchar(20)                      not null comment '대회아이디',
    private String festivityProgramSno;  //int                              not null comment '부대프로그램
    private String langCode;  //varchar(10)                      not null comment '언어코드',
    private String subProgramTitle;  //varchar(200)                     null comment '제목',
    private String contentInfo;  //longtext collate utf8_unicode_ci null comment '내용',
    private String pageId;  //varchar(20)                      null comment '페이지아이디',

    private String langCodeNm; //CUSTOM

    public String getContentInfoDecode() {

        String tempString = this.contentInfo;
        tempString = ReplaceTag(tempString, "decode");

        return tempString;
    }
}
