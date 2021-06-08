package wj.com.cms.wj.stats.vo;

import infomind.com.ext.vo.CmsSearchVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class WjStatsReportLangVO extends CmsSearchVO {

        private String reportSno;   //int          not null comment '보고 자동증가',
        private String countryGb;   //varchar(2)   not null comment '국내/국외구분',
        private String areaCode;    //varchar(10)  not null comment '지역코드',
        private String countryCode; //varchar(20)  not null comment '국가코드',
        private String langCode;    //varchar(10)  not null comment '언어코드',
        private String associationName; //varchar(50)  null comment '협회명',
        private String memo;    //varchar(100) null comment '메모'
        private String langCodeNm; //CUSTOM
}
