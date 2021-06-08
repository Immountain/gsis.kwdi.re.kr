<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javaScript" language="javascript">

     $(document).ready(function(){


         $("#checkall").change(function(){


             if($("#checkall").is(":checked")){
                 $("input:checkbox[id='check01']").prop("checked", true);
                 $("input:checkbox[id='check02']").prop("checked", true);
                 $("input:checkbox[id='check03']").prop("checked", true);

             }else{
                 $("input:checkbox[id='check01']").prop("checked", false);
                 $("input:checkbox[id='check02']").prop("checked", false);
                 $("input:checkbox[id='check03']").prop("checked", false);
             }
         });



       });


    ///mypage/joinStep2.do


    /*********************************************************
     * 인증이 끝나고 회원등록 페이지
     ******************************************************** */
    function checkJoin1(){


        location.href="/mypage/joinStep2.do"


    }


     function fnInsert(form){


         // 체크여부 확인
         if($("input:checkbox[name=check01]").is(":checked") == true&&$("input:checkbox[name=check02]").is(":checked") == true) {

             $('#checkYn').val('Y');
             form.submit();
          return true;

         }else{
             $('#checkYn').val('N');
             alert("필수 항목은 체크 하셔야 합니다.")

         }

     }


     function openPCCWindow(){
         var PCC_window = window.open('', 'PCCV3Window', 'width=400, height=630, resizable=1, scrollbars=no, status=0, titlebar=0, toolbar=0, left=300, top=200' );

         if(PCC_window == null){
             alert(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
         }


        // var aaa $('#reqInfo').val();


         document.reqPCCForm.action = 'https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10_v2.jsp';
         document.reqPCCForm.target = 'PCCV3Window';
         document.reqPCCForm.submit();


         return true;
     }


     function parent_function(di,reqNum){

         // 체크여부 확인
         if(!di) {
             alert("인증 오류.");
             return;
         }else{
             $('#di').val(di);
             $('#reqNum').val(reqNum);
             document.stepForm.submit();
             return true;

         }


     }


     function clickPcc() {

         if($("input:checkbox[name=check01]").is(":checked") == true&&$("input:checkbox[name=check02]").is(":checked") == true) {
             $('#checkYn').val('Y');
             $.ajax({
                 type : "get",
                 url : '/site/sns/setPccInfo.do',
                 dataType: "json",
                 async : false,
                 cache : false,
                 success : function(result) {

                     console.log(result);

                     $("#reqInfo").val(result.pcc.retInfo);
                     $("#retUrl").val(result.pcc.returnUrl);
                     openPCCWindow();
                 },
                 error : function(e) {
                     alert("오류가 발생하였습니다");
                 }
             });

         }else{
             $('#checkYn').val('N');
             alert("필수 항목은 체크 하셔야 합니다.")
         }
     }

</script>


<section class="signup">
    <div class="container">

        <h3 class="subtitle">약관동의</h3>

        <ul class="step">
            <li class="step01 active">
                <strong>STEP1</strong>
                약관동의
            </li>
            <li class="step02">
                <strong>STEP2</strong>
                개인정보 입력
            </li>
            <li class="step03">
                <strong>STEP3</strong>
                회원가입 완료
            </li>
        </ul>
        <form name="stepForm" method="post" action="${pageContext.request.contextPath}/mypage/joinStep2.do" onSubmit="fnInsert(document.forms[0]); return false;">
            <input type="hidden" id="checkYn" name="checkYn" value="N">
            <input type="hidden" id="di" name="di" value="">
            <input type="hidden" id="reqNum" name="reqNum" value="">
        <div class="agree">
            <p class="check all">
                <input id="checkall" name="checkall" type="checkbox"><label for="checkall">전체동의</label>
            </p>

            <div class="terms">

                <h4>이용약관 <small>(필수)</small></h4>

                <div class="textarea">

<pre>
제1조 (목적)
이 약관은 제주테크노파크에서 운영하는 인터넷사이트(이하 "갑"라 한다)이에서 제공하는 인터넷관련 서비스 (이하 "서비스"라 한다)를 이용하는 조건, 절차에 관한 사항 또는 기타 필요 사항을 규정함을 목적으로 합니다.

제2조 (용어의 정의)
1."갑"이란 지역산업 구조의 고도화와 일자리창출 등 지역경제발전을 위해 산업통상자원부와 제주특별자치도의 지원아래 설립된 비영리 재단법인으로의 제주테크노파크를 의미합니다. 또한 "갑"이 운영하는 인터넷사이트를 의미하기도 합니다.
2."회원"이란 '갑'에 로그인하여 이 약관에 따라 회원등록을 하고 '갑'으로부터 승인을 얻어 '갑'이 제공하는 서비스를 받는 법인기업 및 일반사용자를 말하며, 입주법인기업 내부의 서비스 이용자를 일반회원과 구별하여 "기업회원" 이라 칭하며, 회원이라 칭할때는 기업회원 그리고 일반회원을 통칭합니다.
3."아이디(ID)"란 회원 식별과 회원의 서비스 이용을 위하여 회원이 신청하고 '갑'이 승인하는 문자와 숫자의 조합을 말합니다.
4."비밀번호(Password)"란 회원의 비밀 보호를 위하여 회원 자신이 설정한 영문자와 숫자의 조합을 말합니다.

제3조 (약관의 변경)
1.'갑'은 상호, 소재지, 대표자의 성명, 연락처등 정보를 회원이 알 수 있도록 게시합니다.
2.'갑'은 약관의 규제 등에 관한 법률, 전자거래기본법, 전자서명법 , 정보통신망 이용촉진 등에 관한 법률 방문 판매 등에 관한 법률, 소비자보호법 등 관련법을 위배하지 않는 범위에서 이 약관을 변경할 수 있습니다.
3.'갑'이 이 약관을 변경하고자 하는 경우 '갑'은 변경된 내용을 서비스 화면에 게재하거나 개정 시 그 개정사유와 적용일자를 명시하여 그 적용일자 7일전까지 공지합니다. 회원이 적용예정일까지 이의를 제기하지 않으면 변경된 약관을 동의한 것으로 보고 적용합니다.
4.본 약관에서 정하지 아니한 사항과 본 약관의 해석에 관하여는 전자거래소비자 보호지침 및 관계법령 또는 일반적 상관례에 따릅니다.

제 4조 (서비스의 내용과 회원별 이용 범위)
1.'갑'은 다음과 같은 서비스를 제공합니다.
1)'갑'의 사업지원과 관련한 부가서비스
2)기업정보 제공 및 Call센터 운영
3)Online교육, SMS, 시설예약의 고객지원서비스
4)기타 '갑'이 정하는 서비스
2.회원별 이용가능 서비스는 다음과 같습니다.
①기업회원 : 제1항의 모든 서비스
②일반회원 : 제1항중 3)항을 제외한 '갑'이 정하는 서비스

