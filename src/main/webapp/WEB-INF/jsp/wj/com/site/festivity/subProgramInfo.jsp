<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script type="text/javascript">
    $(document).ready(function () {
    });
</script>

<div id="content" class="sub sub01">
    <div class="sub-head">
        <nav class="sub-menu">
            <button>${menuInfo.parentNm}</button>
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
        <div class="bubble">
            <ul class="circles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div>
    </div>

    <div class="content-box">
        <article class="event-navi">
            <div class="container">
                <nav class="navi">
                    <button type="button">${wjFestivityInfoVO.festivityYear}</button>
                    <div class="outline">
                        <c:forEach items="${festivityYearList}" var="item" varStatus="status">
                            <a href="<info:url value="/festivity/list.do?festivityYear=${item.festivityYear}"/>">${item.festivityYear}</a>
                        </c:forEach>
                    </div>
                </nav>
            </div>
        </article>


        <article class="menu-depth3 sub01 item3">
            <!--
                1. 각 서브메뉴별 클래스명 적용 sub01, sub02 ...
                2. 서브메뉴 갯수별 클래스명 적용 item1, item2 ...
            -->
            <div class="container">
                <button type="button">${menuInfo.siteMemuNm}</button>

                <div class="outline">
                    <info:indicatorTag group="" skinName="festivity-sub-memu" siteMemuId="${menuInfo.siteMemuId}"/>


                    <%--<a class="active" href="">대회소개</a>--%>
                    <%--<a href="">주요프로그램</a>--%>
                    <%--<a href="">부대프로그램</a>--%>
                    <%--<a href="">기타</a>--%>
                </div>
            </div>
        </article>


        <article class="navigation">
            <div class="container">
                <p>Home</p>
                <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>
            </div>
        </article>


            <c:if test="${fn:length(categoryList) == 0}">

                <article class="event-empty">
                    <div class="container">
                        <h4>예정된 프로그램이 없습니다.</h4>

                    </div>
                </article>
            </c:if>

        <c:if test="${fn:length(categoryList) != 0}">

            <article class="event-program-head">
                <div class="container">
                    <h4>
                        ${view.subProgramTitle}
                        <button type="button" class="left " onclick="location.href='<info:url value="/festivity/subProgram.do?festivityId=${prevView.festivityId}&festivityProgramSno=${prevView.festivityProgramSno}"/>'"></button>
                        <button type="button" class="right" onclick="location.href='<info:url value="/festivity/subProgram.do?festivityId=${nextView.festivityId}&festivityProgramSno=${nextView.festivityProgramSno}"/>'"></button>
                    </h4>

                    <ul class="location">
                        <c:forEach items="${categoryList}" var="item" varStatus="vs1">
                            <li <c:if test="${item.festivityProgramSno eq searchVO.festivityProgramSno}">class="active"</c:if>>
                                <a href="<info:url value="/festivity/subProgram.do?festivityId=${item.festivityId}&festivityProgramSno=${item.festivityProgramSno}"/>">${item.subProgramTitle}</a>
                            </li>
                        </c:forEach>
                    </ul>

                    <%--<div class="detail">--%>
                        <%--<p>시간<span>111</span></p>--%>
                        <%--<p>프로그램<span>2222</span></p>--%>
                        <%--<p>장소<span>33</span></p>--%>
                    <%--</div>--%>


                </div>
            </article>

            <article class="event-program-list">
                <div class="container">
                    <h4 class="sr-only">리스트</h4>
                    <ul class="list">
                        <%--<c:forEach items="${forumList}" var="item" varStatus="status">--%>
                            <%--<li>--%>
                                <%--<a href="javascript:;">--%>
                                    <%--<p class="image"><img src="<c:url value="/site/info/file/ImageView.do?atchFileId=${item.atchFileId}"/>" alt="${item.mainName}"></p>--%>
                                    <%--<h5><small>${item.contTitle}</small>${item.mainTitle}</h5>--%>
                                    <%--<p class="name"><strong>${item.mainName}</strong>${item.mainInfo}</p>--%>
                                <%--</a>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    </ul>
                </div>
            </article>


            <c:if test="${view.pageType=='CONTENT'}">
                        ${view.contentInfoDecode}
            </c:if>

        </c:if>

    </div>

</div>

<%--https://gsis.kwdi.re.kr/statHtml/statHtml.do?mode=tab&orgId=338&tblId=DT_13204_0003&vw_cd=null&list_id=null&scrId=&seqNo=&language=ko&obj_var_id=undefined&itm_id=undefined&conn_path=null&path=--%>


