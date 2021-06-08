<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#fileuploader").uploadFile({
            url: "<c:url value="/"/>site/info/file/upload.do",
            atchFileId:$("#boardFile").val(),
            viewUrl:"<c:url value='/site/info/file/ImageView.do' />",
            multiple:true,
            dragDrop:true,
            fileName:"uploadfile",
            maxFileCount:$("#maxFileCount").val(),
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
                    url: "<c:url value="/"/>site/info/file/tempList.do",
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
                $("#boardFile").val(data.atchFileId);
            },
            afterUploadAll:function(obj) {
            },
            dynamicFormData: function() {
                var data ={atchFileId:$("#boardFile").val(),prixFixe:'BOARD_'}
                return data;
            },
            onError: function(files,status,errMsg,pd) {
            },
            deleteCallback: function (data, pd) {
                $.ajax({
                    cache: false,
                    url: "<c:url value="/"/>site/info/file/delete.do",
                    dataType: "json",
                    data:{atchFileId:data.atchFileId,fileSn:data.fileSn},
                    success: function(data) {
                        pd.statusbar.hide(); //You choice.
                    }
                });
            },
            downloadCallback:function(data,pd) {
                location.href="/site/info/file/fileDown.do?atchFileId="+data.atchFileId+"&fileSn="+data.fileSn;
            }
        });



    });
    /*********************************************************
     * 저장처리화면
     ******************************************************** */
    function fn_regist_page(form){
        //input item Client-Side validate
        if(confirm("저장 하시겠습니까?")){
            form.submit();
        }
    }

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

<div class="container">
    <h3 class="subtitle">공지사항</h3>
    <form name="editVO" action="${pageContext.request.contextPath}${boardVO.actionInsertUrl}" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
        <div class="inBoard write">


            <div class="board-box">


                <div class="line">
                    <label for="category">카테고리</label>
                    <span class="select-outline">
                    <select id="category" name="category">
                        <option value="1">카테고리</option>
                        <option value="2">카테고리</option>
                        <option value="3">카테고리</option>
                    </select>
                </span>
                </div>


                <div class="line">
                    <label for="title">제목</label>
                    <input id="title" name="title" type="text">
                </div>
                <div class="line">
                    <label for="title">메모</label>
                    <input id="memo" name="memo" type="text">
                </div>




                <ul class="info">
                    <li class="name">
                        <label for="name">이름</label>
                        <input id="name" type="text">
                    </li>
                    <li class="pass">
                        <label for="password">비밀번호</label>
                        <input id="password" name="password" type="password">
                    </li>
                    <li class="notice">
                        <input id="noticeYn" name="noticeYn" type="checkbox">
                        <label for="noticeYn">공지로등록</label>
                    </li>
                    <li class="name">
                        <label for="noticeStartDate">공지시작일</label>
                        <input id="noticeStartDate" name="noticeStartDate" type="text">
                    </li>

                    <li class="name">
                        <label for="noticeEndDate">공지종료일</label>
                        <input id="noticeEndDate" name="noticeEndDate" type="text">
                    </li>

                    <li class="Secret">
                        <input id="secretYn" name="secretYn" type="checkbox">
                        <label for="secretYn" class="before">비밀글로등록</label>
                    </li>

                    <li class="Secret">
                        <input id="commentYn" name="commentYn" type="checkbox">
                        <label for="secretYn" class="before">문의여부</label>
                    </li>

                    <li class="Secret">
                        <label for="inquireType">문의형태</label>
                        <span class="select-outline">
                    <select id="inquireType" name="inquireType">
                        <option value="1">문의1</option>
                        <option value="2">문의1</option>
                        <option value="3">문의1</option>
                    </select>
                </span>
                    </li>


                </ul>
            </div>

            <hr>

            <div class="board-text">
                <label for="boardContent">내용</label>
                <textarea id="boardContent" name="boardContent"></textarea>
            </div>




            <div class="file">
                <%--<button class="btn main" type="button" id="fileuploader" name="fileuploader">파일첨부</button>--%>
                <div id="fileuploader">Upload</div>

                <%--<ul class="file">--%>
                <%--<li>--%>
                <%--<i class='bx bx-file'></i>--%>
                <%--<a href="" title="야옹야옹.hwp">야옹야옹.hwp</a>--%>
                <%--<button><i class='bx bx-x'></i>첨부파일 삭제</button>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<i class='bx bx-file'></i>--%>
                <%--<a href="" title="야옹야옹.hwp">야옹야옹.hwp</a>--%>
                <%--<button><i class='bx bx-x'></i>첨부파일 삭제</button>--%>
                <%--</li>--%>
                <%--</ul>--%>

            </div>


            <ul class="link">
                <li>

                    <label for="category">링크타입</label>
                    <span class="select-outline">
                    <select id="linkType" name="linkType">
                        <option value="">선택하세요</option>
                        <option value="youtube">youtube</option>
                        <option value="_self">_self</option>
                        <option value="_blank">_blank</option>

                    </select>
                </span>
                </li>
                <li>
                    <label class="before">링크URL</label>
                    <input class="main" type="text" name="linkUrl" id="linkUrl">
                </li>

            </ul>

            <input name="boardFile" id="boardFile" type="hidden" value="">
            <input name="maxFileCount" id="maxFileCount" type="text" value="2">

        </div>
        <!--E:SKIN-->

        <div class="btn-set center">
            <button class="btn main">등록</button>
            <button class="btn">취소</button>
        </div>


        <div class="btn-set">
            <button class="button main" type="submit" >등록</button>
            <button class="button" type="button" onclick="history.back()">취소</button>
        </div>
    </form>
</div>

