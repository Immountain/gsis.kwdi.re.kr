package gsis.com.cms.datainfo.jgAtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewA01TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("전 체")
    private String cdmData1;
    @SheetColumn("여 성")
    private String cdmData2;
    @SheetColumn("남 성")
    private String cdmData3;
    @SheetColumn("여성 비율")
    private String cdmData4;
    @SheetColumn("외국인 전 체")
    private String cdmData5;
    @SheetColumn("외국인 여 성")
    private String cdmData6;
    @SheetColumn("외국인 남 성")
    private String cdmData7;
    @SheetColumn("외국인 여성 비율")
    private String cdmData8;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewA01TiileDataVO> listData;


}

















