<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<!-- 테스트용 임시 -->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>




<c:set var="pageTitle">장비메뉴얼 게시판</c:set>
<script type="text/javascript">
    $(document).ready(function() {
        fncShowMessg();

        $("#fileuploader").uploadFile({
            url: "<c:url value="/"/>cms/info/file/upload.do",
            atchFileId:$("#boardFile").val(),
            viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
            allowedTypes:'pdf',
            multiple:true,
            dragDrop:true,
            fileName:"uploadfile",
            maxFileCount:5,
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
                    data:{atchFileId:$("#boardFile").val()},
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
                var url ="<c:url value='/cms/info/file/ImageView.do?atchFileId=' />"+data.atchFileId+"&fileSn="+data.fileSn;
                var testVal =data.orignlFileNm+"("+getSizeStr(data.fileSize)+")</br>"+url;
                pd.filename.html(testVal)
                $("#boardFile").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#boardFile").val(),prixFixe:'BOAD_'}
                return data;
            },
            onError: function(files,status,errMsg,pd) {
            },
            deleteCallback: function (data, pd) {

                if(confirm("현재 파일을 삭제하시겠습니까?")){

                    $.ajax({
                        cache: false,
                        url: "<c:url value="/"/>cms/info/file/delete.do",
                        dataType: "json",
                        data:{atchFileId:data.atchFileId,fileSn:data.fileSn},
                        success: function(data) {
                            pd.statusbar.hide(); //You choice.
                        }
                    });

                }



            },
            downloadCallback:function(data,pd) {
                location.href="/cms/info/file/fileDown.do?atchFileId="+data.atchFileId+"&fileSn="+data.fileSn;
            }
        });



        $("#fileuploader2").uploadFile({
            url: "<c:url value="/"/>cms/info/file/upload.do",
            atchFileId:$("#file01").val(),
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
                    data:{atchFileId:$("#file01").val()},
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
                var url ="<c:url value='/cms/info/file/ImageView.do?atchFileId=' />"+data.atchFileId+"&fileSn="+data.fileSn;
                var testVal =data.orignlFileNm+"("+getSizeStr(data.fileSize)+")</br>"+url;
                pd.filename.html(testVal)
                $("#file01").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#file01").val(),prixFixe:'BOAD_'}
                return data;
            },
            onError: function(files,status,errMsg,pd) {
            },
            deleteCallback: function (data, pd) {

                if(confirm("현재 파일을 삭제하시겠습니까?")){

                    $.ajax({
                        cache: false,
                        url: "<c:url value="/"/>cms/info/file/delete.do",
                        dataType: "json",
                        data:{atchFileId:data.atchFileId,fileSn:data.fileSn},
                        success: function(data) {
                            pd.statusbar.hide(); //You choice.
                        }
                    });

                }


            },
            downloadCallback:function(data,pd) {
                location.href="/cms/info/file/fileDown.do?atchFileId="+data.atchFileId+"&fileSn="+data.fileSn;
            }
        });



    });

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
    /* ********************************************************
     * 저장처리화면
     ******************************************************** */
    function fn_regist_page(form){


        if(!$('#title').val()){

            alert("제목을 입력하세요");
            return;
        }

        if(!$('#temp1').val()){

            alert("발행기관 입력하세요");
            return;
        }

        if(!$('#temp2').val()){

            alert("발행일 입력하세요");
            return;
        }

        if(!$('#temp3').val()){

            alert("분류 입력하세요");
            return;
        }


        if(confirm("수정하시겠습니까?")){
            form.submit();
        }
    }


    function fn_list() {


        document.resultVO.action = "<c:url value='/cms/info/board/c/boardItemList.do'/>";
        document.resultVO.submit();
    }



    /* ********************************************************
    * 서버 처리 후 메세지 화면에 보여주기
    ******************************************************** */
    function fncShowMessg(){
        if("<c:out value='${message}'/>" != ''){
            alert("<c:out value='${message}'/>");
        }
    }
