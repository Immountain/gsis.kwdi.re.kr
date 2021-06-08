<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
    $ifx.contextPath='<c:url value="/"/>';
</script>

<script type="text/javascript">
    $(document).ready(function () {

        ajaxLoadSelect({
            url: "<c:url value='/site/country/code/List.do' />",
            async : true,
            params: [
                {name: 'parentCode', value: 'root'},
                {name: 'langCode', value: '${SITEINFO.langCd}'}
            ],
            selectboxNm: 'city',
            callback: function(i, j) {
            }
        });

        $("#city").change(function() {
            $('#town').attr('disabled', true);
            $('#town').css('background', '#ccc');
            ajaxLoadSelect({
                url: "<c:url value='/site/country/code/List.do' />",
                async : true,
                params: [
                    {name: 'parentCode', value: this.value},
                    {name: 'langCode', value: '${SITEINFO.langCd}'}
                ],
                selectboxNm: 'town',
                callback: function(i, j) {
                    $('#town').attr('disabled', false);
                    $('#town').css('background', '');
                }
            });
        });

        $("#town").change(function() {
            $('#village').attr('disabled', true);
            $('#village').css('background', '#ccc');
            ajaxLoadSelect({
                url: "<c:url value='/site/country/code/List.do' />",
                async : true,
                params: [
                    {name: 'parentCode', value: this.value},
                    {name: 'langCode', value: '${SITEINFO.langCd}'}
                ],
                selectboxNm: 'village',
                callback: function(i, j) {
                    $('#village').attr('disabled', false);
                    $('#village').css('background', '');
                }
            });
        });
    });

    /*********************************************************
     * 아이디 체크 AJAX
     ******************************************************** */
    function fn_id_check(){

        if($('#idCheck').val()=='Y'){
            $('#userId').val($('#tempId').val());
            return;
        }

        var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
        if(!idReg.test( $("input[name=userId]").val() ) ) {
            alert("아이디는 영어 소문자로 시작하는 6~20자 영어 소문자 또는 숫자이어야 합니다.");
            return;
        }

        $.ajax({
            type:"POST",
            url:"<c:url value='/mypage/checkUserId.do' />",
            data:{
                "checkId": $("#userId").val()
            },
            dataType:'json',
            timeout:(1000*30),
            success:function(returnData, status){

                if(status == "success") {

                    if(returnData.usedCnt > 0 ){
                        //사용할수 없는 아이디입니다.
                        alert("사용할수 없는 아이디입니다.");
                        $('#idCheck').val('N');
                    }else{
                        //사용가능한 아이디입니다.

                        if(!returnData.checkId){
                            // $('#span_id').html('사용할수 없는 아이디입니다');
                            // $('#btn_search_id').html('확인');
                        }else{
                            // $('#btn_search_id').html('사용');
                            // $('#span_id').html('사용가능한 아이디입니다.');
                            alert("사용가능한 아이디입니다.");
                            $('#tempId').val(returnData.checkId)
                            $('#idCheck').val('Y');
                        }
                    }
                }else{ alert("ERROR!");}
            }
        });
    }

    /* ********************************************************
     * 저장처리화면
     ******************************************************** */
    function fn_regist_page(form){

        form.action = '<info:url value="/jeju/people/insert.do"/>';

        var isValid = false;
        var message = "";

        <c:if test="${mode ne 'update'}">
        if($('[name=containerYn]:checked').length === 0) {
            alert('개인정보수집에 동의하여주시기 바랍니다.');
            return false;
        }
        if(document.querySelector('#idCheck').value === 'N') {
            alert('아이디 중복체크를 해주시기 바랍니다.');
            return false;
        }
        </c:if>

        $(form).find('[evt=valid]').each(function(idx, v) {
            console.log(v, v.value)
            if($ifx.isEmpty(v.value)) {
                message = v.title + '를(을) 확인하여 주시기 바랍니다.';
                isValid = true;
                return false;
            }
        });
        if(isValid) {
            alert(message);
            return false;
        }

        if(document.querySelector('[name=password]').value !== document.querySelector('[name=tempPassword]').value) {
            alert('비밀번호 확인이 맞지 않습니다.');
            return false;
        }
        if($('[name=sendEmaillYn]:checked').length === 0) {
            alert('메일발송여부를 선택하여주시기 바랍니다.');
            return false;
        }
        if($('[name=sex]:checked').length === 0) {
            alert('<spring:message code="제주인.알람2"/> ');
            return false;
        }
        if(($ifx.isEmpty($('#city').val()) || $('#city').val() !== "t_jejudo")
            && ($ifx.isEmpty($('#village').val()) && $ifx.isEmpty(document.querySelector("[name=address_etc]").value))
        ) {
            alert('<spring:message code="제주인.알람4"/>');
            return false;
        }
        if(($ifx.isEmpty($('#country').val()) && $ifx.isEmpty($('[name=countryEtc]').val()))
            || $ifx.isEmpty($('[name=countryCity]').val())
        ) {
            alert('<spring:message code="제주인.알람5"/>');
            return false;
        }

        //input item Client-Side validate
        if(confirm("<spring:message code="common.regist.msg" />")){
            form.submit();
        }
    }
