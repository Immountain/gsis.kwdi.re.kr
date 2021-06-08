package wj.com.cms.wj.festivity.vo;
import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class WjFestivityInfoVO extends CmsSearchVO {

        private String festivityId;  //varchar(20)  not null comment '대회아이디',
        private String festivityNm;  //varchar(500) null comment '대회명',
        private String festivityNemo;  //varchar(500) null comment '대회메모',
        private String pageStrDay;  //varchar(8)   null comment '페이지오픈시작일',
        private String pageEndDay;  //varchar(8)   null comment '페이지종료일',
        private String festivityStrDay;  //varchar(8)   null comment '시작일',
        private String festivityEndDay;  //varchar(8)   null comment '종료일',
        private String festivityImage;  //varchar(20)  null comment '대회이미지',
        private String latitude;  //float        null comment '위도',
        private String longitude;  //float        null comment '경도',
        private String orderCnt;  //int          null comment '정렬순서',
        private String useYn;  //varchar(2)   null comment '사용여부',
        private String deleteYn;  //varchar(2)   null comment '삭제여부',
        private String festivityYear;  //대회년도

        private List<WjFestivityLangVO> listLang;

}
