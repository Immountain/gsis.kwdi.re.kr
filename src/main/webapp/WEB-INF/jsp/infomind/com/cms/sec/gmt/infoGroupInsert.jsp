<%
	/**
	 * @Class Name  : EgovAuthorInsert.java
	 * @Description : EgovAuthorInsert jsp
	 * @Modification Information
	 * @
	 * @  수정일         수정자          수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2009.02.01    lee.m.j          최초 생성
	 *   2016.06.13    장동한            표준프레임워크 v3.6 개선
	 *
	 *  @author lee.m.j
	 *  @since 2009.03.11
	 *  @version 1.0
	 *  @see
	 *
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comCopSecGmt.title"/></c:set>

	<title>${pageTitle} <spring:message code="title.create" /></title><!-- 그룹관리 등록 -->
	<meta http-equiv="content-type" content="text/html; charset=utf-8">

	<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
	<validator:javascript formName="groupManage" staticJavascript="false" xhtml="true" cdata="false"/>
	<script type="text/javaScript" language="javascript">

		function fncSelectGroupList() {
			var varFrom = document.getElementById("infoGroupManage");
			varFrom.action = "<c:url value='/cms/sec/gmt/InfoGroupList.do'/>";
			varFrom.submit();
		}

		function fncGroupInsert(form) {

			if(confirm("<spring:message code="common.regist.msg" />")){	//등록하시겠습니까?
				if(!validateGroupManage(form)){
					return false;
				}else{
					form.submit();
				}
			}
		}

	</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="infoGroupManage" method="post" action="${pageContext.request.contextPath}/cms/sec/gmt/InfoGroupInsert.do" onSubmit="fncGroupInsert(document.forms[0]); return false;">
		<h3 class="btitle">
			등록 내역
		</h3>
		<div class="rows white-box">
		<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<tr>
						<!-- 입력 -->
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						</tr>
					<tr>
						<!-- 그룹명 -->
						<c:set var="title"><spring:message code="comCopSecGmt.regist.groupNm" /></c:set>
					</tr>
					<tr>
						<th style="width: 9%;">${title} <span class="pilsu">*</span></th>
						<td class="left">
							<form:input path="groupNm" title="${title} ${inputTxt}" size="40" maxlength="50" />
							<div><form:errors path="groupNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>기관사용여부</th>
						<td class="left">
							<select id="groupOrganYn" name="groupOrganYn">
								<option value="">사용안함 </option>
								<option value="Y">사용 </option>
							</select>
						</td>
					</tr>


					<!-- 설명 -->
					<c:set var="title"><spring:message code="comCopSecGmt.regist.groupDc" /></c:set>
					<tr>
						<th style="width: 9%;">${title}</th>
						<td class="left">
							<form:textarea path="groupDc" title="${title} ${inputTxt}" cols="300" rows="10" maxlength="50"/>
							<div><form:errors path="groupDc" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
			<!-- 하단 버튼 -->
			<div class="btn-set right">
				<input type="submit" class="button main" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
				<span class="button"><a href="<c:url value='/cms/sec/gmt/InfoGroupList.do?menuTargetNo=${infoGroupManage.menuTargetNo}' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
			</div>

		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${infoGroupManage.menuTargetNo}">


	</form:form>
</div>

