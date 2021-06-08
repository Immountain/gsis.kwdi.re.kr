<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title><spring:message code="info.top.title"/></title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="<c:url value='/source/css/style.css?ver=20210302' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/tootik/tootik.min.css' />">
<link rel='stylesheet' type="text/css" href="<c:url value='/assets/intro/introjs.css' />">
<link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css?ver=20210302' rel='stylesheet'>
<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css?ver=20210302' />">

<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value='/source/js/common.js'/>" ></script>
<script src="<c:url value='/assets/intro/intro.js'/>" ></script>

<script src="<c:url value='/js/infomind/com/incms.polyfill.js' />"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
$ifx.contextPath='<c:url value="/"/>';
</script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<!-- ax5 -->
<script src="<c:url value="/assets/ax5/ax5core/ax5core.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">

<!-- ztree -->
<script src="<c:url value="/js/infomind/com/common-ui/zTree_v3/js/jquery.ztree.all.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/zTree_v3/css/zTreeStyle/zTreeStyle.css"/>">

<!-- Highchart -->
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.src.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/Highcharts/code/css/highcharts.css"/>">

<!-- billboard
<script src="https://d3js.org/d3.v4.min.js"></script>
<script src="<c:url value="/js/infomind/com/common-ui/billboard/billboard.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/billboard/billboard.min.css"/>">
-->

<!-- modal 필-->
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-modal/ax5modal.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.css"/>">
<script src="<c:url value="/assets/ax5/ax5ui-modal/ax5modal.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.min.js"/>"></script>
<script src="<c:url value="/js/infomind/com/axui5modal.js"/>"></script>


<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>