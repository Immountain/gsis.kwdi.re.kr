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

        document.searchVO.action = "${boardVO.actionViewUrl}?itemId="+item;
        document.searchVO.submit();
    }


    //-->

</script>

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
                    <p>Home</p>
                    <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

                </div>
            </article>

            <c:if test="${fn:length(noticelist) != 0}">



                <article class="mov-head">
                    <div class="container">

                        <h4 class="sr-only">
                            영상자료
                        </h4>

                        <div class="movie">
                            <iframe src="https://www.youtube.com/embed/${noticelist[0].temp1}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

                        </div>

                        <h5>
                            <small>${noticelist[0].regDtYyyy}-${noticelist[0].regDtMm}-${noticelist[0].regDtDd}</small>

                                ${noticelist[0].title}
                        </h5>

                        <p class="text">
                            <c:out value="${fn:replace(noticelist[0].contentInfoDecode , crlf , '<br/>') }" escapeXml="false"/>
                        </p>
                    </div>
                </article>

        </c:if>





            <article class="mov-list">
                <div class="container">

                    <h4 class="sr-only">
                        ${menuInfo.siteMemuNm}
                    </h4>

                    <ul class="list">

                    <c:forEach items="${boardItemList}" var="item" varStatus="status">
                        <li>
                            <a href="${item.linkUrl}" target="${item.linkType}">
                                <p class="image">
                                    <img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.boardFile}">
                                </p>
                                <h5>
                                    ${item.title}
                                </h5>

                                <p class="date">
                                  ${item.regDtYyyy}.${item.regDtMm}.${item.regDtDd}
                                </p>

                            </a>
                        </li>
                    </c:forEach>


                        <%--<c:forEach items="${boardItemList}" var="item" varStatus="status">--%>

                            <%--<li>--%>
                                <%--<a href="${item.linkUrl}" target="${item.linkType}">--%>

                                    <%--<div class="info">--%>
                                        <%--<span>${item.temp1}</span>--%>
                                            <%--&lt;%&ndash;<span>조회수 68</span>&ndash;%&gt;--%>
                                    <%--</div>--%>
                                    <%--<h5>--%>
                                        <%--<small>NO. <c:out value="${paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo-1) * paginationInfo.recordCountPerPage + status.index) }"/></small>--%>
                                            <%--${item.title}--%>
                                    <%--</h5>--%>

                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    </ul>

                </div>
            </article>

            <article class="board-tools">
                <div class="container">

                    <fieldset class="search">
                        <legend class="sr-only">게시판 검색</legend>

                        <input type="text" id="searchKeyword" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>">
                        <button type="submit">검색</button>
                    </fieldset>

                </div>


            </article>


            <article class="pagenation">

                <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_board_select_linkPage"/>
            </article>
            <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
            <input name="siteMemuId" type="hidden" value="<c:out value='${menuInfo.siteMemuId}'/>">
            <input name="searchCondition" type="hidden" value="1">


        </div>
    </form>

</div>

