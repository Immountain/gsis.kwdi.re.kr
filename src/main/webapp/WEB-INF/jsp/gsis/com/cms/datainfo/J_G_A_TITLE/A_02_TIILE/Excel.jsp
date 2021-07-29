<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<script src="<c:url value="/assets/ax5/ax5core/ax5core.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-formatter/ax5formatter.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">


<script type="text/javascript">


    var firstGrid = new ax5.ui.grid();


    $(document.body).ready(function () {

        genGrid();
     });



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



    function gridExcelUplad() {
        var formData = new FormData(document.excelForm);
        $ifx.ajax('<c:url value="/cms/gsis/excel/upload.do"/>', {
            method: 'POST',
            processData: false,
            contentType: false,
            data: formData,
            success: function(res){


                console.log(res);
                firstGrid.setData(res);
            }
        })
    }





</script>
<div class="sub subView">
    <h3 class="btitle">
        기준점
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
            <button type="button" class="button" onclick="gridExcelUplad()"><i class="bx bx-slider-alt"></i>엑셀자료 업로드</button>
            <button type="button" class="button main" name="btn_regist" id="btn_regist">저장처리</button>
            <input type="hidden" id="themaId" name="themaId" value="${view.themaId}">

        </div>
        <div class="rows">

            <form name="excelForm" enctype="multipart/form-data">
                <input class="w100" type="file" name="uploadFile">
            </form>
        </div>


    </div>
    <h3 class="btitle">
        목록
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 500px;"></div>
    </div>
</div>