package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC02TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("조건")
    private String cdmData1;
    @SheetColumn("15~19세")
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
    @SheetColumn("60~64세")
    private String cdmData11;
    @SheetColumn("65세 이상")
    private String cdmData12;





    private String cdmData1T;
    private String cdmData2T;
    private String cdmData3T;
    private String cdmData4T;
    private String cdmData5T;
    private String cdmData6T;
    private String cdmData7T;
    private String cdmData8T;
    private String cdmData9T;
    private String cdmData10T;
    private String cdmData11T;
    private String cdmData12T;





    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC02TiileDataVO> listData;


}

















