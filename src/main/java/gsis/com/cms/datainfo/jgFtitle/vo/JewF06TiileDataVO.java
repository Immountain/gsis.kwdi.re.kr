package gsis.com.cms.datainfo.jgFtitle.vo;

import infomind.com.utils.poi.ss.model.annotations.SheetColumn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

@Data
@ToString
public class JewF06TiileDataVO {



    @SheetColumn("년도")
    private String dataYear;
    @SheetColumn("구분")
    private String dataGb;


    @SheetColumn("대중교통 이용")
    private String cdmData1;
    @SheetColumn("재활용품 분리배출")
    private String cdmData2;
    @SheetColumn("음식물 쓰레기 줄이기")
    private String cdmData3;
    @SheetColumn("합성세제 사용 줄이기")
    private String cdmData4;
    @SheetColumn("일회용품 사용하지 않기")
    private String cdmData5;
    @SheetColumn("친환경 제품 구입·사용하기")
    private String cdmData6;
    @SheetColumn("자연보호 및 환경보전활동 참여하기")
    private String cdmData7;
    @SheetColumn("물 절약하기")
    private String cdmData8;
    @SheetColumn("가정 내 대기전력 줄이기")
    private String cdmData9;

    private String regId="";
    private String regDt="";


    private String strYear="";
    private String endYear="";

    private ArrayList<JewF06TiileDataVO> listData;


}

















