<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title><spring:message code="info.top.title"/></title>

    <!-- css -->
    <link rel="stylesheet" type="text/css" href="source/css/style.css"/>
    <!-- <link rel="stylesheet" type="text/css" href="source/css/style_m.css"/> -->
    <link
            href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css'
            rel='stylesheet'>

    <!-- javaScript -->
    <script src="source/js/jquery-1.11.3.min.js"></script>
    <script src="source/js/slick.min.js"></script>
    <script src="source/js/common.js"></script>

</head>




<body class="open">
<!-- S:skip_navi -->
<div class="skip_navi">
    <a class="sr-only sr-only-focusable" href="#header">메뉴 바로가기</a>
    <a class="sr-only sr-only-focusable" href="#content">본문 바로가기</a>
    <a class="sr-only sr-only-focusable" href="#footer">푸터 바로가기</a>
</div>
<!-- E:skip_navi -->
<!-- S:header -->
<header id="header">
    <button class="menu">
        <i class='bar'></i>
        <i class='bar'></i>
        메뉴토글</button>

    <h1>
        <a href="">인포마인드
            <small>inCMS 기본 템플릿</small>
        </a>
    </h1>

    <p class="welcome">
        <strong>관리자</strong>님 환영합니다.
    </p>
    <button class="logout">
        <i class='bx bx-log-out'></i>
        LOGOUT</button>

</header>
<!-- E:header -->
<!-- S:mainContent -->

<div id="content">

    <aside>

        <div class="profile">
            <p class="photo">
                <img src="source/images/user.gif">
            </p>
            <p class="userName">관리자</p>
        </div>
        <!-- S:main_menu -->
        <nav id="main-menu" class="typeA">

            <ul>
                <li>
                    <h3>
                        <a href="dashboard.html">
                            <i class='bx bxs-dashboard'></i>
                            <span>Dashboard</span></a>
                    </h3>

                </li>
                <li>
                    <h3>
                        <a href="sub.html">
                            <i class='bx bxs-carousel'></i>
                            <span>App</span></a>
                    </h3>
                    <ul>
                        <li>
                            <a href="sub.html">Widgets</a>

                            <ul>
                                <li><a href="sub.html"><i class='bx bx-subdirectory-right'></i>Widgets</a></li>
                                <li><a href="sub.html"><i class='bx bx-subdirectory-right'></i>Widgets</a></li>
                                <li><a href="sub.html"><i class='bx bx-subdirectory-right'></i>Widgets</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="sub.html">
                                Weather</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <h3>
                        <a href="sub.html">
                            <i class='bx bx-intersect'></i>
                            <span>UI Elements</span></a>
                    </h3>
                    <ul>
                        <li>
                            <a href="sub.html">
                                게시판관리</a>
                        </li>
                        <li>
                            <a href="sub.html">
                                게시물관리</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <h3>
                        <a href="sub.html">
                            <i class='bx bxs-file'></i>
                            <span>Forms</span></a>
                    </h3>
                    <ul>
                        <li>
                            <a href="sub.html">
                                input</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <h3>
                        <a href="sub.html">
                            <i class='bx bx-table'></i>
                            <span>Tables</span></a>
                    </h3>
                    <ul>
                        <li>
                            <a href="sub.html">
                                카테고리그룹관리</a>
                        </li>
                        <li>
                            <a href="sub.html">
                                카테고리그룹등록</a>
                        </li>
                        <li>
                            <a href="sub.html">
                                카테고리관리</a>
                        </li>
                        <li>
                            <a href="sub.html">
                                카테고리등록</a>
                        </li>
                    </ul>
                </li>
            </ul>

        </nav>
        <!-- E:main_menu -->
    </aside>
    <section class="view">

        <div class="dashboard subView">


            <div class="tab-navigation">
                <div class="tabs">
                    <div class="item" class="on"><a href="#">탭메뉴1</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴2</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴3</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴4</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴5</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴6</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴7</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴8</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴9</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴10</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴11</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴12</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴13</a><button class="close"><i class='bx bx-x'></i></button></div>
                    <div class="item"><a href="#">탭메뉴14</a><button class="close"><i class='bx bx-x'></i></button></div>
                </div>

                <button class="btn left"><i class='bx bx-chevron-left' ></i></button>

                <button class="btn right"><i class='bx bx-chevron-right' ></i></button>
            </div>

            <h2 class="stitle">
                <i class='bx bxs-dashboard'></i>Dashboard</h2>


            <div class="widget c09 h02 black wall">
                <div class="inline">
                    <h3 class="sr-only">기본정보</h3>
                    <div class="box">
                        <h4>접속자정보</h4>

                    </div>
                </div>
            </div>
            <div class="widget c03 h02 main">
                <div class="inline">
                    <h3>오늘의 제주시 날씨</h3>
                    <div class="box weather">
                        <i class='bx bx-sun'></i>

                        <p>24℃ 맑음</p>
                    </div>
                </div>
            </div>


            <div class="widget c03 ">
                <div class="inline">
                    <h3>위젯 타이틀</h3>
                    <div class="box">
                        asdf
                    </div>

                </div>
            </div>
            <div class="widget c03 ">
                <div class="inline">
                    <h3>위젯 타이틀</h3>
                    <div class="box">
                        asdf
                    </div>

                </div>
            </div>
            <div class="widget c03 ">
                <div class="inline">
                    <h3>위젯 타이틀</h3>
                    <div class="box">
                        asdf
                    </div>

                </div>
            </div>
            <div class="widget c03 gray">
                <div class="inline">
                    <h3>위젯 타이틀</h3>
                    <div class="box">
                        asdf
                    </div>
                </div>
            </div>

        </div>

</div>

</section>

</div>
<!-- E:mainContent -->
<footer id="footer">
    <p class="copyright">© INFOMIND</p>
</footer>
</body>

</html>