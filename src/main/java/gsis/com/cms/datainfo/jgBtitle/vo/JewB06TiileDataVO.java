package gsis.com.cms.datainfo.jgBtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewB06TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("학교")
    private String cdmData1;
    @SheetColumn("교장")
    private String cdmData2;
    @SheetColumn("교감")
    private String cdmData3;
    @SheetColumn("수석교사")
    private String cdmData4;
    @SheetColumn("보직교사")
    private String cdmData5;
    @SheetColumn("평교사")
    private String cdmData6;
    @SheetColumn("계")
    private String cdmData7;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewB06TiileDataVO> listData;


}

















