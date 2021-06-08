<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--마이페이지 view--%>
<%--<br>--%>
<%--//패스워드 변경 화면: /mypage/mberPasswordUpdtView.do--%>
<%--<br>--%>
<%--//회원 수정 화면: /mypage/modify.do--%>
<%--<br>--%>
<%--//회원탈퇴 : /mypage/mberWithdraw.do--%>
<%--<br>--%>
<div id="content">
    <section class="sub-header sub01">
        <!--
            대메뉴에 따른 Class

            플랫폼소개 : sub01
            이용안내 : sub02
            장비관리 : sub03
            장비자료실 : sub04
            커뮤니티 : sub05
        -->

        <div class="container">
            <h2>마이페이지</h2>
        </div>
    </section>

    <section class="sub-menu">
        <div class="container">
            <p class="home"><a href="/">홈으로</a></p>

            <ul class="submenu subdepth01">
                <li>
                    <input id="subdepth01" type="checkbox" /><label for="subdepth01">마이페이지</label>
                    <ul>
                        <li><a href="<c:url value='/mypage/modify.do'/>">회원정보관리</a></li>
                        <li><a href="<c:url value='/mypage/jtp/reserveList.do'/>">실시간예약내역</a></li>
                        <li><a href="<c:url value='/mypage/jtp/consultingList.do'/>">장비상담내역 조회</a></li>
                    </ul>
                </li>
            </ul>

            <%--<ul class="submenu subdepth02">--%>
                <%--<li>--%>
                    <%--<input id="subdepth02" type="checkbox" /><label for="subdepth02" >마이페이지</label>--%>
                    <%--<ul>--%>
                        <%--<li><a href="#">회원정보관</a></li>--%>
                        <%--<li><a class="on" href="#">실시간예약내역</a></li>--%>
                        <%--<li><a href="#">장비상담내역 조</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            <%--</ul>--%>
        </div>
    </section>

    <section class="mypage-main">
        <div class="container">
            <h3 class="subtitle">마이페이지</h3>

            <ul>
                <li>
                    <a class="member" href="<c:url value='/mypage/modify.do'/>">
                        <h4>회원정보관리</h4>
                        <p>
                            <span>고객님의 회원정보를</span>
                            관리할 수 있습니다.
                        </p>
                    </a>
                </li>
                <li>
                    <a class="reservation" href="<c:url value='/mypage/jtp/reserveList.do'/>">
                        <h4>실시간 예약내역</h4>
                        <p>
                            <span>고객님의 예약정보를</span>
                            관리할 수 있습니다.
                        </p>
                    </a>
                </li>
                <li>
                    <a class="consulting" href="<c:url value='/mypage/jtp/consultingList.do'/>">
                        <h4>장비상담내역 조회</h4>
                        <p>
                            <span>고객님의 상담내역을</span>
                            관리할 수 있습니다.
                        </p>
                    </a>
                </li>
            </ul>
        </div>
    </section>
</div>
<!-- E:mainContent -->