package wj.com.cms.wj.festivity.vo;


import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString

public class WjFestivityApplicationVO extends CmsSearchVO {

        private String festivityId;   // varchar(20)  not null comment '대회아이디',
        private String applicationId;   // varchar(20)  not null comment '참가신청아이디',
        private String applicationGb;   // varchar(2)   null comment '참가신청구분',
        private String applicationNm;   // varchar(200) null comment '참가신청명',
        private String applicationContent;   // varchar(500) null comment '참가신청내용',
        private String applicationMemo;   // varchar(300) null comment '참가신청메모',
        private String applicationStrDt;   // varchar(8)   null comment '시작일',
        private String applicationEntDt;   // varchar(8)   null comment '종료일',
        private String useYn;   // varchar(2)   null comment '사용여부',

        //CUSTOM
        private String festivityNm;

        private List<WjFestivityApplicationLangVO> listLang;
}
