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
            var categoryId = $('#categoryId').val();
            var categoryIdNm = $('#categoryIdNm').val();
            var orderCnt = $('#orderCnt').val();
            var vwCd = $('#vwCd').val();
            var tblId = $('#tblId').val();
            var statsUrl = $('#statsUrl').val();
            var useYn = $('#useYn').val();

            if(!categoryId){
                alert('카테고리아이디 입력하세요');
                return;
            }

            if(!categoryIdNm){
                alert('카테고리명 입력하세요');
                return;
            }

            if(!orderCnt){
                alert('정렬순서 입력하세요')
                return;
            }

            if(!vwCd){
                alert('kosis서비스코드 입력하세요')
                return;
            }

            if(!tblId){
                alert('kosis통계아이디 입력하세요')
                return;
            }

            if(!statsUrl){
                alert('통계URL 입력하세요')
                return;
            }

            if(!useYn){
                alert('사용여부 입력하세요')
                return;
            }

            var formData = $("#jewThemaInfoVO").serializeObject();

            var API_SERVER = "<c:url value='/cms/gsis/stats/jewStatsCategoryRegist.do' />";
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
                    <input type="hidden" name="parentId" id="parentId" value="root" />
                    <th style="width:110px"><label for="themaGroupId">테마통계관리그룹아이디<span class="pilsu">*</span></label></th>
                    <td class="left" >
                        <form:input path="themaGroupId" title="${title} ${inputTxt}" maxlength="10"/>
                        <div><form:errors path="themaGroupId" cssClass="error"/></div>
                    </td>
                    <th style="width:100px"  ><label for="themaId">테마통계아이디<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="themaId" title="${title} ${inputTxt}" maxlength="25" />
                        <div><form:errors path="themaId" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th><label for="themaNm">테마통계명<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="themaNm" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다" title="${title} ${inputTxt}" maxlength="8" />
                        <div><form:errors path="themaNm" cssClass="error" /></div>
                    </td>
                    <th>테마통계타이틀 <span class="pilsu">*</span></th>
                    <td class="left">
                        <form:input path="themaTitle"/>
                        <div><form:errors path="useYn" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th>메모<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="themaMemo" maxlength="10"/>
                    </td>
                    <th>기타<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="themaEtc" maxlength="10"/>
                    </td>
                </tr>
                <tr>
                    <th>통계표ID<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:input path="tblId" maxlength="10"/>
                    </td>
                    <th>사용여부<span class="pilsu">*</span></th>
                    <td>
                        <form:select path="useYn">
                            <form:option value="Y" label="사용"/>
                            <form:option value="N" label="사용안함"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <th>조회구분(시계열,횡단면)<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="loadGubun"/>
                    </td>
                    <th>조회설정<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="loadTerm"/>
                    </td>
                </tr>
                <tr>
                    <th>수집타입<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="collectType"/>
                    </td>
                    <th>수집주기<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="collectCycle"/>
                    </td>
                </tr>
                <tr>
                    <th>API-URL<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="apiUrl"/>
                    </td>
                    <th>HTML 파일명</th>
                    <td>
                        <form:input path="htmlFile"/>
                    </td>
                </tr>
                <tr>
                    <th>정렬순번<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다" title="${title} ${inputTxt}" maxlength="8" />
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

