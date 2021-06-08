<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script type="text/javascript">
	$(document).ready(function(){
	});
</script>

<div id="content" class="sub sub01">
    <div class="sub-head">
        <nav class="sub-menu">
            <button type="button">${menuInfo.siteMemuNm}</button>


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
                    <%--&lt;%&ndash;<small>더 큰 제주, 하나되는 <span>글로벌 제주인</span></small>&ndash;%&gt;--%>
                    <%--<small> <spring:message code="메인.제주인이하나가되는글로벌제주인네트워크"/></small>--%>
                    <%--<span>${festivityYearVo.festivityYear}</span>--%>
                <%--</h2>--%>

                <%--<div class="navs">--%>

                    <%--<button class="left off">이전</button>--%>
                    <%--<button class="right">다음</button>--%>



                <%--</div>--%>
            <%--<c:if test="${fn:length(applicationList)>0}">--%>
                <%--<a class="button" href="">참가신청</a>--%>
            <%--</c:if>--%>




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
        </div>
    </div>

    <div class="content-box">

        <article class="event-navi">
            <div class="container">


                <nav class="navi white">

                    <button type="button">${searchVO.festivityYear}</button>
                    <div class="outline">
                        <c:forEach items="${festivityYearList}" var="item" varStatus="status">
                            <a href="<info:url value="/festivity/list.do?festivityYear=${item.festivityYear}"/>">${item.festivityYear}</a>
                        </c:forEach>


                       <%----%>
                        <%--<a href="">2020</a>--%>
                        <%--<a href="">2021</a>--%>
                    </div>
                </nav>



            </div>
        </article>

        <article class="event-list">
            <div class="container">

                <h3 class="sr-only">행사 리스트</h3>

                <ul class="list">

                    <c:forEach items="${wjFestivityList}" var="item" varStatus="status">
                        <li>
                            <a href="<info:url value="/festivity/info.do?festivityId=${item.festivityId}"/>">
                                <h4>
                                    <small>${item.festivityStartYyyy}.${item.festivityStartMm}.${item.festivityStartDd}~${item.festivityEndMm}.${item.festivityEndDd}.</small>


                                    <%--2019.10.12~10.14.--%>

                                    <span>
                                            ${item.festivitySubject}
                                        </span>
                                </h4>
                                <%--<p class="text">--%>
                                        <%--${item.festivityPlace}--%>
                                <%--</p>--%>
                            </a>
                        </li>


                    </c:forEach>



                </ul>

            </div>
        </article>
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