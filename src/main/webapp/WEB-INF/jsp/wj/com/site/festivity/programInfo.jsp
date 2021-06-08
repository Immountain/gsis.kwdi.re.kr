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
        <%--<article class="event-navi">--%>
            <%--<div class="container">--%>
                <%--<h2>--%>
                    <%--<small>더 큰 제주, 하나되는 <span>글로벌 제주인</span></small>--%>
                    <%--<span>${searchVO.festivityId}</span>--%>
                <%--</h2>--%>

                <%--<div class="navs">--%>
                    <%--<button class="left off">이전</button>--%>
                    <%--<button class="right">다음</button>--%>
                <%--</div>--%>
                <%--<a class="button" href=""><spring:message code="대회.참가신청"/></a>--%>
            <%--</div>--%>
        <%--</article>--%>

        <%--<article class="menu-depth3 item4">--%>
            <%--<div class="container">--%>
                <%--<info:indicatorTag group="" skinName="festivity-sub-memu" siteMemuId="${menuInfo.siteMemuId}"/>--%>
            <%--</div>--%>
        <%--</article>--%>

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
                            ${view.programNm}
                        <button type="button" class="left" onclick="location.href='<info:url value="/festivity/program.do?festivityId=${prevView.festivityId}&programSno=${prevView.programSno}"/>'"></button>
                        <button type="button" class="right" onclick="location.href='<info:url value="/festivity/program.do?festivityId=${nextView.festivityId}&programSno=${nextView.programSno}"/>'"></button>
                    </h4>

                    <ul class="location">
                        <c:forEach items="${categoryList}" var="item" varStatus="vs1">
                            <li <c:if test="${item.programSno eq searchVO.programSno}">class="active"</c:if>>
                                <a href="<info:url value="/festivity/program.do?festivityId=${item.festivityId}&programSno=${item.programSno}"/>">${item.programNm}</a>
                            </li>
                        </c:forEach>
                    </ul>

                    <div class="detail">
                        <p><spring:message code="대회.시간"/><span>${view.programStrTime}~${view.programEndTime}</span></p>
                        <p><spring:message code="대회.프로그램"/><span>${view.programNm}</span></p>
                        <p><spring:message code="대회.장소"/><span>${view.programPlace}</span></p>
                    </div>


                </div>
            </article>

            <article class="event-program-list">
                <div class="container">
                    <h4 class="sr-only">리스트</h4>
                    <ul class="list">
                        <c:forEach items="${forumList}" var="item" varStatus="status">
                            <li>
                                <a href="javascript:;">
                                    <p class="image"><img src="<c:url value="/site/info/file/ImageView.do?atchFileId=${item.atchFileId}"/>" alt="${item.mainName}"></p>
                                    <h5><small>${item.contTitle}</small>${item.mainTitle}</h5>
                                    <p class="name"><strong>${item.mainName}</strong>${item.mainInfo}</p>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </article>


            <c:if test="${view.pageType=='CONTENT'}">
                        ${view.contentInfoDecode}
            </c:if>
            <%--<article class="event-detail-li">--%>
                <%--<div class="container">--%>
                    <%--<h4>규모 > 사전신청자 약200명</h4>--%>


                    <%--<h5>서귀포 치유의 숲</h5>--%>
                    <%--<ul>--%>
                        <%--<li>--%>
                            <%--산림치유 프로그램 , 나무체조 , 호흡 등--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--치유의 숲에서 마을주민이 만든 차롱 도시락 체험--%>
                        <%--</li>--%>
                    <%--</ul>--%>


                    <%--<h5>제주투어 A코스-송악산</h5>--%>
                    <%--<ul>--%>
                        <%--<li>--%>
                            <%--송악산 둘레길 산책--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--제주향토정식 체험--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                    <%--<h5>제주투어 B코스-사려니숲길</h5>--%>
                    <%--<ul>--%>
                        <%--<li>--%>
                            <%--사려니숲길 산책, 4·3평화공원 관람--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--제주토속음식 체험--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</article>--%>


            <%--<article class="event-detail-td">--%>
                <%--<div class="container">--%>
                    <%--<h4>출연 > 제주특별자치도립 제주교향악단.합창단,양방언,김지선,김준수,하나아트</h4>--%>
                    <%--<ul>--%>
                        <%--<li>--%>
                            <%--<strong>--%>
                                    <%--<span>--%>
                                        <%--내용--%>
                                    <%--</span>--%>
                                <%--경기병 서곡 [F.v Suppe/Light Cavalry Overture]--%>
                            <%--</strong>--%>
                            <%--<p>--%>
                                    <%--<span>--%>
                                        <%--출연진--%>
                                    <%--</span>--%>
                                <%--제주특별자치도립 제주교향악단--%>
                            <%--</p>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<strong>--%>
                                    <%--<span>--%>
                                        <%--내용--%>
                                    <%--</span>--%>
                                <%--경기병 서곡 [F.v Suppe/Light Cavalry Overture]--%>
                            <%--</strong>--%>
                            <%--<p>--%>
                                    <%--<span>--%>
                                        <%--출연진--%>
                                    <%--</span>--%>
                                <%--제주특별자치도립 제주교향악단--%>
                            <%--</p>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<strong>--%>
                                    <%--<span>--%>
                                        <%--내용--%>
                                    <%--</span>--%>
                                <%--경기병 서곡 [F.v Suppe/Light Cavalry Overture]--%>
                            <%--</strong>--%>
                            <%--<p>--%>
                                    <%--<span>--%>
                                        <%--출연진--%>
                                    <%--</span>--%>
                                <%--제주특별자치도립 제주교향악단--%>
                            <%--</p>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
            <%--</article>--%>


            <c:if test="${fn:length(fileLsit) != 0}">
                <article class="event-detail-photo">
                    <div class="container">
                        <h4 class="sr-only">행사사진</h4>
                            <div class="slider">

                                <c:forEach items="${fileLsit}" var="item" varStatus="status">

                                    <div class="slide">
                                        <a href="">
                                            <img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.atchFileId}&fileSn=${item.fileSn}">
                                            <h5>이미지 제목</h5>
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                        <button class="arrow left">이전<i class='bx bx-chevron-left'></i></button>
                        <button class="arrow right">다음<i class='bx bx-chevron-right'></i></button>
                    </div>
                </article>
            </c:if>







            <%--<article class="event-navi-btns">--%>
                <%--<button class="left">제주인 성공스토리 토크쇼</button>--%>
                <%--<button class="right">글로벌 제주상공인 리더쉽 포럼</button>--%>
            <%--</article>--%>
        </c:if>

    </div>

</div>

<%--https://gsis.kwdi.re.kr/statHtml/statHtml.do?mode=tab&orgId=338&tblId=DT_13204_0003&vw_cd=null&list_id=null&scrId=&seqNo=&language=ko&obj_var_id=undefined&itm_id=undefined&conn_path=null&path=--%>


