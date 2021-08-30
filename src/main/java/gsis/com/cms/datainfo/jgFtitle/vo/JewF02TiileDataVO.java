package gsis.com.cms.datainfo.jgFtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewF02TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("CDM_DATA1")
    private String cdmData1;
    @SheetColumn("CDM_DATA2")
    private String cdmData2;
    @SheetColumn("CDM_DATA3")
    private String cdmData3;
    @SheetColumn("CDM_DATA4")
    private String cdmData4;
    @SheetColumn("CDM_DATA5")
    private String cdmData5;
    @SheetColumn("CDM_DATA6")
    private String cdmData6;
    @SheetColumn("CDM_DATA7")
    private String cdmData7;


    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewF02TiileDataVO> listData;


}

















