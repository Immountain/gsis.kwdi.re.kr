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
<c:set var="pageTitle"><spring:message code="comCopSecRam.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 권한관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="infoAuthorManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">
function fncSelectAuthorList() {
	var varFrom = document.getElementById("infoAuthorManage");
	varFrom.action = "<c:url value='/cms/sec/ram/InfoAuthorList.do'/>";
	varFrom.submit();
}

function fncAuthorInsert(form) {
	if(confirm("<spring:message code="common.regist.msg" />")){	//등록하시겠습니까?
        if(!validateInfoAuthorManage(form)){
        	return false;
        }else{
        	form.submit();
        }
    }
}

function fncAuthorUpdate() {
	var varFrom = document.getElementById("infoAuthorManage");
	varFrom.action = "<c:url value='/cms/sec/ram/InfoAuthorUpdate.do'/>";

	if(confirm("<spring:message code="common.regist.msg" />")){	//등록하시겠습니까?
        if(!validateAuthorManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncAuthorDelete() {
	var varFrom = document.getElementById("infoAuthorManage");
	varFrom.action = "<c:url value='/cms/sec/ram/InfoAuthorDelete.do'/>";
	if(confirm("<spring:message code="common.delete.msg" />")){ //삭제하시겠습니까?
	    varFrom.submit();
	}
}
</script>
	<div class="sub subView">
		<h2 class="stitle">
			<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
		</h2>
<form:form commandName="infoAuthorManage" action="${pageContext.request.contextPath}/cms/sec/ram/InfoAuthorInsert.do" method="post" onSubmit="fncAuthorInsert(document.forms[0]); return false;">
	<h3 class="btitle">
		등록 내역
	</h3>
	<div class="rows white-box">
	<!-- 등록폼 -->
	<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<tbody>
		<tr>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 권한코드 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.authorCode" /></c:set>
		<tr>
			<th style="width: 9%;">${title} <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="authorCode" title="${title} ${inputTxt}" size="40" maxlength="15" />
   				<div><form:errors path="authorCode" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 권한명 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.authorNm" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="authorNm" title="${title} ${inputTxt}" size="40" maxlength="30" />
   				<div><form:errors path="authorNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 설명 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.authorDc" /></c:set>
		<tr>
			<th>${title}</th>
			<td class="left">
			    <form:textarea path="authorDc" title="${title} ${inputTxt}" cols="300" rows="10" maxlength="100"/>
				<div><form:errors path="authorDc" cssClass="error" /></div> 
			</td>
		</tr>
	</tbody>
	</table>
	</div>
	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<input type="submit" class="button main" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
		<span class="button"><a href="<c:url value='/cms/sec/ram/InfoAuthorList.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span><!-- 목록 -->
</div>

	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${infoAuthorManage.menuTargetNo}">

</form:form>	
	</div>


