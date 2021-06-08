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


<!-- S:mainContent -->
<div id="content">
    <div class="sub-outline">

        <tiles:insertAttribute name="content"/>      <!-- 컨텐츠(계속바뀜) -->
    </div>

</div>

</body>

</html>