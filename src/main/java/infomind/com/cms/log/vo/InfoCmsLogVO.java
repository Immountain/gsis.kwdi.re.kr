package infomind.com.cms.log.vo;


import infomind.com.ext.vo.CmsSearchVO;
import lombok.Data;

@Data
public class InfoCmsLogVO extends CmsSearchVO {
    /** 자동증가 */
    private String cmsLogSno;

    /** 발생일 */
    private String logOccrrncDe;

    /** 경로 */
    private String logUrl;

    /** 컨트롤 */
    private String logController;

    /** 타입 */
    private String logHttpMethod;

    /** 메소드 */
    private String logMethod;

    /** 요청자 */
    private String rqesterId;

    /** 요청자아이피 */
    private String rqesterIp;

    /** 검색조건-대분류 */
    private String logSearchLcatId;

    /** 검색조건-중분류 */
    private String logSearchMcatCd;

    /** 검색조건 */
    private String logSearchCondition;

    /** 검색기관 */
    private String logSearchOrgan;

    /** 검색keyword */
    private String logSearchKeyword;

    /** 검색_타입 */
    private String logSbscrbSttus;

    /** 검색사용여부 */
    private String logSearchUseYn;

    /** 검색기준년도 */
    private String logSearchYear;

    /** 검색 API 구분 */
    private String logSearchZeusApi;

    /** 검색시 정렬기준 */
    private String logSearcOrder;

    /** 메뉴번호 */
    private String logMenuTargetNo;

    /** 검색 전체 여부 */
    private String logSearchAllYn;

    /** 게시판 아이디 */
    private String logBoardId;

    /** 현재페이지 */
    private String logPageIndex;

    /** 페이지갯수 */
    private String logPageUnit;

    /** 페이지사이즈 */
    private String logPageSize;

    /** firstindex */
    private String logFirstIndex;

    /** lastindex */
    private String logLastIndex;

    /** recordCountPerPage */
    private String logRecordCountPerPage;

    /** 등록자 */
    private String logRegId;

    /** 수정자 */
    private String logModId;

    /** 시작일 */
    private String logStrDay;

    /** 종료일 */
    private String logEndDay;

    /** 현재일 */
    private String logNowDay;

    /** 현페이지상태 */
    private String logCmd;

    /** 전체 파라미터 */
    private String logAllParams;

    /** 처리구분 */
    private String logProcessSeCode;

    /** 처리시간 */
    private String logProcessTime;

    /** CUSTOM */

    private String userNm;

    private String userId;

    private String jtpOrganCd;

    private String jtpOrganCdNm;

    private String menuNm;
}
