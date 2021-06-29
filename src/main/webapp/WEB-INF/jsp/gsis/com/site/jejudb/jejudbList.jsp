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

        Search("ROOT");

	});


    // 조회
    function Search(parentId) {
        var parentId = parentId;

        var p = {
            parentId: parentId
        };

        $ifx.ajax('<c:url value='/statsdb/infoList.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
               console.log(res)
            }
        })
    }


</script>
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>

            <info:indicatorTag group="" skinName="indicator-memu" siteMemuId="${menuInfo.siteMemuId}"/>

        </div>
    </section>


    <section class="sub-db">
        <div class="container">

            <h4 class="stitle">${menuInfo.siteMemuNm}</h4>

            <article class="db-steps">


                <div class="step step01">
                    <h5>분류 1</h5>
                    <ul class="list">
                        <li>
                            <a class="active" href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>

                    </ul>
                </div>


                <div class="step step02">
                    <h5>분류 2</h5>
                    <ul class="list">
                        <li>
                            <a href="#">카테고리</a>
                        </li>
                        <li>
                            <a class="active" href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>
                        <li>
                            <a href="#">카테고리</a>
                        </li>

                    </ul>
                </div>


                <div class="step step03">
                    <h5>분류 3</h5>
                    <ul class="list">
                        <li>
                            <a href="#" class="active"><i class='bx bx-link-external'></i>총조사 인구 <span>(성/행정구역/연령별)</span></a>
                        </li>
                        <li>
                            <a href="#"><i class='bx bx-link-external'></i>총조사 인구 <span>(성/행정구역/연령별)</span></a>
                        </li>
                        <li>
                            <a href="#"><i class='bx bx-link-external'></i>총조사 인구 <span>(성/행정구역/연령별)</span></a>
                        </li>
                        <li>
                            <a href="#"><i class='bx bx-link-external'></i>총조사 인구 <span>(성/행정구역/연령별)</span></a>
                        </li>
                        <li>
                            <a href="#"><i class='bx bx-link-external'></i>총조사 인구 <span>(성/행정구역/연령별)</span></a>
                        </li>

                    </ul>
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