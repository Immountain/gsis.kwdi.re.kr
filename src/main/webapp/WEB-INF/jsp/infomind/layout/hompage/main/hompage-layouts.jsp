<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>세계제주인대회조직위원회</title>
    <link href="<c:url value="/site/css/boxicons.css"/>" rel="stylesheet">
    <link href="<c:url value="/site/css/style.css"/>" rel="stylesheet">
    <!-- javaScript -->
    <script src="<c:url value="/site/js/jquery-3.5.1.min.js"/>"></script>
    <script src="<c:url value="/site/js/jquery.scrollify.js"/>"></script>
    <script src="<c:url value="/site/js/plax.js"/>"></script>
    <script src="<c:url value="/site/js/slick.min.js"/>"></script>
    <script src="<c:url value="/site/js/common.js"/>"></script>


</head>

<body>
<!-- S:skip_navi -->
<div class="skip_navi">
    <a class="sr-only sr-only-focusable" href="#header">메뉴 바로가기</a>
    <a class="sr-only sr-only-focusable" href="#content">본문 바로가기</a>
    <a class="sr-only sr-only-focusable" href="#footer">푸터 바로가기</a>
</div>
<!-- E:skip_navi -->
<!-- S:header -->
<header id="header">
    <div class="container">
        <h1><a href="/">세계제주인대회조직위원회</a></h1>

        <div class="header-tools">
            <a class="login" href="">login</a>

            <div class="language">
                <button class="ko">language<i class='bx bxs-chevron-down'></i></button>
                <!-- 한국어 : ko / 영어 : en / 일본어 : jp / 중국어 : cn -->

                <div class="list">
                    <a class="acitve" href="">한국어<i class='bx bx-chevron-right'></i></a>
                    <a href="">English<i class='bx bx-chevron-right'></i></a>
                    <a href="">日本語<i class='bx bx-chevron-right'></i></a>
                </div>
            </div>

            <button class="main-menu">
                <span>menu</span>
                <u></u>
                <u></u>
                <u></u>
            </button>
        </div>
    </div>
</header>
<!-- E:header -->

<!-- S:main_menu -->
<nav id="main-menu">
    <h2>전체메뉴</h2>

    <a href="">세계제주인대회</a>
    <a href="">주요사업</a>
    <a href="">알림소식</a>
    <a href="">조직위원회소개</a>

</nav>
<!-- E:main_menu -->





