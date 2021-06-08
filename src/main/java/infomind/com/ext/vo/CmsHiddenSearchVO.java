package infomind.com.ext.vo;

import infomind.com.site.vo.SiteSearchVO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class CmsHiddenSearchVO {

    /** 검색조건-대분류     */
    private String searchlcatid = "";

    /** 검색조건-중분류     */
    private String searchmcatcd = "";

    /** 검색조건 */
    private String searchCondition = "";

    /** 검색기관*/
    private String searchOrgan = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    private String sbscrbSttus = "";

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 검색기준년도 */
    private String searchYear = "";

    /** 검색 API 구분 */
    private String searchZeusApi = "";
    /** 검색시 정렬기준 */
    private String searcOrder = "";
    /** 메뉴번호 */
    private String menuTargetNo = "";


    /** 검색 전체 여부 */
    private String searchAllYn="";


    /** 게시판 아이디 */
    private String boardId = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;



    private String strDay="";
    private String endDay="";
    private String nowDay="";

    public CmsHiddenSearchVO(CmsSearchVO vo){

        /** 검색조건-대분류     */
        this.searchlcatid = vo.getSearchlcatid();

        /** 검색조건-중분류     */
        this.searchmcatcd = vo.getSearchmcatcd();

        /** 검색조건 */
        this.searchCondition = vo.getSearchCondition();

        /** 검색기관*/
        this.searchOrgan = vo.getSearchOrgan();

        /** 검색Keyword */
        this.searchKeyword = vo.getSearchKeyword();

        this.sbscrbSttus = vo.getSbscrbSttus();

        /** 검색사용여부 */
        this.searchUseYn = vo.getSearchUseYn();

        /** 검색기준년도 */
        this.searchYear = vo.getSearchYear();

        /** 검색 API 구분 */
        this.searchZeusApi = vo.getSearchZeusApi();
        /** 검색시 정렬기준 */
        this.searcOrder = vo.getSearcOrder();
        /** 메뉴번호 */
      //  this.menuTargetNo = vo.getMenuTargetNo();


        /** 검색 전체 여부 */
        this.searchAllYn=vo.getSearchAllYn();


        /** 게시판 아이디 */
        this.boardId = vo.getBoardId();

        /** 현재페이지 */
        this.pageIndex = vo.getPageIndex();

        /** 페이지갯수 */
        this.pageUnit = vo.getPageUnit();

        /** 페이지사이즈 */
        this. pageSize = vo.getPageSize();

        /** firstIndex */
        this.firstIndex = vo.getFirstIndex();

        /** lastIndex */
        this.lastIndex = vo.getLastIndex();

        this.recordCountPerPage =vo.getRecordCountPerPage();



        this.strDay=vo.getStrDay();
        this.endDay=vo.getEndDay();
        this.nowDay=vo.getNowDay();



    }


}
