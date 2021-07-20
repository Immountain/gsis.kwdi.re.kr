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

            var themaGroupId = $('#themaGroupId').val()
            var themaId = $('#themaId').val()
            var themaNm = $('#themaNm').val()
            var themaTitle = $('#themaTitle').val()
            var themaMemo = $('#themaMemo').val()
            var themaEtc = $('#themaEtc').val()
            var tblId = $('#tblId').val()
          //  var loadGubun = $('#loadGubun').val()
            // var loadTerm = $('#loadTerm').val()
            var collectType = $('#collectType').val()
            var collectCycle = $('#collectCycle').val()
            var apiUrl = $('#apiUrl').val()
            var htmlFile = $('#htmlFile').val()
            var orderCnt = $('#orderCnt').val()

            if(!themaGroupId){
                alert('테마통계관리그룹아이디 선택하세요')
                return;
            }

            if(!themaId){
                alert('테마통계아이디 입력하세요')
                return;
            }

            if(!themaNm){
                alert('테마통계명 입력하세요')
                return;
            }

            if(!themaTitle){
                alert('테마통계타이틀 입력하세요')
                return;
            }

            if(!themaMemo){
                alert('메모 입력하세요')
                return;
            }

            if(!themaEtc){
                alert('기타 입력하세요')
                return;
            }

            if(!tblId){
                alert('통계표ID 입력하세요')
                return;
            }

            // if(!loadGubun){
            //     alert('조회구분(시계열,횡단면) 입력하세요')
            //     return;
            // }

            // if(!loadTerm){
            //     alert('조회설정 입력하세요')
            //     return;
            // }

            if(!collectType){
                alert('수집타입 입력하세요')
                return;
            }

            if(!collectCycle){
                alert('수집주기 입력하세요')
                return;
            }


            if(collectType==1){

                if(!apiUrl){
                    alert('API_URL 입력하세요')
                    return;
                }
            }



            if(!htmlFile){
                alert('HTML_파일명 입력하세요')
                return;
            }

            if(!orderCnt){
                alert('정렬순번 입력하세요')
                return;
            }

            var formData = $("#jewThemaInfoVO").serializeObject();

            var API_SERVER = "<c:url value='/cms/gsis/thema/jewThemaInfoRegist.do' />";
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
    <form:form commandName="jewThemaInfoVO">
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
                    <th style="width:180px"><label for="themaGroupId">테마통계관리그룹아이디<span class="pilsu">*</span></label></th>
                    <td class="left" >
                        <span class="select-outline">
                            <form:select path="themaGroupId">
                                <form:option value="" label="선택"/>
                                <c:forEach items="${jewGroupList}" var="item">
                                    <form:option value="${item.themaGroupId}" label="${item.themaGroupNm}"/>
                                </c:forEach>
                            </form:select>
                        </span>
                    </td>
                    <th style="width:180px"  ><label for="themaId">테마통계아이디<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="themaId" title="${title} ${inputTxt}" maxlength="10" />
                        <div><form:errors path="themaId" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th><label for="themaNm">테마통계명<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="themaNm" maxlength="100" />
                        <div><form:errors path="themaNm" cssClass="error" /></div>
                    </td>
                    <th>테마통계타이틀 <span class="pilsu">*</span></th>
                    <td class="left">
                        <form:input path="themaTitle" maxlength="100"/>
                        <div><form:errors path="useYn" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th>통계표ID<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="tblId" maxlength="100"/>
                    </td>
                    <th>사용여부<span class="pilsu">*</span></th>
                    <td>
                        <form:select path="useYn">
                            <form:option value="Y" label="사용"/>
                            <form:option value="N" label="사용안함"/>
                        </form:select>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<th>조회구분(시계열,횡단면)<span class="pilsu">*</span></th>--%>
                    <%--<td>--%>
                        <%--<form:select path="loadGubun">--%>
                            <%--<form:option value="0" label="시계열"/>--%>
                            <%--<form:option value="1" label="횡단면"/>--%>
                        <%--</form:select>--%>
                    <%--</td>--%>
                    <%--<th>조회설정<span class="pilsu">*</span></th>--%>
                    <%--<td>--%>
                        <%--<form:select path="loadTerm">--%>
                            <%--<form:option value="0" label="0"/>--%>
                            <%--<form:option value="1" label="1"/>--%>
                        <%--</form:select>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <th>수집타입<span class="pilsu">*</span></th>
                    <td>
                        <form:select path="collectType">
                            <form:option value="0" label="엑셀"/>
                            <form:option value="1" label="API"/>
                        </form:select>
                    </td>
                    <th>수집주기<span class="pilsu">*</span></th>
                    <td>
                        <form:select path="collectCycle">
                            <form:option value="0" label="매일"/>
                            <form:option value="1" label="주단위"/>
                            <form:option value="2" label="월단위"/>
                            <form:option value="3" label="년단위"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th>API-URL<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:input path="apiUrl" maxlength="100"/>
                    </td>
                </tr>
                <tr>
                    <th>HTML 파일명</th>
                    <td>
                        <form:input path="htmlFile" maxlength="100"/>
                    </td>
                    <th>정렬순번<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다" title="${title} ${inputTxt}" maxlength="8" />
                    </td>
                </tr>
                <tr>
                    <th>메모<span class="pilsu">*</span></th>
                    <td>
                        <form:textarea path="themaMemo" maxlength="250"/>
                    </td>
                    <th>기타<span class="pilsu">*</span></th>
                    <td>
                        <form:textarea path="themaEtc" maxlength="250"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form:form>
    <!-- 하단 버튼 -->
    <div class="btn-set right">
        <button type="button" id="btn_save" class="btn main" name="btn_save">등록</button>
    </div>
</div>

