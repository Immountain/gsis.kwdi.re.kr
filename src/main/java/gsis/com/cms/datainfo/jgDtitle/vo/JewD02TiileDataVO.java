package gsis.com.cms.datainfo.jgDtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewD02TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("연령")
    private String cdmData1;
    @SheetColumn("매우 좋음")
    private String cdmData2;
    @SheetColumn("좋은 편임")
    private String cdmData3;
    @SheetColumn("보 통")
    private String cdmData4;
    @SheetColumn("나쁜 편임")
    private String cdmData5;
    @SheetColumn("매우 나쁨")
    private String cdmData6;





    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewD02TiileDataVO> listData;


}

















