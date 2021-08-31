package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC06TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("계")
    private String cdmData1;
    @SheetColumn("관리자")
    private String cdmData2;
    @SheetColumn("전문가 및 관련 종사자")
    private String cdmData3;
    @SheetColumn("사무 종사자")
    private String cdmData4;
    @SheetColumn("서비스 종사자")
    private String cdmData5;
    @SheetColumn("판매 종사자")
    private String cdmData6;
    @SheetColumn("농림어업숙련 종사자")
    private String cdmData7;
    @SheetColumn("기능원 및 관련 종사자")
    private String cdmData8;
    @SheetColumn("장치, 기계조작 및 조립 종사자")
    private String cdmData9;
    @SheetColumn("단순 노무 종사자")
    private String cdmData10;









    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC06TiileDataVO> listData;


}

















