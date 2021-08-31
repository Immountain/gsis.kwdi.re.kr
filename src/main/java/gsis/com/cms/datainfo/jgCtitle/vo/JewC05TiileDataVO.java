package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC05TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("계")
    private String cdmData1;
    @SheetColumn("15~19세")
    private String cdmData2;
    @SheetColumn("20~29세")
    private String cdmData3;
    @SheetColumn("30~39세")
    private String cdmData4;
    @SheetColumn("40~49세")
    private String cdmData5;
    @SheetColumn("50~59세")
    private String cdmData6;
    @SheetColumn("60세 이상")
    private String cdmData7;








    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC05TiileDataVO> listData;


}

















