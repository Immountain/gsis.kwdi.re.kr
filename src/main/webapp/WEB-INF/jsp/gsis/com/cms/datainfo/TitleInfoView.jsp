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

        Search();


        $('#btn_save').click(function() {


            //ajax 통신은 jsp 에서 체크
            var themaTitle = $('#themaTitle').val();
            var themaSubTitle = $('#themaSubTitle').val();
            var txtContent = $('#txtContent').val();

            if(!themaTitle){
                alert("제목을 입력하세요")
                return;
            }

            if(!themaSubTitle){
                alert("메모을 입력하세요")
                return;
            }

            if(!txtContent){
                alert("내용을 입력하세요")
                return;
            }

            var formData = $("#view").serializeObject();

            var API_SERVER = "<c:url value='/cms/gsis/data/fileHisSave.do' />";
            var saveQuestion = confirm("저장 하시겠습니까?");
            if (saveQuestion) {
                $.ajax({
                    url : API_SERVER,
                    type : 'post',
                    data : formData,
                    dateType:'json',

                    beforeSend: function(xhr) {

                        xhr.setRequestHeader("AJAX", "true");

                    },
                    success : function(data) {

                        var jsonObj = JSON.stringify(data);
                        if(data.status=="0"){
                            if(data.message=="SUCCESS"){



                                alert("저장처리 완료했습니다.");

                              $('#themaTitle').val("");
                              $('#themaSubTitle').val("");
                              $('#txtContent').val("");
                              $('#etc').val("");



                                Search();

                            }else{
                                alert(data.message);

                            }
                        }else{

                            alert("처리중 오류가 발생했습니다.");
                        }
                    }, // success
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                    }
                });
            }
        });




    });



    function genGrid() {
        firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
            columns: [
                {key: "regDt", label: "업데이트 일", align: "center", width: 140},
                {key: "themaTitle", label: "제목", align: "center", width: 180},
                {key: "themaSubTitle", label: "메모", align: "center", width: 180},
                {key: "txtContent", label: "내용", align: "center", width: 180},
                {
                    key: "themaFileHisSno", label: "수정", width:60 ,formatter: function () {

                        // console.log(this.item);
                        return "<button type='button' class='btn btn-xs btn-default' onclick=" + "'gotoUpdt(" + this.dindex + ");'> 수정 </button>";
                    }
                },
            ]
        });
    }


    function gotoUpdt(row) {

        var themaTitle = firstGrid.getList()[row].themaTitle;
        var themaSubTitle = firstGrid.getList()[row].themaSubTitle;
        var txtContent = firstGrid.getList()[row].txtContent;
        var etc = firstGrid.getList()[row].etc;
        var themaFileHisSno = firstGrid.getList()[row].themaFileHisSno;



        $('#themaTitle').val(themaTitle);
        $('#themaSubTitle').val(themaSubTitle);
        $('#txtContent').val(txtContent);
        $('#etc').val(etc);
        $('#themaFileHisSno').val(themaFileHisSno);

    }



    // 조회
    function Search() {

        var themaId =$('#themaId').val();


        var p = {
            themaId:themaId
        };

        $ifx.ajax('<c:url value='/cms/gsis/data/fileHisList.do' />', {
            method: "POST",
            data: JSON.stringify(p),
            success: function (res) {
                firstGrid.setData(res.list);
            }
        })
    }

    jQuery.fn.serializeObject = function() {
        var obj = null;
        try {
            if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
                var arr = this.serializeArray();
                if (arr) {
                    obj = {};
                    jQuery.each(arr, function() {
                        obj[this.name] = this.value;
                    });
                }
            }
        } catch (e) {
            alert(e.message);
        } finally {
        }

        return obj;
    };

</script>


<div class="sub subView">
    <h3 class="btitle">
        정보 등록
    </h3>
    <div class="white-box">
        <form id="view" name="view">
                <table class="landscape">
                    <tbody>


                    <tr>
                        <th><label for="themaTitle">제목<span class="pilsu">*</span></label></th>
                        <td class="left" >
                            <input type="text" id="themaTitle" name="themaTitle" class="w500">
                        </td>

                    </tr>
                    <tr>
                        <th><label for="themaSubTitle">메모<span class="pilsu">*</span></label></th>
                        <td class="left">
                            <input type="text" id="themaSubTitle" name="themaSubTitle" class="w500">
                        </td>

                    </tr>
                    <tr>
                        <th>내용<span class="pilsu">*</span></th>
                        <td colspan="4">
                           <textarea name="txtContent" id="txtContent" ></textarea>
                        </td>

                    </tr>
                    <tr>
                        <th>하단내용<span class="pilsu">*</span></th>
                        <td colspan="4">
                            <textarea id="etc" name="etc"></textarea>
                        </td>

                    </tr>
                    </tbody>
                    <input type="hidden" id="themaId" name="themaId" value="${view.themaId}">
                    <input type="hidden" id="themaGroupId" name="themaGroupId" value="${view.themaGroupId}">

                </table>

            <input type="hidden" id="themaFileHisSno" name="themaFileHisSno">
        </form>

        <div class="rows">
            <button type="button" class="button main" name="btn_save" id="btn_save">저장처리</button>
            <button type="button" class="button main" name="btn_save" id="btn_update">수정처리</button>
            <button type="button" class="button" name="btn_save" id="btn_cancel">초기화</button>

        </div>


    </div>
    <h3 class="btitle">
        업데이트 내용
    </h3>
    <div class="rows white-box">
        <div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 200px;"></div>
    </div>
</div>