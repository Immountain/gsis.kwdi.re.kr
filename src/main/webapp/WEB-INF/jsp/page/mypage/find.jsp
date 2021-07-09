<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>





<script type="text/javaScript" language="javascript">



    $(document).ready(function(){








        $("#btn_pw_close").click(function(){


            $(".password-pop").removeClass("on");
            onchangeRadio();


        });

        $("#btn_new_pw").click(function(){



            if($("#newPassword").val() != $("#rePassword").val() ){
                alert("비밀번호 확인 일치 하지 않습니다.");
                return;
            }



            var pw = $("#newPassword").val();
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



            var userNm = $("#pwuserNm").val();
            var userId = $("#userId").val();
            var pwemaill = $("#pwemaill").val();
            var passwordHint = $("select[name=passwordHint]").val();
            var passwordCnsr = $("#passwordCnsr").val();
            var password =$("#newPassword").val();

            var p = {
                userNm:userNm,
                userId:userId,
                email:pwemaill,
                passwordHint:passwordHint,
                passwordCnsr:passwordCnsr,
                password:password,
                userSe:"USR"

            };



            $.ajax({
                type: "GET",
                url: "/mypage/mberNewPassword.do",
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
                        alert("패스워드가 변경됬습니다");

                        $(".password-pop").removeClass("on");
                        onchangeRadio();

                        return;
                    }else{
                        alert("관리자 문의 바랍니다");
                        return;
                    }
                }
            });





        });






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

           // $(".download-pop").addClass("on");


            var userNm = $("#pwuserNm").val();
            var userId = $("#userId").val();
            var pwemaill = $("#pwemaill").val();
            var passwordHint = $("select[name=passwordHint]").val();
            var passwordCnsr = $("#passwordCnsr").val();

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

            if(!passwordHint){

                alert("질문을 선택하세요")
                return;
            }
            if(!passwordCnsr){

                alert("답변 입력하세요")
                return;
            }


            var p = {
                userNm:userNm,
                userId:userId,
                email:pwemaill,
                passwordHint:passwordHint,
                passwordCnsr:passwordCnsr

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

                        $(".password-pop").addClass("on");

                       // alert(data.info.info);

                       // onchangeRadio();

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
        $('#passwordCnsr').val("");
        $("select[name=passwordHint]").val("");



    }


</script>



<!-- S:mainContent -->
<div id="content" class="sub">


    <section class="sub-navigation">
        <div class="container">

            <a class="home" href="/">홈으로 <i class='bx bxs-home'></i></a>

            <!-- <a href="">소개<i class='bx bx-chevron-right' ></i></a> -->
            <strong>아이디/비밀번호찾기</strong>


        </div>
    </section>


    <section class="sub-content">
        <div class="container">

            <h4 class="stitle">아이디/비밀번호찾기</h4>

            <article class="find">
                <fieldset>
                    <legend class="sr-only">찾기 폼</legend>

                    <div class="find-type">
                        <input name="type" id="type01" type="radio" onchange="onchangeRadio()" checked><label for="type01">아이디 찾기</label>
                        <input name="type" id="type02" type="radio" onchange="onchangeRadio()"><label for="type02">비밀번호 찾기</label>
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


                        </dl>
                        <div class="btn-set center full">
                            <button class="btn main" type="button" id="btn_id_find" name="btn_id_find">확인</button>

                            <%--<div class="result">--%>
                                <%--회원님의 아이디는--%>
                                <%--<span><strong>adbs123</strong> 입니다.</span>--%>
                            <%--</div>--%>
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
                            <dt>
                                <label for="passwordHint">질문</label>
                            </dt>
                            <dd>
                                <select id="passwordHint"  name="passwordHint">
                                    <option value="">선택하세요</option>
                                    <c:forEach items="${codeList}" var="item" varStatus="status">
                                        <option value="${item.code}">${item.codeNm}</option>
                                    </c:forEach>

                                </select>
                            </dd>
                            <dt>
                                <label for="passwordCnsr">답변</label>
                            </dt>
                            <dd>
                                <input id="passwordCnsr" name="passwordCnsr" type="text">
                            </dd>


                        </dl>
                        <div class="btn-set center full">
                            <button class="btn main" type="button" id="btn_pw_find" name="btn_pw_find">확인</button>
                            <%--<div class="result">--%>
                                <%--비밀번호는--%>
                                <%--<span><strong>adbs123</strong> 입니다.</span>--%>
                            <%--</div>--%>
                        </div>
                    </div>

                </fieldset>

            </article>


        </div>
    </section>
</div>
<!-- E:mainContent -->

<!-- S:popup 팝업창 입니다.-->
<div class="password-pop">
    <div class="outline">
        <h3>
            비밀번호 변경
        </h3>

        <h4>
            새로운 비밀번호를 입력해주세요
        </h4>

        <div class="input-outline">
            <label for="newPassword">패스워드 입력</label>
            <input type="password" id="newPassword" name="newPassword" placeholder="새 비밀번호 입력">
            <label for="rePassword">패스워드 재입력</label>
            <input type="password" id="rePassword" name="rePassword" placeholder="새 비밀번호 재입력">


        </div>


        <button class="ok" type="button" name="btn_new_pw" id="btn_new_pw">비밀번호 변경</button>
        <button class="close" type="button" id="btn_pw_close" name="btn_pw_close">취소하기</button>

    </div>
</div>
<!-- E:popup -->