<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script type="text/javascript">

    <!--
    /*********************************************************
     * 조회 처리 함수
     ******************************************************** */
    function fn_board_search_page(){
        document.searchVO.pageIndex.value = 1;
        document.searchVO.submit();
    }

    /*********************************************************
     * 페이징 처리 함수
     ******************************************************** */
    function fn_board_select_linkPage(pageNo){
        document.searchVO.pageIndex.value = pageNo;
        document.searchVO.submit();
    }


    /*********************************************************
     * 등록 페이지 처 함수
     ******************************************************** */
    function fn_board_edit_page(){

        document.searchVO.action = "${boardVO.actionEditUrl}";
        document.searchVO.submit();
    }

    /*********************************************************
     * 상세 페이지  함수
     ******************************************************** */
    function fn_detail(item){




        document.searchVO.action = "<info:url value="${boardVO.actionViewUrl}"/>"+ "?itemId="+item;
        document.searchVO.submit();
    }


    //-->

</script>



<!-- S:mainContent -->
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>
            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>
        </div>
    </section>


    <h4 class="stitle">${menuInfo.siteMemuNm}</h4>

    <form name="searchVO" action="<info:url value="${boardVO.actionListUrl}"/>" method="post" onSubmit="fn_board_search_page(); return false;">
        <input name="category" id="category" type="hidden" value="<c:out value='${searchVO.category}'/>">

    <article class="board-tools">
        <div class="container">

            <fieldset class="search">
                <legend class="sr-only">게시판 검색</legend>

                <input type="text" name="searchKeyword" id="searchKeyword">
                <button type="submit">검색</button>
            </fieldset>

        </div>
    </article>


    <article class="board-list">
        <div class="container">

            <h4 class="sr-only">
                게시판 리스트
            </h4>

            <ul class="list">

            <c:forEach items="${boardItemList}" var="item" varStatus="status">

                <li>
                    <a href="<info:url value='${boardVO.actionViewUrl}?itemId=${item.itemId}'/>" onClick="fn_detail('<c:out value="${item.itemId}"/>');return false;">

                        <p class="num">
                            <span>번호</span>
                            <c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }"/>.
                        </p>

                        <h5>
                            <span>제목</span>
                                ${item.title}

                                <c:if test="${item.fileCnt>0}">
                                     <i class='bx bx-paperclip'></i>
                                </c:if>
                        </h5>
                        <p class="name">
                            <span>작성자</span>
                                ${item.regNm}
                        </p>
                        <p class="date">
                            <span>등록일</span>
                                ${item.regDtYyyy}-${item.regDtMm}-${item.regDtDd}
                        </p>
                        <p class="count">
                            <span>조회수</span>
                                ${item.readCnt}
                        </p>

                    </a>
                </li>
            </c:forEach>

            </ul>

        </div>
    </article>

    <article class="pagenation">

        <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_board_select_linkPage"/>
    </article>
    <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
    <input name="siteMemuId" type="hidden" value="<c:out value='${menuInfo.siteMemuId}'/>">
    <input name="searchCondition" type="hidden" value="1">

    </form>

</div>
<!-- E:mainContent -->

