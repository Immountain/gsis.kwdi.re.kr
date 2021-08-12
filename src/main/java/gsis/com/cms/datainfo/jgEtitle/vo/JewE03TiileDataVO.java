package gsis.com.cms.datainfo.jgEtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewE03TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("계")
    private String cdmData1;
    @SheetColumn("기획")
    private String cdmData2;
    @SheetColumn("예산")
    private String cdmData3;
    @SheetColumn("인사")
    private String cdmData4;
    @SheetColumn("감사")
    private String cdmData5;
    @SheetColumn("실국주무과")
    private String cdmData6;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewE03TiileDataVO> listData;


}

















