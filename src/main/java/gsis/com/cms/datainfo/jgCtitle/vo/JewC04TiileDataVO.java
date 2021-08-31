package gsis.com.cms.datainfo.jgCtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewC04TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("결혼")
    private String cdmData1;
    @SheetColumn("임신/출산")
    private String cdmData2;
    @SheetColumn("육아")
    private String cdmData3;
    @SheetColumn("자녀교육")
    private String cdmData4;
    @SheetColumn("가족돌봄")
    private String cdmData5;








    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewC04TiileDataVO> listData;


}

















