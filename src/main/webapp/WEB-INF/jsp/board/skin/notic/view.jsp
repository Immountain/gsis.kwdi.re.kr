<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<%pageContext.setAttribute("crlf", "\r\n"); %>

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



    });



    /*********************************************************
     * 리스 페이지  함수
     ******************************************************** */
    function fn_list_page(){

        document.searchVO.action = "<info:url value="${boardVO.actionListUrl}"/>";
        <%--document.searchVO.action = "${boardVO.actionListUrl}";--%>
        document.searchVO.submit();
    }

</script>
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>
            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>
        </div>
    </section>
    <h4 class="stitle">${menuInfo.siteMemuNm}</h4>



    <form name="searchVO" id="searchVO">

        <input type="hidden" name="itemId" id="itemId" value="${boardItem.itemId}">
        <input name="searchCondition" id="searchCondition" type="hidden" value="${searchVO.searchCondition}">
        <input name="searchKeyword" id="searchKeyword" type="hidden" value="${searchVO.searchKeyword}">
        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
        <input name="category" id="category" type="hidden" value="<c:out value='${searchVO.category}'/>">

    <article class="board-view-head">
        <div class="container">
            <h4>${boardItem.title}</h4>
            <div class="info">
                <span>작성자 : ${boardItem.regNm}</span>
                <span>등록일 : ${boardItem.regDtYyyy}- ${boardItem.regDtMm}- ${boardItem.regDtDd}</span>
            </div>
        </div>
    </article>
    <c:if test="${fn:length(fileList) != 0}">
    <article class="board-view-file">
        <div class="container">
            <h5>첨부파일</h5>
            <c:forEach items="${fileList}" var="item" varStatus="status">
                <a href="/site/info/file/fileDown.do?atchFileId=${item.atchFileId}&fileSn=${item.fileSn}" title="${item.orignlFileNm}">${item.orignlFileNm}</a>
            </c:forEach>
        </div>
    </article>

    </c:if>

    <c:if test="${fn:length(fileList2) != 0}">
        <article class="board-view-image">
            <div class="container">
                <h5 class="sr-only">이미지 뷰</h5>

                <div class="outline">
                    <c:forEach items="${fileList2}" var="item" varStatus="status">
                        <c:if test="${item.isImage()==true}">
                            <img src="/site/info/file/ImageView.do?atchFileId=${item.atchFileId}&fileSn=${item.fileSn}" alt="${item.orignlFileNm}">
                        </c:if>

                    </c:forEach>

                </div>
            </div>
        </article>
    </c:if>

    <article class="board-view-text">
        <div class="container">
            <div class="text-wrap">

                <c:out value="${fn:replace(boardItem.contentInfoDecode , crlf , '<br/>') }" escapeXml="false"/>

            </div>
        </div>
    </article>
    <article class="board-view-tools">
        <div class="container">


            <a class="list" href="javascript:fn_list_page();">목록으로</a>


        </div>
    </article>

    </form>
</div>

