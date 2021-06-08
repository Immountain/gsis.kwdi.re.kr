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

        $ifx.ajax('<c:url value="/cms/wj/jeju/wjJejuPeopleMailSend.do"/>', {
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

    <form:form commandName="wjJejuPeopleSendMail" onsubmit="onSubmit(this); return false;">
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
                                    <button type="button" class="button sub btn-ms" onclick="deleteMailTo(this)" data-tootik="[${item.korName}] ${item.userId}" data-tootik-conf="bottom"><i class='bx bxs-user'></i> ${item.emaill} </button>

                                    <form:hidden path="sendUserList[${status.index}].userId" value="${item.userId}"/>
                                    <form:hidden path="sendUserList[${status.index}].korName" value="${item.korName}"/>
                                    <form:hidden path="sendUserList[${status.index}].enName" value="${item.enName}"/>
                                    <form:hidden path="sendUserList[${status.index}].cnName" value="${item.cnName}"/>
                                    <form:hidden path="sendUserList[${status.index}].atchFileId" value="${item.atchFileId}"/>
                                    <form:hidden path="sendUserList[${status.index}].sex" value="${item.sex}"/>
                                    <form:hidden path="sendUserList[${status.index}].birth" value="${item.birth}"/>
                                    <form:hidden path="sendUserList[${status.index}].city" value="${item.city}"/>
                                    <form:hidden path="sendUserList[${status.index}].town" value="${item.town}"/>
                                    <form:hidden path="sendUserList[${status.index}].village" value="${item.village}"/>
                                    <form:hidden path="sendUserList[${status.index}].addressEtc" value="${item.addressEtc}"/>
                                    <form:hidden path="sendUserList[${status.index}].country" value="${item.country}"/>
                                    <form:hidden path="sendUserList[${status.index}].countryCity" value="${item.countryCity}"/>
                                    <form:hidden path="sendUserList[${status.index}].countryEtc" value="${item.countryEtc}"/>
                                    <form:hidden path="sendUserList[${status.index}].phone" value="${item.phone}"/>
                                    <form:hidden path="sendUserList[${status.index}].tel1" value="${item.tel1}"/>
                                    <form:hidden path="sendUserList[${status.index}].tel2" value="${item.tel2}"/>
                                    <form:hidden path="sendUserList[${status.index}].emaill" value="${item.emaill}"/>
                                    <form:hidden path="sendUserList[${status.index}].company" value="${item.company}"/>
                                    <form:hidden path="sendUserList[${status.index}].spot" value="${item.spot}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolE" value="${item.schoolE}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolEYear" value="${item.schoolEYear}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolM" value="${item.schoolM}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolMYear" value="${item.schoolMYear}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolH" value="${item.schoolH}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolHYear" value="${item.schoolHYear}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolU" value="${item.schoolU}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolUYear" value="${item.schoolUYear}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolG" value="${item.schoolG}"/>
                                    <form:hidden path="sendUserList[${status.index}].schoolGYear" value="${item.schoolGYear}"/>
                                    <form:hidden path="sendUserList[${status.index}].edu" value="${item.edu}"/>
                                    <form:hidden path="sendUserList[${status.index}].religion" value="${item.religion}"/>
                                    <form:hidden path="sendUserList[${status.index}].religionEtc" value="${item.religionEtc}"/>
                                    <form:hidden path="sendUserList[${status.index}].career" value="${item.career}"/>
                                    <form:hidden path="sendUserList[${status.index}].langCode" value="${item.langCode}"/>
                                    <form:hidden path="sendUserList[${status.index}].sendEmaillYn" value="${item.sendEmaillYn}"/>
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

