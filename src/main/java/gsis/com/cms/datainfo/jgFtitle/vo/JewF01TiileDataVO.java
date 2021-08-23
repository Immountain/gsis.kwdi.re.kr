package gsis.com.cms.datainfo.jgFtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewF01TiileDataVO {



    @SheetColumn("전체")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("안전")
    private String cdmData1;
    @SheetColumn("보통")
    private String cdmData2;
    @SheetColumn("불안")
    private String cdmData3;

    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewF01TiileDataVO> listData;


}

















