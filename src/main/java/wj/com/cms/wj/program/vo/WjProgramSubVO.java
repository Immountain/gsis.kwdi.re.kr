package wj.com.cms.wj.program.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class WjProgramSubVO extends CmsSearchVO {

        private String programSubSno;  //int          not null comment '서브프로그램 자동증가',
        private String festivityId;  //varchar(20)  not null comment '대회아이디',
        private String programSno;  //int          not null comment '주요프로그램자동증가',
        private String title;  //varchar(200) null comment '컨텐츠 타이틀',
        private String memo;  //varchar(200) null comment '메모',
        private String leaderYn;  //varchar(2)   null comment '대표여부',
        private String orderCnt;  //int          null comment '정렬',
        private String atchFileId;  //varchar(20)  null comment '이미지정보',
        private String useYn;  //varchar(2)   null comment '사용여부',
        private String deleteYn;  //varchar(200) null comment '삭제여부',



        private List<WjProgramSubLangVO> ListLang;
}
