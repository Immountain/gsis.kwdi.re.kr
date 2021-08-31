package gsis.com.cms.datainfo.jgFtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewF03TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("발생건수")
    private String cdmData1;
    @SheetColumn("검거건수")
    private String cdmData2;
    @SheetColumn("검거비율")
    private String cdmData3;

    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewF03TiileDataVO> listData;


}

















