package gsis.com.cms.datainfo.jgAtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Data
@ToString
public class JewA03TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("전체")
    private String cdmData1;
    @SheetColumn("0~4세")
    private String cdmData2;
    @SheetColumn("5~9세")
    private String cdmData3;
    @SheetColumn("10~14세")
    private String cdmData4;
    @SheetColumn("15~19세")
    private String cdmData5;
    @SheetColumn("20~24세")
    private String cdmData6;
    @SheetColumn("25~29세")
    private String cdmData7;
    @SheetColumn("30~34세")
    private String cdmData8;
    @SheetColumn("35~39세")
    private String cdmData9;
    @SheetColumn("40~44세")
    private String cdmData10;
    @SheetColumn("45~49세")
    private String cdmData11;
    @SheetColumn("50~54세")
    private String cdmData12;
    @SheetColumn("55~59세")
    private String cdmData13;
    @SheetColumn("60~64세")
    private String cdmData14;
    @SheetColumn("65~69세")
    private String cdmData15;
    @SheetColumn("70~74세")
    private String cdmData16;
    @SheetColumn("75~79세")
    private String cdmData17;
    @SheetColumn("80세이상")
    private String cdmData18;


    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewA03TiileDataVO> listData;


}

