제 5조 (서비스의 중단)
1.'갑'은 컴퓨터 등 정보통신설비의 보수점검·교체 및 고장, 통신의 두절 등의 사유가 발생한 경우에는 서비스의 제공을 일시적으로 중단할 수 있습니다.
2.'갑'은 제1항의 사유로 서비스의 제공이 일시적으로 중단됨으로 인하여 회원 또는 제3자가 입은 손해에 대하여는 배상하지 아니합니다. 단 '갑'의 고의 또는 중과실이 있는 경우에는 그러하지 아니합니다.

제 6조 (회원가입)
1.회원은 '갑'이 정한 소정의 가입 양식에 따라 회원정보를 기입한 후 이 약관에 동의한다는 의사 표시를 함으로서 회원가입을 신청합니다.
2.'갑'의 회원정책은 다음과 같습니다.
1)일반회원은 가입에 제한을 두지 않습니다. 일반회원은 가입과 동시에 일반회원으로 등록됩니다.
2)기업회원은 제주테크노파크에 제주테크노파크가 지원하는 기업을 대상으로 합니다. 기업회원은 '갑'의 승인을 얻어야만 회원으로 등록할 수 있습니다.
3.'갑'은 제1항과 같이 회원으로 가입할 것을 신청한 기업회원 또는 일반회원 중 다음 각 호에 해당하는 경우에는 회원으로 등록할 수 없습니다.
1)이 약관에서 정한 사항을 위반하는 경우
2)등록 내용에 허위, 기재누락, 오기가 있는 경우
4.회원가입의 성립시기는 다음과 같습니다.
1)일반회원은 '갑'의 사이트에 정보를 기재하고 등록한 시점
2)기업회원은 회원가입 신청 후 '갑'의 승인이 이루어진 시점

제 7조 (회원 탈퇴 와 상실 등)
1.회원이 탈퇴를 희망하는 경우 인터넷사이트 상에서 '갑' 운영자에게 탈퇴를 요청할 수 있으며 '갑' 은 즉시 회원탈퇴를 처리합니다.
2.회원이 다음 각 호의 사유에 해당하는 경우, '갑'은 회원자격을 임의로 제한 및 정지시킬 수 있습니다.
1)가입 신청 시에 허위 내용을 등록한 경우
2)다른 회원 및 기업회원의 '갑'의 이용을 방해하거나 그 정보를 도용하는 등 전자거래 질서를 위협하는 경우
3)'갑'을 이용하여 법령과 이 약관이 금지하거나 기존 상관례에 반하는 행위를 하는 경우
4)기타 회원으로서의 자격을 지속시키는 것이 부적절 하다고 판단되는 경우
3.'갑'이 회원 자격을 제한, 정지 시킨 후, 동일한 행위가 2회 이상 반복되거나 30일 이내에 그 사유가 시정되지 아니하는 경우 '갑'은 회원자격을 상실 시킬 수 있습니다.
4.'갑'이 회원자격을 상실 시키는 경우에는 '갑'은 해당회원의 회원등록을 말소합니다. 이 경우 회원에게 이를 즉시 통지하고, 회원등록 말소 전에 소명할 기회를 부여합니다.

