<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">대회정보</c:set>

<script type="text/javascript">

	$(document).ready(function() {
		fn_egov_init();
	});


	/*********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init(){
		// 첫 입력란에 포커스..
		document.searchVO.searchKeyword.focus();
	}

	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fn_info_select_linkPage(pageNo){
		document.searchVO.pageIndex.value = pageNo;
		document.searchVO.action = "<c:url value='/cms/wj/festivity/popWjFestivityInfoList.do'/>";
		document.searchVO.submit();
	}
	/*********************************************************
	 * 조회 처리 함수
	 ******************************************************** */
	function fn_info_search_page(){
		document.searchVO.pageIndex.value = 1;
		document.searchVO.submit();
	}

	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>

	function fn_pagedetail(_festivityId, _festivityNm){

		var data ={
			festivityId : _festivityId,
			festivityNm : _festivityNm
		}

		ax5modal.callback(data);
		ax5modal.close();
	};
</script>

<div class="sub subView">
	<h3 class="btitle">
		대회정보 검색
	</h3>

	<form name="searchVO" action="<c:url value='/cms/wj/festivity/popWjFestivityInfoList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
							<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
							<option value="0"  <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> >대회아이디</option><!-- 코드ID -->
							<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >대회명</option><!-- 코드ID -->

					</select>
				</span>

				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
			</div>
		</div>

		<h3 class="btitle">대회정보 <spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
				<tr>
					<th>번호</th>
					<th>대회 아이디</th>
					<th>대회명</th>
					<th>대회메모</th>
					<th>페이지시작일</th>
					<th>페이지종료일</th>
				</tr>
				</thead>

				<!-- 목록영역 -->
				<tbody>
				<c:if test="${fn:length(list) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="item" varStatus="status">
					<tr>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fn_pagedetail('<c:out value="${item.festivityId}"/>','<c:out value="${item.festivityNm}"/>');"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fn_pagedetail('<c:out value="${item.festivityId}"/>','<c:out value="${item.festivityNm}"/>');"><c:out value="${item.festivityId}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fn_pagedetail('<c:out value="${item.festivityId}"/>','<c:out value="${item.festivityNm}"/>');"><c:out value="${item.festivityNm}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fn_pagedetail('<c:out value="${item.festivityId}"/>','<c:out value="${item.festivityNm}"/>');"><c:out value="${item.festivityNemo}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fn_pagedetail('<c:out value="${item.festivityId}"/>','<c:out value="${item.festivityNm}"/>');"><c:out value="${item.pageStrDay}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fn_pagedetail('<c:out value="${item.festivityId}"/>','<c:out value="${item.festivityNm}"/>');"><c:out value="${item.pageEndDay}"/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>

		</div>
		<input name="festivityId" type="hidden" value="">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
