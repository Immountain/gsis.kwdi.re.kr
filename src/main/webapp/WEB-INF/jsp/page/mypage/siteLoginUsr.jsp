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

<div id="content" class="sub sub01">

    <!-- 각 구성요소 article로 모듈화 -->
    <div class="sub-head">

        <nav class="sub-menu">
            <button type="button">제주인 등록</button>
            <info:getMenuModel modelName="subSiteMenu" groupId="${SITEINFO.langCd}-primary" siteMemuId=""/>

            <div class="outline">
                <c:forEach items="${subSiteMenu}" var="item">
                    <c:if test="${item.viewYn eq 'Y'}">
                        <a <c:if test="${item.siteMemuId eq menuInfo.parentId}">class="active"</c:if> href="<info:url value="${item.url}"/>">${item.siteMemuNm}</a>
                    </c:if>
                </c:forEach>
            </div>
        </nav>



        <!-- background-item -->
        <div class='waves'>
            <div class='wave -one'></div>
            <div class='wave -two'></div>
            <div class='wave -three'></div>
        </div>
        <div class="bubble" >
            <ul class="circles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div >




    </div>





    <div class="content-box">



        <article class="login">
            <div class="container">

                <form name="loginForm" id="loginForm" action="<c:url value='/cms/actionLogin.do'/>" method="post">

                    <input type="hidden" id="message" name="message" value="${message}">
                    <input type="hidden" id="userSe" name="userSe" value="GNR">
                    <input type="hidden" id="redirect" name="redirect" value="/">
                    <input type="hidden" id="pageRedirect" name="pageRedirect" value="page">
                    <input name="j_username" type="hidden"/>

                    <div class="container">


                        <fieldset>
                            <legend class="sr-only">제주인 등록을 하지 않은 경우</legend>
                            <div class="sign">

                                <h3 class="sign">제주인 등록을 하지 않은 경우</h3>

                                <p class="info">아래의 등록하기를 눌러 <span>제주인등록을 해주세요.</span></p>

                                <a href="/jeju/people/register.do">제주인 등록하기</a>

                            </div>
                        </fieldset>

                        <fieldset>
                            <legend class="sr-only">제주인 등록을 한 경우</legend>
                            <div class="sign">
                                <h3 class="login">제주인 등록을 한 경우</h3>

                                <p class="info">이미 제주인등록을 하신 분은 <span>로그인 해주세요.</span></p>
                                <div class="input-outline"><input type="text"  id="id" name="id" placeholder="아이디 입력" maxlength="20"><label class="forid" for="id">아이디</label></div>
                                <div class="input-outline"><input type="password" id="password" name="password" placeholder="비밀번호(문자/숫자/특수문자로 조합된 8~16자리)"><label class="forpw" for="password">패스워드</label></div>
                                <button class="submit" type="button" onclick="actionLogin()">로그인</button>
                            </div>
                            <ul class="tools">
                                <li><a href="/mypage/find.do">아이디 / 비밀번호 찾기</a></li>
                            </ul>
                        </fieldset>
                    </div>
                </form>

            </div>
        </article>
    </div>


</div>