</script>



<div id="content" class="sub sub02">

    <!-- 각 구성요소 article로 모듈화 -->
    <div class="sub-head">

        <nav class="sub-menu">
            <button><spring:message code="제주인.제주인등록"/></button>
            <info:getMenuModel modelName="subSiteMenu" groupId="${SITEINFO.langCd}-primary" siteMemuId=""/>

            <div class="outline">
                <c:forEach items="${subSiteMenu}" var="item">
                    <c:if test="${item.viewYn eq 'Y'}">
                        <a <c:if test="${item.siteMemuId eq menuInfo.parentId}">class="active"</c:if> href="<info:url value="${item.url}"/>">${item.siteMemuNm}</a>
                    </c:if>
                </c:forEach>
            </div>
        </nav>






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
                <c:choose>
                    <c:when test="${mode ne 'update'}">
                        <h2><spring:message code="제주인.제주인등록"/></h2>
                    </c:when>
                    <c:otherwise>
                        <h2> <spring:message code="제주인.제주인등록"/></h2>
                    </c:otherwise>
                </c:choose>
            </div>
        </article>


        <c:if test="${mode ne 'update'}">
            <article class="sign-agree" id="clause">

                <div class="container">


                    <div class="outline">
                        <info:include id="${SITEINFO.langCd}-clause"/>
                            <%--<p>개인정보보호정책 약관</p>--%>

                            <%--<p>[개인정보 수집에 대한 동의]</p>--%>
                            <%--<p>세계제주인대회조직위원회는 귀하께서 개인정보취급방침의 내용에 대해 「개인정보수집에 동의함」체크 항목을 클릭할 수 있는 절차를 마련하여, 체크 표기시 개인정보 수집에 대해 동의한 것으로 봅니다.</p>--%>

                            <%--<p>[개인정보의 수집목적 및 이용목적]</p>--%>
                            <%--<p>세계제주인대회조직위원회는 다음과 같은 목적을 위하여 개인정보를 수집하고 있습니다.</p>--%>
                            <%--<p> ① 서비스제공을 위한 계약의 성립(본인식별 및 본인의사 확인 등)</p>--%>
                            <%--<p>② 서비스의 이행(제주인등록) ③ 기타 새로운 서비스, 제주인 등록에 대한 지원 안내</p>--%>
                            <%--<p>④ 단, 이용자의 기본적 인권 침해의 우려가 있는 민감한 개인정보(인종 및 민족, 사상 및 신조, 정치적 성향 및 범죄기록, 건강상태 및 성생활 등)는 수집하지 않습니다.</p>--%>

                            <%--<p>[쿠키에 의한 개인정보 수집]</p>--%>
                            <%--<p> 세계제주인대회조직위원회는 귀하에 대한 외부 로그인 정보(이름과 이메일)를 저장하고 수시로 찾아내는 '쿠키(cookie)'를 사용하여 개인식별합니다.</p>--%>

                            <%--<p>[개인정보의 보유기간 및 이용기간]</p>--%>
                            <%--<p>귀하의 개인정보는 다음과 같이 개인정보의 수집목적 또는 제공받은 목적이 달성되면 파기됩니다.</p>--%>
                            <%--<p> - 제주인 등록 사항을 삭제하였을 경우</p>--%>
                            <%--<p> - 통계 및 지원사업이 종료 되었을 경우</p>--%>

                            <%--<p>위 보유기간에도 불구하고 계속 보유하여야 할 필요가 있을 경우에는 귀하의 동의를 받겠습니다.</p>--%>
                    </div>
                    <input type="checkbox" id="containerYn" name="containerYn">
                    <label for="containerYn"> <spring:message code="제주인.개인정보수집에동의합니다"/></label>
                </div>
            </article>
        </c:if>


        <article class="register-form">
            <div class="container">

                <form:form modelAttribute="wjJejuPeopleVO"
                           commandName="wjJejuPeopleVO"
                           action="${pageContext.request.contextPath}/jeju/people/insert.do"
                           method="post"
                           enctype="multipart/form-data"
                           onSubmit="fn_regist_page(document.forms[0]); return false;">
                    <p class="info">* <spring:message code="제주인.설명9"/> </p>
                    <c:forEach items="${errors}" var="item" varStatus="vs">
                        <p>${item.defaultMessage}</p>
                    </c:forEach>
                    <dl>
                        <dt><spring:message code="제주인.아이디"/> <i>*</i></dt>
                        <c:choose>
                            <c:when test="${mode ne 'update'}">
                                <dd>
                                    <form:input path="userId" title="아이디" evt="valid"/>
                                    <button class="btn sub" id="btn_search_id" name="btn_search_id" type="button" onclick="fn_id_check()">확인</button>
                                    <input type="hidden" id="idCheck" name="idCheck" value="N">
                                    <input type="hidden" id="tempId" name="tempId">
                                </dd>
                            </c:when>
                            <c:otherwise>
                                <dd>
                                    <form:input path="userId" title="아이디" evt="valid" readonly="true"/>
                                </dd>
                            </c:otherwise>
                        </c:choose>
                        <dt><spring:message code="제주인.패스워드"/> <i>*</i></dt>
                        <dd>
                            <form:password path="password" title="비밀번호" value="" evt="valid"/>
                        </dd>
                        <dt>패스워드 확인 <i>*</i></dt>
                        <dd>
                            <input type="password" name="tempPassword" id="tempPassword" title="비밀번호" value="" evt="valid">
                        </dd>


                        <dt><spring:message code="제주인.이메일"/> <i>*</i></dt>
                        <dd>
                            <form:input path="emaill" evt="valid" title="이메일"/>
                            <span class="info">※ <spring:message code="제주인.설명8"/>
                        </dd>
                        <dt><spring:message code="제주인.메일발송여부"/> <i>*</i></dt>
                        <dd>
                            <form:radiobutton path="sendEmaillYn" id="sendEmaillYn1" value="Y"/>
                            <label for="sendEmaillYn1">받음</label>
                            <form:radiobutton path="sendEmaillYn" id="sendEmaillYn2" value="N"/>
                            <label for="sendEmaillYn2">받지않음</label>
                        </dd>


                        <dt><spring:message code="제주인.성명"/> <i>*</i></dt>
                        <dd>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.한글"/></span>
                                <form:input path="korName" evt="valid" title="한글명"/>
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.영문"/></span>
                                <form:input path="enName" evt="valid" title="영문명"/>
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.한자"/></span>
                                <form:input path="cnName" evt="valid" title="한자명"/>
                            </p>
                            <span class="info">※ <spring:message code="제주인.설명1"/></span>

                        </dd>

                        <dt><spring:message code="제주인.사진첨부"/></dt>
                        <dd>
                            <div id="pr_pic"><img src="/jeju/people/preview.do?userId=${wjJejuPeopleVO.userId}" width="150" height="200"></div>
                            <input type="file" name="file" title="첨부파일을 선택하세요!" onchange="$ifx.setThumbnail(event, '#pr_pic img', {width: '150px', height: '200px'});" class="file">
                            <span class="info">※ <spring:message code="제주인.설명2"/></span>
                        </dd>
                        <dt><spring:message code="제주인.성별"/> <i>*</i></dt>
                        <dd>
                            <form:radiobutton path="sex" id="sextype01" value="M"/>
                            <label for="sextype01"><spring:message code="제주인.남"/></label>
                            <form:radiobutton path="sex" id="sextype02" value="W"/>
                            <label for="sextype02"><spring:message code="제주인.여"/></label>
                        </dd>


                        <dt><spring:message code="제주인.생년월일"/> <i>*</i></dt>
                        <dd>
                            <fmt:parseDate value="${wjJejuPeopleVO.birth}" var="birth" pattern="yyyyMMdd"/>
                            <fmt:formatDate var="birthFormat" value="${birth}" pattern="yyyy-MM-dd"/>
                            <form:input type="date" path="birth" evt="valid" title="생년월일" value="${birthFormat}"/>
                        </dd>

                        <dt><spring:message code="제주인.출신지"/> <i>*</i></dt>
                        <dd>

                            <p class="rows">
                                <span class="text"><spring:message code="제주인.제주특별자치도"/></span>
                                <span class="select-outline local">

                                     <info:select name="city" id="city"   val="${wjJejuPeopleVO.city}" first="지역" />

                                   <%----%>
                                    <%--<select name="city" id="city" size="1" onchange="chk_city(this.value);">--%>
                                        <%--<option value="">- 지역 -</option>--%>
                                        <%--<option value="제주도">제주도</option>--%>
                                        <%--<option value="제주시">제주시</option>--%>
                                        <%--<option value="서귀포시">서귀포시</option>--%>
                                    <%--</select>--%>
                                </span>
                                <span class="select-outline local">

                                    <info:select name="town" id="town" val="${wjJejuPeopleVO.town}" first="읍/면/동" />

                                    <%--<select name="town" id="town" size="1" onchange="chk_town(this.value);">--%>
                                        <%--<option value="">--- 읍/면/동 ---</option>--%>
                                    <%--</select>--%>
                                </span>
                                <span class="select-outline local">

                                    <info:select name="village" id="village"   val="${wjJejuPeopleVO.village}" first="리" />

                                    <%--<select name="village" id="village" size="1">--%>
                                        <%--<option value="">-- 리 --</option>--%>
                                    <%--</select>--%>
                                </span>

                            </p>

                            <p class="rows">
                                <span class="text"><spring:message code="제주인.기타지역"/></span>
                                <form:input path="addressEtc" />
                            </p>

                            <span class="info">※ <spring:message code="제주인.설명4"/></span>

                        </dd>

                        <dt><spring:message code="제주인.거주지"/> <i>*</i></dt>
                        <dd>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.국가"/></span>

                                <span class="select-outline local">

                                    <form:select path="country">
                                        <form:option value="">- 국가 -</form:option>
                                        <c:forEach items="${nationlist}" var ="item">
                                            <form:option value="${item.code}" label="${item.codeNm}"/>
                                        </c:forEach>
                                    </form:select>
                                </span>

                                <form:input path="countryEtc" cssClass="small" placeholder="기타 국가"/>
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.지역"/></span>
                                <form:input path="countryCity" />
                            </p>


                            <span class="info">※ <spring:message code="제주인.설명5"/></span>
                        </dd>


                        <dt><spring:message code="제주인.휴대폰"/> <i>*</i></dt>
                        <dd>
                            <form:input path="phone" etc="valid"/>
                            <span class="info">※ <spring:message code="제주인.설명6"/></span>
                        </dd>

                        <dt><spring:message code="제주인.전화번호"/> </dt>
                        <dd>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.자택"/></span>
                                <form:input path="tel1" />
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.직장"/></span>
                                <form:input path="tel2" />
                            </p>
                            <span>※ <spring:message code="제주인.설명7"/></span>
                        </dd>


                        <dt><spring:message code="제주인.소속기관및직위"/></dt>
                        <dd>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.기관"/></span>
                                <form:input path="company" title="소속기관"/>
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.직위"/></span>
                                <form:input path="spot" title="직위"/>
                            </p>
                        </dd>

                        <dt>학력</dt>
                        <dd>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.초등학교"/></span>
                                <form:input path="schoolE" title="학교명" cssClass="school" control-id="ControlID-31"/>

                                <span class="text"><spring:message code="제주인.졸업년도"/></span>
                                <form:input path="schoolEYear" title="졸업년도" cssClass="school-year" control-id="ControlID-32"/>
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.중학교"/></span>
                                <form:input path="schoolM" title="학교명" cssClass="school" />

                                <span class="text"><spring:message code="제주인.졸업년도"/></span>
                                <form:input path="schoolMYear" title="졸업년도" cssClass="school-year" />
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.고등학교"/></span>
                                <form:input path="schoolH" title="학교명" cssClass="school" />

                                <span class="text"><spring:message code="제주인.졸업년도"/></span>
                                <form:input path="schoolHYear" title="졸업년도" cssClass="school-year" />

                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.대학교"/></span>
                                <form:input path="schoolU" title="학교명" cssClass="school" />

                                <span class="text"><spring:message code="제주인.졸업년도"/></span>
                                <form:input path="schoolUYear" title="졸업년도" cssClass="school-year" />
                            </p>
                            <p class="rows">
                                <span class="text"><spring:message code="제주인.대학원이상"/></span>
                                <form:input path="schoolG" title="학교명" cssClass="school" />

                                <span class="text"><spring:message code="제주인.졸업년도"/></span>
                                <form:input path="schoolGYear" title="졸업년도" cssClass="school-year" />
                            </p>

                            <p class="rows">
                                <span class="text"><spring:message code="제주인.최종학력"/></span>
                                <span class="select-outline">
                                        <form:select path="edu">
                                            <c:forEach items="${wjAlist}" var ="item">
                                                <form:option value="${item.code}" label="${item.codeNm}"/>
                                            </c:forEach>
                                        </form:select>
                                </span>
                            </p>
                        </dd>

                        <dt><spring:message code="제주인.활동분야"/></dt>
                        <dd>
                            <c:forEach items="${wjBlist}" var="item" varStatus="vs">
                                <input type="checkbox" name="wjActInfoList[${vs.index}].actCode" id="actCode_${vs.index}" value="${item.code}" <c:if test="${!empty item.checkCode}">checked</c:if>>
                                <label for="actCode_${vs.index}">${item.codeNm}</label>
                            </c:forEach>
                        </dd>

                        <%--<dt><spring:message code="제주인.종교"/></dt>--%>
                        <%--<dd>--%>
                            <%--<p class="rows">--%>
                                 <%--<span class="select-outline local">--%>
                                     <%--<form:select path="religion">--%>
                                         <%--<c:forEach items="${wjClist}" var="item">--%>
                                             <%--<form:option value="${item.code}" label="${item.codeNm}"/>--%>
                                         <%--</c:forEach>--%>
                                     <%--</form:select>--%>
                                 <%--</span>--%>
                            <%--</p>--%>
                            <%--<p class="rows">--%>
                                <%--<form:input path="religionEtc" title="" cssClass="" />--%>
                            <%--</p>--%>
                        <%--</dd>--%>

                        <dt><spring:message code="제주인.경력사항"/></dt>
                        <dd>
                            <form:textarea path="career" title="" cssClass="" rows="5" style="width:757px;"/>
                        </dd>
                    </dl>

                    <div class="btn-set center big">
                        <input type="submit" class="btn main" value="<spring:message code="제주인.등록하기"/>" title="<spring:message code="제주인.등록하기"/>" />
                        <button type="button" id="btn_cancel_save" name="btn_cancel_save" class="btn " name="btn_save"><spring:message code="제주인.취소하기"/></button>
                    </div>
                </form:form>
            </div>
        </article>






    </div>


</div>




