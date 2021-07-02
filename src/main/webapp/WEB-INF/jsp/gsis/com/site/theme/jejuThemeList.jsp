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
                            <a href="">총인구</a>
                            <a href="">농가 및 어가 인구</a>
                            <a href="">연령별 인구</a>
                            <a href="">출생과 사망</a>
                            <a href="">가구주 가구</a>
                            <a href="">1인 가구의 성별 구성</a>
                            <a href="">평균 초혼 연령</a>
                            <a href="">전반적 가족관계 만족도</a>
                        </div>

                    </li>
                    <li>
                        <h5 class="category02">보육과 교육</h5>

                        <div class="text">
                            <a href="">영유아(0~5세) 인구</a>
                            <a href="">제주형 돌봄 공동체 현황</a>
                            <a href="">초･중･고등학교 학생 수 변화</a>
                            <a href="">대학교 진학률</a>
                            <a href="">여성 석, 박사과정 졸업자 현황</a>
                            <a href="">초･중･고등학교의 직위별 여성교원 비율</a>

                        </div>
                    </li>
                    <li>
                        <h5 class="category03">경제활동</h5>

                        <div class="text">
                            <a href="">경제활동참가율</a>
                            <a href="">연령별 경제활동인구 및 고용률</a>
                            <a href="">경력단절 여성 규모</a>
                            <a href="">여성의 경력단절 사유</a>
                            <a href="">연령별 취업자</a>
                            <a href="">취업자의 직업</a>
                            <a href="">임금근로자의 월평균 임금</a>
                            <a href="">사업체의 여성 대표자 및 여성 종사자 비율</a>
                        </div>
                    </li>
                    <li>
                        <h5 class="category04">건강과 복지</h5>
                        <div class="text">
                            <a href="">기대여명</a>
                            <a href="">주관적 건강인지율</a>
                            <a href="">국민연금 가입자 수 및 여성 가입자 비율</a>
                            <a href="">고용보험 피보험자 추이</a>
                            <a href="">출산전･후 휴가, 육아휴직, 육아기근로단축 수급자 현황</a>
                            <a href="">등록 장애인 수</a>
                        </div>

                    </li>
                    <li>
                        <h5 class="category05">사회참여와 문화</h5>

                        <div class="text">
                            <a href="">투표율</a>
                            <a href="">제7회 전국동시지방선거 입후보자 및 당선인 현황</a>
                            <a href="">주요부서 여성공무원 현황</a>
                            <a href="">5급 이상 관리직 여성 공무원 비율</a>
                            <a href="">인구 연간 1인당 평균 독서량</a>
                            <a href="">문화예술 / 스포츠 관람 및 국내관광 경험</a>
                        </div>
                    </li>
                    <li>
                        <h5 class="category06">안전과 환경</h5>

                        <div class="text">
                            <a href="">범죄위험에 대한 안전 인식</a>
                            <a href="">야간보행에 대한 두려움</a>
                            <a href="">범죄발생 및 검거건수</a>
                            <a href="">강력범죄 피해자의 여성 비율</a>
                            <a href="">환경체감 만족도</a>
                            <a href="">환경오염 방지 노력</a>

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