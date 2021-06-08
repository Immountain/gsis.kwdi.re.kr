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
<script src="<c:url value='/source/js/jquery-3.5.1.min.js'/>" ></script>
<script src="<c:url value='/source/js/common.js'/>" ></script>




<script type="text/javaScript" language="javascript">


	$(document).ready(function(){

		$("#password").keydown(function(key) {

			if (key.keyCode == 13) {
				actionLogin();
			}

		});


	});


function checkLogin(userSe) {
    // 일반회원
    // if (userSe == "GNR") {
    //     document.loginForm.rdoSlctUsr[0].checked = true;
    //     document.loginForm.rdoSlctUsr[1].checked = false;
    //     document.loginForm.rdoSlctUsr[2].checked = false;
    //     document.loginForm.userSe.value = "GNR";
    // // 기업회원
    // } else if (userSe == "ENT") {
    //     document.loginForm.rdoSlctUsr[0].checked = false;
    //     document.loginForm.rdoSlctUsr[1].checked = true;
    //     document.loginForm.rdoSlctUsr[2].checked = false;
    //     document.loginForm.userSe.value = "ENT";
    // // 업무사용자
    // } else if (userSe == "USR") {
    //     document.loginForm.rdoSlctUsr[0].checked = false;
    //     document.loginForm.rdoSlctUsr[1].checked = false;
    //     document.loginForm.rdoSlctUsr[2].checked = true;
    //     document.loginForm.userSe.value = "USR";
    // }


	document.loginForm.rdoSlctUsr[0].checked = false;
	document.loginForm.rdoSlctUsr[1].checked = false;
	document.loginForm.rdoSlctUsr[2].checked = true;
	document.loginForm.userSe.value = "USR";

}

function actionLogin() {
	if (document.loginForm.id.value =="") {
        alert("<spring:message code="comUatUia.validate.idCheck" />"); <%-- 아이디를 입력하세요 --%>
    } else if (document.loginForm.password.value =="") {
        alert("<spring:message code="comUatUia.validate.passCheck" />"); <%-- 비밀번호를 입력하세요 --%>
    } else {
        document.loginForm.action="<c:url value='/cms/actionLogin.do'/>";
        //document.loginForm.j_username.value = document.loginForm.userSe.value + document.loginForm.username.value;
        //document.loginForm.action="<c:url value='/j_spring_security_check'/>";
        document.loginForm.submit();
    }
}

function actionCrtfctLogin() {
    document.defaultForm.action="<c:url value='/uat/uia/actionCrtfctLogin.do'/>";
    document.defaultForm.submit();
}

function goFindId() {
    document.defaultForm.action="<c:url value='/uat/uia/egovIdPasswordSearch.do'/>";
    document.defaultForm.submit();
}

function goRegiUsr() {

	var useMemberManage = '${useMemberManage}';

	if(useMemberManage != 'true'){
		<%-- 사용자 관리 컴포넌트가 설치되어 있지 않습니다. \n관리자에게 문의하세요. --%>
		alert("<spring:message code="comUatUia.validate.userManagmentCheck" />");
		return false;
	}

    var userSe = document.loginForm.userSe.value;

    // 일반회원
    if (userSe == "GNR") {
        document.loginForm.action="<c:url value='/uss/umt/EgovStplatCnfirmMber.do'/>";
        document.loginForm.submit();
    // 기업회원
    } else if (userSe == "ENT") {
        document.loginForm.action="<c:url value='/uss/umt/EgovStplatCnfirmEntrprs.do'/>";
        document.loginForm.submit();
    // 업무사용자
    } else if (userSe == "USR") {
    	<%-- 업무사용자는 별도의 회원가입이 필요하지 않습니다. --%>
        alert("<spring:message code="comUatUia.validate.membershipCheck" />");
    }
}

function goGpkiIssu() {
    document.defaultForm.action="<c:url value='/uat/uia/egovGpkiIssu.do'/>";
    document.defaultForm.submit();
}

function setCookie (name, value, expires) {
    document.cookie = name + "=" + escape (value) + "; path=/; expires=" + expires.toGMTString();
}




