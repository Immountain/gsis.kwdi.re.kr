<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<%pageContext.setAttribute("crlf", "\r\n"); %>


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

    <h3 class="subtitle">연구시설∙장비 매뉴얼</h3>

<div class="inBoard list">
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
                <a class="link" href="<c:url value='${boardVO.actionViewUrl}?itemId=${item.itemId}'/>">
                    <p class="image">
                        <img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.file01}"/>
                    </p>
                    <h4>
                        <span>제목</span>${item.title}
                    </h4>
                    <p class="info">
                        발행기관(출처) : ${item.temp1} / 발행일 : ${item.temp2}/ 분류 : ${item.temp3} / 조회 : ${item.readCnt}
                    </p>
                    <p class="text">
                        <c:out value="${fn:replace(item.contentInfoDecode , crlf , '<br/>') }" escapeXml="false"/>
                    </p>
                </a>
                <c:if test="${item.fileCnt >0}">
                    <a class="download" href="/site/info/file/fileDown.do?atchFileId=${item.boardFile}"><i class='bx bx-download'></i> 다운로드</a>
                </c:if>
            </li>

        </c:forEach>




     </ul>



        <article class="pagenation">
            <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_board_select_linkPage"/>
        </article>
        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
        <input name="siteMemuId" type="hidden" value="<c:out value='${menuInfo.siteMemuId}'/>">
    </form>
</div>
</div>

</section>
