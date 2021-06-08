<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
 /**
  * @Class Name : EgovLoginUsr.jsp
  * @Description : Login 인증 화면
  * @Modification Information
  * @
  * @ 수정일               수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.03   박지욱            최초 생성
  * @ 2011.09.25   서준식            사용자 관리 패키지가 미포함 되었을때에 회원가입 오류 메시지 표시
  * @ 2011.10.27   서준식            사용자 입력 탭 순서 변경
  * @ 2017.07.21   장동한            로그인인증제한 작업
  * @ 2019.12.11   신용호            KISA 보안약점 조치 (크로스사이트 스크립트)
  *
  *  @author 공통서비스 개발팀 박지욱
  *  @since 2009.03.03
  *  @version 1.0
  *  @see
  *
  *  Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="comUatUia.title" /></title><!-- 로그인 -->
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="content-type" content="text/html; charset=utf-8">


<%--<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />">--%>
<%--<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/uat/uia/login.css' />">--%>
<%--<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/showModalDialog.js'/>" ></script>--%>
<%--<script type="text/javascript" src="<c:url value='/js/egovframework/com/cmm/jquery.js'/>" ></script>--%>



<!-- css -->
<link rel="stylesheet" type="text/css" href="<c:url value='/source/css/style.css?ver=20210302' />">
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/tootik/tootik.min.css' />">
<link href='https://cdn.jsdelivr.net/npm/boxicons@2.0.5/css/boxicons.min.css?ver=20210302' rel='stylesheet'>

<!-- javaScript -->
<script src="<c:url value='/source/js/jquery-1.11.3.min.js'/>" ></script>
<script src="<c:url value='/source/js/common.js'/>" ></script>




<script type="text/javaScript" language="javascript">


	$(document).ready(function(){
	});


</script>
</head>



<body onLoad="fnInit();" class="login">

<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>
<form name="loginForm" id="loginForm" action="<c:url value='/cms/actionLogin.do'/>" method="post">

	<input type="hidden" id="userSe" name="userSe" value="USR">
	<input type="hidden" id="redirect" name="redirect" value="/cms/index.do">
	<input name="j_username" type="hidden"/>

	<h1>inCMS LOGIN </h1>

	<label for="id" class="sr-only">ID 테스트 페이지</label>
	<input type="text" id="id" name="id" maxlength="10"  placeholder="ID를 입력하세요" autocomplete="off" required autofocus>


	<label for="password" class="sr-only">Password</label>
	<input type="password" id="password" name="password" maxlength="12" placeholder="비밀번호를 입력하세요" required>


	<div class="custom-checkbox">
		<input type="checkbox" id="checkId" onclick="saveid(document.loginForm);" >
		<label for="checkId"><i class='bx bxs-checkbox-checked'></i>아이디 저장</label>
		<%--<input type="checkbox" id="customCheck2">--%>
		<%--<label for="customCheck2"><i class='bx bxs-checkbox-checked'></i>비밀번호 저장</label>--%>
	</div>
	<button type="button"  onclick="actionLogin()" >LOGIN</button>

	<%--<input type="button" class="btn_login" value="<spring:message code="comUatUia.loginForm.login"/>" onclick="actionLogin()"> <!-- 로그인  -->--%>

	<p class="copyright">© 테스트 INFOMIND webmaster / rhdxhd12</p>
</form>



</body>
</html>