<!-- S:mainContent -->
<div id="content" class="main">


    <%--<section class="fullpage event" data-section-name="EVENT">--%>


        <%--<div class="container-full">--%>

            <%--<h3>--%>
                <%--<small>--%>
                    <%--더 커지는 제주 <span>글로벌 제주인 하나 되다!</span>--%>
                <%--</small>--%>
                <%--2021 세계제주인대회--%>
            <%--</h3>--%>

            <%--<ul class="links">--%>
                <%--<li><a href="">온라인 참가신청<i class='bx bx-chevron-right'></i></a></li>--%>
                <%--<li><a href="">신청확인<i class='bx bx-chevron-right'></i></a></li>--%>
            <%--</ul>--%>

            <%--<div class="buttons">--%>
                <%--<a class="btn01" href="">2021 대회소개</a>--%>
                <%--<a class="btn02" href="">지난대회보기</a>--%>
                <%--<a class="btn03" href="">재외도민등록</a>--%>
            <%--</div>--%>

            <%--<div class="mov-layer">--%>

                <%--<iframe src="https://www.youtube.com/embed/qg-cYFSvCfY" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>--%>

                <%--<div class="tools">--%>

                    <%--<input id="none" type="checkbox"><label for="none">오늘 하루 열지 않음</label>--%>
                    <%--<button><i class='bx bx-x' ></i> 닫기</button>--%>
                <%--</div>--%>
            <%--</div>--%>


        <%--</div>--%>

    <%--</section>--%>



    <section class="fullpage main" data-section-name="MAIN">



        <div class="main-image">
            <ul class="slider slider1 right">
                <li>
                    <img src="<c:url value="/site/images/main01.jpg"/>" />
                </li>
                <li>
                    <img src="<c:url value="/site/images/main02.jpg"/>"/>
                </li>
            </ul>
            <ul class="slider slider2 left">
                <li>
                    <img src="<c:url value="/site/images/main02.jpg"/>" />
                </li>
                <li>
                    <img src="<c:url value="/site/images/main01.jpg"/>"/>
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

            <a href="">
                재외제주도민 등록하기<i class='bx bxs-chevron-right'></i>
            </a>
        </div>

        <div class="links">
            <a href="">제주인대회</a>
            <a href="">알림소식</a>
            <a href="">주요사업</a>
            <a href="">조직위원회소개</a>
        </div>


    </section>
    <%--<section class="fullpage community" data-section-name="COMMUNITY">--%>
        <%--<div class="container-full">--%>

            <%--<div class="board notice">--%>
                <%--<h3>공지사항</h3>--%>
                <%--<div class="headline">--%>
                    <%--<a href="">--%>
                        <%--<p class="date">--%>
                            <%--2021<strong>07.19</strong>--%>
                        <%--</p>--%>
                        <%--<p class="text">--%>
                            <%--<strong>2020년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</strong>--%>
                            <%--<span>첨부파일을 참고해주시기 바랍니다.</span>--%>
                        <%--</p>--%>
                    <%--</a>--%>
                <%--</div>--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<a class="more" href="">more</a>--%>
            <%--</div>--%>

            <%--<div class="board press">--%>
                <%--<h3>언론보도</h3>--%>
                <%--<div class="headline">--%>
                    <%--<a href="">--%>
                        <%--<p class="date">--%>
                            <%--2021<strong>07.19</strong>--%>
                        <%--</p>--%>
                        <%--<p class="text">--%>
                            <%--<strong>2020년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</strong>--%>
                            <%--<span>첨부파일을 참고해주시기 바랍니다.</span>--%>
                        <%--</p>--%>
                    <%--</a>--%>
                <%--</div>--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<a class="more" href="">more</a>--%>
            <%--</div>--%>

            <%--<div class="gallery photo">--%>
                <%--<h3>사진자료</h3>--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<a href="">--%>
                            <%--<p class="image">--%>
                                <%--<img src="/site/images/img-temp1.png">--%>
                            <%--</p>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="">--%>
                            <%--<p class="image">--%>
                                <%--<img src="/site/images/img-temp1.png">--%>
                            <%--</p>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<a class="more" href="">more</a>--%>
            <%--</div>--%>

            <%--<div class="gallery mov">--%>
                <%--<h3>영상자료</h3>--%>
                <%--<ul>--%>
                    <%--<li>--%>
                        <%--<a href="">--%>
                            <%--<p class="image">--%>
                                <%--<img src="/site/images/img-temp1.png">--%>
                            <%--</p>--%>
                        <%--</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <%--<a class="more" href="">more</a>--%>
            <%--</div>--%>



        <%--</div>--%>

    <%--</section>--%>

    </section><section class="fullpage community" data-section-name="COMMUNITY">
        <div class="container-full">

            <div class="board notice">
                <h3>공지사항</h3>
                <div class="headline">
                    <a href="">
                        <p class="date">
                            2021<strong>07.19</strong>
                        </p>
                        <p class="text">
                            <strong>2020년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</strong>
                            <span>첨부파일을 참고해주시기 바랍니다.</span>
                        </p>
                    </a>
                </div>
                <ul>
                    <li>
                        <a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>
                    </li>
                    <li>
                        <a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>
                    </li>
                </ul>
                <a class="more" href="">more</a>
            </div>

            <div class="board press">
                <h3>언론보도</h3>
                <div class="headline">
                    <a href="">
                        <p class="date">
                            2021<strong>07.19</strong>
                        </p>
                        <p class="text">
                            <strong>2020년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</strong>
                            <span>첨부파일을 참고해주시기 바랍니다.</span>
                        </p>
                    </a>
                </div>
                <ul>
                    <li>
                        <a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>
                    </li>
                    <li>
                        <a href="">2021년도 세계제주인대회조직위원회 기부금 모금액 및 활용실적</a>
                    </li>
                </ul>
                <a class="more" href="">more</a>
            </div>

            <div class="gallery photo">
                <h3>사진자료</h3>
                <ul>
                    <li>
                        <a href="">
                            <p class="image">
                                <img src="<c:url value="/site/images/img-temp1.png"/>">
                            </p>
                        </a>
                    </li>
                    <li>
                        <a href="">
                            <p class="image">
                                <img src="<c:url value="/site/images/img-temp1.png"/>">
                            </p>
                        </a>
                    </li>
                </ul>
                <a class="more" href="">more</a>
            </div>

            <div class="gallery mov">
                <h3>영상자료</h3>
                <ul>
                    <li>
                        <a href="">
                            <p class="image">
                                <img src="<c:url value="/site/images/img-temp1.png"/>">
                            </p>
                        </a>
                    </li>
                </ul>
                <a class="more" href="">more</a>
            </div>



        </div>



    </section>

    <section class="fullpage map" data-section-name="MAP">
        <div class="container-full">

            <div class="layer">

                <h3>
                    JEJUIN
                    <small>
                        NETWORK
                    </small>
                </h3>
                <p class="text">재외 제주도민 현황을 알아보세요</p>

                <div class="select-box">
                    <ul>
                        <li class="active"><button>미국</button></li>
                        <li><button>중국</button></li>
                        <li><button>일본</button></li>
                        <li><button>캐나다</button></li>
                        <li><button>오스트레일리아(호주)</button></li>
                    </ul>
                </div>
                <p class="count">
                    <span>협회 수: <strong>6</strong></span>
                    <span>회원 수: <strong>2,300</strong></span>

                    <a class="more" href=""><i class='bx bx-plus'></i> more</a>
                </p>

            </div>


            <div class="map-wall en">
                <!--
                    각 구가별 지도 위치표시 클래스

                    미국 : en
                    중국 : cn
                    일본 : jp
                    캐나다 : ca
                    호주 : au
                -->



            </div>



        </div>
    </section>



