package infomind.com.cms.sym.log.ulg.vo;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @Class Name : UserLog.java
 * @Description : 사용자 로그 관리를 위한 VO 클래스
 * @Modification Information
 *
 *    수정일          수정자         수정내용
 *    -------         -------     -------------------
 *    2009. 3. 11.    이삼섭        최초생성
 *    2011. 7. 01.    이기하        패키지 분리(sym.log -> sym.log.ulg)
 *    2011.09.14       서준식      화면에 검색일자를 표시하기위한 멤버변수 추가.
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 3. 11.
 * @version
 * @see
 *
 */
@Data
public class InfoUserLogVO implements Serializable {

	private static final long serialVersionUID = -3030641254553776910L;

	/**
	 * 요청자아이디
	 */
	private String rqesterId = "";


	/**
	 * 서비스명
	 */
	private String srvcNm = "";

	/**
	 * 출력횟수
	 */
	private String outptCo = "";
	/**
	 * 에러횟수
	 */
	private String errorCo = "";
	/**
	 * 검색시작일
	 */
	private String searchBgnDe = "";
	/**
	 * 검색조건
	 */
	private String searchCnd = "";
	/**
	 * 검색종료일
	 */
	private String searchEndDe = "";
	/**
	 * 검색단어
	 */
	private String searchWrd = "";
	/**
	 * 정렬순서(DESC,ASC)
	 */
	private String sortOrdr = "";

	/** 검색사용여부 */
    private String searchUseYn = "";

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

    /** rowNo  */
	private int rowNo = 0;

	/**
	 * 검색시작일_화면용
	 */
	private String searchBgnDeView = "";//2011.09.14

	/**
	 * 검색종료일_화면용
	 */
	private String searchEndDeView = "";//2011.09.14


	private String logOccrrncDe="";

	private String userNm="";

	private String logMethod="";

	private String insertCnt="";

	private String updateCnt="";

	private String listCnt="";

	private String deleteCnt="";

	public String getSearchEndDeView() {
		return searchEndDeView;
	}
	public void setSearchEndDeView(String searchEndDeView) {
		this.searchEndDeView = searchEndDeView;
	}
	public String getSearchBgnDeView() {
		return searchBgnDeView;
	}
	public void setSearchBgnDeView(String searchBgnDeView) {
		this.searchBgnDeView = searchBgnDeView;
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}



	public String getRqesterId() {
		return rqesterId;
	}

	public void setRqesterId(String rqesterId) {
		this.rqesterId = rqesterId;
	}

	public String getSrvcNm() {
		return srvcNm;
	}

	public void setSrvcNm(String srvcNm) {
		this.srvcNm = srvcNm;
	}

	public String getOutptCo() {
		return outptCo;
	}

	public void setOutptCo(String outptCo) {
		this.outptCo = outptCo;
	}

	public String getErrorCo() {
		return errorCo;
	}

	public void setErrorCo(String errorCo) {
		this.errorCo = errorCo;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}

	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	public String getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(String sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

}
