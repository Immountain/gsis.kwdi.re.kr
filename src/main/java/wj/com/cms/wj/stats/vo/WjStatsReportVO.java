package wj.com.cms.wj.stats.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WjStatsReportVO extends CmsSearchVO {

        private String reportSno;   //int         not null comment '보고 자동증가',
        private String countryGb;   //varchar(2)  not null comment '국내/국외구분',
        private String countryCode; //varchar(20) not null comment '국가코드',
        private String areaCode;    //varchar(10) not null comment '지역코드',
        private String title;   //varchar(50) null comment '제목',
        private String establishYear;   //varchar(4)  null comment '창립연도',
        private String membership;  //int         null comment '회원수',
        private String temp1;   //varchar(50) null comment '임시필드1',
        private String temp2;   //varchar(50) null comment '임시필드2',
        private String temp3;   //varchar(50) null comment '임시필드3',
        private String temp4;   //varchar(50) null comment '임시필드4',
        private String temp5;   //varchar(50) null comment '임시필드5',
        private String useYn;   //varchar(2)  null comment '사용여부',
        private String deleteYn;    //varchar(2)  null comment '삭제여부',

        private List<WjStatsReportLangVO> listLang;
}
