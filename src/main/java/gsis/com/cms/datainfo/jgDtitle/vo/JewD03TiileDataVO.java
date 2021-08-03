package gsis.com.cms.datainfo.jgDtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewD03TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("전체")
    private String cdmData1;
    @SheetColumn("18~19세")
    private String cdmData2;
    @SheetColumn("20~24세")
    private String cdmData3;
    @SheetColumn("25~29세")
    private String cdmData4;
    @SheetColumn("30~34세")
    private String cdmData5;
    @SheetColumn("35~39세")
    private String cdmData6;
    @SheetColumn("40~44세")
    private String cdmData7;
    @SheetColumn("45~49세")
    private String cdmData8;
    @SheetColumn("50~54세")
    private String cdmData9;
    @SheetColumn("55~59세")
    private String cdmData10;
    @SheetColumn("60세이상")
    private String cdmData11;






    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewD03TiileDataVO> listData;


}

















