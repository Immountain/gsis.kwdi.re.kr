<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<script type="text/javascript">


    var firstGrid = new ax5.ui.grid();

    $(document).ready(function () {

        genGrid();


        $('#btn_regist_excel').click(function () {

            var themaId = $('#themaId').val();
            var p = {themaId:themaId};
            var API_SERVER = "<c:url value='/cms/gsis/data/excel.do' />";
            ax5modal.open({
                theme: "primary",
                height: 600,
                width: 900,
                header: {
                    title: '${menuInfo.menuNm}'+' 엑셀업로드',
                    btns: {
                        close: {
                            label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
                                // modal.close();
                                ax5modal.close();
                            }
                        }
                    }
                },
                iframe: {
                    method: "get",
                    url: API_SERVER,
                    param: p
                },

            }, function (d) {
                Search();
            });


        });


        $('#btn_regist_info').click(function () {

            var themaId = $('#themaId').val();
            var p = {themaId:themaId};
            var API_SERVER = "<c:url value='/cms/gsis/data/View.do' />";
            ax5modal.open({
                theme: "primary",
                height: 600,
                width: 900,
                header: {
                    title: '${menuInfo.menuNm}'+' 내용',
                    btns: {
                        close: {
                            label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
                                // modal.close();
                                ax5modal.close();
                            }
                        }
                    }
                },
                iframe: {
                    method: "get",
                    url: API_SERVER,
                    param: p
                },

            }, function (d) {
                Search();
            });


        });






    });



    var firstGrid = new ax5.ui.grid();
    function genGrid() {
        firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
            columns: [
                {key: "tw1", label: "년도", align: "center", width: 140},
                {key: "tw2", label: "구분", align: "center", width: 180},
                {key: "tw3", label: "만족", align: "center", width: 180},
                {key: "tw4", label: "불만족", align: "center", width: 180},
                {key: "tw5", label: "경제적부담", align: "center", width: 120},
                {key: "tw6", label: "시간부족", align: "center", width: 120},
                {key: "tw7", label: "교통 혼잡", align: "center", width: 120},
                {key: "tw8", label: "여가시설부족", align: "center", width: 120},
                {key: "tw9", label: "여가정보부족", align: "center", width: 120},
                {key: "tw9", label: "취미가 없어서", align: "center", width: 120},
                {key: "tw10", label: "건강, 체력 부족", align: "center", width: 120},
                {key: "tw11", label: "여가를 함께 즐길 사람이 없음", align: "center", width: 120},
                {key: "tw12", label: "기타", align: "center", width: 120}


            ],
            body:{
                mergeCells: ["tw1", "tw2"]
            }
        });
    }



</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>${menuInfo.menuNm}(${view.themaId})
    </h2>
    <h3 class="btitle">
        검색
    </h3>
    <!-- 검색영역 -->
    <!-- 검색조건선택 -->
    <div class="white-box">
        <div class="rows">
         <span class="select-outline">
                <select name="strYear" id="strYear"
                        title="<spring:message code="title.searchCondition" />">
                       <option selected value=''>검색조건</option><!-- 선택하세요 -->
                       <option value="1">2011</option>
                    <!-- 코드ID -->
                       <option value="2">2012</option>
                    <!-- 코드ID -->
               </select>
         </span>
            ~
            <span class="select-outline">
                <select name="endYear" id="endYear"
                        title="<spring:message code="title.searchCondition" />">
                       <option selected value=''>검색조건</option><!-- 선택하세요 -->
                       <option value="1">2019</option>
                    <!-- 코드ID -->
                       <option value="2">2021</option>
                    <!-- 코드ID -->
               </select>
         </span>


            <%--<input type="text" class="w100" class="main" name="searchKeyword" id="searchKeyword" size="35"--%>
                   <%--title="<spring:message code="title.search" /> <spring:message code="input.input" />" value=''--%>
                   <%--maxlength="155">--%>
            <button type="button" class="button" name="btn_search" id="btn_search"><i class='bx bx-slider-alt'></i>조회</button>
            <button type="button" class="button main" name="btn_regist_info" id="btn_regist_info">내용등록</button>
            <button type="button" class="button main" name="btn_regist_excel" id="btn_regist_excel">엑셀업로드</button>
            <input type="hidden" id="themaId" name="themaId" value="${view.themaId}">

        </div>
    </div>
    <h3 class="btitle">
        목록
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 500px;"></div>
    </div>
</div>

