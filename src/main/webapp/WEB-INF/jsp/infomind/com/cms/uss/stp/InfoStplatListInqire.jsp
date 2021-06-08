<%
 /**
  * @Class Name : InfoStplatListInqire.jsp
  * @Description : InfoStplatListInqire 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.12.03   이기선              최초 생성
  *  @author 인포마인드 개발팀
  *  @since 2020.12.03
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssSamStp.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
<script type="text/javaScript" language="javascript" defer="defer">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.StplatListForm.searchCondition.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.StplatListForm.pageIndex.value = pageNo;
	document.StplatListForm.action = "<c:url value='/cms/uss/stp/InfolatListInqire.do'/>";
   	document.StplatListForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_stplatcn(){
	document.StplatListForm.pageIndex.value = 1;
	document.StplatListForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_stplatdetail(useStplatId) {
	// 사이트 키값(siteId) 셋팅.
	document.StplatListForm.useStplatId.value = useStplatId;
  	document.StplatListForm.action = "<c:url value='/cms/uss/stp/InfoStplatCnUpdtView.do'/>";
  	document.StplatListForm.submit();
}

function fnAddUserView() {
	document.StplatListForm.action = "<c:url value='/cms/uss/stp/InfoStplatCnRegistView.do'/>";
	document.StplatListForm.submit();
}

</script>
</head>
<body onload="fn_egov_init()">
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

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


	<form name="StplatListForm" action="<c:url value='/cms/uss/stp/InfoStplatListInqire.do'/>" method="post" onSubmit="fn_egov_search_stplatcn(); return false;">

	<!-- 검색영역 -->
	<div class="white-box">
		<div class="rows">
		<span class="select-outline">
			<select name="searchCondition" id="searchCondition" title="<spring:message code="comUssUmt.userManageSsearch.searchConditioTitle" />"><!--  -->
				<option value="useStplatNm" <c:if test="${searchVO.searchCondition == 'useStplatNm'}">selected="selected"</c:if> ><spring:message code="comUssSamStp.list.useStplatNm" /></option><!-- 약관명 -->
				<option value="useStplatCn" <c:if test="${searchVO.searchCondition == 'useStplatCn'}">selected="selected"</c:if> ><spring:message code="comUssSamStp.list.useStplatCn" /></option><!-- 약관내용 -->
			</select>
		 </span>
		 <input class="button" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
		 <input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
		 <button type="button" class="button main" name="btn_regist" id="btn_regist" onClick="fnAddUserView(); return false;"
				title="<spring:message code="button.create" /> <spring:message code="input.button" />">
			<spring:message code="button.create"/></button>
		 </div>


	</div>
	<input name="useStplatId" type="hidden" value="">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
	</form>

	<h3 class="btitle">${pageTitle}<spring:message code="title.list" /></h3>


	<div class="rows white-box">

	<!-- 목록영역 -->
	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<colgroup>
		<col style="width: 9%;">
		<col style="width: 20%;">
		<col style="width: ;">
		<col style="width: 13%;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<th><spring:message code="comUssSamStp.list.useStplatNm" /></th><!-- 약관명 -->
		<th><spring:message code="comUssSamStp.list.useStplatCn" /></th><!-- 약관내용 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
	</tr>
	</thead>
	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="4"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
	<tr>
		<td class="center"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
		<td class="center"><c:out value="${resultInfo.useStplatNm}"/></td>
		<td class="center"><a href="<c:url value='/cms/uss/stp/InfoStplatCnUpdtView.do'/>?useStplatId=<c:out value="${resultInfo.useStplatId}"/>" onclick="javascript:fn_egov_inquire_stplatdetail('<c:out value="${resultInfo.useStplatId}"/>');"><c:out value='${fn:substring(resultInfo.useStplatCn, 0, 50)}'/></a></td>

		<td class="center"><c:out value='${resultInfo.frstRegistPnttm}'/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>

	<!-- paging navigation -->
	<article class="pagenation">
		<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_egov_select_linkPage"/>
	</article>

	<!-- 등록버튼 -->
	<!-- 
	<div class="btn">
		<span class="btn_s"><a href="<c:url value='/cms/uss/stp/InfolatCnRegistView.do' />"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span>
	</div>
	 -->
	
</div><!-- end div board -->



</body>
</html>