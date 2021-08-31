package gsis.com.cms.datainfo.jgBtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewB02TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("수눌음 육아나눔터 신규")
    private String cdmData1;
    @SheetColumn("수눌음 육아나눔터 누계")
    private String cdmData2;
    @SheetColumn("사회적 돌봄공동체 참여팀 신규")
    private String cdmData3;
    @SheetColumn("사회적 돌봄공동체 참여팀 누계")
    private String cdmData4;
    @SheetColumn("사회적 참여가족 신규")
    private String cdmData5;
    @SheetColumn("사회적 참여가족 누계")
    private String cdmData6;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewB02TiileDataVO> listData;


}

















