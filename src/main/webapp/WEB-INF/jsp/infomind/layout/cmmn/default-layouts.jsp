<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <tiles:insertAttribute name="inhead"/>
</head>
<body class="open">
<div class="skip_navi">
    <tiles:insertAttribute name="nav"/>
</div>

<script>
    $(document).ready(function(){


        $("#btn_logout").click(function(){
            location.href="/logout.do?redirect=/cms/LoginUsr.do";
        });


    });
</script>
<!-- S:header -->
<header id="header">
    <button class="menu">
        <i class='bar'></i>
        <i class='bar'></i>
        메뉴토글</button>

    <h1>
        <a href="">(사)세계제주인대회<small>조직위원회</small>
        </a>
    </h1>

    <p class="welcome">
        <button type="button" class="button main" onclick="introJs('#content').start()"><i class='bx bxs-file-find' ></i>사용자 가이드</button>
        <strong>${loginVO.name}</strong>님 환영합니다.
    </p>
    <button type="button" id="btn_logout" name="btn_logout" class="logout">
        <i class='bx bx-log-out'></i>
        LOGOUT</button>

</header>
<!-- E:header -->

<div id="content">
    <tiles:insertAttribute name="left"/>      <!-- 왼쪽 메뉴 -->

    <section class="view">

            <div class="tab-navigation">
                <div class="tabs" id="frame-tap">
                    <div class="item on" frame-id="0"><a href="#">Dashboard</a></div>
                    <%--<div class="item"><a href="#">탭메뉴2</a><button class="close"><i class='bx bx-x'></i></button></div>--%>
                </div>

                <button class="btn left"><i class='bx bx-chevron-left' ></i></button>

                <button class="btn right"><i class='bx bx-chevron-right' ></i></button>
            </div>

            <tiles:insertAttribute name="content"/>      <!-- 컨텐츠(계속바뀜) -->

    </section>
</div>

<footer id="footer">
    <tiles:insertAttribute name="footer"/> <!-- 그 하단 -->
</footer>


</body>
</html>