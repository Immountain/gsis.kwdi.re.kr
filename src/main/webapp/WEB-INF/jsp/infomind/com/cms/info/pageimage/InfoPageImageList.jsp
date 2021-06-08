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
<c:set var="pageTitle">이미지 관리</c:set>

<script type="text/javascript">

	$(document).ready(function() {


		fn_egov_init();

	});


/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.InfoPageImageGroupForm.searchCondition.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_info_select_linkPage(pageNo){
	document.InfoPageImageGroupForm.pageIndex.value = pageNo;
	document.InfoPageImageGroupForm.action = "<c:url value='/cms/info/pageimage/selectPageImageList.do'/>";
   	document.InfoPageImageGroupForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_info_search_page(){
	document.InfoPageImageGroupForm.pageIndex.value = 1;
	document.InfoPageImageGroupForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_pagedetail(imageId) {
	// 사이트 키값(siteId) 셋팅.
	document.InfoPageImageGroupForm.imageId.value = imageId;
  	document.InfoPageImageGroupForm.action = "<c:url value='/cms/info/pageimage/UpdatePageImageView.do'/>";
  	document.InfoPageImageGroupForm.submit();
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

			<form name="InfoPageImageGroupForm" action="<c:url value='/cms/info/pageimage/selectPageImageList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">


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
						<button type="button" class="button main" onclick="location.href='<c:url value='/cms/info/pageimage/RegistPageImageView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
					</div>

				</div>
				<h3 class="btitle">
                  <spring:message code="title.list" />


						<%--<img--%>
								<%--srcset="/cms/info/file/ImageThumbnailView.do?imageId=CEO&width=50&height=50 400w,--%>
										  <%--/cms/info/file/ImageThumbnailView.do?imageId=CEO&width=100&height=100 700w,--%>
										  <%--/cms/info/file/ImageThumbnailView.do?imageId=CEO&width=150&height=150 1000w"--%>
								<%--sizes="(max-width: 500px) 444px, (max-width: 800px) 777px,1222px"--%>
								<%--src="/cms/info/file/ImageView.do?imageId=CEO"--%>
								<%--alt="HEROPY" />--%>

				</h3>

				<div class="rows white-box">
					<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
						<thead>
							<tr>
								<th>번호</th><!-- 번호 -->
								<th>이미지 아이디</th>
								<th>이미지파일</th><!--  -->
								<th>이미지명</th><!--  -->
								<th>이미지</th><!--  -->
								<th>사용여부</th><!--  -->
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
							<td class="center"><a href="<c:url value='/cms/info/pageimage/UpdatePageImageView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&imageId=${resultInfo.imageId}" onClick="fn_pagedetail('<c:out value="${resultInfo.imageId}"/>','<c:out value="${resultInfo.imageId}"/>');return false;"><c:out value='${resultInfo.imageId}'/></a></td>
							<td class="center"><c:out value='${resultInfo.imageFile}'/></td>
							<td class="center"><c:out value='${resultInfo.imageNm}'/></td>
							<td class="center"><c:out value='${resultInfo.imageGb}'/></td>
							<td class="center"><c:out value='${resultInfo.useYn}'/></td>
						</tr>
						</c:forEach>
						</tbody>
					</table>

				<!-- paging navigation -->
					<article class="pagenation">
						<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
					</article>

				</div>
					<input name="imageId" type="hidden" value="">
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
					<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
			</form>
</div>
