
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<div id="content" class="main">
    <section class="main-issue">
        <h2 class="sr-only">메인 이슈이미지 슬라이더</h2>

        <div class="main-slider">
            <div>
                <img src="<c:url value='/site/images/issue01.jpg'/>" alt="">
                <h2>
                    <u><strong>빅데이터 플랫폼</strong></u>
                    구축 전문기업</h2>
                <p>
                    <u>인포마인드는 2000년부터 시작된 토종 IT기업으로서</u>
                    도내외 다양한 과제 및 사업수행을 통해</p>
                <p>축적된 노하우를 바탕으로
                    <u>독자적인 기술력을 보유하고있습니다.</u>
                </p>
                <a href="">자세히보기<i class="bx bx-chevron-right"></i></a>
            </div>
            <div>
                <img src="<c:url value='/site/images/issue02.jpg'/>"/>
                <%--<img src="<c:url value='/homepage/images/issue02.jpg'/>" alt="">--%>
                <h2>
                    <u>MINI GOLF SIMULATOR</u>
                    <strong>Swing PUTT</strong>
                </h2>
                <p>
                    Everyday Golf Life! <u>You can everyday enjoy The famous world golf courses.</u></p>
                <p>
                    More accurate, More visual, More exciting</p>

                <a href="">자세히보기<i class="bx bx-chevron-right"></i></a>
            </div>
        </div>

        <button class="dm-prev" title="다음슬라이드">
            <i class='bx bx-chevron-left'></i>이전슬라이드</button>
        <button class="dm-next" title="이전슬라이드">
            <i class='bx bx-chevron-right'></i>다음슬라이드</button>

    </section>

    <section class="news">
        <div class="container">
            <h2>NEWS <a href="">더보기 <i class='bx bx-plus'></i></a></h2>
            <ul>
                <li class="first">
                    <a href="">
                        <p class="img"><img src="<c:url value='/site/images/noimg.png'/>"></p>
                        <h3>강희석 (주)인포마인드 사장 제주도지사 표창</h3>
                        <i>2018-10-11</i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <h3>강희석 (주)인포마인드 사장 제주도지사 표창</h3>
                        <i>2018-10-11</i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <h3>강희석 (주)인포마인드 사장 제주도지사 표창</h3>
                        <i>2018-10-11</i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <h3>강희석 (주)인포마인드 사장 제주도지사 표창</h3>
                        <i>2018-10-11</i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <h3>강희석 (주)인포마인드 사장 제주도지사 표창</h3>
                        <i>2018-10-11</i>
                    </a>
                </li>
                <li>
                    <a href="">
                        <h3>강희석 (주)인포마인드 사장 제주도지사 표창</h3>
                        <i>2018-10-11</i>
                    </a>
                </li>
            </ul>
        </div>
    </section>

    <section class="business">
        <h2>BUSINESS UNITS <small>고객별 맞춤형 분석과 커스터마이징을 통해 <u>최상의 솔루션을 제공합니다.</u></small></h2>

        <div class="business-slider">
            <div>
                <a href="">
                    <i class='bx bxs-calculator'></i>
                    <h3>INPOS</h3>
                </a>
            </div>
            <div>
                <a href="">
                    <i class='bx bxs-hdd' ></i>
                    <h3>GOLF SIMULATOR</h3>
                </a>
            </div>
            <div>
                <a href="">
                    <i class='bx bx-data'></i>
                    <h3>BIGDATA</h3>
                </a>
            </div>
            <div>
                <a href="">
                    <i class='bx bx-link'></i>
                    <h3>BLOCKCHAIN</h3>
                </a>
            </div>
            <div>
                <a href="">
                    <i class='bx bx-radar'></i>
                    <h3>SMARTFARM</h3>
                </a>
            </div>
            <div>
                <a href="">
                    <i class='bx bx-window'></i>
                    <h3>WEB/SI</h3>
                </a>
            </div>
        </div>
    </section>

    <section class="mov">
        <h2>당당한 제주의 글로벌기업
            <u>인포마인드</u>
        </h2>

        <video autoplay="autoplay" muted loop="loop" poster="<c:url value='/site/images/main.jpg'/>">
            <source src="<c:url value='/site/images/main.mp4'/>" type="video/mp4">
        </video>
    </section>

</div>