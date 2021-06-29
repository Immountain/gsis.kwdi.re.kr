<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">통계소식 게시판</c:set>

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
	document.searchVO.action = "<c:url value='/cms/info/board/c/boardItemList.do'/>";
   	document.searchVO.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_info_search_page(){
	document.searchVO.pageIndex.value = 1;
	document.searchVO.submit();
}

/* ********************************************************
* 등록회면 처리 함수
******************************************************** */
function fn_Regist() {


	document.searchVO.action = "<c:url value='/cms/info/board/c/RegistBoardItemView.do'/>";
	document.searchVO.submit();
}


/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_detail(itemId) {

	var  readYnCnt = '${menuInfo.authVO.readYn}';
	if(readYnCnt>0){


		// 사이트 키값(siteId) 셋팅.
		document.searchVO.itemId.value = itemId;
		document.searchVO.action = "<c:url value='/cms/info/board/c/boardItemView.do'/>";
		document.searchVO.submit();
	}else{
		alert("보기 권한이 없습니다.");
		return;

	}



}
	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
</script>

<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i><spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
		검색
	</h3>

	<form name="searchVO" action="<c:url value='/cms/info/board/c/boardItemList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
							<option selected value=''><spring:message code="input.select" /></option>
							<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >제목</option>
						   <option value="2"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >메모</option>
					</select>
				</span>

				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>

				<c:if test="${menuInfo.authVO.writeYn >0}">
					<button type="button" class="button main" onclick="fn_Regist()"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>

				</c:if>

			</div>
		</div>

		<h3 class="btitle">${pageTitle}<spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>메모</th>
						<th>등록자</th>
						<th>등록일</th>
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
							<td class="center"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
							<td class="center">
								<a href="<c:url value='/cms/info/board/c/boardItemView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&itemId=${item.itemId}" onClick="fn_detail('<c:out value="${item.itemId}"/>','<c:out value="${item.itemId}"/>');return false;">
									<c:out value="${item.title}"/>
								</a>
							</td>
							<td class="center"><c:out value="${item.memo}"/></td>
							<td class="center"><c:out value="${item.regNm}"/></td>
							<td class="center"><c:out value="${item.regDt}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>

			</div>
		<input name="itemId" type="hidden" value="">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="boardId" id="boardId" type="hidden" value="${searchVO.boardId}">
		<input name="cmd" type="hidden" value="<c:out value='list'/>">
	</form>
</div>
