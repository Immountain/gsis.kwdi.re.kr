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


<script src="<c:url value="/js/infomind/com/jquery-3.4.1.slim.min.js"/>"></script>

<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-mask/ax5mask.css"/>">
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">

<script src="<c:url value="/assets/dist/yearpicker.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/dist/yearpicker.css"/>">

<script type="text/javascript">


    var firstGrid = new ax5.ui.grid();


    $(document.body).ready(function () {


        $('#strYear').yearpicker();
        $('#endYear').yearpicker();



        //닫기
        $('#modal-close').click(function () {
            ax5modal.close();
        });



        genGrid();
     });



    function genGrid() {
        firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
            columns: [
                {key: "dataYear", label: "년도", align: "center", width: 60},
                {key: "dataGb", label: "구분", align: "center", width: 60},
                {key: "cdmData1", label: "출생건수", align: "center", width: 60 ,formatter: "money",editor: {type: "text"}},
                {key: "cdmData2", label: "조출생률", align: "center", width: 60,formatter: "money",editor: {type: "text"}},
                {key: "cdmData3", label: "사망건수", align: "center", width: 60,formatter: "money",editor: {type: "text"}},
                {key: "cdmData4", label: "조사망률", align: "center", width: 60,formatter: "money",editor: {type: "text"}}





            ]
            // ,
            // body:{
            //     mergeCells: ["dataYear", "dataGb"]
            // }
        });
    }



    function gridExcelUplad() {


        var formData = new FormData(document.excelForm);
        $ifx.ajax('<c:url value="/cms/gsis/a04/upload.do"/>', {
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


    function saveGrid() {


        var strYear =  "";
        var endYear =  "";

        // if(!strYear){
        //
        //    alert("기준 시작 년도을 선택하세요");
        //     return;
        // }
        //
        // if(!endYear){
        //
        //     alert("기준 종료 년도을 선택하세요");
        //     return;
        // }


        if(firstGrid.getList().length==0){


            alert("생성할 데이터가 없습니다.");
            return;
        }


        var List = firstGrid.getList();
        var p = {strYear:strYear,endYear:endYear,listData:List}


     if(!confirm('데이터을 생성 하시겠습니까?')) return false;

        $ifx.ajax('<c:url value='/cms/gsis/a04/updateObject.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                ax5modal.callback('OK');
                ax5modal.close();
                alert("저장처리 완료했습니다.");

            }
        })
    }



    function download() {

        //
        // var strYear =  $('#strYear').val();
        // var endYear =  $('#endYear').val();
        //
        // if(!strYear){
        //
        //     alert("기준 시작 년도을 선택하세요");
        //     return;
        // }
        //
        // if(!endYear){
        //
        //     alert("기준 종료 년도을 선택하세요");
        //     return;
        // }


        var form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "POST");  //Post 방식
        form.setAttribute("action", "<c:url value="/cms/gsis/a04/download.do"/>"); //요청 보낼 주소

        // var hiddenField = document.createElement("input");
        // hiddenField.setAttribute("type", "hidden");
        // hiddenField.setAttribute("name", "strYear");
        // hiddenField.setAttribute("value", $('#strYear').val());
        // form.appendChild(hiddenField);
        //
        // var hiddenField2 = document.createElement("input");
        // hiddenField2.setAttribute("type", "hidden");
        // hiddenField2.setAttribute("name", "endYear");
        // hiddenField2.setAttribute("value", $('#endYear').val());
        // form.appendChild(hiddenField2);


        document.body.appendChild(form);
        form.submit();
    }
    
    function btn_add() {


        var strYear =  $('#strYear').val();
        var dataGb =  $('#dataGb').val();

        if(!strYear){

            alert("기준년도을 입력하세요.");
            return;
        }



        var check =false;


        for (var i = 0; i <firstGrid.getList().length; i++) {


            var checkDataYear =firstGrid.getList()[i].dataYear;
            var checkDataGb =firstGrid.getList()[i].dataGb;

            if(strYear ==checkDataYear &&dataGb==checkDataGb){
                check =true;

            }
        };

        if(check==true){

            alert("같은 내용이 존재 합니다.")
            return;
        }




        var p ={

            dataYear: strYear
           ,dataGb:dataGb
           ,cdmData1:0
           ,cdmData2:0
           ,cdmData3:0
           ,cdmData4:0




        }

        firstGrid.addRow($.extend({}, firstGrid.list[Math.floor(Math.random() * firstGrid.list.length)],  p,{__index: undefined}));



        // var updateIndex = Math.floor(Math.random() * firstGrid.list.length);
        // firstGrid.updateRow($.extend({}, firstGrid.list[updateIndex], {dataYear: strYear,dataGb:dataGb}), updateIndex);



    }

    function btn_remove() {

        firstGrid.removeRow();

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

            <%--<input type="text" id="strYear" name="strYear" value="" /> ~<input type="text" id="endYear" name="endYear" value="" />--%>

            <%--maxlength="155">--%>
            <button type="button" class="button" onclick="gridExcelUplad()"><i class="bx bx-slider-alt"></i>엑셀자료 업로드</button>
            <button type="button" class="button main" onclick="saveGrid()"><i class="bx bx-slider-alt"></i>변경사항 저장</button>
            <button type="button" class="button main" onclick="download()"><i class="bx bx-slider-alt"></i>엑셀양식 다운로드</button>
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
    <input type="text" id="strYear" name="strYear" value="" />
    <select id="dataGb" name="dataGb">
        <option value="전체">전체</option>
    </select>

    <button type="button" class="button" onclick="btn_add()">로우추가</button>
    <button type="button" class="button" onclick="btn_remove()">로우 제거</button>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 500px;"></div>
    </div>
</div>