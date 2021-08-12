package gsis.com.cms.datainfo.jgEtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewE04TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("전체")
    private String cdmData1;
    @SheetColumn("5급이상 공무원")
    private String cdmData2;
    @SheetColumn("6급이상 공무원")
    private String cdmData3;
    @SheetColumn("6급 공무원")
    private String cdmData4;




    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewE04TiileDataVO> listData;


}

















