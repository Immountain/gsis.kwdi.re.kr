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
    <meta name="author" content="INFOMIND">
    <title>INFOMIND</title>


    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">


    <!-- css -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/site/css/style.css' />">
    <!-- javaScript -->
    <script src="<c:url value='/site/js/jquery-1.11.3.min.js'/>" ></script>
    <script src="<c:url value='/site/js/common.js'/>" ></script>
    <script src="<c:url value='/site/js/slick.min.js'/>" ></script>


    <!--CSS:SKIN-->

    <c:if test="${addCss != ''}">

        <link rel="stylesheet" type="text/css" href="<c:url value='${addCss}' />">

    </c:if>
</head>
<body class="sub">
        <!-- S:skip_navi -->
        <div class="skip_navi">
            <a class="sr-only sr-only-focusable" href="#content">본문 바로가기</a>
            <a class="sr-only sr-only-focusable" href="#footer">푸터 바로가기</a>
        </div>
<!-- E:skip_navi -->
<!-- S:header -->
<header id="header" class="fit">
    <div class="container">
        <h1>
            <a href="/">(주)인포마인드</a>
        </h1>
        <button class="allmenu">
            <i class='bx bx-menu-alt-right'></i>전체메뉴보기</button>
    </div>

    <!-- S:main_menu -->
    <info:menuTag group="11" skinName="main-memu"/>
    <!-- E:main_menu -->
</header>
<!-- E:header -->
<!-- S:mainContent -->
<tiles:insertAttribute name="content"/>      <!-- 컨텐츠(계속바뀜) -->
<!-- E:mainContent -->
<!-- S:footer -->
<footer id="footer">
    <div class="container">
        <h2>ⓒ INFOMIND</h2>
        <address>
            <p>제주특별자치도 제주시 삼도일동 530 제동해피죤 제7층 제807호 (주)인포마인드 1111</p>
            <p>TEL 064)712-4787 | FAX 064)712-4786</p>

        </address>
    </div>
</footer>
<!-- E:footer -->
</body>

</html>