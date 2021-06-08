<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">

<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<c:set var="pageTitle">pageHisDetail</c:set>
<script type="text/javascript">

    $(document).ready(function() {

        var codeEditor = $ifx.generateCodeEditor(resultVO.contentInfo, {
            useThemeSelector: true
        });
        fncShowMessg();
        fieldErrors();

    });

    function fnUpdt(){

        editorSave();
        var	API_SERVER = "<c:url value='/cms/info/page/popInfoPageInfoHisUpdt.do'/>";
        var formData = $("#resultVO").serialize();
        $.ajax({
            type : 'post',
            url : API_SERVER,
            data : formData,
            dataType : 'json',
            success : function(res){

                ax5modal.close();
                alert('수정 되었습니다')
            },
            error(res){
                alert("error");
            }

        })

    };
</script>



<div class="sub subView">
<form:form commandName="resultVO">

    <div class="btn-set right">
        <button type="button" id="btn_print" name="btn_print" class="btn main" name="btn_save" onclick="fnPrint()">프린트</button>
    </div>
    <h3 class="btitle">
        등록 내역
    </h3>

    <div class="rows white-box">
        <!-- 등록폼 -->
        <table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
            <tr>
                <th width="20">페이지아이디<span class="pilsu">*</span></th>
                <td class="left">
                    <form:input path="pageId" readonly="true"/>
                </td>
                <th width="10%">등록/수정타입<span class="pilsu">*</span></th>
                <td class="left" width="20%">
                    <form:select path="modType">
                        <form:option value="I" label="등록타입"/>
                        <form:option value="U" label="수정타입"/>
                    </form:select>
                </td>
            </tr>

            <tr>
                <th>내용<span class="pilsu">*</span></th>
                <td colspan="3">
                    <textarea name="contentInfo" id="contentInfo" rows="19" cols="30"><c:out value="${resultVO.contentInfoDecode}"/></textarea>
                </td>
            </tr>


        </table>
    </div>
    </div>

    <!-- 하단 버튼 -->
<%--    <div class="btn-set right">--%>
<%--        <button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="fnUpdt()">수정</button>--%>
<%--    </div>--%>
    <form:hidden path="pageHisSno" id="pageHisSno" />
    <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>

`