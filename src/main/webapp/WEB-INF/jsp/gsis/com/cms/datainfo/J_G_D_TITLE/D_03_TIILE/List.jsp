<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script src="<c:url value="/assets/dist/yearpicker.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/dist/yearpicker.css"/>">

<script type="text/javascript">


    var firstGrid = new ax5.ui.grid();

    $(document).ready(function () {



        $('#strYear').yearpicker();
        $('#endYear').yearpicker();


        genGrid();


        $('#btn_regist_excel').click(function () {

            var themaId = $('#themaId').val();
            var p = {themaId:themaId};
            var API_SERVER = "<c:url value='/cms/gsis/data/excel.do' />";
            ax5modal.open({
                theme: "primary",
                height: 800,
                width: 1000,
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
                height: 800,
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

        $('#btn_down_excel').click(function () {
            firstGrid.exportExcel("4-11(국민연금 가입자 수 및 여성 가입자 비율).xls");
        });




        Search();

    });



    var firstGrid = new ax5.ui.grid();
    function genGrid() {
        firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
            columns: [
                {key: "dataYear", label: "년도", align: "center", width: 60},
                {key: "dataGb", label: "구분", align: "center", width: 60},
                {key: "cdmData1", label: "전체", align: "center", width: 60 ,formatter: "money"},
                {key: "cdmData2", label: "18~19세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData3", label: "20~24세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData4", label: "25~29세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData5", label: "30~34세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData6", label: "35~39세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData7", label: "40~44세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData8", label: "45~49세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData9", label: "50~54세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData10", label: "55~59세", align: "center", width: 60,formatter: "money"},
                {key: "cdmData11", label: "60세 이상", align: "center", width: 60,formatter: "money"}




            ],
            body:{
                mergeCells: ["dataYear", "dataGb"]
            }
        });
    }



    // 조회
    function Search() {

        var strYear =$('#strYear').val();
        var endYear =$('#endYear').val();


        var p = {
            strYear:strYear,endYear:endYear
        };

        $ifx.ajax('<c:url value='/cms/gsis/d03/List.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                firstGrid.setData(res.list);
            }
        })
    }


</script>
<div class="sub subView">
    <nav class="navigation">
        <i class='bx bxs-home'></i>${menuInfo.depthFullname}
    </nav>
    <h2 class="stitle">
        <i class='bx bxs-dashboard'></i>${menuInfo.menuNm}
    </h2>
    <h3 class="btitle">
        검색
    </h3>
    <!-- 검색영역 -->
    <!-- 검색조건선택 -->
    <div class="white-box">
        <div class="rows">
            <input type="text" id="strYear" name="strYear" value="" maxlength="4"/> ~<input type="text" id="endYear" name="endYear" value="" maxlength="4"/>

            <%--<input type="text" class="w100" class="main" name="searchKeyword" id="searchKeyword" size="35"--%>
                   <%--title="<spring:message code="title.search" /> <spring:message code="input.input" />" value=''--%>
                   <%--maxlength="155">--%>
            <button type="button" class="button" name="btn_search" id="btn_search" onclick="Search()">조회</button>
            <button type="button" class="button main" name="btn_regist_info" id="btn_regist_info">내용등록</button>
            <button type="button" class="button main" name="btn_regist_excel" id="btn_regist_excel">엑셀업로드</button>
            <button type="button" class="button main" name="btn_down_excel" id="btn_down_excel">엑셀 다운로드</button>
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

