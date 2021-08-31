package gsis.com.cms.datainfo.jgAtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewA06TiileDataVO {

    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;



    @SheetColumn("계")
    private String cdmData1;
    @SheetColumn("비율")
    private String cdmData2;

    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewA06TiileDataVO> listData;
}
