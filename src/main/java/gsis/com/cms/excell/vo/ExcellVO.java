package gsis.com.cms.excell.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExcellVO {

    @SheetColumn("기준일")
    private String baseDay; // BASE_DAY     varchar(8)                           not null comment '기준일',

    @SheetColumn("JTP시설장비등록ID")
    private String jtpEquipId; // JTP_EQUIP_ID varchar(30)                          not null comment 'JTP시설장비 등록ID(J-YYYYMMDD+일려번호12자리)',

    @SheetColumn("한글명")
    private String korNm;

    @SheetColumn("영문명")
    private String engNm;

    @SheetColumn("이용기업코드")
    private String comCd; // COM_CD       varchar(20)                          null comment '이용기업코드',

    @SheetColumn("이용기업이름")
    private String comNm; // COM_NM       varchar(20)                          null comment '이용기업이름',

    @SheetColumn("실제이용가능시간(H)")
    private Integer kpiRet0; // KPI_RET1     big

    @SheetColumn("일간가동시간(H)")
    private Integer kpiRet1; // KPI_RET1     bigint                               null comment '일간가동시간(H)',

    @SheetColumn("일간유지보수시간(H)")
    private Integer kpiRet2; // KPI_RET2     bigint                               null comment '일간유지보수시간(H)',

    @SheetColumn("장비수익금")
    private Integer kpiRet3; // KPI_RET3     bigint                               null comment '장비수익금',

    @SheetColumn("장비유지관리비")
    private Integer kpiRet4; // KPI_RET4     bigint                               null comment '장비유지관리비',

    private String startBaseDay;
    private String endBaseDay;


}
