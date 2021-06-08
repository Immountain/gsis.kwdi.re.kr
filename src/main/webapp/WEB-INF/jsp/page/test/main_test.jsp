<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<div id="content" class="main">


    <c:choose>
        <c:when test="${festivitySize > 0}">
            <section class="fullpage event" data-section-name="EVENT">


                <div class="container-full">

                    <h3>
                        <small>
                            더 커지는 제주 <span>글로벌 제주인 하나 되다!</span>
                        </small>
                            ${festivityInfo.festivityYear} <spring:message code="메인.세계제주인대회"/>
                    </h3>

                    <ul class="links">
                        <li><a href="">온라인 참가신청<i class='bx bx-chevron-right'></i></a></li>
                        <li><a href="">신청확인<i class='bx bx-chevron-right'></i></a></li>
                    </ul>

                    <div class="buttons">
                        <a class="btn01" href="<info:url value="/festivity/info.do?festivityId=${festivityInfo.festivityId}"/>">${festivityInfo.festivityYear} <spring:message code="대회.대회소개"/></a>
                        <a class="btn02" href="<info:url value="/festivity/list.do"/>">지난대회보기</a>
                        <a class="btn03" href="<info:url value="/jeju/people/register.do"/>">재외도민등록</a>
                    </div>

                    <div class="mov-layer">

                        <iframe src="https://www.youtube.com/embed/qg-cYFSvCfY" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

                        <div class="tools">

                            <input id="none" type="checkbox"><label for="none">오늘 하루 열지 않음</label>
                            <button><i class='bx bx-x' ></i> 닫기</button>
                        </div>
                    </div>


                </div>

            </section>
        </c:when>
        <c:otherwise>
            <section class="fullpage main" data-section-name="MAIN">
                <div class="main-image">
                    <ul class="slider slider1 right">
                        <li>
                            <img src="/site/images/main01.jpg" />
                        </li>
                        <li>
                            <img src="/site/images/main02.jpg"/>
                        </li>
                    </ul>
                    <ul class="slider slider2 left">
                        <li>
                            <img src="/site/images/main02.jpg" />
                        </li>
                        <li>
                            <img src="/site/images/main01.jpg"/>
                        </li>
                    </ul>

                </div>


                <div class="container">

                    <h3>
                        GLOBAL JEJUIN
                        <small>
                            HUB NETWORK
                        </small>
                    </h3>

                    <a href="<info:url value="/jeju/people/register.do"/>">
                        <spring:message code="메인.제주인데이터베이스관리"/><i class='bx bxs-chevron-right'></i>
                    </a>
                </div>

                <div class="links">
                    <a href="<info:url value="/festivity/list.do"/>"> <spring:message code="메인.세계제주인대회"/></a>
                    <a href="<info:url value="/board/notic/list.do"/>"> <spring:message code="메인.알림소식"/></a>
                    <a href=""> <spring:message code="메인.주요사업"/></a>
                    <a href=""> <spring:message code="메인.조직위원회소개"/></a>
                </div>

            </section>
        </c:otherwise>
    </c:choose>



    <section class="fullpage community" data-section-name="COMMUNITY">
        <div class="container-full">

            <info:noticTag boardId="notic" skinName="notic" listCount="3"/>
            <info:noticTag boardId="media" skinName="media" listCount="3"/>
            <info:noticTag boardId="image" skinName="image" listCount="2"/>
            <info:noticTag boardId="video" skinName="video" listCount="1"/>
        </div>



    </section>
    <section class="fullpage campaign" data-section-name="CAMPAIGN">
        <div class="container-full">



            <h4>
                <small><spring:message code="대회.001"/></small>
                <spring:message code="대회.002"/>
                <span><spring:message code="대회.003"/></span>
            </h4>

            <div class="box">
                <p><spring:message code="대회.004"/></p>
                <p><spring:message code="대회.005"/></p>
                <span><spring:message code="대회.006"/></span>
            </div>



        </div>
    </section>


    <%--<section class="fullpage map" data-section-name="MAP">--%>
        <%--<div class="container-full">--%>

            <%--<div class="layer">--%>

                <%--<h3>--%>
                    <%--JEJUIN--%>
                    <%--<small>--%>
                        <%--NETWORK--%>
                    <%--</small>--%>
                <%--</h3>--%>
                <%--<p class="text"><spring:message code="메인.제주인이하나가되는글로벌제주인네트워크"/></p>--%>

                <%--<div class="select-box" id="mapSelector">--%>
                    <%--<ul>--%>
                        <%--<li class="active"><button type="button" value="en">미국</button></li>--%>
                        <%--<li><button type="button" value="cn">중국</button></li>--%>
                        <%--<li><button type="button" value="jp">일본</button></li>--%>
                        <%--<li><button type="button" value="ca">캐나다</button></li>--%>
                        <%--<li><button type="button" value="au">오스트레일리아(호주)</button></li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
                <%--<p class="count">--%>
                    <%--<span><spring:message code="메인.협회"/> : <strong>6</strong></span>--%>
                    <%--<span><spring:message code="메인.회원"/> : <strong>2,300</strong></span>--%>

                    <%--<a class="more" href=""><i class='bx bx-plus'></i> more</a>--%>
                <%--</p>--%>

            <%--</div>--%>


            <%--<div class="map-wall en">--%>
                <%--<!----%>
                    <%--각 구가별 지도 위치표시 클래스--%>

                    <%--미국 : en--%>
                    <%--중국 : cn--%>
                    <%--일본 : jp--%>
                    <%--캐나다 : ca--%>
                    <%--호주 : au--%>
                <%---->--%>



            <%--</div>--%>



        <%--</div>--%>
    <%--</section>--%>



</div>
<!-- E:mainContent -->
<script>
    $(document.body).ready(function(){

        //
        // var mapCode = 'en cn jp ca au'.split(' ');
        //
        // $('#mapSelector').on('click', 'li:not(.active) button', function(e){
        //
        //     var $mapWall = $('div.map-wall');
        //     mapCode.forEach(function(e, i) {
        //         $mapWall.removeClass(e);
        //     });
        //     $mapWall.addClass(this.value);
        //
        //     $('#mapSelector li').removeClass('active');
        //     $(this).closest('li').addClass('active');
        // });
    });
</script>

