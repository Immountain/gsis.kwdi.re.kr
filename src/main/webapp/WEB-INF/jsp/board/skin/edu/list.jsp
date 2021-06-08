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

    <h3 class="subtitle">교육정보</h3>

<div class="inBoard list">
     <form name="searchVO" action="<c:url value='${boardVO.actionListUrl}'/>" method="post" onSubmit="fn_board_search_page(); return false;">
        <fieldset>
            <legend class="sr-only">교육 검색</legend>

            <label for="category">교육유형</label>
            <span class="select-outline">
                  <select id="category" name="category">
                       <option value="">전체</option>
                     <c:forEach items="${categoryList}" var="item" varStatus="status">
                         <option value="${item.boardCategoryId}" <c:if test="${item.boardCategoryId == searchVO.category}">selected="selected"</c:if>> ${item.boardCategoryNm}</option>

                     </c:forEach>

                   </select>
            </span>
            <label for="searchCondition">검색어</label>
            <span class="select-outline opt">
                <select id="searchCondition" name="searchCondition">
                    <option selected value=''>선택하세요</option><!-- 선택하세요 -->
					<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >제목</option>
					<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >내용</option>
                </select>
            </span>
            <div class="search-box">
                <input id="searchKeyword" type="text" name="searchKeyword" value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155">
                <button type="submit"><i class='bx bx-search'></i>검색</button>
            </div>



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

                    <h4>
                        <span>제목</span>${item.title}
                    </h4>
                    <p class="num"><span>번호</span><u><c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }"/></u></p>
                    <p class="category"><span>교육유형</span><u>${item.categoryNm}</u></p>
                    <p class="term"><span>접수기간</span><u>${item.noticeStartYyyy}-${item.noticeStartMm}-${item.noticeStartDd}~${item.noticeEndYyyy}-${item.noticeEndMm}-${item.noticeEndDd}</u></p>
                    <p class="stat"><span>상태</span> <u <c:if test="${item.checkDayYn == 'Y'}">class="stat-on"</c:if> > <c:if test="${item.checkDayYn == 'Y'}">접수중</c:if><c:if test="${item.checkDayYn == 'N'}">접수마감</c:if></u></p>
                    <p class="date"><span>공고일</span><u>${item.regDtYyyy}-${item.regDtMm}-${item.regDtDd}</u></p>

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
