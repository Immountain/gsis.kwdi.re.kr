package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC03TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("15-54세 기혼여성 인구")
    private String cdmData1;
    @SheetColumn("비취업여성 인구")
    private String cdmData2;
    @SheetColumn("비취업 여성 비율")
    private String cdmData3;
    @SheetColumn("경력단절 여성 인구")
    private String cdmData4;
    @SheetColumn("제주 경력단절 여성 비율")
    private String cdmData5;








    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC03TiileDataVO> listData;


}

















