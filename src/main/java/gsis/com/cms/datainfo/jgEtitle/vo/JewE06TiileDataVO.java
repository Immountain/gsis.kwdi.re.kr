package gsis.com.cms.datainfo.jgEtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewE06TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("문화예술 및 스포츠 관람")
    private String cdmData1;
    @SheetColumn("국내관광여행")
    private String cdmData2;
   




    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewE06TiileDataVO> listData;


}

















