<%
/**
 * @Class Name : EgovSysLogDetail.jsp
 * @Description : 시스템 로그 정보 상세조회 화면
 * @Modification Information
 * @
 * @  수정일      수정자          수정내용
 * @  -------    --------       ---------------------------
 * @ 2009.03.11   이삼섭          최초 생성
 * @ 2011.07.08   이기하          패키지 분리, 경로수정(sym.log -> sym.log.lgm)
 * @ 2017.09.17   이정은          표준프레임워크 v3.7 개선
 * 
 *	@author 공통서비스 개발팀 이삼섭
 * @since 2009.03.11
 * @version 1.0
 * @see
 *
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="pageTitle"><spring:message code="comSymLogLgm.sysLog.title"/></c:set>
<!DOCTYPE html>
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">


<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript">
</script>




<div class="sub subView">

<h3 class="btitle"><spring:message code="title.list" /></h3>
<div class="rows white-box">
	<table class="landscape" style="table-layout:fixed;word-break:break-all;" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<!-- 메소드명 -->
		<tr>
			<th width="95"><spring:message code="comSymLogLgm.sysLog.methodNm" /></th>
			<td class="left" width="250"><c:out value="${result.logMethod}"/></td>

			<!-- 발생일자 -->
			<th width="85"><spring:message code="comSymLogLgm.sysLog.occrrncDe" /></th>
			<td class="left" ><c:out value="${result.logOccrrncDe}"/></td>
			<!-- 메뉴명 -->
			<th width="115">메뉴명</th>
			<td class="left" colspan="3" width="310"><c:out value="${result.menuNm}"/></td>
		</tr>

		<tr>
			<!-- 유저 아이디 -->
			<th>유저ID</th>
			<td class="left" colspan="3"><c:out value="${result.userId}"/></td>
			<!-- 유저 -->
			<th>유저</th>
			<td class="left" colspan="3"><c:out value="${result.userNm}"/></td>
		</tr>

		<tr>
			<!-- 처리구분 -->
			<th><spring:message code="comSymLogLgm.sysLog.processSeCode" /></th>
			<td class="left" colspan="3"><c:out value="${result.logProcessSeCode}"/></td>
		<!-- 처리시간 -->
			<th><spring:message code="comSymLogLgm.sysLog.processTime" /></th>
			<td class="left" colspan="3"><c:out value="${result.logProcessTime}"/></td>
		</tr>

		<tr>
			<!-- 컨트롤 -->
			<th>컨트롤</th>
			<td class="left" colspan="3"><c:out value="${result.logController}"/></td>
			<!-- 경로 -->
			<th>경로</th>
			<td class="left" colspan="3"><c:out value="${result.logUrl}"/></td>
		</tr>

		<tr>
			<!-- 타입 -->
			<th>타입</th>
			<td class="left"><c:out value="${result.logHttpMethod}"/></td>
			<!-- 프로세스 타입 -->
			<th>프로세스 타입</th>
			<td class="left"><c:out value="${result.logProcessSeCode}"/></td>
			<!-- 프로세스 처리시간 -->
			<th>프로세스 처리 시간</th>
			<td class="left" colspan="3"><c:out value="${result.logProcessTime}"/></td>
		</tr>

		<!-- 검색조건 대분류 -->
		<tr>
			<th>검색조건 대분류</th>
			<td class="left" colspan="3"><c:out value="${result.logSearchLcatId}"/></td>

			<th>검색조건 중분류</th>
			<td class="left" colspan="3"><c:out value="${result.logSearchMcatCd}"/></td>
		</tr>

		<tr>
			<th>검색조건</th>
			<td class="left" width="15%"><c:out value="${result.logSearchCondition}"/></td>
			<!-- 검색기관 -->
			<th>검색기관</th>
			<td class="left" width="15%"><c:out value="${result.logSearchOrgan}"/></td>
			<!-- 검색Keyword -->
			<th>검색Keyword</th>
			<td class="left" width="15%"><c:out value="${result.logSearchKeyword}"/></td>

			<!-- 검색 타입 -->
			<th width="80">검색 타입</th>
			<td class="left" width="15%"><c:out value="${result.logSbscrbSttus}"/></td>
		</tr>



		<!-- 현재페이지 -->
		<tr>
			<th>현재페이지</th>
			<td class="left" colspan="7"><c:out value="${result.logPageIndex}"/></td>
		</tr>
		<tr>
			<!-- 전체 파라미터 -->
			<th>전체 파라미터</th>
			<td class="left" nowrap colspan="7">
				<div style=width:100%;height:100%;overflow:auto>
					<c:out value="${result.logAllParams}"/>
				</div>
			</td>
		</tr>
	</table>
</div>
</div>
