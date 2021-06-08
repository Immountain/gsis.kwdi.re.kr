package wj.com.cms.wj.festivity.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@ToString
@Getter
@Setter
public class WjFestivityScheduleVO extends CmsSearchVO {
        private String festivityId;      //varchar(20)  not null comment '대회아이디',
        private String scheduleSno;      //int          not null comment '대회일정자동증가',
        private String scheduleDay;      //varchar(8)   null comment '일짜',
        private String scheduleStrTime;      //varchar(5)   null comment '시작시간',
        private String scheduleEndTime;      //varchar(5)   null comment '종료시간',
        private String title;      //varchar(200) null comment '제목',
        private String meo;      //varchar(200) null comment '메모',
        private String orderCnt;      //int          null comment '정렬순번',
        private String useYn;      //varchar(2)   null comment '사용여부',
        private String deleteYn;      //varchar(2)   null comment '삭제여부',
        private String regId;      //varchar(20)  null comment '등록자',
        private String regDt;      //datetime     null comment '등록일',
        private String modId;      //varchar(20)  null comment '수정자',
        private String modDt;      //datetime     null comment '수정일'

        private List<WjFestivityScheduleLangVO> listLang;

}
