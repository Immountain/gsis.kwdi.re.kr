package wj.com.cms.wj.event.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WjEventInfoVO extends CmsSearchVO {

    private String eventId;   //varchar(20) not null comment '이벤트아이디',
    private String eventStrDay;   //varchar(8)  null comment '시작일',
    private String eventStrTime;   //varchar(4)  null comment '시작일시',
    private String eventEndDay;   //varchar(8)  null comment '종료일',
    private String eventEndTime;   //varchar(4)  null comment '종료일시',
    private String atchFileId;   //varchar(20) null comment '이미지',
    private String useYn;   //varchar(2)  null comment '사용여부',
    private String deleteYn;   //varchar(2)  null comment '삭제여부',

    private List<WjEventInfoLangVO> listLang;
}
