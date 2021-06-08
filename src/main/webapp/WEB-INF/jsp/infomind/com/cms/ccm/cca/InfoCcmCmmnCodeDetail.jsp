<%
 /**
  * @Class Name : EgovCcmCmmnCodeDetail.jsp
  * @Description : 공통코드 상세조회 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.08.09   이정은              표준프레임워크 v3.7 개선
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<c:set var="pageTitle"><spring:message code="comSymCcmCca.cmmnCodeVO.title"/></c:set>

<script type="text/javascript">
/* ********************************************************
 * 삭제처리
 ******************************************************** */
 function fn_egov_delete_code(codeId){



 	alert("codeId===>"+codeId);


	if(confirm("<spring:message code="common.delete.msg" />")){	
		// Delete하기 위한 키값을 셋팅
		document.CcmCodeForm.codeId.value = codeId;	
		document.CcmCodeForm.action = "<c:url value='/cms/ccm/cca/RemoveCcmCmmnCode.do'/>";
		document.CcmCodeForm.submit();
	}	
}	
</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.detail" />
	</h2>
	<form name="CcmDeCodeForm"  action="<c:url value='/cms/ccm/cca/UpdateCcmCmmnCodeView.do'/>" method="post">
	<form:form commandName="CcmDeCodeForm" action="<c:url value='/cms/ccm/cca/UpdateCcmCmmnCodeView.do'/>" method="post" >


		<h3 class="btitle">
			상세 내역
		</h3>

		<div class="rows white-box">
				<!-- 상세조회 -->
				<table class="landscape" summary="<spring:message code="common.summary.inqire" arguments="${pageTitle}" />">
					<tbody>
					<!-- 분류코드명 -->
					<tr>
						<th><spring:message code="comSymCcmCca.cmmnCodeVO.clCodeNm" /></th>
						<td class="left"><c:out value="${result.clCodeNm}"/></td>
					</tr>
					<!-- 코드ID -->
					<tr>
						<th><spring:message code="comSymCcmCca.cmmnCodeVO.codeId" /></th>
						<td class="left"><c:out value="${result.codeId}"/></td>
					</tr>
					<!-- 코드ID명 -->
					<tr>
						<th><spring:message code="comSymCcmCca.cmmnCodeVO.codeIdNm" /></th>
						<td class="left"><c:out value="${result.codeIdNm}"/></td>
					</tr>
					<!-- 코드ID설명 -->
					<tr>
						<th class="vtop"><spring:message code="comSymCcmCca.cmmnCodeVO.codeIdDc" /></th>
						<td class="cnt">
							<c:out value="${fn:replace(result.codeIdDc , crlf , '<br/>')}" escapeXml="false" />
						</td>
					</tr>
					<!-- 사용여부 -->
					<tr>
						<th><spring:message code="comSymCcmCca.cmmnCodeVO.useAt" /></th>
						<td class="left"><c:out value="${result.useAt}"/></td>
					</tr>


					</tbody>
				</table>
				<input name="codeId" id="codeId" type="hidden" value="<c:out value="${result.codeId}" />">
				<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">

		</div>


			<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.update" />" title="<spring:message code="title.update" /> <spring:message code="input.button" />" />
			<span class="button"><a href="<c:url value='/cms/ccm/cca/RemoveCcmCmmnCode.do?codeId=${result.codeId}' />" onClick="fn_egov_delete_code('<c:out value="${result.codeId}"/>'); return false;" title="<spring:message code="title.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></a></span>
			<span class="button"><a href="<c:url value='/cms/ccm/cca/SelectCcmCmmnCodeList.do?menuTargetNo=${menuInfo.menuTargetNo}' />"  title="<spring:message code="title.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>
		</form:form>

</div>
