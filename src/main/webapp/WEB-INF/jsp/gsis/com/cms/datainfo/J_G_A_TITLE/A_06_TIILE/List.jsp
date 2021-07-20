<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>




<script src="<c:url value="/assets/ax5/ax5core/ax5core.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-formatter/ax5formatter.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">




<script type="text/javascript">


    var firstGrid = new ax5.ui.grid();

    $(document).ready(function () {

        genGrid();
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
         <span class="select-outline">
               기간: <select name="searchCondition" id="searchCondition"
                        title="<spring:message code="title.searchCondition" />">
                       <option selected value='2011'>2011</option><!-- 선택하세요 -->
                       <option value="1">2020</option>
                       <option value="2">2021</option>
               </select>
             ~
             <select name="searchCondition" id="searchCondition2"
                     title="<spring:message code="title.searchCondition" />">
                       < <option selected value='2019'>2019</option><!-- 선택하세요 -->
                       <option value="1">2020</option>
                       <option value="2">2021</option>
               </select>
         </span>
         <form name="excelForm" enctype="multipart/form-data">
                <input class="w100" type="file" name="uploadFile">
            </form>
            <button type="button" class="button main" onclick="gridExcelUplad()"><i class="bx bx-slider-alt"></i>엑셀자료 업로드</button>
            <button type="button" class="button" ><i class="bx bx-slider-alt"></i>저장</button>

        </div>
    </div>
    <h3 class="btitle">
        내용 (업데이트 기준일 2021-07-17)
    </h3>
    <div class="white-box">

        <table class="landscape" >
            <tbody>


            <tr>
                <th>제목<span class="pilsu">*</span></th>
                <td>
                    <input type="text" value="여가활용 만족여부 및 불만족 이유(2011~2019)">
                </td>
                <th>제목 영문<span class="pilsu">*</span></th>
                <td>
                    <input type="text" value="Satisfaction about Leisure Activity and Reasons for Dissatisfaction(2011~2019)">
                </td>

            </tr>
            <tr>
                <th>내용<span class="pilsu">*</span></th>
                <td><textarea >
                    주 : 1) 만족은 '매우 만족'과 '약간 만족'을 합산한 것, 불만족은 '매우 불만족'과 '약간 불만족'을 합산한 것임
                    2) 15세 이상 인구 대상임
                    자료 : 통계청, '사회조사'; 한국여성정책연구원,｢성인지통계시스템｣(www.gsis.kwdi.re.kr)
                    Source : Statistics Korea, Socia Survey; Korean Women's Development Institute, Gender Statistics Information System
                </textarea>
                </td>
            </tr>


            </tbody>
        </table>

    </div>

    <h3 class="btitle">
        목록
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 500px;"></div>
    </div>
</div>

