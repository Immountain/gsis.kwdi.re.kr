<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetailsService" %>

<%@ page import="egovframework.rte.fdl.string.EgovStringUtil" %>
<%@ page import="java.lang.String" %>
<%
	boolean authenticateFail = request.getAttribute("authenticateFail") != null && !request.getAttribute("authenticateFail").toString().equals("");

	boolean authFail = request.getAttribute("authFail") != null && !request.getAttribute("authFail").toString().equals("");

	String target = EgovStringUtil.null2void((String)request.getAttribute("target"));
	target = target.equals("") ? "_top" : target;
%>
<c:set var="pageTitle"><spring:message code="comCmmErr.accessDenied.code"/></c:set><!-- 사용자접근권한 에러 -->



<!DOCTYPE html>
<html lang="ko">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JEJU TECHNOPARK</title>

	<!-- css -->
	<link rel="stylesheet" type="text/css" href="<c:url value='/source/css/style.css?ver=20210302' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/tootik/tootik.min.css' />">
	<link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css?ver=20210302' rel='stylesheet'>

	<script language="javascript">
		function fncGoAfterErrorPage(){
			if('<%=authenticateFail%>' == 'true' ){
				document.dummyForm.target="_top";
				document.dummyForm.action = "<c:url value='/empaftererrorpage.do'/>";
				document.dummyForm.submit();
			}else if('<%=authFail%>' == 'true'){
				document.dummyForm.target="<%=target%>";
				document.dummyForm.action = "<c:url value='/empaftererrorpage.do'/>";
				document.dummyForm.submit();
			}else{
				//document.location.href = "<c:url value='/empaftererrorpage.do'/>";
				history.back(-2);
			}
		}
	</script>


</head>
<body class="error-page">
<h1>
	<i class="bx bx-error"></i>
	${pageTitle}
	<small>${SPRING_SECURITY_403_EXCEPTION}</small>
</h1>
<p class="info-text">
	<%
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			String password = ((UserDetails) principal).getPassword();
			out.println("Account : " + username + "<br>");
		}
	%>
</p>
<button class="btn sub" type="button" onclick="fncGoAfterErrorPage();" >뒤로가기</button>
<p class="mt-5 mb-3 text-muted">Copyright(c)2014 JEJU TECHNOPARK. All rights reserved</p>
</form>
</body>

</html>
