<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %><!DOCTYPE html>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name=”description” content=”제주연구장비정보망”>
    <meta name=”keywords” content=”제주연구장비정보망,제주연구장비”>
    <title>제주 공동활용 연구시설장비 통합플랫폼</title>

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">

    <!-- css -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/site/css/style.css?ver=20210302' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/site/css/boxicons.css?ver=20210302' />">

    <!-- 기타 css
            <c:if test="${addCss != ''}">

                <link rel="stylesheet" type="text/css" href="<c:url value='${addCss}' />">

            </c:if>
       -->

    <!-- javaScript -->
    <script src="<c:url value='/site/js/jquery-3.5.1.min.js'/>" ></script>
    <script src="<c:url value='/site/js/slick.min.js'/>" ></script>
    <script src="<c:url value='/site/js/common.js'/>" ></script>


</head>

<body class="main">
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

        <h1>
            <a href="/"><span>JTP</span><small>제주 공동활용</small>연구시설장비 통합플랫폼</a>
        </h1>

        <button class="menu">
            <i class='bar'></i>
            <i class='bar'></i>
            <i class='bar'></i>
            메뉴열기
        </button>

    </div>
</header>
<!-- E:header -->

<aside>
    <ul class="tools">
        <c:if test="${loginVO==null}">

            <li><a class="login" href="/mypage/login.do">로그인</a></li>
            <li><a class="sign-up" href="/mypage/joinStep1.do">회원가입</a></li>
        </c:if>
        <c:if test="${loginVO!=null}">

            <li><a class="login" href="/logout.do?redirect=/">로그아웃</a></li>
        </c:if>
        <li><a class="mypage" href="/mypage/view.do">마이페이지</a></li>
        <li class="full"><a class="cms" href="">관리화면</a></li>
    </ul>

    <!-- S:main_menu -->
    <nav id="main-menu">

        <info:menuTag group="11" skinName="main-memu"/>
    </nav>
    <!-- E:main_menu -->



    <button class="menu open">
        <i class='bar'></i>
        <i class='bar'></i>
        <i class='bar'></i>
        메뉴닫기
    </button>
</aside>

<!-- S:mainContent -->

<div id="content">

    <section class="sub-header ${menuInfo.class1}">
        <!--
            대메뉴에 따른 Class

            플랫폼소개 : sub01
            이용안내 : sub02
            장비관리 : sub03
            장비자료실 : sub04
            커뮤니티 : sub05
        -->

        <div class="container">

            <h2>${menuInfo.siteMemuNm}</h2>

        </div>
    </section>

    <section class="sub-menu">
        <div class="container">

            <p class="home"><a href="/">홈으로</a></p>

            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>
        </div>
    </section>





    <section class="<c:choose><c:when test="${pageCss!=null}">${pageCss}</c:when><c:otherwise>sub-ready</c:otherwise></c:choose>">
        <tiles:insertAttribute name="content"/>
    </section>



</div>
<!-- E:mainContent -->
<footer id="footer">
    <div class="container">

        <ul class="footer-tools">
            <li><a href="/page/privacy">개인정보처리방침</a></li>
            <li><a href="/page/terms">이용약관</a></li>
            <li><a href="">오시는길</a></li>
        </ul>

        <h2>
            <a href=""><span>JTP</span><small>제주 공동활용</small>연구시설장비 통합플랫폼</a>
        </h2>
        <address>

            <p><u>상호명 : (재)제주테크노파크</u> 대표자 : 원장 태성길 사업자번호 : 616-82-15584</p>
            <p>(63208) 제주특별자치도 제주시 중앙로 217 (이도이동) <span>제주벤처마루 9F TEL. 064)720-2300 FAX. 064)751-3443</span></p>
            <p>Copyright(c)2020 JEJU TECHNOPARK. All rights reserved.</p>
        </address>

        <div class="agency">
            <button class="agency-open">유관기관 <i class="bx bxs-chevron-right"></i></button>
            <div class="agency-link">
                <a href="http://www.jeju.go.kr" target="blank" title="새창">제주특별자치도</a>
                <a href="http://www.jeju.go.kr" target="blank" title="새창">제주특별자치도</a>
            </div>
        </div>

    </div>
</footer>
</body>

</html>

















								
								