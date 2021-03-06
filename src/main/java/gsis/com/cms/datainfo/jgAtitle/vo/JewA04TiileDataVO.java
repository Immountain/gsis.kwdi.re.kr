package gsis.com.cms.datainfo.jgAtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewA04TiileDataVO {

    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;



    @SheetColumn("출생건수")
    private String cdmData1;
    @SheetColumn("조출생률")
    private String cdmData2;
    @SheetColumn("사망건수")
    private String cdmData3;
    @SheetColumn("조사망률")
    private String cdmData4;


    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewA04TiileDataVO> listData;
}
