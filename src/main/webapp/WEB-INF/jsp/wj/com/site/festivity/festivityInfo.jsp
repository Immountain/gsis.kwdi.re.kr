<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<%pageContext.setAttribute("crlf", "\r\n"); %>

<script type="text/javascript">
	$(document).ready(function(){
	});
</script>
<%--${SITEINFO.langCd}-primary--%>



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

                <%--<c:if test="${fn:length(applicationList)>0}">--%>
                    <%--<a class="button" href=""><spring:message code="대회.참가신청"/></a>--%>
                <%--</c:if>--%>




            <%--</div>--%>
        <%--</article>--%>

        <%--<article class="menu-depth3 item4">--%>
            <%--<div class="container">--%>
                <%--&lt;%&ndash;<a class="active" href="<info:url value="/festivity/info.do"/>">대회소개</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="<info:url value="/festivity/program.do"/>">주요프로그램</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="">부대프로그램</a>&ndash;%&gt;--%>
                <%--<info:indicatorTag group="" skinName="festivity-sub-memu" siteMemuId="${menuInfo.siteMemuId}"/>--%>
                <%--&lt;%&ndash;<a href="">기타</a>&ndash;%&gt;--%>
            <%--</div>--%>
        <%--</article>--%>

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

        <article class="event-navi">
            <div class="container">
                <nav class="navi">
                    <button type="button">${view.festivityYear}</button>
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

                <%--<p>제주인대회</p>--%>
                <%--<p>대회소개</p>--%>
            </div>
        </article>




        <article class="event-head">
            <div class="container">
                <h4><spring:message code="대회.대회소개"/></h4>

                <dl>
                    <dt><spring:message code="대회.기간"/></dt>
                    <dd>${view.festivityStartYyyy}.${view.festivityStartMm}.${view.festivityStartDd}(<spring:message code="${view.festivityStrWeekday}"/>) ~ ${view.festivityEndDd}(<spring:message code="${view.festivityEndWeekday}"/>)</dd>

                    <dt><spring:message code="대회.장소"/></dt>
                    <dd><c:out value="${view.festivityPlace}"/></dd>

                    <dt><spring:message code="대회.참가규모"/></dt>
                    <dd>
                        <c:out value="${fn:replace(view.festivityScale , crlf , '<p/>') }" escapeXml="false"/>

                    </dd>

                    <dt><spring:message code="대회.주제"/></dt>
                    <dd><c:out value="${view.festivitySubject}"/></dd>

                    <dt><spring:message code="대회.주최"/></dt>
                    <dd><c:out value="${view.festivityHost}"/></dd>

                    <dt><spring:message code="대회.주관"/></dt>
                    <dd><c:out value="${view.festivitySupervises}"/></dd>
                </dl>
            </div>
        </article>

        <article class="event-schedule">
            <div class="container">
                <h4><spring:message code="대회.대회일정"/></h4>
                <c:forEach items="${scheduleDayGroup}" var="i" varStatus="vs1">
                    <fmt:parseDate value="${i.key}" var="parseDate" pattern="yyyyMMdd"/>
                    <div class="day">
                        <h5>DAY ${vs1.count} <small><fmt:formatDate value="${parseDate}" pattern="yyyy.MM.dd"/></small></h5>
                        <ul class="list">
                            <c:forEach items="${i.value}" var="j" varStatus="vs2">
                                <li>
                                    <span><c:out value="${j.scheduleStrTime}" /> ~ <c:out value="${j.scheduleEndTime}"/></span>
                                    <span><c:out value="${j.schedulePlace}"/></span>
                                    <strong><c:out value="${j.scheduleProgram}"/></strong>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:forEach>
            </div>
        </article>

        <%--<article class="event-status">--%>
            <%--<div class="container">--%>
                <%--<h4><spring:message code="대회.주요추진현황"/></h4>--%>
                   <%--<c:out value="${fn:replace(view.festivitySituation , crlf , '<p/>') }" escapeXml="false"/>--%>
                <%--</div>--%>
        <%--</article>--%>
    </div>
</div>

<%--행사 소개
기간:${view.festivityStrDay}~${view.festivityEndDay}</br>
장소:${view.festivityPlace}</br>
참가규모:${view.festivityScale}</br>
주제:${view.festivitySubject}</br>
주최:${view.festivityHost}</br>
주관:${view.festivitySupervises}</br></br></br>

<c:forEach items="${scheduleList}" var="item" varStatus="status">
시간:${item.scheduleStrTime} -${item.scheduleEndTime}
프로그램:${item.scheduleProgram}
장소:${item.schedulePlace} </br>
</c:forEach>

주요추진상황${view.festivitySituation}
--%>

