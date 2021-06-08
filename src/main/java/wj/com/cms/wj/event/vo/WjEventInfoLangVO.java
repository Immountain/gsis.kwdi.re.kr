package wj.com.cms.wj.event.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class WjEventInfoLangVO extends CmsSearchVO {

        private String eventId;  //varchar(20)  not null comment '이벤트아이디',
        private String langCode;  //varchar(10)  null comment '언어코드',
        private String eventTitle;  //varchar(200) null comment '제목',
        private String eventContent;  //varchar(500) null comment '내용',
        private String langCodeNm;
}
