package gsis.com.cms.datainfo.jgBtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewB03TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("초등학교 학생수")
    private String cdmData1;
    @SheetColumn("초등학교 여성")
    private String cdmData2;
    @SheetColumn("초등학교 여성비율")
    private String cdmData3;
    @SheetColumn("중학교 학생수")
    private String cdmData4;
    @SheetColumn("중학교 여성")
    private String cdmData5;
    @SheetColumn("중학교 여성비율")
    private String cdmData6;
    @SheetColumn("고등학교 학생수")
    private String cdmData7;
    @SheetColumn("고등학교 여성")
    private String cdmData8;
    @SheetColumn("고등학교 여성비율")
    private String cdmData9;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewB03TiileDataVO> listData;


}

















