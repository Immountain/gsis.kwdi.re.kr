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
             <info:include  id="jew.com.head"/>
        </head>
        <body class="main-layout">
            <!-- S:skip_navi -->
                <info:include  id="jew.com.page.skipnavi"/>

            <!-- E:skip_navi -->
            <!-- S:header -->
            <header id="header">
            <div class="container">
            <h1><a href="/"><small>제주여성가족연구원</small>성인지통계시스템</a></h1>

            <button class="main-menu">
            메뉴호출버튼
            <i class='bx bx-menu'></i>
            </button>
            </div>
            </header>
            <!-- E:header -->

            <info:include  id="jew.com.page.topmenu"/>
            <tiles:insertAttribute name="content"/>
            <info:include  id="jew.com.page.foote"/>
        </body>

</html>







