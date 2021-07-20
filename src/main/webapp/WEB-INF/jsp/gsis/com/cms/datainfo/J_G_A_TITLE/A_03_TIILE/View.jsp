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
                {key: "tw1", label: "업데이트 일", align: "center", width: 140},
                {key: "tw2", label: "수집시간", align: "center", width: 180},
                {key: "tw3", label: "등록자", align: "center", width: 180},
                {key: "tw4", label: "삭제", align: "center", width: 180}



            ]
        });
    }




</script>
<div class="sub subView">
    <h3 class="btitle">
        정보 등록
    </h3>
    <div class="white-box">

        <table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
            <tbody>


            <tr>
                <th><label for="themaGroupId">제목<span class="pilsu">*</span></label></th>
                <td class="left" >
                    <input type="text" id="themaGroupId" name="themaGroupId">
                </td>
                <th><label for="themaGroupNm">서브제목<span class="pilsu">*</span></label></th>
                <td class="left">
                    <input type="text" id="themaGroupNm" name="themaGroupNm">
                </td>
            </tr>
            <tr>
                <th>내용<span class="pilsu">*</span></th>
                <td colspan="4">
                   <textarea></textarea>
                </td>

            </tr>



            </tbody>
        </table>

        <div class="rows">
            <button type="button" class="button main" name="btn_regist" id="btn_regist">저장처리</button>
            <input type="hidden" id="themaId" name="themaId" value="${view.themaId}">
        </div>


    </div>
    <h3 class="btitle">
        업데이트 내용
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 200px;"></div>
    </div>
</div>