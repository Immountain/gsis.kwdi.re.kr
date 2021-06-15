package gsis.com.cms.thema.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JewThemaGroupVO extends CmsSearchVO {

    private String themaGroupId;  //varchar(20)  not null comment '테마통계관리그룹아이디',
    private String themaGroupNm;  //varchar(200) null comment '테마그룹명',
    private String orderCnt;  //int          null comment '정렬순번',
    private String useYn;  //varchar(2)   null comment '사용여부',
    private String deleteYn;  //varchar(2)   null comment '삭제여부',
    private String regDt;  //datetime     null comment '등록일',
    private String regId;  //varchar(20)  null comment '등록자',
    private String modDt;  //datetime     null comment '수정일',
    private String modId;  //varchar(20)  null comment '수정자'

}
