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
    @SheetColumn("0~9세")
    private String cdmData2;
    @SheetColumn("10~19세")
    private String cdmData3;
    @SheetColumn("20~29세")
    private String cdmData4;
    @SheetColumn("30~39세")
    private String cdmData5;
    @SheetColumn("40~49세")
    private String cdmData6;
    @SheetColumn("50~59세")
    private String cdmData7;
    @SheetColumn("60~69세")
    private String cdmData8;
    @SheetColumn("70~79세")
    private String cdmData9;
    @SheetColumn("80세이상")
    private String cdmData10;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewA03TiileDataVO> listData;


}

















