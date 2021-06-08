<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

<script type="text/javaScript" language="javascript">

    $(document).ready(function(){



       var chngGb ='${chngGb}';

       if(chngGb=='0'){
           $('.subtitle').text('장비 담당자 전환 변경 요청');


       }else{

           $('.subtitle').text('기관정보 변경 요청');
           selectBoxCloudIdInit('${userManageVO.jtpOrganCd}');
       }

        $('#remark2_view').hide();
        var cnt ='${count}';
        if(cnt>0){
            selectBoxCloudIdInit2('${jtpMberReqVO.aftrJtpOrganCd}');
            $('#aftrJtpCloudId').prop('disabled',true);
            $('#aftrJtpOrganCd').val('${jtpMberReqVO.aftrJtpOrganCd}');
            $('#orgNm').val('${jtpMberReqVO.organNm}');
        }else{

            selectBoxCloudIdInit2('${userManageVO.jtpOrganCd}');
            $('#aftrJtpOrganCd').val('${userManageVO.jtpOrganCd}');
            $('#orgNm').val('${userManageVO.orgNm}');
        }
    });



    function selectBoxCloudIdInit(organCd) {

        ajaxLoadSelect({
            url: "<c:url value='/mypage/selectCloudListByOrganCd.do' />",
            async : false,
            params: [
                {name: 'code', value: organCd}
            ],
            selectboxNm: 'tempbfoJtpCloudId'
        });
    }

    function selectBoxCloudIdInit2(organCd) {

        ajaxLoadSelect({
            url: "<c:url value='/mypage/selectCloudListByOrganCd.do' />",
            async : false,
            params: [
                {name: 'code', value: organCd}
            ],
            selectboxNm: 'aftrJtpCloudId'
        });
    }




    function orgOpen() {


        $("#searchKeyword").val('');

        $("#row_list").hide();
        var radioVal = $('input[name="type"]:checked').val();
        if(radioVal=='ENT'){
            var $rowList = $("#row_list")
            $rowList.empty();
            $("#searchKeyword").val();
            $("#modal-co").addClass("on");
        }else {

            var $rowList = $("#row_list")
            $rowList.empty();
            $("#searchKeyword").val();


            $("#modal-co").addClass("on");

            //  alert("기관회원만 조회 가능 합니다.");
            // return;
        }

    }

    function orgAjaxList() {


        var searchCondition = $("#searchCondition option:selected").val();
        var searchKeyword = $("#searchKeyword").val();


        var p = {
            searchCondition: searchCondition,
            searchKeyword: searchKeyword

        };


        if(!$("#searchKeyword").val()){

            alert("검색어 입력하세요");
            return;
        }

        var searchKeyword =$("#searchKeyword").val();
        if(searchKeyword.length <3){
            alert("검색어 3자리 이상 입력하세요");
            return;
        }

        var $rowList = $("#row_list")
        $rowList.empty();

        $.ajax({
            type: "GET", //전송방식을 지정한다 (POST,GET)
            url: "/mypage/organMstList.do",//호출 URL을 설정한다. GET방식일경우 뒤에 파라티터를 붙여서 사용해도된다.
            dataType: "json",//호출한 페이지의 형식이다. xml,json,html,text등의 여러 방식을 사용할 수 있다.
            data: p,
            error: function () {
                alert("불러오기 실패 하였습니다.");
            },
            success: function (data) {
                $("#row_list").show();
                $.each(data.list, function(idx, data) {
                    var $li = $('<li />');
                    var $a = $('<a />');

                    $a.append(
                        $('<p />', {class: 'select'})
                            .append($('<span />', {text: '선택'}))
                            .append($('<u />').append(
                                $('<button />', {text: '선택'}).on('click', function(e) {


                                    $("#orgNm").val(data.organNm);
                                    $("#orgCode").val(data.busiRegistNo);
                                    $("#ownerNm").val(data.ownerNm);
                                    $("#addr").val(data.address1);
                                    $("#detailAdres").val(data.address2);
                                    $("#indutyCode").val(data.carrierTob);
                                    $("#zipNo").val(data.zipNo);
                                    $("#aftrJtpOrganCd").val(data.jtpOrganCd);
                                    $("#modal-co").removeClass("on");

                                    selectBoxCloudIdInit2(data.jtpOrganCd);


                                })
                                )
                            )
                    )
                    $a.append($('<p />', {class: 'num', html: '<span>사업자번호</span><u>' + (data.busiRegistNo || '') + '</u>'}))
                    $a.append($('<p />', {class: 'name', html: '<span>기업/기관명</span><u>' + (data.organNm || '') + '</u>'}))
                    $a.append($('<p />', {class: 'ceo', html: '<span>대표자명</span><u>' + (data.ownerNm || '') + '</u>'}))
                    $li.append($a);
                    $rowList.append($li);
                });
                if(data.list.length == 0) {
                    var $li = $('<li />');
                    var $a = $('<a />');

                    $a.append($('<p />', {class: '', html: '<span></span><u>검색결과가 없습니다</u>'}))
                    $li.append($a);
                    $rowList.append($li);
                }
            }
        });
    }

    /*********************************************************
     * 저장처리
     ******************************************************** */
    function fnInsert(form) {

        if(!$("#aftrJtpOrganCd").val()){

            alert("소속기업/기관 선택하세요.");
            return;
        }
        var aftrJtpCloudId =$("#aftrJtpCloudId option:selected").val();
        var bfoJtpCloudId =$("#bfoJtpCloudId option:selected").val();


        if(!aftrJtpCloudId){

            alert("서비스활용기관 선택하세요.");
            return;
        }

        if(!$("#remark1").val()){

            alert("요청사유 입력하세요.");
            return;
        }

        if(aftrJtpCloudId==bfoJtpCloudId){


            alert("서비스활용기관이 기존하고 같습니다.");
            return;

        }
        if(confirm("변경요청 하시겠습니까? 요청시 관리자 승인 완료가 되야 변경된 정보을 활용할수 있습니다.")){
            form.submit();
        }





        
    }
    /*********************************************************
     * 취소 처리
     ******************************************************** */
    function cancel() {

        if(!$("#remark2").val()){

            alert("취소사유 입력하세요.");
            return;
        }




        var varForm = document.getElementById("jtpMberReqVO");
        if(confirm("취소처리 하시겠습니까?")){
            varForm.action="<c:url value='/mypage/orgCancel.do'/>";
            varForm.submit();
        }


        
    }


