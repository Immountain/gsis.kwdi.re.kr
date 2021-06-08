package wj.com.cms.wj.program.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;
@Getter
@Setter
@ToString

public class WjProgramInfoVO extends CmsSearchVO {

        private String festivityId;    //varchar(20)  not null comment '대회아이디',
        private String programSno;    //int          not null comment '주요프로그램자동증가',
        private String programTitle;    //varchar(500) null comment '프로그램명칭',
        private String programDay;    //varchar(8)   null comment '날짜',
        private String programStrTime;    //varchar(5)   null comment '시작시간',
        private String programEndTime;    //varchar(5)   null comment '종료시간',
        private String programPlace;    //varchar(500) null comment '장소',
        private String programEtc;    //varchar(200) null comment '기타정보',
        private String pageType;    //varchar(20)  null comment '페이지타입',
        private String atchFileId;    //varchar(20)  null comment '이미지리스트',
        private String orderCnt;    //int          null comment '정렬순번',
        private String deleteYn;    //varchar(2)   null comment '삭제여부',

        /** FESTIVITY_NM CUSTOM */
        private String festivityNm;

        private List<WjProgramInfoLangVO> listLang;

}
