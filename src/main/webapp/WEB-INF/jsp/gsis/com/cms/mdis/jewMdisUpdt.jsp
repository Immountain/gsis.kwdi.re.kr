<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle">통계DB카테고리</c:set>

<!-- datePicker -->
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/pickadate/lib/themes/classic.css"/>">
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/pickadate/lib/themes/classic.date.css"/>">
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/pickadate/lib/themes/classic.time.css"/>">
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/picker.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/picker.date.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/picker.time.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/translations/ko_KR.js"/>"></script>
<script src="<c:url value="/js/infomind/com/moment.js"/>"></script>

<script type="text/javascript">

    $(document.body).ready(function () {

        $('#strDay').pickadate({
            format: 'yyyy-mm-dd',
            formatSubmit: 'yyyymmdd'
        });
        $('#endDay').pickadate({
            format: 'yyyy-mm-dd',
            formatSubmit: 'yyyymmdd'
        });

        //닫기
        $('#modal-close').click(function () {
            ax5modal.close();
        });


        $('#btn_save').click(function() {

            var strDay =$('#strDay').val().replaceAll("-","");
            var endDay =$('#endDay').val().replaceAll("-","");

            $('#collectionStrDay').val(strDay);
            $('#collectionEndDay').val(endDay)

            var mdisNum = $('#mdisNum').val()
            var mdisType = $('#mdisType').val()
            var mdisKorNm = $('#mdisKorNm').val()
            var mdisEnNm = $('#mdisEnNm').val()
            var dataOfForm = $('#dataOfForm').val()
            var projectKorNm = $('#projectKorNm').val()
            var projectEnNm = $('#projectEnNm').val()
            var pi = $('#pi').val()
            var organization = $('#organization').val()
            var supportingOrganization = $('#supportingOrganization').val()
            var copyrightHolder = $('#copyrightHolder').val()
            var investigatePurpose = $('#investigatePurpose').val()
            var investigateContent = $('#investigateContent').val()
            var keyword = $('#keyword').val()
            var collectionStrDay = $('#collectionStrDay').val()
            var collectionEndDay = $('#collectionEndDay').val()
            var investigateArea = $('#investigateArea').val()
            var analysisUnit = $('#analysisUnit').val()
            var subject = $('#subject').val()
            var mdisTime = $('#mdisTime').val()
            var researchOrganization = $('#researchOrganization').val()
            var extraction = $('#extraction').val()
            var caseNumber = $('#caseNumber').val()
            var weight = $('#weight').val()
            var dataForm = $('#dataForm').val()
            var dataStructure = $('#dataStructure').val()
            var mdisLanguage = $('#mdisLanguage').val()
            var publicYn = $('#publicYn').val()
            var publicReasons = $('#publicReasons').val()
            var remark = $('#remark').val()
            var useYn = $('#useYn').val()

            if(!collectionStrDay){
                alert('자료수집시작일 입력하세요')
                return;
            }

            if(!collectionEndDay){
                alert('자료수집종료일 입력하세요')
                return;
            }

            if(collectionStrDay > collectionEndDay){
                alert('종료일이 시작일보다 빠를 수 없습니다')
                return;
            }

            if(!mdisKorNm){
                alert('자료명(국문) 입력하세요')
                return;
            }

            if(!mdisEnNm){
                alert('자료명(영문) 입력하세요')
                return;
            }

            //면대면
            if($("#interviewSurvey").is(":checked")){
                $("#interviewSurvey").val("Y");
            }else{
                $("#interviewSurvey").val("N");
            }

            //자기기입식
            if($("#selfAdministeredSurvey").is(":checked")){
                $("#selfAdministeredSurvey").val("Y");
            }else{
                $("#selfAdministeredSurvey").val("N");
            }

            //우편조사
            if($("#mailSurvey").is(":checked")){
                $("#mailSurvey").val("Y");
            }else{
                $("#mailSurvey").val("N");
            }

            //전화조사
            if($("#phoneSurvey").is(":checked")){
                $("#phoneSurvey").val("Y");
            }else{
                $("#phoneSurvey").val("N");
            }

            //온라인조사
            if($("#onlineSurvey").is(":checked")){
                $("#onlineSurvey").val("Y");
            }else{
                $("#onlineSurvey").val("N");
            }



            var formData = $("#jewMdisVO").serializeObject();

            var API_SERVER = "<c:url value='/cms/gsis/stats/jewMdisUpdt.do' />";
            var saveQuestion = confirm("수정 하시겠습니까?");
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
                                alert("수정 처리 완료했습니다.");

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


        $("#etcFileuploader").uploadFile({
            url: "<c:url value="/"/>cms/info/file/upload.do",
            atchFileId:$("#etc").val(),
            viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
            multiple:true,
            dragDrop:true,
            fileName:"uploadfile",
            maxFileCount:1,
            returnType:"json",
            showPreview:true,
            previewHeight: "100px",
            previewWidth: "100px",
            showDelete: true,
            showDownload:true,
            sequential:true,
            sequentialCount:1,
            onLoad:function(obj) {
                $.ajax({
                    cache: false,
                    url: "<c:url value="/"/>cms/info/file/tempList.do",
                    dataType: "json",
                    data:{atchFileId:$("#etc").val()},
                    success: function(data) {
                        for(var i=0;i<data.length;i++) {
                            obj.createProgress(data[i]);
                        }
                    }
                });
            },
            onSubmit:function(files) {
            },
            onSuccess:function(files,data,xhr,pd) {


                var url ="<c:url value='/site/info/file/ImageView.do?atchFileId=' />"+data.atchFileId+"&fileSn="+data.fileSn;


                var testVal =data.orignlFileNm+"("+getSizeStr(data.fileSize)+")</br>"+url;


                pd.filename.html(testVal)

                $("#etc").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#etc").val(),prixFixe:'DATA_'}
                return data;
            },
            onError: function(files,status,errMsg,pd) {
            },
            deleteCallback: function (data, pd) {
                $.ajax({
                    cache: false,
                    url: "<c:url value="/"/>cms/info/file/delete.do",
                    dataType: "json",
                    data:{atchFileId:data.atchFileId,fileSn:data.fileSn},
                    success: function(data) {
                        pd.statusbar.hide(); //You choice.
                    }
                });
            },
            downloadCallback:function(data,pd) {
                location.href="/cms/info/file/fileDown.do?atchFileId="+data.atchFileId+"&fileSn="+data.fileSn;
            }
        });

        $("#fileuploader").uploadFile({
            url: "<c:url value="/"/>cms/info/file/upload.do",
            atchFileId:$("#dataFile").val(),
            viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
            multiple:true,
            dragDrop:true,
            fileName:"uploadfile",
            maxFileCount:1,
            returnType:"json",
            showPreview:false,
            previewHeight: "100px",
            previewWidth: "100px",
            showDelete: true,
            showDownload:true,
            sequential:true,
            sequentialCount:1,
            onLoad:function(obj) {
                $.ajax({
                    cache: false,
                    url: "<c:url value="/"/>cms/info/file/tempList.do",
                    dataType: "json",
                    data:{atchFileId:$("#dataFile").val()},
                    success: function(data) {
                        for(var i=0;i<data.length;i++) {
                            obj.createProgress(data[i]);
                        }
                    }
                });
            },
            onSubmit:function(files) {
            },
            onSuccess:function(files,data,xhr,pd) {


                var url ="<c:url value='/site/info/file/ImageView.do?atchFileId=' />"+data.atchFileId+"&fileSn="+data.fileSn;


                var testVal =data.orignlFileNm+"("+getSizeStr(data.fileSize)+")</br>"+url;
                pd.filename.html(testVal)



                $("#dataFile").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#dataFile").val(),prixFixe:''}
                return data;
            },
            onError: function(files,status,errMsg,pd) {
            },
            deleteCallback: function (data, pd) {
                $.ajax({
                    cache: false,
                    url: "<c:url value="/"/>cms/info/file/delete.do",
                    dataType: "json",
                    data:{atchFileId:data.atchFileId,fileSn:data.fileSn},
                    success: function(data) {
                        pd.statusbar.hide(); //You choice.
                    }
                });
            },
            downloadCallback:function(data,pd) {
                location.href="/cms/info/file/fileDown.do?atchFileId="+data.atchFileId+"&fileSn="+data.fileSn;
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


    function getSizeStr(size) {
        var sizeStr = "";
        var sizeKB = size / 1024;
        if(parseInt(sizeKB) > 1024) {
            var sizeMB = sizeKB / 1024;
            sizeStr = sizeMB.toFixed(2) + " MB";
        } else {
            sizeStr = sizeKB.toFixed(2) + " KB";
        }
        return sizeStr;
    }
</script>

<div class="sub subView">
    <!-- 등록폼 -->
    <form:form commandName="jewMdisVO">
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
                    <input type="hidden" name="collectionStrDay" id="collectionStrDay" value="" />
                    <input type="hidden" name="collectionEndDay" id="collectionEndDay" value="" />
                    <form:hidden path="jewMdisSno"/>
                    <th style="width:100px"><label for="mdisType">자료유형<span class="pilsu">*</span></label></th>
                    <td class="left" >
                        <form:input path="mdisType" title="${title} ${inputTxt}" maxlength="10"/>
                    </td>
                    <th style="width:100px"  ><label for="mdisNum">자료번호<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="mdisNum" title="${title} ${inputTxt}" maxlength="10" />
                    </td>
                </tr>
                <tr>
                    <th><label for="mdisKorNm">자료명(국문)<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                            <%--                        <form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다" title="${title} ${inputTxt}" maxlength="8" />--%>
                        <form:input path="mdisKorNm" maxlength="200" cssClass="w600"/>
                    </td>
                </tr>
                <tr>  <th>자료명(영문)<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <input type="text" id="mdisEnNm" name="mdisEnNm" value="${jewMdisVO.mdisEnNm}" maxlength="200" class="w600">
                        <%--<form:input path="mdisEnNm" maxlength="200" cssClass="w600"/>--%>
                    </td>
                </tr>
                <tr>
                    <th>자료인용서식<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:textarea path="dataOfForm" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <th>연구과제명(국문)<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:input path="projectKorNm" maxlength="150" cssClass="w600"/>
                    </td>
                </tr>
                <tr>
                    <th>연구과제명(영문)<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <input type="text" id="projectEnNm" name="projectEnNm" value="${jewMdisVO.projectEnNm}" maxlength="200" class="w600">
                    </td>
                </tr>
                <tr>
                    <th>연구책임자<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="pi" maxlength="10" cssClass="w200"/>
                    </td>
                    <th>공동연구자<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="coPi" maxlength="100" cssClass="w200"/>
                    </td>
                </tr>
                <tr>
                    <th>연구수행기관<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="organization" maxlength="25" cssClass="w200"/>
                    </td>
                    <th>연구비지원기관<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="supportingOrganization" maxlength="25" cssClass="w200"/>
                    </td>
                </tr>
                <tr>
                    <th>저작권자<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="copyrightHolder" maxlength="25" cssClass="w300"/>
                    </td>
                    <th>키워드<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="keyword" maxlength="100" cssClass="w300"/>
                    </td>
                </tr>

                <tr>
                    <th>조사목적<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:textarea path="investigatePurpose" maxlength="250"/>
                    </td>
                </tr>
                <tr>

                    <th>조사내용<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:textarea path="investigateContent" maxlength="250"/>
                    </td>
                </tr>
                <tr>
                    <th>자료수집기간</th>
                    <td>
                        <input class="date" type="date" id="strDay" name="strDay" max="9999-12-31" value="${jewMdisVO.collectionStrDay}">
                        <span>~</span>
                        <input class="date" type="date" id="endDay" name="endDay" max="9999-12-31" value="${jewMdisVO.collectionEndDay}">
                    </td>
                </tr>
                <tr>
                    <th>조사지역<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="investigateArea" maxlength="25" />
                    </td>
                    <th>분석단위<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="analysisUnit" maxlength="25"/>
                    </td>
                </tr>
                <tr>
                    <th>조사대상<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="subject" maxlength="100" cssClass="w300"/>
                    </td>
                    <th>시간적차원<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="mdisTime" maxlength="25" cssClass="w300"/>
                    </td>
                </tr>
                <tr>
                    <th>조사수행기관<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="researchOrganization" maxlength="25"/>
                    </td>
                    <th>표본추출방법<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="extraction" maxlength="50"/>
                    </td>
                </tr>
                <tr>
                    <th>조사방법</th>
                    <td colspan="3">
                        <input id="interviewSurvey" name="interviewSurvey" type="checkbox" <c:if test="${jewMdisVO.interviewSurvey =='Y'}">checked</c:if>><label for="interviewSurvey">면대면</label>
                        <input id="selfAdministeredSurvey" name="selfAdministeredSurvey" type="checkbox" <c:if test="${jewMdisVO.selfAdministeredSurvey == 'Y'}">checked</c:if>><label for="selfAdministeredSurvey">자기기입식</label>
                        <input id="mailSurvey" name="mailSurvey" type="checkbox" <c:if test="${jewMdisVO.mailSurvey == 'Y'}">checked</c:if>><label for="mailSurvey">우편조사</label>
                        <input id="phoneSurvey" name="phoneSurvey" type="checkbox" <c:if test="${jewMdisVO.phoneSurvey == 'Y'}">checked</c:if>><label for="phoneSurvey">전화조사</label>
                        <input id="onlineSurvey" name="onlineSurvey" type="checkbox" <c:if test="${jewMdisVO.onlineSurvey == 'Y'}">checked</c:if>><label for="onlineSurvey">온라인조사</label>
                    </td>
                </tr>
                <tr>
                    <th>조사방법기타<span class="pilsu">*</span></th>
                    <td colspan="3">
                        <form:input path="etcSurvey" maxlength="50" cssClass="w300"/>
                        <br>
                        (해당사항에 표시하고 필요한 경우 상세 기술함)
                    </td>
                </tr>
                <tr>
                    <th>사례수<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="caseNumber" maxlength="50"/>
                    </td>
                    <th>가중치<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="weight" maxlength="25"/>
                    </td>
                </tr>

                <tr>
                    <th>자료형식<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="dataForm" maxlength="25"/>
                    </td>
                    <th>자료구성<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="dataStructure" maxlength="25"/>
                    </td>
                </tr>
                <tr>
                    <th>자료공개여부</th>
                    <td colspan="3">
                        <form:select path="publicYn">
                            <form:option value="Y" label="공개"/>
                            <form:option value="N" label="공개안함"/>
                            <form:option value="D" label="제한적 공개"/>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <th>자료공개사유</th>
                    <td colspan="3">
                        <form:input path="publicReasons" maxlength="100" cssClass="w400"/>
                    </td>
                </tr>
                <tr>
                    <th>비고</th>
                    <td colspan="3">
                        <form:textarea path="remark" maxlength="250"/>
                    </td>
                </tr>
                <tr>

                    <th>사용언어<span class="pilsu">*</span></th>
                    <td>
                        <form:input path="mdisLanguage" maxlength="25"/>
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
                    <th><label >기타파일<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                        <div id="etcFileuploader">Upload</div>
                    </td>
                </tr>
                <tr>
                    <th><label >데이터파일<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                        <div id="fileuploader">Upload</div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <input name="etc" id="etc" type="hidden" value="${jewMdisVO.etc}">
        <input name="dataFile" id="dataFile" type="text" value="${jewMdisVO.dataFile}">
    </form:form>
    <!-- 하단 버튼 -->
    <div class="btn-set right">
        <button type="button" id="btn_save" class="btn main" name="btn_save">수정</button>
    </div>
</div>

