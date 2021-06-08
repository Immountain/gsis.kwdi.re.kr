<%
 /**
  * @Class Name : InfoCcmCmmnCodeList.jsp
  * @Description : 공통코드 목록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.10.19   양진혁              최초 생성
  *  @author 인포마인드 개발팀
  *  @since 2020.10.19
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle">페이지관리</c:set>

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
	document.searchVO.action = "<c:url value='/cms/info/page/selectPageInfoList.do'/>";
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
 * 상세회면 처리 함수
 ******************************************************** */
function fn_pagedetail(pageId) {
	// 사이트 키값(siteId) 셋팅.
	document.searchVO.pageId.value = pageId;
  	document.searchVO.action = "<c:url value='/cms/info/page/UpdatePageInfoView.do'/>";
  	document.searchVO.submit();
}
	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>

	function fnSearchHistory(pageId){
		var p = {
			menuTargetNo :${menuInfo.menuTargetNo},
			pageId : pageId
		};
		var API_SERVER = "<c:url value='/cms/info/page/popInfoPageInfoHisList.do' />";
		ax5modal.open({
			theme: "primary",
			height: 500,
			width: 1167,
			header: {
				title: 'history',
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
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>페이지관리 <spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
		검색

	</h3>

			<form name="searchVO" action="<c:url value='/cms/info/page/selectPageInfoList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">


				<!-- 검색영역 -->
				<!-- 검색조건선택 -->
			<div class="white-box">
					<div class="rows">
						<span class="select-outline">
						<select name="pageGroupId" title="<spring:message code="title.searchCondition" />">
							<option selected value=''>선택하세요</option><!-- 선택하세요 -->
							<c:forEach items="${list_group}" var="item" varStatus="status">
								<option value="${item.pageGroupId}"  <c:if test="${searchVO.pageGroupId == item.pageGroupId}">selected="selected"</c:if> >${item.pageGroupNm}</option><!-- 페이지그룹 -->
							</c:forEach>
						</select>
						</span>

						<span class="select-outline">
										 <select name="pageType" title="<spring:message code="title.searchCondition" />">
												<option selected value=''>선택하세요</option><!-- 선택하세요 -->
												<option value="page"  <c:if test="${searchVO.pageType == 'page'}">selected="selected"</c:if> >page</option><!-- 코드ID -->
												<option value="include"  <c:if test="${searchVO.pageType == 'include'}">selected="selected"</c:if> >include</option><!-- 코드ID -->

										</select>
						</span>

						<span class="select-outline">
										 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
												<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
												<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >아이디</option><!-- 코드ID -->
												<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >명칭</option><!-- 코드ID -->

										</select>
						</span>

						<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
						<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
						<button type="button" class="button main" onclick="location.href='<c:url value='/cms/info/page/RegistPageInfoView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
					</div>

				</div>
				<h3 class="btitle">
					<spring:message code="title.list" />
				</h3>

				<div class="rows white-box">
					<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
						<thead>
							<tr>
								<th>번호</th><!-- 번호 -->
								<th>페이지 그룹</th>
								<th>페이지 아이디</th>
								<th>페이지 타입</th>
								<th>레이아웃아이디</th><!-- 코드ID -->
								<th>페이지명칭</th><!-- 코드ID -->
								<th>실행환경</th><!-- 사용여부 -->
								<th>사용여부</th><!-- 사용여부 -->
								<th>history</th>
							</tr>
						</thead>
						<!-- 목록영역 -->
						<tbody>
						<c:if test="${fn:length(list_progrmmanage) == 0}">
						<tr>
							<td colspan="6"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
						<c:forEach items="${list_progrmmanage}" var="resultInfo" varStatus="status">
						<tr>
							<td class="center"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
							<td class="center"><c:out value='${resultInfo.pageGroupNm}'/></td>
							<td class="center">
								<a href="<c:url value='/cms/info/page/UpdatePageInfoView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&pageId=${resultInfo.pageId}" onClick="fn_pagedetail('<c:out value="${resultInfo.pageId}"/>','<c:out value="${resultInfo.pageId}"/>');return false;">
									<c:out value='${resultInfo.pageId}'/> <i class='bx bxs-wrench'></i>
								</a></td>

							<td class="center"><c:out value='${resultInfo.pageType}'/></td>
							<td class="center"><c:out value='${resultInfo.layoutId}'/></td>
							<td class="center"><c:out value='${resultInfo.pageNm}'/></td>
							<td class="center"><c:out value='${resultInfo.modType}'/></td>
							<td class="center"><c:out value='${resultInfo.useYn}'/></td>
							<td class="center">
								<button type="button" class="button main" onclick="fnSearchHistory('<c:out value="${resultInfo.pageId}"/>'); return false;"<spring:message code="input.button" />">보기</button>
							</td>
						</tr>
						</c:forEach>
						</tbody>
					</table>

				<!-- paging navigation -->
					<article class="pagenation">
						<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
					</article>

				</div>
					<input name="pageId" type="hidden" value="">
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
					<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
			</form>
</div>
