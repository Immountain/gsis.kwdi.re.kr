package gsis.com.cms.excell.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExcellVO {

    @SheetColumn("년도")
    private String tw1; // BASE_DAY     varchar(8)                           not null comment '기준일',

    @SheetColumn("구분")
    private String tw2; // JTP_EQUIP_ID varchar(30)                          not null comment 'JTP시설장비 등록ID(J-YYYYMMDD+일려번호12자리)',

    @SheetColumn("만족")
    private String tw3;

    @SheetColumn("불만족")
    private String tw4;

    @SheetColumn("경제적부담")
    private String tw5; // COM_CD       varchar(20)                          null comment '이용기업코드',

    @SheetColumn("시간부족")
    private String tw6; // COM_NM       varchar(20)                          null comment '이용기업이름',

    @SheetColumn("교통혼잡")
    private String tw7; // COM_NM       varchar(20)                          null comment '이용기업이름',
    @SheetColumn("여가시설부족")
    private String tw8; // COM_NM       varchar(20)                          null comment '이용기업이름',
    @SheetColumn("여가정보부족")
    private String tw9; // COM_NM       varchar(20)                          null comment '이용기업이름',
    @SheetColumn("취미가 없어서")
    private String tw10; // COM_NM       varchar(20)                          null comment '이용기업이름',


    @SheetColumn("건강, 체력 부족여가를 함께 즐길 사람이 없음")
    private String tw11; // COM_NM       varchar(20)                          null comment '이용기업이름',


    @SheetColumn("기타")
    private String tw12; // COM_NM       varchar(20)                          null comment '이용기업이름',



}
