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
                        <input name="type" id="type01" type="radio" checked><label for="type01">아이디 찾기</label>
                        <input name="type" id="type02" type="radio"><label for="type02">비밀번호 찾기</label>
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

                            <div class="result">
                                회원님의 아이디는
                                <span><strong>adbs123</strong> 입니다.</span>
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
                            <dt>
                                <label for="find-text">질문</label>
                            </dt>
                            <dd>
                                <select id="find-text">
                                    <option>나의 좌우명은?</option>
                                </select>
                            </dd>
                            <dt>
                                <label for="find-answer">답변</label>
                            </dt>
                            <dd>
                                <input id="find-answer" name="answer" type="text">
                            </dd>


                        </dl>
                        <div class="btn-set center full">
                            <button class="btn main">확인</button>

                            <div class="result">
                                비밀번호는
                                <span><strong>adbs123</strong> 입니다.</span>
                            </div>
                        </div>
                    </div>

                </fieldset>

            </article>


        </div>
    </section>
</div>
<!-- E:mainContent -->