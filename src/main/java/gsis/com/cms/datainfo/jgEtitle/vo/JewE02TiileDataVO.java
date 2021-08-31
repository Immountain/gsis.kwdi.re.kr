package gsis.com.cms.datainfo.jgEtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewE02TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("선거명")
    private String cdmData1;
    @SheetColumn("후보자 수")
    private String cdmData2;
    @SheetColumn("후보자 시도지사")
    private String cdmData3;
    @SheetColumn("후보자 교육감")
    private String cdmData4;
    @SheetColumn("후보자 시도의회의원")
    private String cdmData5;
    @SheetColumn("후보자 시도의회의원(지역구)")
    private String cdmData6;
    @SheetColumn("후보자 시도의회의원(교육위원)")
    private String cdmData7;
    @SheetColumn("후보자 비례대표")
    private String cdmData8;
    @SheetColumn("당선인 수")
    private String cdmData9;
    @SheetColumn("당선인 시도지사")
    private String cdmData10;
    @SheetColumn("당선인 교육감")
    private String cdmData11;
    @SheetColumn("당선인 시도의회의원")
    private String cdmData12;
    @SheetColumn("당선인 시도의회의원(지역구)")
    private String cdmData13;
    @SheetColumn("당선인 시도의회의원(교육위원)")
    private String cdmData14;
    @SheetColumn("당선인 비례대표")
    private String cdmData15;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewE02TiileDataVO> listData;


}

















