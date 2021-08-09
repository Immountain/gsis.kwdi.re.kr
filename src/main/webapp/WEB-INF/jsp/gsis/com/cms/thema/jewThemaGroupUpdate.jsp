<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle">통계DB카테고리</c:set>
<script type="text/javascript">


    $(document.body).ready(function () {

        //닫기
        $('#modal-close').click(function () {
            ax5modal.close();
        });


        $('#btn_save').click(function() {


            //ajax 통신은 jsp 에서 체크
            var themaGroupId = $('#themaGroupId').val();
            var themaGroupNm = $('#themaGroupNm').val();
            var orderCnt = $('#orderCnt').val();
            var useYn = $('#useYn').val();

            if(!themaGroupId){
                alert("테마통계관리그룹아이디 입력하세요")
                return;
            }

            if(!themaGroupNm){
                alert("테마그룹명 입력하세요")
                return;
            }

            if(!orderCnt){
                alert("정렬순번 입력하세요")
                return;
            }

            if(!useYn){
                alert("사용여부 입력하세요")
                return;
            }

            var formData = $("#jewThemaGroupVO").serializeObject();

            var API_SERVER = "<c:url value='/cms/gsis/thema/jewThemaGroupUpdt.do' />";
            var saveQuestion = confirm("저장 하시겠습니까?");
            if (saveQuestion) {
                $.ajax({
                    url : API_SERVER,
                    type : 'post',
                    data : formData,
                    dateType:'json',

                    beforeSend: function(xhr) {

                        xhr.setRequestHeader("AJAX", "true");

                    },
                    success : function(data) {

                        var jsonObj = JSON.stringify(data);
                        if(data.status=="0"){
                            if(data.message=="SUCCESS"){

                                ax5modal.callback('OK');
                                ax5modal.close();
                                alert("저장처리 완료했습니다.");

                            }else{
                                alert(data.message);

                            }
                        }else{

                            alert("처리중 오류가 발생했습니다.");
                        }
                    }, // success
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                    }
                });
            }
        });
    });

    jQuery.fn.serializeObject = function() {
        var obj = null;
        try {
            if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
                var arr = this.serializeArray();
                if (arr) {
                    obj = {};
                    jQuery.each(arr, function() {
                        obj[this.name] = this.value;
                    });
                }
            }
        } catch (e) {
            alert(e.message);
        } finally {
        }

        return obj;
    };

</script>

<div class="sub subView">
    <!-- 등록폼 -->
    <form:form commandName="jewThemaGroupVO">
        <h3 class="btitle">
            등록 내역
        </h3>
        <div class="rows white-box">
            <table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
                <tbody>
                <!-- 입력 -->
                <c:set var="inputTxt"><spring:message code="input.input" /></c:set>
                <c:set var="inputYes"><spring:message code="input.yes" /></c:set>
                <c:set var="inputNo"><spring:message code="input.no" /></c:set>
                <tr>
                    <th><label for="themaGroupId">테마통계관리그룹아이디</label></th>
                    <td class="left" >
                        <form:input path="themaGroupId" title="${title} ${inputTxt}" maxlength="10" readonly="true"/>
                        <div><form:errors path="themaGroupId" cssClass="error"/></div>
                    </td>
                    <th><label for="themaGroupNm">테마그룹명<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="themaGroupNm" title="${title} ${inputTxt}" maxlength="100" />
                        <div><form:errors path="themaGroupNm" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th><label for="orderCnt">정렬순번<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다" title="${title} ${inputTxt}" maxlength="8" />
                        <div><form:errors path="orderCnt" cssClass="error" /></div>
                    </td>
                    <th>사용여부<span class="pilsu">*</span></th>
                    <td class="left">
                        <form:select path="useYn">
                            <form:option value="Y" label="사용"/>
                            <form:option value="N" label="사용안함"/>
                        </form:select>
                        <div><form:errors path="useYn" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th><label for="divCss">스타일</label></th>
                    <td class="left" colspan="3" >
                        <form:input path="divCss" maxlength="20"/>
                        <div><form:errors path="divCss" cssClass="error"/></div>
                    </td>

                </tr>
                </tbody>
            </table>
        </div>
    </form:form>
    <!-- 하단 버튼 -->
    <div class="btn-set right">
        <button type="button" id="btn_save" class="btn main" name="btn_save">수정</button>
    </div>
</div>

