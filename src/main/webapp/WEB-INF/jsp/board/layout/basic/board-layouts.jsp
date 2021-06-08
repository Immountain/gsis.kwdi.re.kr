<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta  name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title><spring:message code="info.top.title"/></title>
        <!--CSS:SKIN-->
        <link rel="stylesheet" type="text/css" href="<c:url value='/board/basic/css/board.css' />">


        <link
                href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css'
                rel='stylesheet'>

    </head>

    <body>


    <!-- S:mainContent -->
    <div id="content">
    <section class="subView">
    <tiles:insertAttribute name="content"/>      <!-- 컨텐츠(계속바뀜) -->
    </section>

    </div>

    </body>

    </html>