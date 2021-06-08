<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">배너 그룹</c:set>

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
	document.searchVO.action = "<c:url value='/cms/info/banner/bannerGroupList.do'/>";
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
function fn_pagedetail(bannerGroupId) {
	// 사이트 키값(siteId) 셋팅.
	document.searchVO.bannerGroupId.value = bannerGroupId;
  	document.searchVO.action = "<c:url value='/cms/info/banner/UpdateBannerGroupView.do'/>";
  	document.searchVO.submit();
}
	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
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

	<form name="searchVO" action="<c:url value='/cms/info/banner/bannerGroupList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
							<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
							<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >아이디</option><!-- 코드ID -->
							<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >명칭</option><!-- 코드ID -->


					</select>
				</span>

				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
				<button type="button" class="button main" onclick="location.href='<c:url value='/cms/info/banner/RegistBannerGroupView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
			</div>
		</div>

		<h3 class="btitle"><spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
					<tr>
						<%--
						BANNER_GROUP_ID is '배너 그룹 아이디'
						BANNER_GROUP_NM is '배너 그룹 명'
						BANNER_GROUP_TXT is '배너 그룹 설명'
						BANNER_GROUP_IMAGE is '배너 그룹 이미지'
						STYLE_CLASS is '배너 그룹 스타일'
						USE_YN is '배너 그룹 사용여부'
						DELETE_YN is '배너 그룹 삭제여부'
						TEMP1 is '임시필드1'
						TEMP2 is '임시필드2'
						TEMP3 is '임시필드3'
						TEMP4 is '임시필드4'
						TEMP5 is '임시필드5'
						REG_ID is '등록자'
						REG_DT is '등록일'
						MOD_ID is '수정자'
						MOD_DT is '수정일'
						--%>
						<th>번호</th>
						<th>배너 그룹 아이디</th>
						<th>배너 그룹 명</th>
						<th>배너 그룹 사용여부</th>
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
								<a href="<c:url value='/cms/info/banner/UpdateBannerGroupView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&bannerGroupId=${item.bannerGroupId}" onClick="fn_pagedetail('<c:out value="${item.bannerGroupId}"/>','<c:out value="${item.bannerGroupId}"/>');return false;">
									<c:out value='${item.bannerGroupId}'/>
								</a>
							</td>
							<td class="center"><c:out value='${item.bannerGroupNm}'/></td>
							<td class="center"><c:out value='${item.useYn}'/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>

			</div>
		<input name="bannerGroupId" type="hidden" value="">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
