package wj.com.cms.wj.sub.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WjSubProgramVO extends CmsSearchVO {

    private String festivityId;  //varchar(20)  not null comment '대회아이디',
    private String festivityProgramSno;  //int          not null comment '부대프로그램 자동증가',
    private String title;  //varchar(200) null comment '제목',
    private String pageType;  //varchar(2)   null comment '페이지타입',
    private String atchFileId;  //varchar(20)  null comment '파일',
    private String orderCnt;  //int          null comment '정렬순서',
    private String useYn;  //varchar(2)   null comment '사용여부',
    private String deleteYn;  //varchar(2)   null comment '삭제여부',
    private String festivityNm;  //대회명

    private List<WjSubProgramLangVO> listLang;

}
