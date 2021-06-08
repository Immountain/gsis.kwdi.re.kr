package wj.com.cms.wj.event.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class WjEventUserVO extends CmsSearchVO {

        private String eventUserSno;   //int          not null comment '신청자동증가',
        private String eventId;   //varchar(20)  not null comment '이벤트아이디',
        private String userNm;   //varchar(20)  null comment '이름',
        private String memo;   //varchar(200) null comment '메모',
        private String atchFileId;   //varchar(20)  null comment '파일',
        private String userEmail;   //varchar(10)  null comment '이메일',
        private String userPhone;   //varchar(10)  null comment '전화번호',
        private String deleteYn;   //varchar(2)   null comment '삭제여부',
        private String regIp;   //varchar(20)  null comment '등록아이피',

}