</script>


<!--  Modal 사업자등록번호 조회 -->
<div id="modal-co" class="modal">
    <!-- Modal content -->
    <div class="modal-content big">
        <h2>소속 기업/기관 찾기 <button class="close">닫기</button></h2>
        <div class="inside">

            <div class="co-check">

                    <span class="select-outline">
                        <span>@</span>

                        <select id="searchCondition" name="searchCondition">
                            <option value="2">기관명</option>
                            <option value="1">사업자번호</option>
                        </select>
                    </span>
                <div class="search-box">
                    <input type="text" id="searchKeyword" name="searchKeyword">
                    <button type="button" onclick="orgAjaxList()"><i class="bx bx-search" ></i>검색</button>
                </div>

            </div>
            <ul class="co-list" id="row_list" name="row_list">

            </ul>
            <%--<article class="pagenation">--%>
            <%--<a href="?pageIndex=1" title="이전 페이지로 이동" class="move" onclick="linkPage(1);return false; ">이전<i class="bx bx-chevron-left " aria-hidden="true"></i></a>--%>
            <%--<strong>1</strong>--%>
            <%--<a href="?pageIndex=2" onclick="linkPage(2);return false; " title="2 페이지로 이동">2</a>--%>
            <%--<a href="?pageIndex=3" onclick="linkPage(3);return false; " title="3 페이지로 이동">3</a>--%>
            <%--<a href="?pageIndex=11" title="다음 페이지로 이동" class="move" onclick="linkPage(6);return false; ">다음<i class="bx bx-chevron-right" aria-hidden="true"></i></a>--%>
            <%--</article>--%>
        </div>
    </div>

</div>
<!-- Modal-->




