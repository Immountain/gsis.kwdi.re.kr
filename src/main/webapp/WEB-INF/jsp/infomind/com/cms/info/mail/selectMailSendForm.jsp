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
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>"></script>
<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>"></script>
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<c:set var="pageTitle">주요프로그램</c:set>
<script type="text/javascript">

    $(document).ready(function () {

        fncShowMessg();
        fieldErrors();

    });


    /* ********************************************************
    * 서버 처리 후 메세지 화면에 보여주기
    ******************************************************** */
    function fncShowMessg() {
        if ("<c:out value='${message}'/>" != '') {
            alert("<c:out value='${message}'/>");
        }
    }

    function fieldErrors() {
        var msg = "";
        <spring:hasBindErrors name="resultVO">
        <c:forEach items="${errors.fieldErrors}" var="error">
        msg = msg + '${error.defaultMessage}' + "\n";
        </c:forEach>
        </spring:hasBindErrors>
        if (msg) {
            alert(msg);
        }
    }

    function deleteMailTo(_this) {
        var $td = $(_this).closest('td');
        if($td.find('div > div').length <= 1) {
            alert('메일 발송은 최소 한명 이상 설정되어야합니다.');
            return false;
        }

        if(!confirm('메일 발송에서 제외 하시겠습니까?')) {
            return false;
        }

        $(_this).closest('div').remove();

        $td.find('div').each(function (i, v) {
            $(v).find('input[type=hidden]').each(function(i2, v2) {
                var name = v2.name.split('.')[1];
                v2.name = 'sendUserList[' + i + '].' + name;
            });
        })
    }

    function onSubmit(_this) {

        if($ifx.isEmpty(_this.subject.value)) {
            alert('제목을 입력하여 주시기 바랍니다.');
            return false;
        }
        if($ifx.isEmpty(_this.text.value)) {
            alert('내용을 입력하여 주시기 바랍니다.');
            return false;
        }

        $ifx.ajax('<c:url value="/cms/info/mail/selectMailSend.do"/>', {
            method: 'post',
            processData: false,
            contentType: false,
            data: new FormData(_this),
            success: function(res) {
                alert(res.message)
            }
        })
        return false;
    }
</script>


<div class="sub subView">

    <form:form commandName="infoMailSendFormVO" onsubmit="onSubmit(this); return false;">
        <h3 class="btitle">
            메일 전송
        </h3>

        <div class="rows white-box">
            <table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
                <colgroup>
                    <col width="20%"/>
                    <col width="*"/>
                </colgroup>
                <tr>
                    <th>전송명단<span class="pilsu">*</span></th>
                    <td>
                        <div style="display: block; height: 80px; overflow-y: scroll;">
                            <c:forEach items="${list}" var="item" varStatus="status">
                                <div style="display: inline-block;">
                                    <button type="button" class="button sub btn-ms" onclick="deleteMailTo(this)" data-tootik="[${item.userNm}] ${item.userId}" data-tootik-conf="bottom"><i class='bx bxs-user'></i> ${item.userEmail} </button>

                                    <form:hidden path="sendUserList[${status.index}].esntlId" value="${item.esntlId}"/>
                                    <form:hidden path="sendUserList[${status.index}].userId" value="${item.userId}"/>
                                    <form:hidden path="sendUserList[${status.index}].password" value="${item.password}"/>
                                    <form:hidden path="sendUserList[${status.index}].userNm" value="${item.userNm}"/>
                                    <form:hidden path="sendUserList[${status.index}].userZip" value="${item.userZip}"/>
                                    <form:hidden path="sendUserList[${status.index}].userAdres" value="${item.userAdres}"/>
                                    <form:hidden path="sendUserList[${status.index}].userEmail" value="${item.userEmail}"/>
                                    <form:hidden path="sendUserList[${status.index}].groupId" value="${item.groupId}"/>
                                    <form:hidden path="sendUserList[${status.index}].userSe" value="${item.userSe}"/>
                                    <form:hidden path="sendUserList[${status.index}].orgnztId" value="${item.orgnztId}"/>
                                </div>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>전송대상<span class="pilsu">*</span></th>
                    <td>
                        <form:select path="sendAgentKey" cssClass="wfull main">
                            <form:option value="default" label="default"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th>제목<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="subject" cssClass="wfull main"/>
                    </td>
                </tr>
                <tr>
                    <th>내용<span class="pilsu">*</span></th>
                    <td>
                        <form:textarea path="text" rows="10"/>
                    </td>
                </tr>
            </table>
        </div>

        <div class="btn-set right">
            <button type="submit" class="button" id="btn_mail_send" name="btn_mail_send">전송</button>
            <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
        </div>
    </form:form>
</div>

