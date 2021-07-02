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


    var mask = new ax5.ui.mask();
    var firstGrid = new ax5.ui.grid();
    function genGrid() {
        firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
            columns: [
                {key: "baseDay", label: "기준일", align: "center", width: 140},
                {key: "jtpEquipId", label: "JTP시설장비등록ID", align: "center", width: 180},
                {key: "korNm", label: "한글명", align: "center", width: 180},
                {key: "engNm", label: "영문명", align: "center", width: 180},
                {key: "comCd", label: "이용기업코드", align: "center", width: 120},
                {key: "comNm", label: "이용기업이름", align: "center", width: '*'},
                {key: "kpiRet0", label: "실제이용가능시간(H)", align: "center", width: 120, editor: {type: "number"}},
                {key: "kpiRet1", label: "일간가동시간(H)", align: "center", width: 120, editor: {type: "number"}},
                {key: "kpiRet2", label: "일간유지보수시간(H)", align: "center", width: 120, editor: {type: "number"}},
                {key: "kpiRet3", label: "장비수익금", align: "center", width: 120, editor: {type: "number"}},
                {key: "kpiRet4", label: "장비유지관리비", align: "center", width: 120, editor: {type: "number"}}
            ]
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


               // console.log(res);
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
        <i class='bx bxs-dashboard'></i>마이크로데이터 목록
    </h2>
    <h3 class="btitle">
        검색
    </h3>
    <!-- 검색영역 -->
    <!-- 검색조건선택 -->
    <div class="white-box">
        <div class="rows">
         <span class="select-outline">
                <select name="searchCondition" id="searchCondition"
                        title="<spring:message code="title.searchCondition" />">
                       <option selected value=''><spring:message code="input.select"/></option><!-- 선택하세요 -->
                       <option value="1">자료명</option>
                       <option value="2">연구책임자</option>
               </select>
         </span>

            <span class="select-outline">
                <select name="searchUseYn" id="searchUseYn" title="사용여부">
                       <option selected value=''>전체</option><!-- 선택하세요 -->
                       <option value="Y">사용</option><!-- 코드ID -->
                       <option value="N">사용안함</option><!-- 코드ID -->
               </select>
           </span>
            <form name="excelForm" enctype="multipart/form-data">
                <input class="w100" type="file" name="uploadFile">
            </form>
            <button type="button" class="button main" onclick="gridExcelUplad()"><i class="bx bx-slider-alt"></i>엑셀자료 업로드</button>
        </div>
    </div>
    <h3 class="btitle">
        목록
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
    </div>
</div>