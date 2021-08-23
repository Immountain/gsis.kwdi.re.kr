package gsis.com.cms.datainfo.jgBtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewB01TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("0~5세 인구")
    private String cdmData1;
    @SheetColumn("여아")
    private String cdmData2;
    @SheetColumn("남아")
    private String cdmData3;
    @SheetColumn("여아 성비")
    private String cdmData4;
    @SheetColumn("제주 0~5세 비율")
    private String cdmData5;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewB01TiileDataVO> listData;


}

















