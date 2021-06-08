package wj.com.cms.wj.application.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class WjApplicationUserVO extends CmsSearchVO {

        private String applicationUserSno;   // int          not null comment '참가신청자자동증가',
        private String festivityId;   // varchar(20)  null comment '대회아이디',
        private String applicationId;   // varchar(20)  null comment '참가신청아이디',
        private String userNm;   // varchar(200) null comment '이름',
        private String userEmail;   // varchar(200) null comment '이메일',
        private String userPhone;   // varchar(10)  null comment '전화번호',
        private String deleteYn;   // varchar(2)   null comment '삭제여부',
        private String regDt;   // datetime     null comment '등록일',
        private String regIp;   // varchar(20)  null comment '등록아이피',
        private String modDt;   // datetime     null comment '수정일',
        private String modIp;   // varchar(20)  null comment '수정자아이피'
}
