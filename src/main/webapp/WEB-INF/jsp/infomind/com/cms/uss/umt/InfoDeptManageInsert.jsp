<%
    /**
     * @Class Name :  InfoDeptManageUpdt.jsp
     * @Description :  부서관리 수정하는 화면
     * @Modification Information
     *
     */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.deptManage.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="infoDeptManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript">


    $(document).ready(function() {

        fn_egov_init();

        fieldErrors();
    });


    /* ********************************************************
     * 초기화
     ******************************************************** */
    function fn_egov_init(){
        // 첫 입력란에 포커스..
        $("#orgnztNm").focus();
    }
    /* ********************************************************
     * 저장처리화면
     ******************************************************** */
    function insertProgramListManage(form){

        var orgnztNm = $("#orgnztNm").val();

        if(orgnztNm.length  == 0) {
            $("#orgnztNm").focus();
            return;
        }

        //input item Client-Side validate
        //if (!validateInfoDeptManageVO(form)) {
        //	return false;
        //} else {
        if(confirm("<spring:message code="common.save.msg" />")){
            form.submit();
        }
        //}
    }

    function fieldErrors() {
        var msg ="";
        <spring:hasBindErrors name="infoDeptManageVO">
        <c:forEach items="${errors.fieldErrors}" var="error">
        msg =msg+'${error.defaultMessage}'+"\n";
        </c:forEach>
        </spring:hasBindErrors>
        if(msg){

            alert(msg);
        }
    }


</script>

<c:set var="deptName"><spring:message code="comUssUmt.deptManageRegist.deptName"/></c:set>
<c:set var="deptDc"><spring:message code="comUssUmt.deptManageRegist.deptDc"/></c:set>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


<div class="sub subView">
<h2 class="stitle">
    <i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.save" />
</h2>


<!-- 상단타이틀 -->
<form:form commandName="infoDeptManageVO"    action="${pageContext.request.contextPath}/cms/uss/umt/addDeptManage.do" method="post" onSubmit="insertProgramListManage(document.forms[0]); return false;">
    <div class="rows white-box">

        <!-- 등록폼 -->
        <table class="landscape" summary="<spring:message code="common.summary.update" arguments="${pageTitle}" />">
            <tbody>



            <!-- 부서명 -->
            <c:set var="title"><spring:message code="comUssUmt.deptManageRegist.deptName"/> </c:set>
            <tr>
                <th style="width: 9%;"><label for="orgnztNm">${title} <span class="pilsu">*</span></label></th>
                <td class="left">
                    <form:input path="orgnztNm" name="orgnztNm" id="orgnztNm"  title="${title} ${deptName}" size="70" maxlength="70" />
                    <div><form:errors path="orgnztNm" cssClass="error" /></div>
                </td>
            </tr>

            <!-- 설명 -->
            <c:set var="title"><spring:message code="comUssUmt.deptManageRegist.deptDc"/> </c:set>
            <tr>
                <th><label for="orgnztDc">${title } <span class="pilsu">*</span></label></th>
                <td class="nopd">
                    <form:textarea path="orgnztDc" title="${title} ${deptDc}" cols="300" rows="10" />
                    <div><form:errors path="orgnztDc" cssClass="error" /></div>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <!-- 하단 버튼 -->
    <div class="btn-set right">
        <input type="submit" class="button" value="<spring:message code="button.save" />" title="<spring:message code="button.save" /> <spring:message code="input.button" />" />

        <a href="<c:url value='/cms/uss/umt/selectDeptManageList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" class="button" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a>
    </div>

    </div>
    <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>

