<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<script type="text/javascript">
    $(document).ready(function() {

        //파일 업로드
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


        if(!$('#title').val()){

            alert("제목을 입력하세요");
            return;
        }

        if($("#noticeYn").is(":checked")){
            $("#noticeYn").val("Y");
        }else{
            $("#noticeYn").val("N");
        }



      var strDay =$('#strDay').val().replaceAll("-","")+$("#strHour option:selected").val() +$("#strMin option:selected").val();
      var endDay =$('#endDay').val().replaceAll("-","")+$("#endHour option:selected").val() +$("#endMin option:selected").val();

      $('#noticeStartDate').val(strDay);
      $('#noticeEndDate').val(endDay)


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
<section class="${pageCss}">

<div class="container">
    <h3 class="subtitle">공지사항</h3>
    <form name="editVO" action="${pageContext.request.contextPath}${boardVO.actionInsertUrl}" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
    <input name="boardFile" id="boardFile"  type="hidden" value="">
    <input name="maxFileCount" id="maxFileCount"  type="hidden" value="${boardVO.atchPosblFileNumber}">
    <input id="noticeStartDate"  name="noticeStartDate" type="hidden" value="">
    <input id="noticeEndDate" name="noticeEndDate"  type="hidden" value="">



      <div class="inBoard write">


            <div class="board-box">

                <div class="line">
                    <strong> <label for="title">제목</label></strong>
                    <input id="title" name="title" type="text" placeholder="제목을 입력하세요">
                </div>
               <div class="line">
                   <strong> <label for="title">메모</label></strong>
                    <input id="memo" name="memo" type="text" placeholder="메모 입력하세요">
                </div>



                <div class="line">
                    <strong>공지여부</strong>
                    <input id="noticeYn" name="noticeYn" type="checkbox"><label for="noticeYn">공지사항입니다.</label>
                </div>


                <div class="line">
                    <strong class="show">게시기간</strong>

                    <p class="rows">
                        <input class="date" type="date" id="strDay" name="strDay" max="9999-12-31" value="${boardVO.tempStrDay}">
                        <u>-</u>
                        <input class="date" type="date" id="endDay" name="endDay" max="9999-12-31" value="${boardVO.tempEndDay}">
                    </p>
                    <p class="rows">

                    <span class="select-outline time">
                        <select id="strHour" name="strHour">
                            <c:forEach var="hour" begin="0" end="23">
                              <option value="<c:if test="${hour<10}">0</c:if>${hour}"><c:if test="${hour<10}">0</c:if>${hour}</option>
                            </c:forEach>

                        </select>
                    </span>
                        <u>시</u>
                        <span class="select-outline time">
                        <select id="strMin" name="strMin">
                         <c:forEach var="min" begin="0" end="59">
                             <option value="<c:if test="${min<10}">0</c:if>${min}"><c:if test="${min<10}">0</c:if>${min}</option>
                         </c:forEach>
                        </select>
                    </span>
                        <u>분</u>
                        <u>-</u>

                        <span class="select-outline time">
                        <select id="endHour" name="endHour">
                              <c:forEach var="hour" begin="0" end="23">
                                  <option value="<c:if test="${hour<10}">0</c:if>${hour}"><c:if test="${hour<10}">0</c:if>${hour}</option>
                              </c:forEach>

                        </select>
                    </span>
                        <u>시</u>
                        <span class="select-outline time">
                        <select id="endMin" name="endMin">
                            <c:forEach var="min" begin="0" end="59">
                                <option value="<c:if test="${min<10}">0</c:if>${min}"><c:if test="${min<10}">0</c:if>${min}</option>
                            </c:forEach>
                        </select>
                    </span>
                        <u>분</u>
                    </p>
                </div>


            </div>
        <div class="board-text">
            <label for="boardContent">내용</label>
            <textarea id="boardContent" name="boardContent"></textarea>
        </div>

          <ul class="link">

              <li>

                  <label for="linkType">링크타입</label>
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
                  <label class="before">링크</label>
                  <input class="main" type="text" name="linkUrl" id="linkUrl">
              </li>

          </ul>
          <div id="fileuploader">Upload</div>
    </div>
    <!--E:SKIN-->

        <div class="btn-set center">
            <button class="btn main" type="submit">등록</button>
            <button class="btn" type="button" ype="button" onclick="history.back()">취소</button>
        </div>
    </form>
</div>
</section>
