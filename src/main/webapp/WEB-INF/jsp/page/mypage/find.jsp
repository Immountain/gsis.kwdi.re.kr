<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>





<script type="text/javaScript" language="javascript">



    $(document).ready(function(){


        $("#idInfo").hide(); // Hide



        $("#btn_id_find").click(function(){
           var userNm = $("#userNm").val();
           if(!userNm){
                alert("이름을 입력하세요")
                return;
            }
            var emaill = $("#emaill").val();
            if(!emaill){
                alert("이메일 입력하세요")
                return;
            }
            var userNm = $("#userNm").val();
            var emaill = $("#emaill").val();

           var p = {
               userNm: userNm,
               email: emaill
            };
            $.ajax({
                type: "GET",
                url: "/mypage/getSiteUserIdFind.do",
                dataType: "json",
                data: p,
                error: function () {
                    alert("처리중 실패 하였습니다.");
                },
                success: function (data) {
                    //console.log(data);
                    if(data.info.successCode=='9999'){
                        alert("없는 사용자 정보 입니다");
                        return;

                    }else if(data.info.successCode=='8888'){
                       alert("관리자 문의 바랍니다");
                        return;
                    }else if(data.info.successCode=='0000'){
                        alert("아이디는["+data.info.info+"]입니다.");
                        return;
                    }else{
                        alert("관리자 문의 바랍니다");
                        return;
                    }
                }
            });
        });


        $("#btn_pw_find").click(function(){


            var userNm = $("#pwuserNm").val();
            var userId = $("#userId").val();
            var pwemaill = $("#pwemaill").val();



            if(!userNm){

                alert("이름을 입력하세요");
                return;
            }

            if(!userId){

                alert("아이디 입력하세요");
                return;
            }
            if(!pwemaill){

                alert("이메일 입력하세요")
                return;
            }

        var p = {
                userNm:userNm,
                userId:userId,
                email:pwemaill

            };
            $.ajax({
                type: "GET",
                url: "/mypage/getSiteUserPwFind.do",
                dataType: "json",
                data: p,
                error: function () {
                    alert("처리중 실패 하였습니다.");
                },
                success: function (data) {
                  //  console.log(data);
                    if(data.info.successCode=='9999'){
                        alert("없는 사용자 정보 입니다");
                        return;

                    }else if(data.info.successCode=='8888'){

                        alert("관리자 문의 바랍니다");
                        return;
                    }else if(data.info.successCode=='0000'){
                        alert(data.info.info);

                        onchangeRadio();

                        return;
                    }
                    else{
                        alert("관리자 문의 바랍니다");
                        return;

                    }
                }
            });
        });


    });


    function onchangeRadio() {



        $('#userNm').val("");
        $('#emaill').val("");




        $('#pwuserNm').val("");
        $('#userId').val("");
        $('#pwemaill').val("");




    }


</script>



<div id="content" class="sub sub01">

    <div class="sub-head">



        <!-- background-item -->
        <div class='waves'>
            <div class='wave -one'></div>
            <div class='wave -two'></div>
            <div class='wave -three'></div>
        </div>
        <div class="bubble" >
            <ul class="circles">
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
                <li></li>
            </ul>
        </div >


    </div>

    <div class="content-box">

        <article class="sub-title">
            <div class="container">
                <h2>
                    아이디/비밀번호 찾기
                </h2>

            </div>
        </article>


        <section class="find-id">
        <div class="container">

            <input type="hidden" id="info" name="info">

            <fieldset>
                <legend class="sr-only">로그인폼</legend>

                <div class="find-type">
                    <input name="type" id="type01" type="radio" onchange="onchangeRadio()" checked><label for="type01">아이디 찾기</label>
                    <input name="type" id="type02" type="radio" onchange="onchangeRadio()" ><label for="type02">비밀번호 찾기</label>
                </div>

                <div class="find-form form-id on">
                    <dl>
                        <dt>
                            <label for="userNm">이름</label>
                        </dt>
                        <dd>
                            <input id="userNm" name="userNm" type="text">
                        </dd>
                        <dt>
                            <label for="emaill">이메일</label>
                        </dt>
                        <dd>
                            <input id="emaill" name="emaill" type="text">
                        </dd>
                        <%--<dt>--%>
                            <%--<label for="phone1">휴대전화</label>--%>
                        <%--</dt>--%>
                        <%--<dd>--%>
                            <%--<input id="phone1" name="phone1" class="tel" type="text" maxlength="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>
                            <%--<u>-</u>--%>
                            <%--<input type="text" class="tel" id="phone2" name="phone2" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>
                            <%--<u>-</u>--%>
                            <%--<input type="text" class="tel" id="phone3" name="phone3" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>

                            <%--<button type="button" id="btn_verification" name="btn_verification">전송</button>--%>
                            <%--<input type="hidden" id="verificationYn" name="verificationYn" value="N">--%>
                        <%--</dd>--%>
                        <%--<dt>--%>
                            <%--<label for="verificationCode">인증번호</label>--%>
                        <%--</dt>--%>
                        <%--<dd>--%>
                            <%--<input id="verificationCode" name="verificationCode" type="text">--%>
                        <%--</dd>--%>
                    </dl>
                    <div class="btn-set center full">
                        <button class="btn main" type="button" id="btn_id_find" name="btn_id_find">확인</button>

                        <%--<strong>adbs123</strong> 입니다.--%>
                          <div class="result" id="idInfo" name="idInfo">
                            회원님의 아이디는
                            <span id="userIdInfo" ></span>
                        </div>
                    </div>
                </div>

                <div class="find-form form-pw">
                    <dl>
                        <dt>
                            <label for="pwuserNm">이름</label>
                        </dt>
                        <dd>
                            <input id="pwuserNm" name="pwuserNm" type="text">
                        </dd>
                        <dt>
                            <label for="userId">아이디</label>
                        </dt>
                        <dd>
                            <input id="userId" name="userId" type="text">
                        </dd>
                        <dt>
                            <label for="pwemaill">이메일</label>
                        </dt>
                        <dd>
                            <input id="pwemaill" name="pwemaill" type="text">
                        </dd>

                        <%--<dt>--%>
                            <%--<label for="pwphone1">휴대전화</label>--%>
                        <%--</dt>--%>
                        <%--<dd>--%>
                            <%--<input id="pwphone1" name="pwphone1" class="tel" type="text" maxlength="3" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>
                            <%--<u>-</u>--%>
                            <%--<input type="text" class="tel" id="pwphone2" name="pwphone2" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>
                            <%--<u>-</u>--%>
                            <%--<input type="text" class="tel" id="pwphone3" name="pwphone3" maxlength="4" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">--%>

                            <%--<button type="button" id="btn_pwverification" name="btn_verification">전송</button>--%>
                            <%--<input type="hidden" id="pwverificationYn" name="pwverificationYn" value="N">--%>
                        <%--</dd>--%>
                        <%--<dt>--%>
                            <%--<label for="pwverificationCode">인증번호</label>--%>
                        <%--</dt>--%>
                        <%--<dd>--%>
                            <%--<input id="pwverificationCode" name="pwverificationCode" type="text">--%>
                        <%--</dd>--%>
                    </dl>
                    <div class="btn-set center full">
                        <button class="btn main" type="button" id="btn_pw_find" name="btn_pw_find">확인</button>

                        <div class="result">
                            비밀번호는
                            <span><strong>해당 이메일로 전송 됩니다</strong></span>
                        </div>
                    </div>
                </div>

            </fieldset>





        </div>
    </section>

    </div>

</div>
<!-- E:mainContent -->