function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
        offset = document.cookie.indexOf(search);
        if (offset != -1) { // 쿠키가 존재하면
            offset += search.length;
            // set index of beginning of value
            end = document.cookie.indexOf(";", offset);
            // 쿠키 값의 마지막 위치 인덱스 번호 설정
            if (end == -1)
                end = document.cookie.length;
            return unescape(document.cookie.substring(offset, end));
        }
    }
    return "";
}

function saveid(form) {
    var expdate = new Date();
    // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
    if (form.checkId.checked)
        expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
    else
        expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
    setCookie("saveid", form.id.value, expdate);
}




function getid(form) {
    form.checkId.checked = ((form.id.value = getCookie("saveid")) != "");
}


	function getLogType() {


		var LogType = getCookie("LogType");
		if(!LogType){

			fnLoginTypeSelect("ENT");
		}else{

			fnLoginTypeSelect(LogType);
		}




	}


function fnInit() {


    getid(document.loginForm);

	getLogType();



    <c:if test="${not empty fn:trim(message) &&  message ne ''}">
    alert("<c:out value='${message}'/>");
    </c:if>

}

function fnLoginTypeSelect(objName){

		// document.getElementById("typeGnr").className = "";
		// document.getElementById("typeEnt").className = "";
		// document.getElementById("typeUsr").className = "";
		//
		// document.getElementById(objName).className = "on";

		if(objName == "GNR"){ //일반회원
			document.loginForm.userSe.value = "GNR";



		}else if(objName == "ENT"){	//기업회원
			 document.loginForm.userSe.value = "ENT";

			$("input:radio[name='loginType']:radio[value='ENT']").prop('checked', true); // 선택하기




		}else if(objName == "USR"){	//업무사용자
			 document.loginForm.userSe.value = "USR";
			$("input:radio[name='loginType']:radio[value='USR']").prop('checked', true); // 선택하기
		}




	var todayDate = new Date();
	todayDate.setDate( todayDate.getDate() + 365 );
	document.cookie = "LogType" + "=" + escape(document.loginForm.userSe.value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"



}



</script>
</head>



<body onLoad="fnInit();" class="login">

<!-- javascript warning tag  -->

<form name="loginForm" id="loginForm" action="<c:url value='/cms/actionLogin.do'/>" method="post">
	<input type="hidden" id="message" name="message" value="<c:out value='${message}'/>">
	<input type="hidden" id="userSe" name="userSe" value="ENT">
	<input type="hidden" id="redirect" name="redirect" value="/cms/index.do">
	<input name="j_username" type="hidden"/>

	<h1>CMS LOGIN</h1>

	<%--<div class="login-tab item2">--%>
		<%--<input type="radio" id="typeEnt"  onclick="fnLoginTypeSelect('ENT')" name="loginType" value="ENT"><label for="typeEnt">장비 담당자</label>--%>
		<%--<input type="radio" id="typeUsr"  onclick="fnLoginTypeSelect('USR')" name="loginType" value="USR"><label for="typeUsr">시스템 관리자</label>--%>
	<%--</div>--%>


	<div class="outline">
			<label for="id" class="sr-only">ID</label>
			<input type="text" id="id" name="id" maxlength="20"  placeholder="ID를 입력하세요" autocomplete="off" required autofocus value="infomind">


			<label for="password" class="sr-only">Password</label>
			<input type="password" id="password" name="password" maxlength="12" placeholder="비밀번호를 입력하세요" required value="info4787">


			<div class="custom-checkbox">
				<input type="checkbox" id="checkId" onclick="saveid(document.loginForm);" >
				<label for="checkId"><i class='bx bxs-checkbox-checked'></i>아이디 저장</label>
				<%--<input type="checkbox" id="customCheck2">--%>
				<%--<label for="customCheck2"><i class='bx bxs-checkbox-checked'></i>비밀번호 저장</label>--%>
			</div>


			<button type="button"  onclick="actionLogin()" >LOGIN</button>

			<%--<input type="button" class="btn_login" value="<spring:message code="comUatUia.loginForm.login"/>" onclick="actionLogin()"> <!-- 로그인  -->--%>

	</div>
	<p class="copyright">Copyright 2018. (사)세계제주인대회조직위원회 all rights reserved.</p>
</form>



</body>
</html>


