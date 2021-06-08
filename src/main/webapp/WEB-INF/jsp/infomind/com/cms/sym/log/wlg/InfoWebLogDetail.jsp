<%
/**
 * @Class Name : EgovWebLogDetail.jsp
 * @Description : 웹로그 정보 상세조회 화면
 * @Modification Information
 * @
 * @  수정일      수정자          수정내용
 * @  -------    --------       ---------------------------
 * @ 2009.03.11   이삼섭          최초 생성
 * @ 2011.07.08   이기하          패키지 분리, 경로수정(sym.log -> sym.log.wlg)
 * @ 2017.09.21   이정은          표준프레임워크 v3.7 개선
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
<c:set var="pageTitle"><spring:message code="comSymLogWlg.webLog.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.detail" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/popup_com.css' />">
<script type="text/javascript">
</script>
</head>
<body>

<div class="sub subView">
	<!-- 타이틀 -->

	<!-- 상세조회 -->
	<h3 class="btitle"><spring:message code="title.detail" /></h3>
	<div class="rows white-box">
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<tbody>

		<tr>
			<th>타켓아이디</th>
			<td class="left"><c:out value="${result.targetId}"/></td>
			<th>아이피</th>
			<td class="left"><c:out value="${result.ip}"/></td>
		</tr>

		<tr>
			<th>방문일</th>
			<td class="left"><c:out value="${result.tempVdate}"/></td>
			<th>OS</th>
			<td class="left"><c:out value="${result.os}"/></td>
		</tr>

		<tr>
			<th>브라우저</th>
			<td class="left"><c:out value="${result.browser}"/></td>
			<th>타켓브라우저</th>
			<td class="left"><c:out value="${result.browserS}"/></td>
		</tr>
			<th>URL</th>
			<td class="left"><c:out value="${result.siteUrl}"/></td>
			<th>이전URL</th>
			<td class="left"><c:out value="${result.refererUrl}"/></td>
		<tr>
			<th>위치</th>
			<td class="left"><c:url value="${result.loc}"/></td>
			<th>방문타입</th>
			<td class="left"><c:url value="${result.visitType}"/></td>
		</tr>


	</tbody>
	</table>
	</div>
	<!-- 닫기 버튼 -->


</div>
</body>
</html>
