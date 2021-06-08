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
<link href="<c:url value="/"/>site/css/boxicons.css" rel="stylesheet">
<link href="<c:url value="/"/>site/css/style.css" rel="stylesheet">

<!-- javaScript -->
<script src="<c:url value="/"/>site/js/jquery-3.5.1.min.js"></script>
<script src="<c:url value="/"/>site/js/jquery.scrollify.js"></script>
<script src="<c:url value="/"/>site/js/plax.js"></script>
<script src="<c:url value="/"/>site/js/slick.min.js"></script>
<script src="<c:url value="/"/>site/js/common.js"></script>
</head>

<body class="sub-layout">

<info:include id="wj.com.page.skipnavi"/>
<info:include id="wj.com.page.header"/>
<info:include id="wj.com.page.mainmenu"/>
<tiles:insertAttribute name="content"/>
<info:include id="wj.com.page.footer"/>


</body>
</html>