<!-- S:mainContent -->
<form name="jtpMberReqVO" id="jtpMberReqVO" method="post" action="${pageContext.request.contextPath}/mypage/orgUpdt.do" onSubmit="fnInsert(document.forms[0]); return false;">

    <input type="hidden" id="type" name="type" value="${userManageVO.type}">
    <input type="hidden" id="chngGb" name="chngGb" value="${chngGb}">
    <input type="hidden" id="reqSeq" name="reqSeq" value="${jtpMberReqVO.reqSeq}">

    <div id="content">
        <section class="signup">
            <div class="container">

                <h3 class="subtitle">회원정보수정</h3>

                <div class="input">
                    <h4>
                        기존 정보
                    </h4>
                    <dl>
                        <dt><label for="orgNm">소속기업/기관</label></dt>
                        <dd>
                            <input id="temp_orgNm" name="temp_orgNm" class="with-button" type="text" readonly value="${userManageVO.orgNm}">
                            <input id="bfoJtpOrganCd" name="bfoJtpOrganCd" type="hidden" value="${userManageVO.jtpOrganCd}">
                            <input id="bfoJtpCloudId" name="bfoJtpCloudId" type="hidden" value="${userManageVO.jtpCloudId}">

                        </dd>
                        <c:if test="${chngGb!=0}">
                            <dt><label for="bfoJtpCloudId">서비스활용기관</label></dt>
                            <dd>
                                <span class="select-outline opt">
                                    <info:select name="tempbfoJtpCloudId" id="tempbfoJtpCloudId"  style="width:300px;" val="${userManageVO.jtpCloudId}" disabled="true" />
                                </span>
                            </dd>
                        </c:if>
                    </dl>
                    <h4>
                        요청 정보

                        <small>
                            * 표시는 필수 입력 항목입니다.
                        </small>
                    </h4>

                    <dl>
                        <dt><label for="orgNm">소속기업/기관</label><i>*</i></dt>
                        <dd>
                            <input id="orgNm" name="orgNm"  type="text" readonly>
                            <input id="orgCode" name="orgCode" type="hidden">
                            <input id="ownerNm" name="ownerNm" type="hidden">
                            <input id="zipNo" name="zipNo" type="hidden">
                            <input id="addr" name="addr" type="hidden">
                            <input id="detailAdres" name="detailAdres" type="hidden">
                            <input id="indutyCode" name="indutyCode" type="hidden">
                            <input id="aftrJtpOrganCd" name="aftrJtpOrganCd" type="hidden">

                            <c:if test="${count==0}">
                                <button type="button" id="btn_org_open" name="btn_org_open" onclick="orgOpen()">변경</button>
                            </c:if>


                        </dd>
                        <dt><label for="aftrJtpCloudId">서비스활용기관</label><i>*</i></dt>
                        <dd>
                        <span class="select-outline opt">
                            <info:select name="aftrJtpCloudId" id="aftrJtpCloudId"  style="width:300px;" val="${jtpMberReqVO.aftrJtpCloudId}"  />
                        </span>
                        </dd>

                        <c:if test="${count==0}">

                            <dt><label for="remark1">요청사유</label><i>*</i></dt>
                            <dd>
                                <input id="remark1" name="remark1"  type="text" maxlength="100">
                            </dd>

                        </c:if>
                        <c:if test="${count>0}">
                            <dt><label for="remark2">취소사유</label><i>*</i></dt>
                            <dd>
                                <input id="remark2" name="remark2"  type="text" maxlength="100">
                            </dd>
                        </c:if>
                    </dl>
                </div>

                <div class="btn-set center big">
                    <button class="btn" type="button" onclick="history.back()" >뒤로가기</button>

                    <c:if test="${count==0}">
                        <button class="btn main" type="submit" id="btn_title" name="btn_title">

                            <c:if test="${chngGb==0}">
                                전환요청
                            </c:if>

                            <c:if test="${chngGb==1}">
                                변경요청
                            </c:if>

                        </button>
                    </c:if>
                    <c:if test="${count>0}">
                        <button class="btn main" type="button" id="btn_cancel" onclick="cancel()"  name="btn_cancel">
                            <c:if test="${chngGb==0}">
                                전환취소
                            </c:if>

                            <c:if test="${chngGb==1}">
                                변경취소
                            </c:if>
                        </button>
                    </c:if>
                </div>

            </div>
        </section>

    </div>



</form>
<!-- E:mainContent -->