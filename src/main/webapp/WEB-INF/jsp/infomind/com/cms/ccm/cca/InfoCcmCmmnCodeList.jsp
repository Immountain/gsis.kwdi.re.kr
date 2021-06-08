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
<c:set var="pageTitle"><spring:message code="comSymCcmCca.cmmnCodeVO.title"/></c:set>

<script type="text/javascript">

	$(document).ready(function() {


		fn_egov_init();

	});


/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){
	// 첫 입력란에 포커스..
	document.CcmCodeForm.searchCondition.focus();
}

/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_egov_select_linkPage(pageNo){
	document.CcmCodeForm.pageIndex.value = pageNo;
	document.CcmCodeForm.action = "<c:url value='/cms/ccm/cca/SelectCcmCmmnCodeList.do'/>";
   	document.CcmCodeForm.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_egov_search_code(){
	document.CcmCodeForm.pageIndex.value = 1;
	document.CcmCodeForm.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_egov_inquire_codedetail(codeId) {
	// 사이트 키값(siteId) 셋팅.
	document.CcmCodeForm.codeId.value = codeId;
  	document.CcmCodeForm.action = "<c:url value='/cms/ccm/cca/SelectCcmCmmnCodeDetail.do'/>";
  	document.CcmCodeForm.submit();
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

			<form name="CcmCodeForm" action="<c:url value='/cms/ccm/cca/SelectCcmCmmnCodeList.do'/>" method="post" onSubmit="fn_egov_search_code(); return false;">


				<!-- 검색영역 -->
				<!-- 검색조건선택 -->
			<div class="white-box">
					<div class="rows">
						<span class="select-outline">
										 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
												<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
												<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId" /></option><!-- 코드ID -->
												<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code" /></option><!-- 코드ID -->
												<option value="3"  <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm" /></option><!-- 코드명 -->
										</select>
						</span>

						<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
						<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
						<button type="button" class="button main" onclick="location.href='<c:url value='/cms/ccm/cca/RegistCcmCmmnCodeView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
					</div>

				</div>
				<h3 class="btitle">
					<spring:message code="title.list" />
				</h3>

				<div class="rows white-box">
					<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
						<thead>
							<tr>
								<th><spring:message code="table.num" /></th><!-- 번호 -->
								<th><spring:message code="comSymCcmCca.cmmnCodeVO.clCodeNm" /></th><!-- 분류코드명 -->
								<th class="board_th_link"><spring:message code="comSymCcmCca.cmmnCodeVO.codeId" /></th><!-- 코드ID -->
								<th><spring:message code="comSymCcmCca.cmmnCodeVO.codeIdNm"/></th><!-- 코드ID -->
								<th><spring:message code="comSymCcmCca.cmmnCodeVO.useAt" /></th><!-- 사용여부 -->
							</tr>
						</thead>
						<!-- 목록영역 -->
						<tbody>
						<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="5"><spring:message code="common.nodata.msg" /></td>
						</tr>
						</c:if>
						<c:forEach items="${resultList}" var="resultInfo" varStatus="status">
						<tr>
							<td class="center"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
							<td class="center"><c:out value='${resultInfo.clCodeNm}'/></td>
							<td class="center"><a href="<c:url value='/cms/ccm/cca/SelectCcmCmmnCodeDetail.do'/>?codeId=${resultInfo.codeId}" onClick="fn_egov_inquire_codedetail('<c:out value="${resultInfo.codeId}"/>');return false;"><c:out value='${fn:substring(resultInfo.codeId, 0, 40)}'/></a></td>
							<td class="center"><a href="<c:url value='/cms/ccm/cca/SelectCcmCmmnCodeDetail.do'/>?codeId=${resultInfo.codeId}" onClick="fn_egov_inquire_codedetail('<c:out value="${resultInfo.codeId}"/>');return false;"><c:out value='${fn:substring(resultInfo.codeIdNm, 0, 40)}'/></a></td>
							<td class="center"><c:out value='${resultInfo.useAt}'/></td>
						</tr>
						</c:forEach>
						</tbody>
					</table>

				<!-- paging navigation -->
					<article class="pagenation">
						<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_egov_select_linkPage"/>
					</article>

				</div>



					<input name="codeId" type="hidden" value="">
					<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
					<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
			</form>
</div>
