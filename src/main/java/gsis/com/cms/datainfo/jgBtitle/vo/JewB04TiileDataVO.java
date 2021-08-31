package gsis.com.cms.datainfo.jgBtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewB04TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("졸업자수")
    private String cdmData1;
    @SheetColumn("진학자수")
    private String cdmData2;
    @SheetColumn("진학률")
    private String cdmData3;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewB04TiileDataVO> listData;


}

















