<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">

<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value='/source/js/common.js'/>" ></script>

<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<script src="<c:url value='/js/infomind/com/incms.polyfill.js' />"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
$ifx.contextPath='<c:url value="/"/>';
</script>


<script type="text/javascript">


    $(document).ready(function() {

        getCommentList();


        $('#btn_comment_save').click(function() {
            var saveQuestion = confirm("저장 하시겠습니까?");
            if (saveQuestion) {
                var p = {
                    itemId: $("#itemId").val(),
                    title: $("#title").val(),
                    txtContent: $("#txtContent").val(),
                    infoCommentSno:1
                };
                var API_SERVER = "<c:url value='/comment/cmm/save.do' />";
                $.ajax({
                    url : API_SERVER,
                    type : 'get',
                    data : p,
                    dateType:'json',
                    contentType: "application/json",  // ajax 통신으로 보내는 타입
                    beforeSend: function(xhr) {

                        xhr.setRequestHeader("AJAX", "true");

                    },
                    success : function(data) {
                        var jsonObj = JSON.stringify(data);
                        if(data.status=="0"){
                            if(data.message=="SUCCESS"){
                                alert("저장처리 완료했습니다.");
                                getCommentList();
                            }else{
                                alert(data.message);
                            }
                        }else{
                            alert("에러가 발생했습니다.");
                        }
                    }, // success
                    error: function(XMLHttpRequest, textStatus, errorThrown) {

                    }
                });
            }

        });
        $('#btn_comment_delete').click(function() {
            alert("삭제")

        });

    });

    /*********************************************************
     * 코멘트 불려오기
     ******************************************************** */
    function getCommentList() {


        $.ajax({
            type : "GET", //전송방식을 지정한다 (POST,GET)
            url : "/comment/cmm/list.do",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
            dataType : "text",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
            data:{itemId:$("#itemId").val()},
            error : function(){
                alert("불러오기 실패 하였습니다.");
            },
            success : function(Parse_data){
                $("#commentList").html(Parse_data); //div에 받아온 값을 넣는다.
            }
        });

    }
    /*********************************************************
     * 코멘트 삭제
     ******************************************************** */
    function commentDelete() {

        alert("삭제");
    }


    /*********************************************************
     * 수정 페이지  함수
     ******************************************************** */
    function fn_board_upd_page(){


        document.searchVO.action = "${boardVO.actionUpdViewUrl}";
        document.searchVO.submit();
    }



</script>


<div class="inBoard view">
    <form name="searchVO" id="searchVO">
        <div class="board-box">
            <h4><small>카테고리명</small> ${boardItem.title}</h4>
            <ul class="info">
                <li class="name">작성자 <u>관리자</u></li>
                <li class="date">작성일자 <u>  ${boardItem.regDtYyyy}- ${boardItem.regDtMm}- ${boardItem.regDtDd}</u></li>
                <li class="count">조회수 <u> ${boardItem.readCnt}</u></li>
            </ul>
        </div>
        <hr>

        <div class="board-text">
            ${boardItem.contentInfoDecode}${boardVO.actionUpdViewUrl}
        </div>


        <c:if test="${fn:length(fileList) != 0}">
            <ul class="file">
                <c:forEach items="${fileList}" var="item" varStatus="status">
                    <li>
                        <i class='bx bx-file'></i>
                        <a href="" title="${item.orignlFileNm}">${item.orignlFileNm}</a>
                    </li>

                </c:forEach>


            </ul>
        </c:if>



        <c:if test="${boardItem.linkType != ''}">

            <ul class="link">
                <li>
                    <i class='bx bx-link'></i>
                    <a href="" title="http://daum.net">${boardItem.linkUrl}(${boardItem.linkType})</a>
                </li>

            </ul>

        </c:if>



        <div class="reply">





            <fieldset class="reply-input">
                <legend>댓글입력폼</legend>

                <div class="line">
                    <label for="reply-name">이름</label>
                    <input id="reply-name" type="text">
                </div>

                <div class="line">
                    <label for="password">비밀번호</label>
                    <input id="password" name="password" type="password">
                </div>

                <div class="rating">
                    <h5>별점입력</h5>
                    <input name="score" id="rating01" type="radio" value="1"><label for="rating01">1점</label>
                    <input name="score" id="rating02" type="radio" value="2"><label for="rating02">2점</label>
                    <input name="score" id="rating03" type="radio" value="3"><label for="rating03">3점</label>
                    <input name="score" id="rating04" type="radio" value="4"><label for="rating04">4점</label>
                    <input name="score" id="rating05" type="radio" value="5"><label for="rating05">5점</label>
                </div>

                <div class="tools">
                    <label for="secretYn">비밀글</label>
                    <input name="secretYn" id="secretYn" type="checkbox">
                </div>
                <div class="line">
                    <label for="title">제목</label>
                    <input id="title" name="title" type="text">
                </div>
                <div class="line">
                    <label for="txtContent">내용</label>
                    <textarea id="txtContent" name="txtContent"></textarea>
                </div>
                <button class="btn main" type="button" id="btn_comment_save" name="btn_comment_save"><i class='bx bxs-pencil' ></i>등록</button>
            </fieldset>



            <ul class="list" id="commentList" name="commentList">


            </ul>



        </div>



        <input type="hidden" name="itemId" id="itemId" value="${boardItem.itemId}">

        <div class="btn-set align">
            <%--<div class="left">--%>
            <%--<button class="button">수정</button>--%>
            <%--<button class="button">삭제</button>--%>
            <%--<button class="button main">등록</button>--%>
            <%--</div>--%>
            <div class="right">
                <button class="button main" type="button" onclick="fn_board_upd_page()" >수정</button>
                <button class="button main">삭제</button>
                <button class="button" type="button" onclick="history.back()" >뒤로가기</button>
            </div>
        </div>

    </form>

</div>





