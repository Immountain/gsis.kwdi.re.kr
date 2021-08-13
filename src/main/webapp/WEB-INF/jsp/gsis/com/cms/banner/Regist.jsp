<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle">메인배너 등록</c:set>
<script type="text/javascript">


    $(document.body).ready(function () {

        //닫기
        $('#modal-close').click(function () {
            ax5modal.close();



        });


        $("#fileuploader").uploadFile({
            url: "<c:url value="/"/>cms/info/file/upload.do",
            atchFileId:$("#bannerImage").val(),
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
                    data:{atchFileId:$("#bannerImage").val()},
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

                $("#bannerImage").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#bannerImage").val(),prixFixe:'B_'}
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



        $("#mobileFileuploader").uploadFile({
            url: "<c:url value="/"/>cms/info/file/upload.do",
            atchFileId:$("#mobileBannerImage").val(),
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
                    data:{atchFileId:$("#mobileBannerImage").val()},
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

                $("#mobileBannerImage").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#mobileBannerImage").val(),prixFixe:'B_'}
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




        $('#btn_save').click(function() {


            //ajax 통신은 jsp 에서 체크
            var bannerType = $('#bannerType').val();
            var bannerNm = $('#bannerNm').val();
            var bannerMemo = $('#bannerMemo').val();
            var strDt = $('#strDt').val();
            var endDt = $('#endDt').val();
            var sortOrdr = $('#sortOrdr').val();




            var useYn = $('#useYn').val();

            if(!bannerType){
                alert("배너타입 선택하세요")
                return;
            }

            if(!bannerNm){
                alert("배너제목 입력하세요")
                return;
            }

            if(!bannerMemo){
                alert("메모 입력하세요")
                return;
            }
            if(!strDt){
                alert("시작일 입력하세요")
                return;
            }

            if(!endDt){
                alert("종료일 입력하세요")
                return;
            }
            if(!sortOrdr){
                alert("정렬순번 입력하세요")
                return;
            }

            if(!useYn){
                alert("사용여부 입력하세요")
                return;
            }

            var formData = $("#searchVO").serializeObject();

            var API_SERVER = "<c:url value='/cms/gsis/banner/insertJewBanner.do' />";
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
    <form:form commandName="searchVO">
        <h3 class="btitle">
            등록 내역
        </h3>
        <div class="rows white-box">
            <table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
                <tbody>

                <tr>
                    <th>배너타입<span class="pilsu">*</span></th>
                    <td class="left" colspan="3" >
                        <form:select path="bannerType">
                            <form:option value="" label="선택하세요"/>
                            <form:option value="IMAGE" label="배너 이미지"/>
                            <form:option value="LIST" label="배너 리스트"/>
                        </form:select>
                        <div><form:errors path="useYn" cssClass="error" /></div>
                    </td>
                </tr>

                <tr>
                    <th><label for="bannerNm">제목<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3" >
                        <form:input path="bannerNm" maxlength="20" cssClass="w400" placeholder="ex)여성의 경제활동 참가율"/>
                        <div><form:errors path="bannerNm" cssClass="error"/></div>
                    </td>

                </tr>
                <tr>
                    <th><label for="bannerMemo">메모<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                        <form:input path="bannerMemo" maxlength="100" cssClass="w400" placeholder="ex)2019"/>
                        <div><form:errors path="bannerMemo" cssClass="error" /></div>
                    </td>


                </tr>
                <tr>
                    <th><label for="bannerTxt">내용<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                        <form:input path="bannerTxt" maxlength="100" cssClass="w400"  placeholder="ex)52.8%"/>
                        <div><form:errors path="bannerTxt" cssClass="error" /></div>
                    </td>


                </tr>

                <tr>
                    <th><label for="strDt">시작일<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="strDt" type="date"/>
                        <div><form:errors path="strDt" cssClass="error" /></div>
                    </td>
                    <th>종료일<span class="pilsu">*</span></th>
                    <td class="left">
                        <form:input path="endDt" type="date"/>
                        <div><form:errors path="endDt" cssClass="error"/></div>
                    </td>
                </tr>

                <tr>
                    <th><label for="linkType">링크타입<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:select path="linkType">
                            <form:option value="" label="사용안함"/>
                            <form:option value="_self" label="_self"/>
                            <form:option value="_blank" label="_blank"/>
                        </form:select>
                        <div><form:errors path="sortOrdr" cssClass="error" /></div>
                    </td>
                    <th>링크주소<span class="pilsu">*</span></th>
                    <td class="left">
                        <form:input path="linkUrl"/>
                        <div><form:errors path="linkUrl" cssClass="error"/></div>
                    </td>
                </tr>


                <tr>
                    <th><label for="sortOrdr">정렬순번<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <form:input path="sortOrdr" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다"  maxlength="8" />
                        <div><form:errors path="sortOrdr" cssClass="error" /></div>
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
                    <th><label >PC 이미지 업로드<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                        <div id="fileuploader">Upload</div>
                    </td>
                </tr>
                <tr>
                    <th><label >모바일 이미지 업로드<span class="pilsu">*</span></label></th>
                    <td class="left" colspan="3">
                        <div id="mobileFileuploader">Upload</div>
                    </td>
                </tr>


                </tbody>
                <form:hidden path="bannerImage"></form:hidden>
                <form:hidden path="mobileBannerImage"></form:hidden>
            </table>
        </div>
    </form:form>
    <!-- 하단 버튼 -->
    <div class="btn-set right">
        <button type="button" id="btn_save" class="btn main" name="btn_save">등록</button>
    </div>
</div>

