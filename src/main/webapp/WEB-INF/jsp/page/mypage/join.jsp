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







    });




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
                    }else{
                        //사용가능한 아이디입니다.

                        if(!returnData.checkId){
                            alert("사용할수 없는 아이디입니다.");
                        }else{

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


        if(!$("#email").val()){
            alert('이메일 입력하세요.');
            return;
        }else{


            if (CheckEmail($("#email").val())) {

            }else {
                alert('잘못된 이메일입니다');
                return;
            }

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


        if(!$("select[name=passwordHint]").val()){

            alert("질문을 선택하세요.");
            return;
        }


        if(!$("#passwordCnsr").val()){

            alert("질문 답변 입력하세요.");
            return;
        }





        $.ajax({
            type:"POST",
            url:"<c:url value='/mypage/checkEmail.do' />",
            data:{
                "email": $("#email").val()
            },
            dataType:'json',
            timeout:(1000*30),
            success:function(returnData, status){

                if(status == "success") {

                    if(returnData.usedCnt > 0 ){
                        //사용할수 없는 아이디입니다.
                        alert("이미 사용하는  이메일 주소 입니다.");
                        return;
                    }else{
                        if(!returnData.checkId){
                            alert("이미 사용하는  이메일 주소 입니다.");
                            return;
                        }else{


                            if(confirm("회원가입 하시겠습니까?")){
                                form.submit();
                            }

                        }
                    }
                }else{ alert("사용하는 이메일 체크중 오류가 발생하였습니다. ");return;}
            }
        });







    }



    function CheckEmail(str){

        var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

        if(!reg_email.test(str)) {

            return false;
        }else {

            return true;
         }
    }




</script>



<!-- S:mainContent -->
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>

            <!-- <a href="">소개<i class='bx bx-chevron-right' ></i></a> -->
            <strong>회원가입</strong>


        </div>
    </section>



    <section class="sub-content">

        <form name="siteJoinVO" method="post" action="${pageContext.request.contextPath}/mypage/mberInsert.do" onSubmit="fnInsert(document.forms[0]); return false;">
            <input type="hidden" id="tempId" name="tempId">
            <input type="hidden" id="idCheck" name="idCheck" value="N">
            <input type="hidden" id="type" name="type" value="GNR">

        <div class="container">



            <h4 class="stitle">회원가입</h4>
            <article class="sign">
                <h5>
                    회원 기본정보 입력
                    <small>
                        * 표시는 필수 입력 항목입니다.
                    </small>
                </h5>
                <dl>

                    <dt><label for="id">아이디</label> <i>*</i></dt>
                    <dd>
                        <input id="id" name="id" class="with-button" type="text" placeholder="영문+숫자 조합 6~12자리">
                        <button class="check-id" type="button" id="btn_idCheck" name="btn_idCheck" onclick="fn_id_check()">아이디 중복확인</button>
                    </dd>

                    <dt><label for="passwd">비밀번호</label> <i>*</i></dt>
                    <dd>
                        <input id="passwd" name="passwd" type="password" placeholder="영문+숫자+특수문자로 조횝된 8~16자리" maxlength="20">
                    </dd>

                    <dt><label for="passwdCheck">비밀번호 확인</label> <i>*</i></dt>
                    <dd>
                        <input id="passwdCheck" name="passwdCheck" type="password" placeholder="설정하신 비밀번호를 한번 더 입력해주세요." maxlength="20">
                    </dd>

                    <dt><label for="name">이름</label> <i>*</i></dt>
                    <dd>
                        <input id="name" name="name" type="text" placeholder="홍길동" maxlength="30">
                    </dd>
                    <dt>성별 <i>*</i></dt>
                    <dd>
                        <input id="type01" name="sexdstnCode" type="radio" value="M" checked ><label for="type01">남자</label>
                        <input id="type02" name="sexdstnCode" type="radio" value="F"><label for="type02">여자</label>

                    </dd>

                    <dt><label for="email">이메일</label> <i>*</i></dt>
                    <dd>
                        <input type="text" id="email" name="email"  placeholder="이메일 주소 입력 @ 확인" class="with-mail" maxlength="30">
                        <%--<span>@</span>--%>
                        <%--<select id="mail" name="mail">--%>
                            <%--<option value="">직접입력(선택)</option>--%>
                        <%--</select>--%>

                    </dd>

                    <dt><label for="phone1">휴대전화</label> <i>*</i></dt>
                    <dd>
                        <input id="phone1" type="text" class="tel" maxlength="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                        <u>-</u>
                        <input id="phone2" type="text" class="tel" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
                        <u>-</u>
                        <input id ="phone3" type="text" class="tel" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">

                    </dd>

                    <dt>
                        <label for="find-text">질문</label><i>*</i>
                    </dt>
                    <dd>
                        <select id="find-text" id="passwordHint" name="passwordHint">
                            <option value="">선택하세요</option>
                            <c:forEach items="${codeList}" var="item" varStatus="status">
                            <option value="${item.code}">${item.codeNm}</option>
                            </c:forEach>





                        </select>
                        <span class="info">비밀번호 찾기에 활용됩니다.</span>
                    </dd>
                    <dt>
                        <label for="passwordCnsr">답변</label><i>*</i>
                    </dt>
                    <dd>
                        <input id="passwordCnsr" name="passwordCnsr" type="text" maxlength="20">
                    </dd>

                    <%--<dt>메일 수신여부 <i>*</i></dt>--%>
                    <%--<dd>--%>
                        <%--<input id="sendMail"  name="sendMail" type="checkbox"><label for="sendMail">메일을 받겠습니다.</label>--%>
                    <%--</dd>--%>

                    <%--<dt>SMS 수신여부 <i>*</i></dt>--%>
                    <%--<dd>--%>
                        <%--<input id="sendSms" name="sendSms" type="checkbox"><label for="sendSms">SMS를 받겠습니다.</label>--%>
                    <%--</dd>--%>

                </dl>
                <button class="btn main">회원가입</button>
            </article>
        </div>

        </form>
    </section>

</div>
<!-- E:mainContent -->


















