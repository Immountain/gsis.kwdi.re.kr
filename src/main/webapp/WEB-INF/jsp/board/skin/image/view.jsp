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
        document.searchVO.action = "${boardVO.actionListUrl}";
        document.searchVO.submit();
    }


    /*********************************************************
     * 이미지 뷰  함수
     ******************************************************** */
    function fn_img_view(atchFileId,fileSn){

        var imgurl ="/site/info/file/ImageView.do?atchFileId="+atchFileId+"&fileSn="+fileSn ;

        $("#imageView").attr("src", imgurl);


    }



    function loadImg(_this) {
        $(_this).closest('p').attr('style', 'background: url("' + _this.src + '") center / cover no-repeat;');
    }


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

        <form name="searchVO" id="searchVO">

            <input type="hidden" name="itemId" id="itemId" value="${boardItem.itemId}">
            <input name="searchCondition" id="searchCondition" type="hidden" value="${searchVO.searchCondition}">
            <input name="searchKeyword" id="searchKeyword" type="hidden" value="${searchVO.searchKeyword}">
            <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
            <input name="category" id="category" type="hidden" value="<c:out value='${searchVO.category}'/>">

            <article class="navigation">
                <div class="container">
                    <p>Home</p>
                    <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

                </div>
            </article>

            <article class="photo-view">
                <div class="container">

                    <div class="big">
                        <h4>
                            ${boardItem.title}
                        </h4>

                        <p class="image">
                            <img id="imageView" name="imageView" src="/site/info/file/ImageView.do?atchFileId=${boardItem.file01}" onload="loadImg(this)">
                        </p>

                        <p class="text">
                            <strong>
                                ${boardItem.memo}
                            </strong>

                            <c:out value="${fn:replace(boardItem.contentInfoDecode , crlf , '<br/>') }" escapeXml="false"/>
                        </p>
                    </div>

                    <ul class="thumb">


                        <c:if test="${fn:length(fileList2) != 0}">

                            <c:forEach items="${fileList2}" var="item" varStatus="status">

                                <li>
                                    <a href="javascript:fn_img_view('${item.atchFileId}','${item.fileSn}');">

                                        <%--<img src="<c:url value="/"/>site/info/file/ImageThumbnailView.do?width=800&height=800&atchFileId=${item.file01}">--%>
                                        <%----%>

                                        <img src="/site/info/file/ImageThumbnailView.do?width=800&height=800&atchFileId=${item.atchFileId}&fileSn=${item.fileSn}" alt="${item.orignlFileNm}">
                                    </a>
                                </li>

                            </c:forEach>
                       </c:if>
                    </ul>
                </div>
            </article>

            <article class="board-view-tools">
                <div class="container">

                    <%--<button class="left">이전글</button>--%>
                    <%--<button class="right">다음글</button>--%>

                    <a class="list" href="javascript:fn_list_page();">목록으로</a>


                </div>
            </article>
        </form>
    </div>
</div>




