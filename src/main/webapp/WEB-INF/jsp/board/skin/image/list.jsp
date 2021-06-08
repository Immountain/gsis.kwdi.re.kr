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
     * 상세 페이지  함수
     ******************************************************** */
    function fn_detail(item){

        document.searchVO.action = "<info:url value="${boardVO.actionViewUrl}"/>"+ "?itemId="+item;
        document.searchVO.submit();
    }


    //-->

</script>

<!-- S:mainContent -->
<div id="content" class="sub sub02">

    <!-- 각 구성요소 article로 모듈화 -->
    <div class="sub-head">

        <nav class="sub-menu">
            <button type="button">${menuInfo.parentNm}</button>
            <info:getMenuModel modelName="subSiteMenu" groupId="${SITEINFO.langCd}-primary" siteMemuId=""/>

            <div class="outline">
                <c:forEach items="${subSiteMenu}" var="item">
                    <c:if test="${item.viewYn eq 'Y'}">
                        <a <c:if test="${item.siteMemuId eq menuInfo.parentId}">class="active"</c:if> href="<info:url value="${item.url}"/>">${item.siteMemuNm}</a>
                    </c:if>
                </c:forEach>
            </div>
        </nav>
        <!-- background-item -->
        <div class='waves'>
            <div class='wave -one'></div>
            <div class='wave -two'></div>
            <div class='wave -three'></div>
        </div>
        <div class="bubble" >
            <ul class="circles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div >




    </div>




    <form name="searchVO" action="<info:url value="${boardVO.actionListUrl}"/>" method="post" onSubmit="fn_board_search_page(); return false;">
    <div class="content-box">

        <article class="menu-depth3 sub02 item4 normal">
            <!--
                1. 각 서브메뉴별 클래스명 적용 sub01, sub02 ...
                2. 서브메뉴 갯수별 클래스명 적용 item1, item2 ...
            -->
            <div class="container">
                <button type="button">${menuInfo.siteMemuNm}</button>

                <div class="outline">
                    <info:indicatorTag group="" skinName="board-sub-memu" siteMemuId="${menuInfo.siteMemuId}"/>
                </div>
            </div>
        </article>

        <article class="navigation">
            <div class="container">
                <p>홈</p>
                <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

            </div>
        </article>




        <article class="event-navi">
            <div class="container">
                <nav class="navi">
                    <button type="button">ALL</button>
                    <div class="outline">

                        <a href="/board/image/list.do">ALL</a>
                        <c:forEach items="${categoryList}" var="item" varStatus="status">
                                <a href="/board/image/list.do?category=${item.boardCategoryId}">${item.boardCategoryNm}</a>
                        </c:forEach>
                        <input name="category" type="hidden" value="<c:out value='${searchVO.category}'/>">
                        <input name="categoryNm" type="hidden" value="<c:out value='${searchVO.categoryNm}'/>">


                        <%--<select id="category" name="category">--%>
                            <%--<option value="">전체</option>--%>
                            <%--<c:forEach items="${categoryList}" var="item" varStatus="status">--%>
                                <%--<option value="${item.boardCategoryId}" <c:if test="${item.boardCategoryId == searchVO.category}">selected="selected"</c:if>> ${item.boardCategoryNm}</option>--%>

                            <%--</c:forEach>--%>

                        <%--</select>--%>
                   <%----%>

                        <%--<a href="">2020</a>--%>
                        <%--<a href="">2021</a>--%>
                    </div>
                </nav>
            </div>
        </article>


        <article class="photo-category-list">
            <div class="container">

                <h4 class="sr-only">
                    ${menuInfo.siteMemuNm}
                </h4>

                <ul class="list">
                <c:forEach items="${boardItemList}" var="item" varStatus="status">
                    <li>
                        <a href="<info:url value='${boardVO.actionViewUrl}?itemId=${item.itemId}'/>" onClick="fn_detail('<c:out value="${item.itemId}"/>');return false;">
                            <p class="image">
                                <img src="<c:url value="/"/>site/info/file/ImageThumbnailView.do?width=800&height=800&atchFileId=${item.file01}">
                            </p>
                            <h5>
                                    ${item.title}
                            </h5>

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



        <%--<article class="photo-arrow">--%>
            <%--<div class="container">--%>

                <%--<a href=""><i class='bx bx-caret-left'></i>이전</a>--%>
                <%--<a href=""><i class='bx bx-caret-right'></i>다음</a>--%>

            <%--</div>--%>
        <%--</article>--%>
    </div>
    </form>

</div>
<!-- E:mainContent -->



