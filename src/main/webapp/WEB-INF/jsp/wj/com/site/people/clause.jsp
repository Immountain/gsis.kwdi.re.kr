<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>


<script type="text/javascript">
    $(document).ready(function () {



    });



</script>



<div id="content" class="sub sub02">

    <!-- 각 구성요소 article로 모듈화 -->
    <div class="sub-head">

        <nav class="sub-menu">
            <button>제주인대회</button>
            <info:getMenuModel modelName="subSiteMenu" groupId="${SITEINFO.langCd}-primary" siteMemuId=""/>

            <div class="outline">
                <c:forEach items="${subSiteMenu}" var="item">
                    <c:if test="${item.viewYn eq 'Y'}">
                        <a <c:if test="${item.siteMemuId eq menuInfo.parentId}">class="active"</c:if> href="<info:url value="${item.url}"/>">${item.siteMemuNm}</a>
                    </c:if>
                </c:forEach>
            </div>
        </nav>



        <article class="sub-title">
            <div class="container">
                <h2>
                    제주인 약관
                </h2>

            </div>
        </article>
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
        <article class="register-form">
            <div class="container">
                개인정보보호정책 약관

                [개인정보 수집에 대한 동의]
                세계제주인대회조직위원회는 귀하께서 개인정보취급방침의 내용에 대해 「개인정보수집에 동의함」체크 항목을 클릭할 수 있는 절차를 마련하여, 체크 표기시 개인정보 수집에 대해 동의한 것으로 봅니다.

                [개인정보의 수집목적 및 이용목적]
                세계제주인대회조직위원회는 다음과 같은 목적을 위하여 개인정보를 수집하고 있습니다.
                ① 서비스제공을 위한 계약의 성립(본인식별 및 본인의사 확인 등)
                ② 서비스의 이행(제주인등록) ③ 기타 새로운 서비스, 제주인 등록에 대한 지원 안내
                ④ 단, 이용자의 기본적 인권 침해의 우려가 있는 민감한 개인정보(인종 및 민족, 사상 및 신조, 정치적 성향 및 범죄기록, 건강상태 및 성생활 등)는 수집하지 않습니다.

                [쿠키에 의한 개인정보 수집]
                세계제주인대회조직위원회는 귀하에 대한 외부 로그인 정보(이름과 이메일)를 저장하고 수시로 찾아내는 '쿠키(cookie)'를 사용하여 개인식별합니다.

                [개인정보의 보유기간 및 이용기간]
                귀하의 개인정보는 다음과 같이 개인정보의 수집목적 또는 제공받은 목적이 달성되면 파기됩니다.
                - 제주인 등록 사항을 삭제하였을 경우
                - 통계 및 지원사업이 종료 되었을 경우

                위 보유기간에도 불구하고 계속 보유하여야 할 필요가 있을 경우에는 귀하의 동의를 받겠습니다.


            </div>
        </article>






    </div>


</div>






