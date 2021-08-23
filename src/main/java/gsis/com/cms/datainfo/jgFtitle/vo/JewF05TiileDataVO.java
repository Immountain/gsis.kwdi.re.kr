package gsis.com.cms.datainfo.jgFtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewF05TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("대기")
    private String cdmData1;
    @SheetColumn("수질")
    private String cdmData2;
    @SheetColumn("토양")
    private String cdmData3;
    @SheetColumn("소음·진동")
    private String cdmData4;
    @SheetColumn("녹지환경")
    private String cdmData5;

    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewF05TiileDataVO> listData;


}

















