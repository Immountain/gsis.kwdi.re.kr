<%
/**
 * @Class Name : EgovloginLogList.jsp
 * @Description : 접속로그 정보목록 화면
 * @Modification Information
 * @
 * @  수정일      수정자          수정내용
 * @  -------     --------    ---------------------------
 * @ 2009.03.11   이삼섭          최초 생성
 * @ 2011.07.08   이기하          패키지 분리로 경로 수정(sym.log -> sym.log.clg)
 * @ 2011.09.14   서준식          검색 후 화면에 검색일자 표시안되는 오류 수정
 * @ 2017.09.21   이정은          표준프레임워크 v3.7 개선
 * @author 공통서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comSymLogClg.loginLog.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script type="text/javascript">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.LoginLogForm.searchWrd.focus();
}
/* ********************************************************
 * 달력
 ******************************************************** */
function fn_egov_init_date(){
	
	$("#searchBgnDe").datepicker(  
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
	         , buttonImageOnly: true
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});


	$("#searchEndDe").datepicker( 
	        {dateFormat:'yy-mm-dd'
	         , showOn: 'button'
	         , buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'  
	         , buttonImageOnly: true
	         
	         , showMonthAfterYear: true
	         , showOtherMonths: true
		     , selectOtherMonths: true
				
	         , changeMonth: true // 월선택 select box 표시 (기본은 false)
	         , changeYear: true  // 년선택 selectbox 표시 (기본은 false)
	         , showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
	});
}
/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.LoginLogForm.pageIndex.value = pageNo;
	document.LoginLogForm.action = "<c:url value='/cms/sym/log/clg/InfoSelectLoginLogList.do'/>";
   	document.LoginLogForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_loginLog(){
	var vFrom = document.LoginLogForm;

	 if(vFrom.searchEndDe.value != ""){
	     if(vFrom.searchBgnDe.value > vFrom.searchEndDe.value){
	         alert("<spring:message code="comSymLogClg.validate.dateCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
	         return;
		  }
	 }else{
		 vFrom.searchEndDe.value = "";
	 }

	vFrom.pageIndex.value = "1";
	vFrom.action = "<c:url value='/cms/sym/log/clg/InfoSelectLoginLogList.do'/>";
	vFrom.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_detail_loginLog(logId) {
	var p = {
		logId: logId,


	};
	var API_SERVER = "<c:url value='/cms/sym/log/clg/InfoSelectLoginLogDetail.do' />?logId="+logId;
	ax5modal.open({
		theme: "primary",
		height: 500,
		width: 800,
		header: {
			title: '접속로그 상세조회',
			btns: {
				close: {
					label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
						// modal.close();
						ax5modal.close();
					}
				}
			}
		},
		iframe: {
			method: "get",
			url: API_SERVER,
			param: p
		},
	}, function (d) {

	});
}
</script>
	<div class="sub subView">
		<nav class="navigation">
			<i class='bx bxs-home'></i>${menuInfo.depthFullname}</a>
		</nav>
		<h2 class="stitle">
			<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
		</h2>

		<h3 class="btitle">
			검색
		</h3>
<form name="LoginLogForm" action="<c:url value='/cms/sym/log/clg/InfoSelectLoginLogList.do'/>" method="post" onSubmit="fn_egov_search_loginLog(); return false;">
	<div class="white-box">
		<div class="rows">
				<div>
					<div style="line-height:6px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>발생일자 :  <!-- 로그유형-->
					<input type="date" id="searchBgnDe" name="searchBgnDe" value="${searchVO.searchBgnDe}"><u class="be">~</u><input type="date" id="searchEndDe" name="searchEndDe" value="${searchVO.searchEndDe}">
				</div>
				<div style="line-height:6px;">&nbsp;&nbsp;&nbsp;&nbsp;</div><spring:message code="comSymLogClg.loginLog.loginMthd" /> :  <!-- 로그유형-->
			<!-- 검색키워드 및 조회버튼 -->
				<input class="button" name="searchWrd" type="text"  size="15" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchWrd}"/>'  maxlength="15" >
				<input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
		</div>
	</div>
	<h3 class="btitle"><spring:message code="title.list" /></h3>

	<!-- 목록영역 -->
	<div class="rows white-box">
		<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comSymLogClg.loginLog.logId" /></th><!-- 로그ID -->
		<th><spring:message code="comSymLogClg.loginLog.occrrncDe" /></th><!-- 발생일자 -->
		<th><spring:message code="comSymLogClg.loginLog.loginMthd" /></th><!-- 로그유형 -->
		<th><spring:message code="comSymLogClg.loginLog.loginNm" /></th><!-- 사용자 -->
		<th>기업/기관</th>
		<th><spring:message code="comSymLogClg.loginLog.loginIp" /></th><!-- 접속IP -->
		<th><spring:message code="comSymLogClg.loginLog.detail" /></th><!-- 상세보기 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="7"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="result" varStatus="status">
	<tr>
		<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td><c:out value='${result.logId}'/></td>
		<td><c:out value='${fn:substring(result.creatDt,0,10)}'/></td>
		<td><c:out value='${result.loginMthd}'/></td>
		<td><c:out value='${result.loginNm}'/></td>
		<td><c:out value='${result.organNm}'/></td>
		<td><c:out value='${result.loginIp}'/></td>
		<td>
		<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>"  class="cursor" onclick="fn_egov_detail_loginLog('<c:out value="${result.logId}"/>'); return false;" alt="<spring:message code="title.detail" />"  title="<spring:message code="title.detail" />">

		</td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	</div>
	<!-- paging navigation -->
	<article class="pagenation">
		<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
	</article>

<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>

	</div>