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


        $("#jtpCloudIdView").hide();

        $("input[name=id]").keyup(function(event){

            if (!(event.keyCode >=37 && event.keyCode<=40)) {

                var inputVal = $(this).val();

                $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
             }
            });


        $("input[name='type']:radio").change(function () {
            //라디오 버튼 값을 가져온다.
            var serviceType = this.value;

            if(serviceType == "GNR"){//일반

                $("#orgNm").val("");
                $("#orgCode").val("");
                $("#ownerNm").val("");
                $("#addr").val("");
                $("#detailAdres").val("");
                $("#indutyCode").val("");
                $("#zipNo").val("");
                $("#jtpOrganCd").val("");
                $("#jtpCloudIdView").hide();
                $("#organPilsu").hide();

            }else if(serviceType == "ENT"){//기업

                $("#orgNm").val("");
                $("#orgCode").val("");
                $("#ownerNm").val("");
                $("#addr").val("");
                $("#detailAdres").val("");
                $("#indutyCode").val("");
                $("#zipNo").val("");
                $("#jtpOrganCd").val("");
                $("#jtpCloudIdView").show();
                $("#organPilsu").show();



            }

        });


        $("#searchKeyword").keydown(function(key) {

            if (key.keyCode == 13) {

                orgAjaxList();
            }

        });

        // ZEUS 인증번호 요청
        $("#btnRequestCerti").click(function(e) {
            e.preventDefault();

            if ($("#zeusUserId").val() == "") {
                alert("ZEUS ID를 입력하세요.");
                return;
            }

            var param = new Object();
            param.key = "bead8283-0d02-4edd-b615-d82d84df739f"; // 하드코딩해야 하나..? 용암해수
            param.zeusUserId = $("#zeusUserId").val();
            param.userNm = $("#userNm").val();
            param.organUserId = $("#zeusUserId").val();

            $.ajax({
                type: "GET",
                url: "/mypage/reqCertify.do",
                data: param,
                error: function () {
                    alert("처리중 실패 하였습니다.");
                },
                success: function (data) {
                    var result = data.RESULT.result;

                    if (result.codes[0].code.code == "E0100") {
                        alert("인증번호 요청이 성공했습니다. 인증번호 입력 후 확인을 클릭하세요.");
                        return;
                    } else {
                        alert(result.codes[0].code.code + ":" + result.codes[0].code.message);
                    }
                }
            });
        });


        // ZEUS 인증확인
        $("#btnConrimCerti").click(function(e) {
            e.preventDefault();

            if ($("#zeusUserId").val() == "") {
                alert("ZEUS ID를 입력하세요.");
                return;
            }

            if ($("#certKey").val() == "") {
                alert("인증번호를 입력하세요.");
                return;
            }

            var param = new Object();
            param.key = "bead8283-0d02-4edd-b615-d82d84df739f"; // 하드코딩해야 하나..? 용암해수
            param.zeusUserId = $("#zeusUserId").val();
            param.userNm = $("#userNm").val();
            param.organUserId = $("#zeusUserId").val();
            param.certKey = $("#certKey").val();

            $.ajax({
                type: "GET",
                url: "/mypage/reqVerify.do",
                data: param,
                error: function () {
                    alert("처리중 실패 하였습니다.");
                },
                success: function (data) {
                    var result = data.RESULT.result;

                    if (result.codes[0].code.code == "E0100") {
                        alert("인증이 완료되었습니다.");

                        $("#modal-zeus").removeClass("on");
                        $("#zeusCertiYn").val("Y");

                        $("#zeusId").removeClass("danger");
                        $("#zeusId").addClass("ok");
                        $("#zeusId").val(result.data.zeusUserId);

                        $(".check-zeus").css("display", "none");
                        $(".info").html("ZEUS 회원 인증이 완료되었습니다.");
                    } else {
                        alert(result.codes[0].code.code + ":" + result.codes[0].code.message);
                    }
                }
            });
        });





    });





    function fn_open_idCheck(){

        $('#span_id').html('');
        $("#modalUserId").val('');
        $('#btn_search_id').html('확인');
        $('#tempId').val();
        $('#idCheck').val('N');
        $("#modal-id").addClass("on");

    }


    /*********************************************************
     * 아이디 체크 AJAX
     ******************************************************** */
    function fn_id_check(){

        if($('#idCheck').val()=='Y'){
            $('#id').val($('#tempId').val());
            $("#modal-id").removeClass("on");
            return;
        }

        var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
        if( !idReg.test( $("input[name=modalUserId]").val() ) ) {
            alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
            return;
        }

        $.ajax({
            type:"POST",
            url:"<c:url value='/mypage/checkUserId.do' />",
            data:{
                "checkId": $("#modalUserId").val()
            },
            dataType:'json',
            timeout:(1000*30),
            success:function(returnData, status){


                console.log(returnData);

                if(status == "success") {

                    if(returnData.usedCnt > 0 ){
                        //사용할수 없는 아이디입니다.
                       alert("사용할수 없는 아이디입니다.");
                        $('#idCheck').val('N');
                    }else{
                        //사용가능한 아이디입니다.

                        if(!returnData.checkId){
                        $('#span_id').html('사용할수 없는 아이디입니다');
                            $('#btn_search_id').html('확인');
                        }else{


                            $('#btn_search_id').html('사용');
                            $('#span_id').html('사용가능한 아이디입니다.');
                            $('#tempId').val(returnData.checkId)
                            $('#idCheck').val('Y');
                        }



                    }
                }else{ alert("ERROR!");return;}
            }
        });
    }



    /*********************************************************
     * 아이디 체크 AJAX
     ******************************************************** */
    function fn_id_checkTpe2(){


        if($('#idCheck').val()=='Y'){

            $('#idCheck').val('N');
            $('#tempId').val("");
        }



        // if($('#idCheck').val()=='Y'){
        //     $('#id').val($('#tempId').val());
        //     $("#modal-id").removeClass("on");
        //     return;
        // }


        ///^[a-z0-9][a-z0-9_\-]{4,19}$/;


        var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
        if( !idReg.test( $("input[name=id]").val() ) ) {
            alert("아이디는 영문자로 시작하는 6~20자 영문자 또는 숫자이어야 합니다.");
            return;
        }



        $.ajax({
            type:"POST",
            url:"<c:url value='/mypage/checkUserId.do' />",
            data:{
                "checkId": $("#id").val()
            },
            dataType:'json',
            timeout:(1000*30),
            success:function(returnData, status){


                console.log(returnData);

                if(status == "success") {

                    if(returnData.usedCnt > 0 ){
                        //사용할수 없는 아이디입니다.
                        alert("사용할수 없는 아이디입니다.");
                        $('#idCheck').val('N');
                        return;
                    }else{
                        //사용가능한 아이디입니다.

                        if(!returnData.checkId){

                          alert("사용할수 없는 아이디입니다");
                          return;
                            // $('#span_id').html('사용할수 없는 아이디입니다');
                            // $('#btn_search_id').html('확인');
                        }else{


                         //   $('#btn_search_id').html('사용');
                         //   $('#span_id').html('사용가능한 아이디입니다.');
                            alert("사용가능한 아이디입니다.");
                            $('#tempId').val(returnData.checkId)
                            $('#idCheck').val('Y');
                        }



                    }
                }else{ alert("ERROR!");return;}
            }
        });
    }



    /*********************************************************
     * 저장처리
     ******************************************************** */
    function fnInsert(form){



        if(!$("#id").val()){

            alert("아이디을 입력하세요.");
            return;
        }

        if($("#idCheck").val()=='N'){

            alert("아이디 중복확인 바랍니다.");
            return;
        }



        if($("#id").val()!=$("#tempId").val()){

            alert("아이디 중복확인 바랍니다.");
            return;
        }




        if(!$("#name").val()){

            alert("이름을 입력하세요.");
            return;
        }

        if($("#passwd").val() != $("#passwdCheck").val() ){
            alert("비밀번호 확인 일치 하지 않습니다.");
            return;
        }



        var pw = $("#passwd").val();
        var num = pw.search(/[0-9]/g);
        var eng = pw.search(/[a-z]/ig);
        var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

        if (pw.length < 8 || pw.length > 20) {

            alert("비밀번호는 8자리 ~ 20자리 이내로 입력해주세요.");
            return false;
        } else if (pw.search(/\s/) != -1) {
            alert("비밀번호는 공백 없이 입력해주세요.");
            return false;
        } else if (num < 0 || eng < 0 || spe < 0) {
            alert("비빌번호는 영문,숫자, 특수문자를 혼합하여 입력해주세요.");
            return false;
        } else {
            console.log("통과");

        }

        if($('input:radio[name=type]').is(':checked')==false){

            alert("회원분류 선택하세요.");
            return;

        }
        if(!$("#phone1").val()){

            alert("휴대전호 앞자리 입력하세요.");
            return;
        }
        if(!$("#phone2").val()){

            alert("휴대전호 중간자리 입력하세요.");
            return;
        }

        if(!$("#phone3").val()){

            alert("휴대전호 끝자리 입력하세요.");
            return;
        }

        // if(!$("#tel1").val()){
        //
        //     alert("일반전화 앞자리 입력하세요.");
        //     return;
        // }
        // if(!$("#tel2").val()){
        //
        //     alert("일반전화 중간자리 입력하세요.");
        //     return;
        // }
        //
        // if(!$("#tel3").val()){
        //
        //     alert("일반전화 끝자리 입력하세요.");
        //     return;
        // }


        if(!$("#email").val()){
            alert('이메일 입력하세요.');
            return;
        }else{


            if (CheckEmail($("#email").val())) {

            }
            else {
                alert('잘못된 이메일입니다');
                return;
            }

        }



        var radioVal = $('input[name="type"]:checked').val();
            if(radioVal=='ENT'){


                if(!$("#jtpOrganCd").val()){

                    alert('소속기업/기관 선택하세요');
                    return;
                }


                var jtpCloudId =$("#jtpCloudId option:selected").val();
                if(!jtpCloudId){

                    alert("기관횐원 서비스활용기관 선택은 필수 입니다.");
                    return;
                }

             }
        if(confirm("회원가입 하시겠습니까?")){
            form.submit();
        }

    }



    function CheckEmail(str){

        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if(!reg_email.test(str)) {

            return false;
        }else {

            return true;
         }
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

                                    // console.log(data);
                                    // console.log(data.organNm)
                                    // console.log(data.ownerNm)
                                    // console.log(data.zipNo)
                                    // console.log(data.address1)
                                    // console.log(data.address2)
                                    // console.log(data.indutyCode)
                                    // console.log(data.busiRegistNo)


                                    $("#orgNm").val(data.organNm);
                                    $("#orgCode").val(data.busiRegistNo);
                                    $("#ownerNm").val(data.ownerNm);
                                    $("#addr").val(data.address1);
                                    $("#detailAdres").val(data.address2);
                                    $("#indutyCode").val(data.carrierTob);
                                    $("#zipNo").val(data.zipNo);
                                    $("#jtpOrganCd").val(data.jtpOrganCd);

                                    $("#modal-co").removeClass("on");




                                    selectBoxCloudIdInit(data.jtpOrganCd);


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


    function selectBoxCloudIdInit(organCd) {

        ajaxLoadSelect({
            url: "<c:url value='/mypage/selectCloudListByOrganCd.do' />",
            async : false,
            params: [
                {name: 'code', value: organCd}
            ],
            selectboxNm: 'jtpCloudId'
        });
    }


    function zeusOpen() {

        $("#modal-zeus").addClass("on");

    }

</script>
<section class="signup">


    <!--  Modal ZEUS -->
    <div id="modal-zeus" class="modal">

        <!-- Modal content -->
        <div class="modal-content">
            <h2>ZEUS 인증하기<button class="close">닫기</button></h2>
            <div class="inside">

                <div class="zeus-check">
                    <input type="text" id="zeusUserId" name="zeusUserId" placeholder="ZEUS ID를 입력하세요">
                    <input type="text" id="userNm" name="userNm" value="${pccVo.name}" readonly>
                    <button id="btnRequestCerti">인증번호 요청</button>
                    <input type="text" id="certKey" name="certKey" placeholder="인증번호를 입력하세요">
                </div>

                <div class="btn-set center full">
                    <button class="btn main" id="btnConrimCerti" name="btnConrimCerti">확인</button>
                </div>

                <div class="result">
                    <strong id="zeusCertiStatus">미인증 상태</strong>
                    <span id="remark">인증번호는 ZEUS에 등록된 핸드폰으로 전송됩니다. ZEUS에 등록된 ID와 이름이 상이할 경우 인증에 실패할 수 있습니다.</span>
                </div>

                <div class="btn-set center full zeus">
                    <button class="btn sub zeus" onclick="window.open('https://www.zeus.go.kr/user/findUserId');">ZEUS ID 찾기</button>
                </div>
            </div>
        </div>

    </div>


    <!--  Modal 아이디중복확인 -->
    <div id="modal-id" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <h2>아이디 중복확인 <button class="close">닫기</button></h2>
            <div class="inside">
                <div class="text-check">
                    <input type="text" id="modalUserId" name="modalUserId" maxlength="20">
                    <span id="span_id" name="span_id"></span>
                </div>
            </div>
            <div class="btn-set center full">
                <button class="btn sub" id="btn_search_id" name="btn_search_id" type="button" onclick="fn_id_check()">확인</button>
            </div>
        </div>

    </div>
    <!-- Modal-->




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







    <div class="container">

        <h3 class="subtitle">개인정보 입력</h3>

        <ul class="step">
            <li class="step01">
                <strong>STEP1</strong>
                약관동의
            </li>
            <li class="step02 active">
                <strong>STEP2</strong>
                개인정보 입력
            </li>
            <li class="step03">
                <strong>STEP3</strong>
                회원가입 완료
            </li>
        </ul>
        <form name="siteJoinVO" method="post" action="${pageContext.request.contextPath}/mypage/mberInsert.do" onSubmit="fnInsert(document.forms[0]); return false;">

            <input type="hidden" id="tempId" name="tempId">
            <input type="hidden" id="idCheck" name="idCheck" value="N">
            <input type="hidden" id="di" name="di" value="${pccVo.di}">
            <input type="hidden" id="reqNum" name="reqNum" value="${pccVo.reqNum}">
            <div class="input">
            <h4>
                회원정보

                <small>
                    * 표시는 필수 입력 항목입니다.
                </small>
            </h4>

            <dl>

                <dt><label for="id">아이디</label> <i>*</i></dt>
                <dd>
                    <input id="id" name="id" class="with-button" type="text" maxlength="20" placeholder="영문 또는 숫자 조합 6~20자리">
                    <button type="button" id="btn_idCheck" name="btn_idCheck" onclick="fn_id_checkTpe2()">아이디 중복확인</button>
                </dd>

                <dt><label for="name">이름</label> <i>*</i></dt>
                <dd>
                    <input id="name" name="name" type="text" placeholder="" maxlength="30" value="${pccVo.name}" readonly>
                </dd>
                <dt><label for="passwd">비밀번호</label> <i>*</i></dt>
                <dd>
                    <input id="passwd" name="passwd" type="password" placeholder="영문+숫자+특수문자로 조횝된 8~20자리" maxlength="20">
                    <span>영문+숫자+특수문자로 조횝된 8~20자리</span>
                </dd>

                <dt><label for="passwdCheck">비밀번호 확인</label> <i>*</i></dt>
                <dd>
                    <input id="passwdCheck" name="passwdCheck" type="password" placeholder="설정하신 비밀번호를 한번 더 입력해주세요." maxlength="20">
                </dd>

                <dt>회원분류 <i>*</i></dt>
                <dd>
                    <input id="type01" name="type" type="radio" value="GNR" checked><label for="type01">개인회원</label>
                    <input id="type02" name="type" type="radio" value="ENT"><label for="type02">기관회원</label>
                </dd>


                <dt><label for="phone1">휴대전화</label> <i>*</i></dt>
                <dd>
                    <input type="text" id="phone1" name="phone1" class="tel" maxlength="3" value="${pccVo.phone1}"  readonly onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="앞자리 숫자입력">
                    <u>-</u>
                    <input type="text" id="phone2" name="phone2" class="tel" maxlength="4" value="${pccVo.phone2}"  readonly onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="중간자리 숫자입력">
                    <u>-</u>
                    <input type="text" id="phone3" name="phone3" class="tel" maxlength="4" value="${pccVo.phone3}"  readonly onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="끝자리 숫자입력">
                    <span class="info">진행상황 등을 SMS로 알려드립니다.</span>
                </dd>

                <dt><label for="tel1">일반전화</label></dt>
                <dd>
                    <input type="text" id="tel1" name="tel1" class="tel" maxlength="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="앞자리 숫자입력">
                    <u>-</u>
                    <input type="text" id="tel2" name="tel2" class="tel" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="중간자리 숫자입력">
                    <u>-</u>
                    <input type="text" id="tel3" name="tel3" class="tel" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="끝자리 숫자입력">

                </dd>
                <dt><label for="email">이메일</label><i>*</i></dt>
                <dd>
                    <input type="text" id="email" name="email" class="with-mail" maxlength="30"  placeholder="이메일 주소 입력 @ 확인">
                </dd>
                <dt><label for="zeusId">ZEUS 회원번호</label></dt>
                <dd>
                    <input type="hidden" id="zeusCertiYn" name="zeusCertiYn" value="N" />
                    <input id="zeusId" name="zeusId" class="with-button danger" type="text" readonly>
                    <button class="check-zeus" type="button" onclick="zeusOpen()">ZEUS 인증하기</button>
                    <span class="info red">ZEUS 회원 인증을 하지 않을시 사이트 이용에 제한이 있을수 있음을 참고하여주세요. <a href="/page/manual" target="_blank"><i class='bx bx-info-circle'></i>자세히보기</a></span>
                </dd>


                <%--<input type="hidden" id="zeusCertiYn" name="zeusCertiYn" value="N" />--%>
                <dt><label for="orgNm">소속기업/기관</label><span id="organPilsu" style="display: none"><i>*</i></span></dt>
                <dd>
                    <input id="orgNm" name="orgNm" class="with-button" type="text" readonly>
                    <input id="orgCode" name="orgCode" type="hidden">
                    <input id="ownerNm" name="ownerNm" type="hidden">
                    <input id="zipNo" name="zipNo" type="hidden">
                    <input id="addr" name="addr" type="hidden">
                    <input id="detailAdres" name="detailAdres" type="hidden">
                    <input id="indutyCode" name="indutyCode" type="hidden">
                    <input id="jtpOrganCd" name="jtpOrganCd" type="hidden">
                    <button type="button" id="btn_org_open" name="btn_org_open" onclick="orgOpen()" >찾기</button>
                </dd>

                <div id="jtpCloudIdView" name="jtpCloudIdView">
                        <dt><label for="jtpCloudId">서비스활용기관</label><i>*</i></dt>
                        <dd>
                            <span class="select-outline opt">
                                <info:select name="jtpCloudId" id="jtpCloudId"  style="width:300px;"  />
                            <%--<select id="jtpCloudId" name="jtpCloudId">--%>
                                <%--<option value="">직접입력(선택)1111111111111</option>--%>
                                <%--<option value="">직접입력(선택)</option>Fselect-outline--%>
                                <%--<option value="">직접입력(선택)</option>--%>
                                <%--<option value="">직접입력(선택)</option>--%>
                                <%--<option value="">직접입력(선택)</option>--%>
                            <%--</select>--%>
                        </span>
                        </dd>
                </div>


            </dl>



        </div>


            <div class="btn-set center big">
                <button class="btn" type="button" onclick="history.back()">취소</button>
                <button class="btn main" type="submit">다음</button>
            </div>
        </form>



    </div>
</section>
