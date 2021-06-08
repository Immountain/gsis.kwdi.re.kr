<%
/**
 * @Class Name  : EgovRoleInsert.java
 * @Description : EgovRoleInsert jsp
 * @Modification Information
 * @
 * @  수정일         수정자          수정내용
 * @ -------    --------    ---------------------------
 * @ 2009.02.01    lee.m.j       최초 생성
 *   2016.06.13    장동한          표준프레임워크 v3.6 개선
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
<c:set var="pageTitle"><spring:message code="comCopSecRmt.title"/></c:set>

<title>${pageTitle} <spring:message code="title.create" /></title><!-- 롤관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="roleManage" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript">

function fncSelectRoleList() {
    var varFrom = document.getElementById("infoRoleManage");
    varFrom.action = "<c:url value='/cms/sec/rmt/InfoRoleList.do'/>";
    varFrom.submit();
}

function fncRoleInsert(form) {

    if(confirm("<spring:message code="common.save.msg" />")){ //저장하시겠습니까?
        if(!validateRoleManage(form)){
            return false;
        }else{
        	form.submit();
        }
    }
}

function fncRoleUpdate() {
    var varFrom = document.getElementById("infoRoleManage");
    varFrom.action = "<c:url value='/cms/sec/rmt/InfoRoleUpdate.do'/>";

    if(confirm("<spring:message code="common.save.msg" />")){ //저장하시겠습니까?
        if(!validateRoleManage(varFrom)){
            return;
        }else{
            varFrom.submit();
        }
    }
}

function fncRoleDelete() {
    var varFrom = document.getElementById("infoRoleManage");
    varFrom.action = "<c:url value='/cms/sec/rmt/InfoRoleDelete.do'/>";
    if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
        varFrom.submit();
    }
}

</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
<form:form commandName="infoRoleManage" method="post" action="${pageContext.request.contextPath}/cms/sec/rmt/InfoRoleInsert.do" onSubmit="fncRoleInsert(document.forms[0]); return false;">
	<h3 class="btitle">
		등록 내역
	</h3>
	<div class="rows white-box">
	<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<tbody>
		<!-- 입력 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<!-- 롤아이디 -->
		<c:set var="title"><spring:message code="comCopSecRam.list.rollId" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="roleCode" title="${title} ${inputTxt}" size="40" maxlength="25" />
				<div><form:errors path="roleCode" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 명 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollNm" /></c:set>
		<tr>
			<th style="width: 9%">${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="roleNm" title="${title} ${inputTxt}" size="40" maxlength="30" />
				<div><form:errors path="roleNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 패턴 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollPtn" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="rolePtn" title="${title} ${inputTxt}" size="40" maxlength="150" />
				<div><form:errors path="rolePtn" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 설명 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollDc" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
			    <form:textarea path="roleDc" title="${title} ${inputTxt}" cols="300" rows="10" maxlengt="100"/>
				<div><form:errors path="roleDc" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 타입 -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollType" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:select path="roleTyp">
					<form:options items="${cmmCodeDetailList}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				<div><form:errors path="roleTyp" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 롤 Sort -->
		<c:set var="title"><spring:message code="comCopSecRam.regist.rollSort" /></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="roleSort" title="${title} ${inputTxt}" size="40" maxlength="5" />
				<div><form:errors path="roleSort" cssClass="error" /></div> 
			</td>
		</tr>
	</tbody>
	</table>

	<!-- 하단 버튼 -->
		<div class="btn-set right">
	    <button class="button" type="button" onclick="fncSelectRoleList()"> <spring:message code="button.list" /></button>
		<input type="submit" class="button main" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" /><!-- 등록 -->
	</div><div style="clear:both;"></div>
	
</div>
	<input type="hidden" name="searchCondition" value="<c:out value='${infoRoleManage.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${infoRoleManage.searchKeyword}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${infoRoleManage.pageIndex}'/>"/>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${infoRoleManage.menuTargetNo}">
</form:form>

</div>
