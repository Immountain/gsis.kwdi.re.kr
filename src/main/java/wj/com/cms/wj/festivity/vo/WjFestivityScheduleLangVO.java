package wj.com.cms.wj.festivity.vo;


import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class WjFestivityScheduleLangVO extends CmsSearchVO {

                private String festivityId;    //varchar(20)  not null comment '대회아이디',
                private String scheduleSno;    //int          not null comment '대회일정자동증가',
                private String langCode;    //varchar(10)  not null comment '언어코드',
                private String scheduleTitle;    //varchar(200) null comment '일정제목',
                private String scheduleProgram;    //varchar(200) null comment '프로그램',
                private String schedulePlace;    //varchar(500) null comment '장소',

                private String langNm ="";

                //code name
                private String langCodeNm;


                List<WjFestivityScheduleLangVO> listLang;
}
