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
    //-->

</script>

<section class="${pageCss}">

<div class="container">

    <h3 class="subtitle">공지사항</h3>

<div class="inBoard list">

    <%--<c:if test="${fn:length(categoryList) != 0}">--%>


        <%--<ul class="category">--%>

            <%--<c:forEach items="${categoryList}" var="item" varStatus="status">--%>
                <%--<li>--%>
                    <%--<a href="">${item.boardCategoryNm}</a>--%>
                <%--</li>--%>

            <%--</c:forEach>--%>
        <%--</ul>--%>
    <%--</c:if>--%>
    <form name="searchVO" action="<c:url value='${boardVO.actionListUrl}'/>" method="post" onSubmit="fn_board_search_page(); return false;">
        <input name="category" id="category" type="hidden" value="<c:out value='${searchVO.category}'/>">


        <fieldset class="search">
            <legend class="sr-only">게시판 검색</legend>
            <p class="counter">전체 <strong>${paginationInfo.totalRecordCount}</strong>건</p>
            <label for="searchCondition">검색범위</label>
            <span class="select-outline">
                <select id="searchCondition" name="searchCondition">
                    <option selected value=''>선택하세요</option><!-- 선택하세요 -->
					<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >제목</option>
					<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >내용</option>
                </select>
            </span>

            <label for="searchKeyword">검색어</label>
            <input id="searchKeyword" type="text" name="searchKeyword" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
            <button type="submit"><i class='bx bx-search'></i>검색</button>
        </fieldset>





        <ul class="list">
            <c:if test="${fn:length(boardItemList) == 0}">
                <tr>
                    <td colspan="6">내용이 없습니다</td>
                </tr>
            </c:if>
            <c:forEach items="${boardItemList}" var="item" varStatus="status">
            <li>
                <a href="<c:url value='${boardVO.actionViewUrl}?itemId=${item.itemId}'/>">

                    <h4><span>제목</span>${item.title}  <c:if test="${item.fileCnt > 0}"> <i class='bx bx-paperclip'></i></c:if>     </h4>
                    <p class="num"><span>번호</span><u><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }"/></u></p>
                    <p class="name"><span>작성자</span><u>${item.regNm}</u></p>
                    <p class="date"><span>등록일</span><u>${item.regDtYyyy}-${item.regDtMm}-${item.regDtDd}</u></p>
                    <p class="count"><span>조회수</span><u>${item.readCnt} </u></p>
                </a>
            </li>
            </c:forEach>
        </ul>
        <div class="btn-set align">
            <div class="left">
            </div>
            <div class="right">

                <c:if test="${boardVO.boardAuth.writeYn >0}">
                <button class="btn main" type="button" onclick="fn_board_edit_page()"><i class='bx bx-pencil' ></i>작성하기</button>
                </c:if>
            </div>
        </div>


        <article class="pagenation">
            <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_board_select_linkPage"/>
        </article>
        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
        <input name="siteMemuId" type="hidden" value="<c:out value='${menuInfo.siteMemuId}'/>">
    </form>
</div>
</div>

</section>
