package gsis.com.cms.thema.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JewThemaInfoVO extends CmsSearchVO {

        private String themaGroupId;  //varchar(20)  not null comment '테마통계관리그룹아이디',
        private String themaId;  //varchar(20)  not null comment '테마통계아이디',
        private String themaNm;  //varchar(200) null comment '테마통계명',
        private String themaTitle;  //varchar(200) null comment '테마통계타이틀',
        private String themaMemo;  //varchar(500) null comment '메모',
        private String themaEtc;  //varchar(500) null comment '기타',
        private String tblId;  //varchar(200) null comment '통계표ID',
        private String loadGubun;  //varchar(2)   null comment '조회구분(시계열,횡단면)',
        private String loadTerm;  //varchar(2)   null comment '조회설정',
        private String collectType;  //varchar(2)   null comment '수집타입',
        private String collectCycle;  //varchar(2)   null comment '수집주기',
        private String apiUrl;  //varchar(200) null comment 'API_URL',
        private String htmlFile;  //varchar(200) null comment 'HTML_파일명',
        private String orderCnt;  //int          null comment '정렬순번',
        private String useYn;  //varchar(2)   null comment '사용여부',
        private String deleteYn;  //varchar(2)   null comment '삭제여부',
        private String regDt;  //datetime     null comment '등록일',
        private String regId;  //varchar(20)  null comment '등록자',
        private String modDt;  //datetime     null comment '수정일',
        private String modId;  //varchar(20)  null comment '수정자'

}