</div>
<!-- E:mainContent -->
<!-- S:footer -->
<footer id="footer">


    <div class="outsite-slider">
        <div class="container">
            <div class="slider">


                <div class="slide">
                    <a href="https://www.jeju.go.kr" target="_BLANK"><img src="<c:url value="/site/images/banner01.png"/>" alt="제주특별자치도" />제주특별자치도</a>
                </div>
                <div class="slide">
                    <a href="https://www.jpdc.co.kr" target="_BLANK"><img src="<c:url value="/site/images/banner02.png"/>" alt="제주특별자치도개발공사" />제주특별자치도개발공사</a>
                </div>
                <div class="slide">
                    <a href="https://ijto.or.kr" target="_BLANK"><img src="<c:url value="/site/images/banner03.png"/>" alt="제주관광공사" />제주관광공사</a>
                </div>
                <div class="slide">
                    <a href="http://www.kf.or.kr" target="_BLANK"><img src="<c:url value="/site/images/banner04.png"/>" alt="한국국제교류재단" />한국국제교류재단</a>
                </div>
                <div class="slide">
                    <a href="http://www.okf.or.kr" target="_BLANK"><img src="<c:url value="/site/images/banner05.png"/>" alt="재외동포재단" />재외동포재단</a>
                </div>

            </div>
            <button class="arrow left">이전<i class='bx bx-chevron-left'></i></button>
            <button class="arrow right">다음<i class='bx bx-chevron-right'></i></button>
        </div>

    </div>

    <div class="container">


        <h2>세계제주인대회조직위원회</h2>

        <ul>
            <li>
                <a href="">개인정보보호정책</a>
            </li>
            <li>
                <a href="">오시는길</a>
            </li>
        </ul>

        <address>
            <p>
                (63219) 제주특별자치도 제주시 청사로1길 18-4(도남동)
                <span>(사)세계제주인대회조직위원회</span>
            </p>
            <p>
                Tel : +82-64-753-1240 Fax : +82-64-753-1239
                <span>Mail : globaljejuin@hanmail.net</span>
            </p>
            <p>
                Copyright 2018. (사)세계제주인대회조직위원회
                <span>all rights reserved.</span>
            </p>


        </address>



    </div>
</footer>
<!-- E:footer -->
</body>

</html>