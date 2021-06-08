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

        selectBoxCloudIdInit('${userManageVO.jtpOrganCd}');
        initView();

        $("input[name=id]").keyup(function(event){

            if (!(event.keyCode >=37 && event.keyCode<=40)) {

                var inputVal = $(this).val();

                $(this).val(inputVal.replace(/[^a-z0-9]/gi,''));
            }
        });



        $("#btn_pw_change").click(function(){

            if($("#newPassword").val() != $("#newPassword2").val() ){
                $("#pwmsg2").text("입력하신 새 비밀번호가 일치하지 않습니다.");
                return;
            }

            var pw = $("#newPassword").val();
            var num = pw.search(/[0-9]/g);
            var eng = pw.search(/[a-z]/ig);
            var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

            if (pw.length < 8 || pw.length > 20) {
                alert("8자리 ~ 20자리 이내로 입력해주세요.");
                return false;
            } else if (pw.search(/\s/) != -1) {
                alert("비밀번호는 공백 없이 입력해주세요.");
                return false;
            } else if (num < 0 || eng < 0 || spe < 0) {
                alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
                return false;
            } else {

            }

            var p = {
                newPassword: $("#newPassword").val(),
                newPassword2: $("#newPassword2").val(),
                oldPassword: $("#oldPassword").val()
            };

            $.ajax({
                type: "GET",
                url: "/mypage/mberPasswordUpdt.do",
                dataType: "json",
                data: p,
                error: function () {
                    alert("처리중 실패 하였습니다.");
                },
                success: function (data) {
                 //   console.log(data);
                    if(data.info.successCode=='9999'){
                        $("#pwmsg").text("비밀번호가 일치하지 않습니다.");
                        return;
                    }else if(data.info.successCode=='7777'){
                        alert("로그인 다시하셔야 합니다.");
                        return;
                    }else if(data.info.successCode=='8888'){
                        $("#pwmsg").text("비밀번호 관련 관리자 문의 바랍니다");
                        return;
                    }else{
                        alert("비빌번호가 변경되었습니다");
                        $("#modal-passwd").removeClass("on");
                    }
                }
            });
        });

        $("button.check-zeus").click(function() {
            $("#modal-zeus").addClass("on");
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
                url: "/jtp/cms/rese/zeus/reqCertify.do",
                data: param,
                error: function () {
                    alert("처리중 실패 하였습니다.");
                },
                success: function (data) {
                    var errors = data.RESULT.errors;

                    if (errors != null) {
                        alert(errors[0].code + ":" + errors[0].message);
                        return;
                    }

                    var result = data.RESULT.result;

                    if (result.codes[0].code.code == "E0100") {
                        alert("인증번호 요청이 성공했습니다. 인증번호 입력 후 확인을 클릭하세요.");
                        return;
                    }

                    if (result.codes[0] != null) {
                        alert(result.codes[0].code.code + ":" + result.codes[0].code.message);
                        return;
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
                url: "/jtp/cms/rese/zeus/reqVerify.do",
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
                        $(".info").html("ZEUS 회원 인증이 완료되었습니다. 수정버튼을 클릭하세요.");
                    } else {
                        alert(result.codes[0].code.code + ":" + result.codes[0].code.message);
                    }
                }
            });
        });

    });


    /*********************************************************
     * 수정처리
     ******************************************************** */
    function fnInsert(form) {

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
        } else {

            if (CheckEmail($("#email").val())) {

            }
            else {
                alert('잘못된 이메일입니다');
                return;
            }
        }

        if(confirm("회원정보 수정 하시겠습니까?")){
            form.submit();
        }
    }


    function CheckEmail(str) {
        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if(!reg_email.test(str)) {
            return false;
        }

        return true;
    }


    function fn_open_pw(){
        $("#pwmsg").text("");
        $("#pwmsg2").text("");
        $("#modal-passwd").addClass("on");

        $("#oldPassword").val("");
        $("#newPassword").val("");
        $("#newPassword2").val("");
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


    function initView() {

        <%--var type ='${userManageVO.type}';--%>

        <%--if(type=='ENT'){--%>
            <%--$("#btn_org_open").text("변경요청");--%>
        <%--}else{--%>

            <%--$("#btn_org_open").text("변경");--%>

        <%--}--%>


    }


</script>


<!--  Modal ZEUS -->
<div id="modal-zeus" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <h2>ZEUS 인증하기<button class="close">닫기</button></h2>
        <div class="inside">

            <div class="zeus-check">
                <input type="text" id="zeusUserId" name="zeusUserId" placeholder="ZEUS ID를 입력하세요">
                <input type="text" id="userNm" name="userNm" value="${userManageVO.name}" readonly>
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



<!--  Modal passwd -->
<div id="modal-passwd" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <h2>비밀번호 변경하기<button class="close">닫기</button></h2>
        <div class="inside">

            <div class="passwd-check">
                <input type="password" id="oldPassword" name="oldPassword" placeholder="현재 비밀번호를 입력하세요">
                <span id="pwmsg" name="pwmsg"></span>
                <input type="password" id="newPassword" name="newPassword" placeholder="새 비밀번호를 입력하세요">
                <input type="password" id="newPassword2" name="newPassword2" placeholder="새 비밀번호를 다시한번 입력하세요">
                <span id="pwmsg2" name="pwmsg2"></span>
            </div>

            <div class="btn-set center full">
                <button class="btn main" type="button" id="btn_pw_change" name="btn_pw_change">확인</button>
            </div>
        </div>
    </div>
</div>
<!-- Modal-->



<!-- S:mainContent -->
<form name="siteJoinVO" method="post" action="${pageContext.request.contextPath}/mypage/mberUpdt.do" onSubmit="fnInsert(document.forms[0]); return false;">
    <input type="hidden" id="uniqId" name="uniqId" value="${userManageVO.uniqId}">
    <input type="hidden" id="type" name="type" value="${userManageVO.type}">

    <div id="content">
        <section class="signup">
            <div class="container">

                <h3 class="subtitle">회원정보수정</h3>

                <div class="input">
                    <h4>
                        회원정보

                        <small>
                            * 표시는 필수 입력 항목입니다.
                        </small>
                    </h4>

                    <dl>
                        <dt>이름</dt>
                        <dd>
                            <strong>${userManageVO.name}</strong>
                        </dd>
                        <dt>아이디</dt>
                        <dd>
                            <strong>${userManageVO.id}</strong>
                        </dd>

                        <dt><label for="btn_passwd">비밀번호 변경</label></dt>
                        <dd>
                            <button  type="button" id="btn_passwd" name="btn_passwd" onclick="fn_open_pw()">비밀번호 변경하기</button>
                        </dd>

                        <dt>회원분류 <i>*</i></dt>
                        <dd>

                            <strong> <c:if test="${userManageVO.type =='GNR'}" >일반(기관) </c:if>
                                     <c:if test="${userManageVO.type =='ENT'}" >장비 담당자 </c:if>
                            </strong>
                            <c:if test="${userManageVO.type=='GNR'}">
                                <button  type="button" id="btn_change_type" name="btn_change_type" onclick="location.href='/mypage/orgModify.do?chngGb=0'">장비 담당자 회원전환</button>
                            </c:if>


                        </dd>

                        <dt><label for="email">이메일</label> <i>*</i></dt>
                        <dd>
                            <input type="text" class="with-mail" id="email" name="email" value="${userManageVO.email}" >
                        </dd>

                        <dt><label for="phone1">휴대전화</label> <i>*</i></dt>
                        <dd>
                            <input type="text" id="phone1" name="phone1" value="${userManageVO.phone1}" class="tel" maxlength="3" readonly onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="앞자리 숫자입력">
                            <u>-</u>
                            <input type="text" id="phone2" name="phone2" value="${userManageVO.phone2}" class="tel" maxlength="4"  readonly onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="중간자리 숫자입력">
                            <u>-</u>
                            <input type="text" id="phone3" name="phone3" value="${userManageVO.phone3}" class="tel" maxlength="4" readonly onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="끝자리 숫자입력">
                            <span class="info">진행상황 등을 SMS로 알려드립니다.</span>
                        </dd>

                        <dt><label for="tel1">일반전화</label></dt>
                        <dd>
                            <input type="text" id="tel1" name="tel1" value="${userManageVO.tel1}" class="tel" maxlength="3"  onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="앞자리 숫자입력">
                            <u>-</u>
                            <input type="text" id="tel2" name="tel2" value="${userManageVO.tel2}" class="tel" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="중간자리 숫자입력">
                            <u>-</u>
                            <input type="text" id="tel3" name="tel3" value="${userManageVO.tel3}" class="tel" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="끝자리 숫자입력">

                        </dd>

                        <dt><label for="zeusId">ZEUS 회원번호</label></dt>
                        <dd>
                            <input type="hidden" id="zeusCertiYn" name="zeusCertiYn" value="${userManageVO.zeusCertiYn}" />
                            <c:if test="${userManageVO.zeusCertiYn == 'N'}">
                                <input id="zeusId" name="zeusId" class="with-button danger" type="text" readonly>
                                <button class="check-zeus" type="button">ZEUS 인증하기</button>
                                <span class="info">ZEUS 회원 인증을 해야 ZEUS와 연동이 가능합니다.</span>
                            </c:if>

                            <c:if test="${userManageVO.zeusCertiYn == 'Y'}">
                                <input id="zeusId" name="zeusId" class="with-button ok" type="text" value="${userManageVO.zeusId}" readonly>
                                <span class="info">ZEUS 회원 인증이 완료되었습니다.</span>
                            </c:if>
                        </dd>
                    </dl>

                    <dl>
                        <dt><label for="orgNm">소속기업/기관</label><c:if test="${userManageVO.type =='ENT'}" ><i>*</i></c:if></dt>
                        <dd>
                            <input id="orgNm" name="orgNm" class="with-button" type="text" readonly value="${userManageVO.orgNm}">
                            <input id="orgCode" name="orgCode" type="hidden">
                            <input id="ownerNm" name="ownerNm" type="hidden">
                            <input id="zipNo" name="zipNo" type="hidden">
                            <input id="addr" name="addr" type="hidden">
                            <input id="detailAdres" name="detailAdres" type="hidden">
                            <input id="indutyCode" name="indutyCode" type="hidden">
                            <input id="jtpOrganCd" name="jtpOrganCd" type="hidden" value="${userManageVO.jtpOrganCd}">

                            <c:if test="${userManageVO.type=='GNR'}">
                                <button type="button" id="btn_org_open" name="btn_org_open" onclick="orgOpen()">변경</button>
                            </c:if>
                            <c:if test="${userManageVO.type=='ENT'}">
                                <button type="button" id="btn_org_open" name="btn_org_open" onclick="location.href='/mypage/orgModify.do?chngGb=1' ">변경요청</button>
                            </c:if>
                        </dd>
                        <c:if test="${userManageVO.type=='ENT'}">
                            <div id="jtpCloudIdView" name="jtpCloudIdView">
                                <dt><label for="jtpCloudId">서비스활용기관</label><i>*</i></dt>
                                <dd>
                                <span class="select-outline opt">
                                    <info:select name="jtpCloudId" id="jtpCloudId"  style="width:300px;" val="${userManageVO.jtpCloudId}" disabled="true" />
                                </span>
                                </dd>
                            </div>
                        </c:if>
                    </dl>


                </div>

                <div class="btn-set center big">
                    <button class="btn" type="button" onclick="location.href='<c:url value='/mypage/view.do' />'" >취소</button>
                    <button class="btn main" type="submit">수정</button>
                </div>

            </div>
        </section>

    </div>



</form>
<!-- E:mainContent -->