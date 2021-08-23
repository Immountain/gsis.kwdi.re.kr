package gsis.com.cms.datainfo.jgEtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewE05TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("인구 1인당 평균 독서 권수")
    private String cdmData1;
    @SheetColumn("독서인구 비율")
    private String cdmData2;
    @SheetColumn("독서 평균권수")
    private String cdmData3;
    @SheetColumn("잡지류")
    private String cdmData4;
    @SheetColumn("잡지류 평균권수")
    private String cdmData5;
    @SheetColumn("교양서적")
    private String cdmData6;
    @SheetColumn("교양서적 평균권수")
    private String cdmData7;
    @SheetColumn("직업관련서적")
    private String cdmData8;
    @SheetColumn("직업관련서적 평균권수")
    private String cdmData9;
    @SheetColumn("생활취미 정보서적")
    private String cdmData10;
    @SheetColumn("생활취미 정보서적 평균권수")
    private String cdmData11;
    @SheetColumn("기타")
    private String cdmData12;
    @SheetColumn("기타 평균권수")
    private String cdmData13;




    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewE05TiileDataVO> listData;


}

















