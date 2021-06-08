<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javaScript" language="javascript">


    function goLosgPage() {

        location.href="/mypage/login.do"

    }

</script>

<div id="content">
   <section class="signup">
        <div class="container">

            <h3 class="subtitle">회원가입</h3>

            <ul class="step">
                <li class="step01">
                    <strong>STEP1</strong>
                    약관동의
                </li>
                <li class="step02">
                    <strong>STEP2</strong>
                    개인정보 입력
                </li>
                <li class="step03 active">
                    <strong>STEP3</strong>
                    회원가입 완료
                </li>
            </ul>

            <div class="result">
                <h4><u>회원가입</u>이 완료되었습니다.</h4>

                <p>JTP 제주연구장비정보포털 회원가입을 환영합니다.</p>
                <p>아래 버튼을 누르시면 로그인 페이지로 이동합니다.</p>



                <div class="btn-set center big">
                    <button class="btn main" type="button" onclick="goLosgPage()" >로그인하기</button>
                </div>
            </div>
        </div>
    </section>

</div>
<!-- E:mainContent -->
