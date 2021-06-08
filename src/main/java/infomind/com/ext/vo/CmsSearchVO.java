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
public class CmsSearchVO extends SiteSearchVO {

    /** Mode     */
    private String mode = "";

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
    
    private String regId;
    private String regDt;
    private String modId;
    private String modDt;

    private String strDay="";
    private String endDay="";
    private String nowDay="";


    private List<String> hourList;
    private List<String> minList;





    public String getNewDay(){

        if("".equals(this.nowDay)){

            return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        }
        return this.nowDay;
    }


    public String getStrDay(){

        if(StringUtils.isBlank(this.strDay)){
            Calendar cal = java.util.Calendar.getInstance();
            DateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
            cal.add(cal.DATE, -14); // 7일(일주일)을 뺀다
            String dateStr = format.format(cal.getTime());

            return dateStr;
        }

        return this.strDay;
    }


    public String getEndDay(){

        if(StringUtils.isBlank(this.endDay)){
            return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
        }

        return endDay;
    }


    public List<String> getHourList(){
        this.hourList = new ArrayList<String>();
        for(int i=0; i<24; i++){

            String hour ="";
            if(i<10){

                hour ="0"+i;
            }else{

                hour =""+i;
            }
            this.hourList.add(hour);
        }
        return this.hourList ;

    }

    public List<String> getMinList(){
        this.minList = new ArrayList<String>();
        for(int i=0; i<60; i++){

            String min ="";
            if(i<10){
                min ="0"+i;
            }else{
                min =""+i;
            }
            this.minList .add(min);

        }
        return this.minList ;

    }




    private Integer __index;
    private boolean __created__ = false;
    private boolean __modified__ = false;
    private boolean __deleted__ = false;

    public void setSearchAllPage() {
        this.firstIndex = 0;
        this.recordCountPerPage = Integer.MAX_VALUE;
        this.pageUnit = Integer.MAX_VALUE;
        this.pageSize = Integer.MAX_VALUE;
    }

}
