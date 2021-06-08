package wj.com.cms.wj.festivity.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString

public class WjFestivityApplicationLangVO extends CmsSearchVO {

    private String festivityId;   //varchar(20)  not null comment '대회아이디',
    private String applicationId;   //varchar(20)  not null comment '참가신청아이디',
    private String langCode;   //varchar(10)  not null comment '언어코드',
    private String applicationTitle;   //varchar(500) null comment '참가신청제목',
    private String contentInfo;   //varchar(500) null comment '참가신청내용',
    private String memo;   //varchar(500) null comment '참가신청메모',

    private String langCodeNm;
}