제 8조 (회원에 대한 통지)
1.'갑'이 회원에 대한 통지를 하는 경우, 회원이 '갑'에 제출한 전자우편 주소로 할 수 있습니다.
2.'갑'은 불특정다수 회원에 대한 통지의 경우 1주일 이상 '갑'의 게시판에 게시함으로써 개별 통지에 갈음할 수 있습니다.

제 9조 (개인정보보호)
1.'갑'은 회원들의 개인정보를 '갑'의 필요에 의하여 비상업적인 목적 내에서만 사용할 수 있다.
2.회원은 '갑'이 정한 필수적 기재사항은 반드시 입력하여야 합니다. 선택적 기재사항에 대하여는 회원이 원하는 경우에만 입력합니다.
3.'갑'은 취득한 회원정보를 제1항에 기재된 목적 이외의 목적으로 이용할 수 없습니다. 또한 회원의 사전승낙 없이 이를 제3자에게 제공하지 아니합니다. 다만, 법령에 의하여 회원정보를 제공하여야 하는 경우는 제외합니다.
4.회원은 자신이 제공한 정보를 열람할 수 있으며, 그 정보가 잘못된 경우 수정을 할 수 있습니다. 또한 언제든지 자신이 제공한 정보를 삭제할 것을 할 수 있습니다.
5.이용 계약 해지의 경우 해지된 개인정보는 외부에 노출되지 않습니다.

제 10조 ('갑'의 의무)
1.'갑'은 법령과 이 약관이 금지하거나 개인정보보호법에 반하는 행위를 하지 않으며 이 약관이 정하는 바에 따라 지속적이고, 안정적인 서비스를 제공하는 데 최선을 다하여야 합니다.
2.'갑'은 회원이 안전하게 인터넷 서비스를 이용할 수 있도록 회원의 개인정보보호를 위하여 최대한 노력 합니다.
3.'갑'은 회원이 원하지 않을 경우 영리목적의 광고성 전자우편을 발송하지 않습니다.

제 11조 (회원의 의무)
1.회원이 탈퇴를 희망하는 경우 인터넷사이트 상에서 '갑' 운영자에게 탈퇴를 요청할 수 있으며 '갑' 은 즉시 회원탈퇴를 처리합니다.
2.회원은 다음 행위를 하여서는 안됩니다.
1)신청 또는 변경 시 허위내용의 등록
2)'갑'이 게시한 정보의 변경
3)'갑'이 정한 정보 이외의 정보(컴퓨터 프로그램 등)의 송신 또는 게시
4)'갑' 및 기타 제3자의 저작권 등 지적재산권에 대한 침해
5)'갑' 운영의 방해
6)'갑' 및 기타 제3자의 명예를 손상시키거나 업무를 방해하는 행위
7)외설 또는 폭력적인 메시지·화상·음성 등의 정보를 '갑'에 공개 또는 게시하는 행위
3.회원은 '갑'을 이용하는 경우 자신이 신청한 ID 및 PW를 사용해야 하며, 기업회원의 경우 '갑'의 승인을 받아야 합니다. 그리고 회원 자신의 ID 및 Password에 관한 관리소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 책임은 회원에게 있습니다.

제 12조 (지적재산권의 귀속 및 이용제한)
1.'갑'이 작성한 저작물에 대한 저작권 및 기타 지적재산권은 '갑'에 귀속합니다.
2.회원은 '갑'을 이용함으로써 얻은 정보를 '갑'의 사전 승낙 없이 복제, 송신, 출판, 배포,방송, 기타 방법에 의하여 영리목적으로 이용하거나 제3자에게 이용하게 하여서는 안됩니다.

제 13조 (정보의 수집 및 이용)
1.'갑'은 공개된 혹은 비공개된 정보에 대하여 원소유자와 협의한 후에 수집합니다.
2.'갑'이 수집한 정보를 상업적인 목적으로 이용하고자 할 경우, 원소유자와 합의 또는 계약 등의 절차를 거칩니다.
3.'갑'은 수집한 정보에 대하여 위의 2항의 절차를 거치지 않고 제3자에게 양도하거나 판매하지 않습니다.
4.'갑'이 수집한 정보의 저작권은 원소유자에게 있으며 가공한 정보(컨텐츠)를 이용하는 경우에는 출처를 명시합니다.
5.'갑'의 사이트에 게재된 정보에 대해 회원 및 이용자에게도 위와 같은 정책을 적용합니다.

