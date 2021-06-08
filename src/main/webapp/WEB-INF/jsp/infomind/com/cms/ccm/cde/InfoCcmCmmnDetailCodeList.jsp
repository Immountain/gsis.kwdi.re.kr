<%
 /**
  * @Class Name : EgovCcmCmmnDetailCodeList.jsp
  * @Description : 공통상세코드 목록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.08.31   이정은              표준프레임워크 v3.7 개선
  *  @author 공통서비스팀
  *  @since 2009.02.01
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

<c:set var="pageTitle"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.title"/></c:set>

<script type="text/javascript">
	$(document).ready(function() {


		fn_egov_init();

	});
	/*********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init(){
		// 첫 입력란에 포커스..
		document.CcmDeCodeForm.searchCondition.focus();

	}

	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fn_egov_select_linkPage(pageNo){
		document.CcmDeCodeForm.pageIndex.value = pageNo;
		document.CcmDeCodeForm.action = "<c:url value='/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do'/>";
		document.CcmDeCodeForm.submit();
	}
	/*********************************************************
	 * 조회 처리 함수
	 ******************************************************** */
	function fn_egov_search_code(){
		document.CcmDeCodeForm.pageIndex.value = 1;
		document.CcmDeCodeForm.submit();
	}
	/* ********************************************************
	 * 상세회면 처리 함수
	 ******************************************************** */
	function fn_egov_inquire_codedetail(codeId, code) {
		// 사이트 키값(siteId) 셋팅.
		document.CcmDeCodeForm.codeId.value = codeId;
		document.CcmDeCodeForm.code.value = code;
		document.CcmDeCodeForm.action = "<c:url value='/cms/ccm/cde/SelectCcmCmmnDetailCodeDetail.do'/>";
		document.CcmDeCodeForm.submit();
	}

	function fnLangCodeRegist(codeId,code) {
		var p = {
			codeId : codeId,
			code : code,
			menuTargetNo : ${menuInfo.menuTargetNo}
		};
		var API_SERVER = "<c:url value='/cms/info/langCode/RegistLangCodeView.do' />?codeId="+codeId;
		ax5modal.open({
			theme: "primary",
			height: 501,
			width: 957,
			header: {
				title: '코드언어팩',
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
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
	검색
	</h3>
	<form name="CcmDeCodeForm" accept-charset="UTF-8" action="<c:url value='/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do'/>" method="post" onSubmit="fn_egov_search_code(); return false;">
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
				<button type="button" class="button main" onclick="location.href='<c:url value='/cms/ccm/cde/RegistCcmCmmnDetailCodeView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
		</div>

		</div>

		<h3 class="btitle">
			<spring:message code="title.list" />
		</h3>

		<div class="rows white-box">
			<table  class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
						<tr>
							<th style="width: 9%;"><spring:message code="table.num" /></th>
							<th style="width: 10%;"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId" /></th>
							<th style="width: 13%;"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code" /></th>
							<th style="width: 30%;"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm"/></th>
							<th style="width: 10%;"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.useAt" /></th>
							<th style="width: 10%;">코드언어팩</th>
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
							<td class="center"><c:out value='${resultInfo.codeId}'/></td>
							<td class="center"><c:out value='${resultInfo.code}'/></td>
							<td class="center"><a href="<c:url value='/cms/ccm/cde/SelectCcmCmmnDetailCodeDetail.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&codeId=${resultInfo.codeId}&amp;code=${resultInfo.code}" onClick="fn_egov_inquire_codedetail('<c:out value="${resultInfo.codeId}"/>','<c:out value="${resultInfo.code}"/>');return false;"><c:out value='${fn:substring(resultInfo.codeNm, 0, 40)}'/></a></td>
							<td class="center"><c:out value='${resultInfo.useAt}'/></td>
							<td class="center">
								<button type="button" class="button main" onclick="fnLangCodeRegist('<c:out value="${resultInfo.codeId}"/>','<c:out value="${resultInfo.code}"/>'); return false" title="<spring:message code="button.create"/><spring:message code="input.button"/>">코드언어팩</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_egov_select_linkPage"/>
    </article>



	<input name="codeId" type="hidden" value="">
	<input name="code" type="hidden" value="">
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
</form>
</div>