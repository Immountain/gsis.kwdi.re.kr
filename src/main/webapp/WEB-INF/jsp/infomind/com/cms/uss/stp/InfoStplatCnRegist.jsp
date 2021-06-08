<%
 /**
  * @Class Name : InfoStplatCnRegist.jsp
  * @Description : InfoStplatCnRegist 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.12.03   이기선              최초 생성
  *  @author 인포마인드 개발팀
  *  @since 2020.12.03
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
<c:set var="pageTitle"><spring:message code="comUssSamStp.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="stplatManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">

/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	// 첫 입력란에 포커스
	document.getElementById("stplatManageVO").useStplatNm.focus();

}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_regist_stplatcn(form){
	//input item Client-Side validate
	if (!validateStplatManageVO(form)) {	
		return false;
	} else {
		if(confirm("<spring:message code="common.regist.msg" />")){	
			form.submit();	
		}
	} 
}
</script>

</head>
<body onLoad="fn_egov_init();">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>

	<form:form commandName="stplatManageVO" action="${pageContext.request.contextPath}/cms/uss/stp/InfoStplatCnRegist.do" method="post" onSubmit="fn_egov_regist_stplatcn(document.forms[0]); return false;">

	<!-- 타이틀 -->
	<h3 class="btitle">
		<caption>${pageTitle} <spring:message code="title.create" /></caption>
	</h3>

	<div class="rows white-box">
	<!-- 등록폼 -->
	<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<colgroup>
		<col width="9%"/>
		<col width="91%"/>
	</colgroup>
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 약관명 -->
		<c:set var="title"><spring:message code="comUssSamStp.list.useStplatNm"/></c:set>
		<tr>
			<th><label for="useStplatNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			    <form:input path="useStplatNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
   				<div><form:errors path="useStplatNm" cssClass="error" /></div>     
			</td>
		</tr>
		<!-- 약관내용 -->
		<c:set var="title"><spring:message code="comUssSamStp.list.useStplatCn"/></c:set>
		<tr>
			<th><label for="useStplatCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="useStplatCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="useStplatCn" cssClass="error" /></div>  
			</td>
		</tr>
		<!-- 정보제공동의내용 -->
		<c:set var="title"><spring:message code="comUssSamStp.list.infoProvdAgreCn"/></c:set>
		<tr>
			<th><label for="infoProvdAgreCn">${title}</label> <span class="pilsu">*</span></th>
			<td class="nopd">
				<form:textarea path="infoProvdAgreCn" title="${title} ${inputTxt}" cols="300" rows="20" />   
				<div><form:errors path="infoProvdAgreCn" cssClass="error" /></div>       
			</td>
		</tr>
	</tbody>
	</table>
	</div>
	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<span class="button"><a href="<c:url value='/cms/uss/stp/InfoStplatListInqire.do' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
	</div><div style="clear:both;"></div>
	
</div><!-- div end(wTableFrm)  -->

<input name="cmd" type="hidden" value="<c:out value='save'/>">
</form:form>

</body>
</html>