</script>
<div class="sub subView">
    <h2 class="stitle">
        <i class='bx bxs-dashboard' ></i>${pageTitle} 수정
    </h2>
    <form:form commandName="resultVO" id="resultVO" name="resultVO" action="${pageContext.request.contextPath}/cms/info/board/c/update.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">

        <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
        <input name="boardFile" id="boardFile" type="hidden" value="${resultVO.boardFile}">
        <input name="file01" id="file01" type="hidden" value="${resultVO.file01}">
        <input name="cmd" type="hidden" value="<c:out value='save'/>">
        <input name="maxFileCount" id="maxFileCount" type="hidden" value="${boardVO.atchPosblFileNumber}">
        <input name="itemId" id="itemId" type="hidden" value="${resultVO.itemId}">
        <input id="noticeStartDate"  name="noticeStartDate" type="hidden" value="${resultVO.noticeStartDate}">
        <input id="noticeEndDate" name="noticeEndDate"  type="hidden" value="${resultVO.noticeEndDate}">


        <h3 class="btitle">${pageTitle} 수정</h3>
        <div class="rows white-box">
            <!-- 등록폼 -->
            <table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
                <tbody>
                <!-- 입력/선택 -->
                <tr>
                    <th style="width: 9%;">
                        <label for="boardId">게시판 아이디<span class="pilsu">*</span></label>
                    </th>
                    <td class="left">
                        <form:input path="boardId"  size="70" maxlength="20" readonly="true"/>
                        <div><form:errors path="boardId" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th style="width: 9%;">
                        <label for="title">제목<span class="pilsu">*</span></label>
                    </th>
                    <td class="left">
                        <form:input path="title"  size="70" maxlength="20" />
                        <div><form:errors path="title" cssClass="error" /></div>
                    </td>
                </tr><tr>
                    <th style="width: 9%;">
                        <label for="memo">메모<span class="pilsu">*</span></label>
                    </th>
                    <td class="left">
                        <form:input path="memo"  size="70" maxlength="20" />
                        <div><form:errors path="memo" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th style="width: 9%;">
                        <label for="memo">발행기관<span class="pilsu">*</span></label>
                    </th>
                    <td class="left">
                        <form:input path="temp1"  size="70" maxlength="20" />
                        <div><form:errors path="temp1" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th style="width: 9%;">
                        <label for="memo">발행일<span class="pilsu">*</span></label>
                    </th>
                    <td class="left">
                        <form:input path="temp2"  size="70" maxlength="20" />
                        <div><form:errors path="temp2" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th style="width: 9%;">
                        <label for="memo">분류<span class="pilsu">*</span></label>
                    </th>
                    <td class="left">
                        <form:input path="temp3"  size="70" maxlength="20" />
                        <div><form:errors path="temp3" cssClass="error" /></div>
                    </td>
                </tr>

                <tr>
                    <th><label for="boardContent">내용 <span class="pilsu">*</span></label></th>
                    <td class="nopd">
                        <div>
                            <form>
                                <textarea name="boardContent" id="boardContent" rows="10" cols="80">${resultVO.contentInfoDecode}</textarea>
                            </form>
                        </div>
                        <div><form:errors path="boardContent" cssClass="error" /></div>
                    </td>
                </tr>
                <tr>
                    <th><label >파일 업로드<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <div id="fileuploader">Upload</div>

                    </td>
                </tr>
                <tr>
                    <th><label >리스트 이미지<span class="pilsu">*</span></label></th>
                    <td class="left">
                        <div id="fileuploader2">Upload</div>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <!-- 하단 버튼 -->
        <div class="btn-set right">
            <c:if test="${menuInfo.authVO.modifyYn >0}">
            <input type="submit" class="button" value="수정하기" title="수정버튼" />
            </c:if>
            <button type="button" class="button main" onclick="fn_list()"  title="목록"  >목록</button>


        </div>

    </form:form>
</div>