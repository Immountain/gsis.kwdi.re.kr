package gsis.com.cms.datainfo.jgDtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewD01TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("0세")
    private String cdmData1;
    @SheetColumn("1세")
    private String cdmData2;
    @SheetColumn("5세")
    private String cdmData3;
    @SheetColumn("10세")
    private String cdmData4;
    @SheetColumn("15세")
    private String cdmData5;
    @SheetColumn("20세")
    private String cdmData6;
    @SheetColumn("25세")
    private String cdmData7;
    @SheetColumn("30세")
    private String cdmData8;
    @SheetColumn("35세")
    private String cdmData9;
    @SheetColumn("40세")
    private String cdmData10;
    @SheetColumn("45세")
    private String cdmData11;
    @SheetColumn("50세")
    private String cdmData12;
    @SheetColumn("55세")
    private String cdmData13;
    @SheetColumn("60세")
    private String cdmData14;
    @SheetColumn("65세")
    private String cdmData15;
    @SheetColumn("70세")
    private String cdmData16;
    @SheetColumn("75세")
    private String cdmData17;
    @SheetColumn("80세")
    private String cdmData18;
    @SheetColumn("85세")
    private String cdmData19;
    @SheetColumn("90세")
    private String cdmData20;
    @SheetColumn("95세")
    private String cdmData21;
    @SheetColumn("100세 이상")
    private String cdmData22;





    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewD01TiileDataVO> listData;


}

















