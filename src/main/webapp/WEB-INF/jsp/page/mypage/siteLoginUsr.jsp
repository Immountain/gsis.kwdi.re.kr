<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script type="text/javaScript" language="javascript">


    $(document).ready(function(){

        if($('#message').val()){
            alert($('#message').val());
        }

        $("#password").keydown(function(key) {

            if (key.keyCode == 13) {
                actionLogin();
            }

        });



    });


    function checkLogin(userSe) {


        // 일반회원
        if (userSe == "GNR") {
            document.loginForm.userSe.value = "GNR";
        // 기업회원
        } else if (userSe == "ENT") {
            document.loginForm.userSe.value = "ENT";
        // 업무사용자
        } else if (userSe == "USR") {

            document.loginForm.userSe.value = "USR";
        }




    }

    function actionLogin() {
        if (document.loginForm.id.value =="") {
            alert("<spring:message code="comUatUia.validate.idCheck" />"); <%-- 아이디를 입력하세요 --%>
        } else if (document.loginForm.password.value =="") {
            alert("<spring:message code="comUatUia.validate.passCheck" />"); <%-- 비밀번호를 입력하세요 --%>
        } else {
            <%--document.loginForm.action="<c:url value='/page/actionLogin.do'/>";--%>
            document.loginForm.action="<c:url value='/cms/actionLogin.do'/>";
            document.loginForm.submit();
        }
    }
</script>

<!-- S:mainContent -->
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>
            <strong>로그인</strong>


        </div>
    </section>



    <c:if test="${loginVO==null}">

        <a class="login" href="<info:url value="/mypage/login.do"/>">login</a>
    </c:if>
    <c:if test="${loginVO!=null}">

        <a class="login" href="/logout.do?redirect=<info:url value="/"/>">logout</a>

    </c:if>

    <section class="sub-content">
        <div class="container">
            <form name="loginForm" id="loginForm" action="<c:url value='/cms/actionLogin.do'/>" method="post">
                <input type="hidden" id="message" name="message" value="${message}">
                <input type="hidden" id="userSe" name="userSe" value="GNR">
                <input type="hidden" id="redirect" name="redirect" value="/">
                <input type="hidden" id="pageRedirect" name="pageRedirect" value="page">
                <input name="j_username" type="hidden"/>


            <h4 class="stitle">로그인</h4>

                    <article class="login">
                        <fieldset>
                            <legend class="sr-only">로그인폼</legend>

                            <input type="text" id="id" name="id"  placeholder="아이디">
                            <input type="password" d="password" name="password" placeholder="비밀번호">
                            <button class="submit" type="button" onclick="actionLogin()">로그인</button>

                            <ul class="tools">
                                <li><a href="/mypage/find.do">아이디 / 비밀번호 찾기</a></li>
                            </ul>

                            <div class="sign">
                                회원가입 후 사이트의 모든 기능을 이용 가능합니다.</span>

                                <a href="/mypage/join.do">회원가입</a>
                            </div>
                        </fieldset>

                    </article>
            </form>

        </div>
    </section>
</div>
<!-- E:mainContent -->

