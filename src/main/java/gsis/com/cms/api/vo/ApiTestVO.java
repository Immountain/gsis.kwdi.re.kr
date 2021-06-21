package gsis.com.cms.api.vo;

import lombok.Data;

@Data
public class ApiTestVO {


    private String method ="";
    private String type ="";
    private String orgId ="";
    private String tblId ="";
    private String apiKey ="";
    /**
     * 사용자 등록 통계표
     */
    private String userStatsId ="";
    /**
     * 수록주기
     */
    private String prdSe ="Y";
    /**
     * 시점기준_시작수록시점
     */
    private String startPrdDe ="";
    /**
     * 시점기준_종료수록시점
     */
    private String endPrdDe ="";
    /**
     * 최신자료기준_최근수록시점 개수
     */
    private String newEstPrdCnt ="1";
    /**
     * 최신자료기준_수록시점 간격
     */
    private String prdInterval ="";
    /**
     * 결과 유형(json)
     */
    private String format ="json";

    private String jsonVD ="Y";


}
