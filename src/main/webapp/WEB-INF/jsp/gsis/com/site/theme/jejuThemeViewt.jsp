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

            <h4 class="stitle">전반적 가족관계 만족도</h4>

            <article class="theme-view">

                <div class="head">
                    <h5>
                        가족관계에 대해 만족하는 여성은 59.8%, 남성은 58.9%로 여성이 높아
                    </h5>

                    <div class="text category01">
                        <!-- category 1 ~ 6 까지 클래스명 적용 -->


                        2020년 현재 전반적인 가족관계에 대한 만족도는 여성이 59.8%, 남성 58.9%로여성이 남성보다 0.9% 높게 나타남

                        <p class="info">
                            2014년에서 2018년 사이 여성과 남성 모두 가족관계 만족도가 상승하였으나 2020년에 여성과 남성 모두 떨어짐
                        </p>
                    </div>
                </div>

                <div class="chart">
                    <!-- 챠트영역 -->
                </div>


                <p class="info">
                    좌우터치로 스크롤 가능합니다.
                    <span>단위 %</span>
                </p>
                <div class="table-outline">
                    <table>
                        <thead>
                        <tr>
                            <th colspan="2">구분</th>
                            <th>계</th>
                            <th>만족</th>
                            <th>보통</th>
                            <th>불만</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td rowspan="3">2014</td>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        <tr>
                            <td>전체</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                            <td>100.0</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

                <div class="information">
                    <p>주 : 만족 = 매우 만족 + 만족, 불만족 = 매우 불만족 + 불만족</p>
                    <p>자료 : 통계청, 「사회조사」(2014, 2016, 2018, 2020), http://www.kostat.go.kr</p>
                </div>

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