제 14조 (분쟁의 해결)
1.'갑'은 회원이 제기하는 의견이나 불만을 반영하고 그 피해를 보상처리하기 위하여 고객상담센타(Call센터)를 설치 운영합니다.
2.'갑'은 회원으로부터 제출되는 의견이나 불만이 정당하다고 판단되는 경우 우선적으로 처리합니다. 다만, 신속한 처리가 곤란한 경우에는 회원에게 그 사유와 처리일정을 즉시 통보합니다.
3.'갑'과 회원간에 발생한 분쟁은 전자거래기본법 제28조 및 동 시행령 제15조에 의하여 설치 전자거래분쟁 조정위원회의 조정에 따를 수 있습니다.
4.'갑' 회원간에 발생한 분쟁에 관한 재판 관할권은 제주테크노파크의 소재지를 관할하는 법원으로 합니다.

부칙
1.(시행일) 이 약관은 2014년 6월 1일부터 시행합니다.
</pre>
                </div>
                <p class="check">
                    <input id="check01" name="check01" type="checkbox"><label for="check01">동의합니다. (필수)</label>
                </p>

                <h4>개인정보 수집·이용 내역 <small>(필수)</small></h4>

                <div class="textarea">
<pre>
"JEUS" 회원가입에 있어 수집하는 필수정보 항목은 다음과 같습니다.

필수항목
----
성명, 아이디, 비밀번호, 이메일, 휴대전화, 소속기관

수집목적
----
1. 회원 가입 및 관리
: 본인확인, 회원 가입·탈퇴 의사 확인, 불만사항 등 민원처리, 공지사항 안내 등
2. 서비스 제공
: 등록·예약정보 관리 및 연계, 예약클라우드 사용 및 관리, 상담이용, 나눔터 등록, 신청 및 관리, 장비교육 신청, 국가연구시설장비 실태조사·통계 및 보고자료, 정책·사업·제도·서비스 등 홍보, 신청·승인·반려 등 서비스 알림(SMS·메일)

보유기간
----
탈퇴 후 5년

</pre>
                </div>
                <p class="check">
                    <input id="check02" name="check02" type="checkbox"><label for="check02">동의합니다. (필수)</label>
                </p>

                <h4>개인정보 수집·이용 내역 <small>(선택)</small></h4>

                <div class="textarea">
<pre>
"JEUS" 회원가입에 있어 수집하는 필수정보 항목은 다음과 같습니다.

선택항목
----
전화번호, 부서, 직위, 책임자, 주소, 사업자정보, 예약장비, 검색키워드

수집목적
----
원활한 서비스 제공(서비스 신청자격 확인, 정보 품질 검증, 정보연계 이용자 식별, 예약관련 청구 및 청구서 발행 등)

보유기간
----
탈퇴 후 5년

</pre>
                </div>


                <p class="check">
                    <input id="check03" name="check03" type="checkbox"><label for="check03">동의합니다. (선택)</label>
                </p>

                <p class="infotext">
                    ※ 위의 개인정보 수집·이용에 대한 동의를 거부할 권리가 있습니다. 그러나 동의를 거부할 경우 정보 등록·관리, 예약 신청·관리 등의 세부 서비스 이용에 제한이 있을 수 있습니다
                </p>


                <div class="btn-set center big">
                    <button class="btn" type="button" onclick="history.back()">취소</button>
                    <button class="btn main" type="button" onclick="clickPcc()">다음</button>
                </div>
            </div>
        </div>
        </form>
    </div>

</section>

<form name="reqPCCForm" id="pccForm" method="post" action="https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10.jsp" >
    <input type="hidden" id="reqInfo" name="reqInfo" value="" />
    <!-- 요청정보 -->
    <input type="hidden" id="retUrl" name="retUrl" value="" />
    <!-- 리턴받을URL -->
    <input type="hidden" name="verSion"	value = "2">
    <!--모듈 버전정보-->
</form>


<!-- 본인실명확인서비스 요청 form --------------------------->
<%--<form name="reqPCCForm" id="reqPCCForm" method="post" action ="https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10_v2.jsp">--%>
    <%--<input type="hidden" name="reqInfo"    value = "010001">--%>
    <%--<input type="hidden" name="retUrl"      value = "/site/sns/authResultPopup.do">--%>
    <%--<input type="hidden" name="verSion"	value = "2">				 <!--모듈 버전정보-->--%>

<%--</form>--%>


