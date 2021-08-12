package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC08TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("전체 산업체")
    private String cdmData1;
    @SheetColumn("여성 대표자 산업체")
    private String cdmData2;
    @SheetColumn("여성대표자 비율")
    private String cdmData3;
    @SheetColumn("전체 종사자")
    private String cdmData4;
    @SheetColumn("여성 종사자")
    private String cdmData5;
    @SheetColumn("여성 종사자 비율")
    private String cdmData6;








    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC08TiileDataVO> listData;


}

















