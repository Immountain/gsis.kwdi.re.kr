package gsis.com.cms.datainfo.jgDtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewD05TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("출산전후 휴가급여 인원")
    private String cdmData1;
    @SheetColumn("출산전후 휴가급여 지급액")
    private String cdmData2;
    @SheetColumn("육아휴직급여 인원")
    private String cdmData3;
    @SheetColumn("육아휴직급여 지급액")
    private String cdmData4;
    @SheetColumn("육아기근단축급여 인원")
    private String cdmData5;
    @SheetColumn("육아기근단축급여 지급액")
    private String cdmData6;

    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewD05TiileDataVO> listData;


}

















