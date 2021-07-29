package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC01TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분 경제활동인구")
    private String dataGb;


    @SheetColumn("경제활동인구")
    private String cdmData1;
    @SheetColumn("경제활동참가율")
    private String cdmData2;



    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC01TiileDataVO> listData;


}

















