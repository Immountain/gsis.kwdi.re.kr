<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<script src="<c:url value='/js/infomind/com/moment.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){

      //Search("ROOT");

	});



</script>
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>

            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

        </div>
    </section>


    <section class="sub-content">
        <div class="container">

            <h4 class="stitle">테마통계</h4>

            <article class="theme-list">

                <ul class="list">
                    <li>
                        <h5 class="category01">인구와 가족</h5>

                        <div class="text">

                        <c:forEach items="${atitleList}" var="item" varStatus="status">
                            <a href="/jejuTheme/view.do">${item.themaNm}</a>
                        </c:forEach>
                        </div>

                    </li>
                    <li>
                        <h5 class="category02">보육과 교육</h5>

                        <div class="text">
                            <c:forEach items="${btitleList}" var="item" varStatus="status">
                                <a href="/jejuTheme/view.do">${item.themaNm}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li>
                        <h5 class="category03">경제활동</h5>

                        <div class="text">
                            <c:forEach items="${ctitleList}" var="item" varStatus="status">
                                <a href="/jejuTheme/view.do">${item.themaNm}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li>
                        <h5 class="category04">건강과 복지</h5>
                        <div class="text">
                            <c:forEach items="${dtitleList}" var="item" varStatus="status">
                                <a href="/jejuTheme/view.do">${item.themaNm}</a>
                            </c:forEach>
                        </div>

                    </li>
                    <li>
                        <h5 class="category05">사회참여와 문화</h5>

                        <div class="text">
                            <c:forEach items="${etitleList}" var="item" varStatus="status">
                                <a href="/jejuTheme/view.do">${item.themaNm}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li>
                        <h5 class="category06">안전과 환경</h5>

                        <div class="text">
                            <c:forEach items="${ftitleList}" var="item" varStatus="status">
                                <a href="/jejuTheme/view.do">${item.themaNm}</a>
                            </c:forEach>
                        </div>
                    </li>
                </ul>


            </article>


        </div>
    </section>
</div>
<!-- E:mainContent -->





<!-- S:popup -->
<div class="layer-pop">
    <div class="outline">

        <!--
            최종 링크에 맞는 아이프레임 호출
        -->

        팝업창


        <button class="close">닫기 <i class='bx bx-x' ></i></button>

    </div>
</div>
<!-- E:popup -->



