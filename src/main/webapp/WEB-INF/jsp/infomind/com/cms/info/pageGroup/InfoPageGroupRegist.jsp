<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<c:set var="pageTitle">페이지 그룹 관리</c:set>
<script type="text/javascript">
	$(document).ready(function() {
		fncShowMessg();

	});

	/* ********************************************************
     * 저장처리화면
     ******************************************************** */
	function fn_regist_page(form){
		//input item Client-Side validate
		if(confirm("<spring:message code="common.regist.msg" />")){
			form.submit();
		}
	}

	/* ********************************************************
    * 서버 처리 후 메세지 화면에 보여주기
    ******************************************************** */
	function fncShowMessg(){
		if("<c:out value='${message}'/>" != ''){
			alert("<c:out value='${message}'/>");
		}
	}
</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="resultVO" action="${pageContext.request.contextPath}/cms/info/pageGroup/insertPageGroup.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">등록 내역</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
				<!-- 입력/선택 -->
				<tr>
					<th style="width: 9%;">
						<label for="pageGroupId">페이지 그룹 아이디<span class="pilsu">*</span></label>
					</th>
					<td class="left">
						<form:input path="pageGroupId"  size="70" maxlength="70" />
						<div><form:errors path="pageGroupId" cssClass="error" /></div>
					</td>
				</tr>
				<tr>
					<th><label for="pageGroupNm">페이지 그룹 명칭  <span class="pilsu">*</span></label></th>
					<td class="left">
						<form:input path="pageGroupNm" size="70" maxlength="70" />
						<div><form:errors path="pageGroupNm" cssClass="error" /></div>
					</td>
				</tr>




				<tr>
					<!-- 사용여부 -->
					<th>사용여부<span class="pilsu">*</span></th>
					<td class="left">
						<form:select path="useYn" cssClass="txt">
							<form:option value="Y"  label="사용"/>
							<form:option value="N" label="사용안함"/>
						</form:select>
						<div><form:errors path="useYn" cssClass="error" /></div>
					</td>
				</tr>

				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
			<span class="button"><a href="<c:url value='/cms/info/pageGroup/InfoPageGroupList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>

		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>