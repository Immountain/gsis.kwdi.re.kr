<%
 /**
  * @Class Name : InfoUserInsert.jsp
  * @Description : 사용자등록View JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.12.01   이기선              최초 생성
  *  @author 인포마인드 개발팀
  *  @since 2020.12.01
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.deptUserManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->

<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="userManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javascript" src="<c:url value='/js/egovframework/com/sym/ccm/zip/EgovZipPopup.js' />" ></script>
<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

	<script type="text/javaScript" language="javascript" defer="defer">
/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	//모달 셋팅
	fn_modal_setting();

	$("#emplyrSttusCode").val("P");

	$("#groupId").val("GROUP_00000000000000");



}
/*********************************************************
 * 셀렉트 박스
 ******************************************************** */


function selectBoxInit() {
	<%--/** 처음휴대폰번호 selectBox */--%>
	<%--var API_SERVER = "<c:url value='/cms/code/search/selectComtccmmndetailcodeList.do' />";--%>
	<%--ajaxLoadSelect({--%>
		<%--url: API_SERVER,--%>
		<%--params: [--%>
			<%--{name: 'codeId', value: 'MOBILE_NUM'}--%>

		<%--],--%>
		<%--selectboxNm: 'startMoblno'--%>
	<%--});--%>

	<%--/** 지역전화번호 selectBox */--%>
	<%--var API_SERVER = "<c:url value='/cms/code/search/selectComtccmmndetailcodeList.do' />";--%>
	<%--ajaxLoadSelect({--%>
		<%--url: API_SERVER,--%>
		<%--params: [--%>
			<%--{name: 'codeId', value: 'AREA_NUM'}--%>

		<%--],--%>
		<%--selectboxNm: 'areaNo'--%>
	<%--});--%>
}


/*********************************************************
 * 모달셋팅
 ******************************************************** */
function fn_modal_setting(){
	//버튼에 모달 연결
	$("#btnEmplyrId").egovModal( "egovModal" );
	
	//타이틀 설졍
	$("#egovModal").setEgovModalTitle("<spring:message code="comUssUmt.userManageRegistModal.title" />"); //아이디 중복 확인
	var content = "";
	content = content + "<div class='modal-alignL' style='margin:5px 0 0 0'>"+"<spring:message code="comUssUmt.userManageRegistModal.userIsId" /> :"+"</div>"; //사용할아이디
	content = content + "<div class='modal-alignL'>"+"<input type='text' id='checkIdModal' name='checkIdModal' value='' size='20' maxlength='20' />"+"</div>";	
	content += "<div style='clear:both;'></div>";
	content += "<div id='divModalResult' style='margin:10px 0 0 0'><spring:message code="comUssUmt.userManageRegistModal.initStatus" /></div>"; //결과 : 중복확인을 실행하십시오.
	//모달 body 설정
	$("#egovModal").setEgovModalBody(content);

	var footer = "";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalOk' onclick='fn_id_checkOk()'>확인</button></div>";
	//footer += "<div class='modal-btn'><button class='btn_s2' id='btnModalSelect' onclick='fn_id_check()'>조회</button></div>";
	footer += "<span class='btn_style1 blue' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'>확인</a></span>&nbsp;";
	footer += "<span class='btn_style1 blue' id='btnModalSelect' onclick='fn_id_check()'><a href='#'>조회</a></span>&nbsp;";
	//모달 footer 설정
	$("#egovModal").setEgovModalfooter(footer);
	
	//엔터이벤트처리
	$("input[name=checkIdModal]").keydown(function (key) {
		if(key.keyCode == 13){
			fn_id_check();	
		}
	});
	
	footer = null;
	content = null;
}
/*********************************************************
 * 아이디 체크 AJAX
 ******************************************************** */
function fn_id_check(){	
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/uss/umt/InfoIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					//사용할수 없는 아이디입니다.
					$("#divModalResult").html("<font color='red'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.useMsg" /></font>");
				}else{
					//사용가능한 아이디입니다.
					$("#divModalResult").html("<font color='blue'><spring:message code="comUssUmt.userManageRegistModal.result" /> : ["+returnData.checkId+"]<spring:message code="comUssUmt.userManageRegistModal.notUseMsg" /></font>");
				}
			}else{ alert("ERROR!");}
		}
		});
}

/*********************************************************
 * 아이디 체크 확인
 ******************************************************** */
function fn_id_checkOk(){
	$.ajax({
		type:"POST",
		url:"<c:url value='/cms/uss/umt/InfoIdDplctCnfirmAjax.do' />",
		data:{
			"checkId": $("#checkIdModal").val()			
		},
		dataType:'json',
		timeout:(1000*30),
		success:function(returnData, status){
			if(status == "success") {
				if(returnData.usedCnt > 0 ){
					alert("<spring:message code="comUssUmt.userManageRegistModal.noIdMsg" />"); //사용이 불가능한 아이디 입니다.

				}else{
					
					$("input[name=emplyrId]").val(returnData.checkId);
					$("#egovModal").setEgovModalClose();
				}
			}else{ alert("ERROR!");}
		}
		});
}

function fnIdCheck1(){
    var retVal;
    var url = "<c:url value='/cms/uss/umt/InfoIdDplctCnfirmView.do'/>";
    var varParam = {};
    varParam.checkId = document.userManageVO.emplyrId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.userManageVO.emplyrId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.userManageVO.emplyrId.value = retVal;
	}
}

function fnListPage(){
    document.userManageVO.action = "<c:url value='/cms/uss/umt/InfoUserManage.do'/>";
    document.userManageVO.submit();
}

function fnInsert(form){


	$("#moblphonNo").val($("#startMoblno").val() +"-"+ $("#mobile2").val() +"-"+$("#mobile3").val());

	if($("#officeTelno2").val()!="" || $("#officeTelno3").val()!=""){
		$("#offmTelno").val( $("#officeTelno2").val() +"-"+$("#officeTelno3").val());
		if($("#offmTelno").val().length <7){
			alert("사무실 전화번호를 확인하거나 비워주세요");
			$("#officeTelno2").focus()
			return false;
		}
	}


	if($("#moblphonNo").val().length <9){
		alert("핸드폰 번호를 바르게 입력해주세요");
		$("#mobile2").focus();
		return false;
	}


	if(confirm("<spring:message code="common.regist.msg" />")){
	    if(validateUserManageVO(form)){
	    	if(form.password.value != form.password2.value){
	            alert("<spring:message code="fail.user.passwordUpdate2" />");
	            return false;
	        }

	    	form.submit();
	        return true;
	    }
	}
}

function fn_egov_inqire_cert() {
	var url = "<c:url value='/uat/uia/EgovGpkiRegist.do' />";
	var popupwidth = '500';
	var popupheight = '400';
	var title = '인증서';

	Top = (window.screen.height - popupheight) / 3;
	Left = (window.screen.width - popupwidth) / 2;
	if (Top < 0) Top = 0;
	if (Left < 0) Left = 0;
	Future = "fullscreen=no,toolbar=no,location=no,directories=no,status=no,menubar=no,	scrollbars=no,resizable=no,left=" + Left + ",top=" + Top + ",width=" + popupwidth + ",height=" + popupheight;
	PopUpWindow = window.open(url, title, Future)
	PopUpWindow.focus();
}

function fn_egov_dn_info_setting(dn) {
	var frm = document.userManageVO;

	frm.subDn.value = dn;
}

/*
if (typeof(opener.fn_egov_dn_info_setting) == 'undefined') {
	alert('메인 화면이 변경되거나 없습니다');
	this.close();
} else {
	opener.fn_egov_dn_info_setting(dn);
	this.close();
}
*/

var onlyNum = /[^0-9]/g;
function checkType(id){
	var tId = "#"+id;
	var value = $(tId).val();
	value= value.replace(onlyNum,"");
	$(tId).val(value);

}



</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body onload="fn_egov_init()">

<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>

	<form:form commandName="userManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoUserInsert.do" name="userManageVO" method="post" onSubmit="fnInsert(document.forms[0]); return false;">

	<h3 class="btitle">
		<caption>${pageTitle} <spring:message code="title.create" /></caption>
	</h3>

	<div class="rows white-box">


	<!-- 등록폼 -->
	<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<colgroup>
			<col width="9%"/>
			<col width="91%"/>
		</colgroup>
	<tbody>
		<!-- 입력/선택 -->
		<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
		<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
		<!-- 일반회원아이디 -->
		<c:set var="title">시스템회원 아이디</c:set>
		<tr>
			<th><label for="emplyrId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="emplyrId" id="emplyrId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" style="width:20;" />
				<button id="btnEmplyrId" class="button" onClick="return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.deptUserManageRegistBtn.idSearch" /></button>
				<div><form:errors path="emplyrId" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 일반회원이름 -->
		<c:set var="title">시스템 회원명</c:set>
		<tr>
			<th><label for="emplyrNm">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:input path="emplyrNm" title="${title} ${inputTxt}" size="50" maxlength="60" />
				<div><form:errors path="emplyrNm" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 비밀번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.pass"/></c:set>
		<tr>
			<th><label for="password">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<form:password path="password" title="${title} ${inputTxt}" size="50" maxlength="20" />
				<span>영문,숫자 조합 8~20자 </span>
				<div><form:errors path="password" cssClass="error" /></div> 
			</td>
		</tr>
		<!-- 비밀번호확인 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passConfirm"/></c:set>
		<tr>
			<th><label for="password2">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
			<input name="password2" id="password2" title="${title} ${inputTxt}" type="password" size="50" maxlength="20" />
			</td>
		</tr>
		<!-- 비밀번호힌트 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passHit"/></c:set>
		<tr style="display:none;">
			<th><label for="passwordHint">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
<%--				<form:select path="passwordHint" id="passwordHint" title="${title} ${inputSelect}" >--%>
<%--					<form:option value="" label="${inputSelect}"/>--%>
<%--					<form:options items="${passwordHint_result}" itemValue="code" itemLabel="codeNm"/>--%>
<%--				</form:select>--%>
				<input type="hidden" id="passwordHint" name="passwordHint" value="P01">
				<div><form:errors path="passwordHint" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 비밀번호정답 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.passOk"/></c:set>
		<tr style="display:none;">
			<th><label for="passwordCnsr">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
<%--				<form:input path="passwordCnsr" id="passwordCnsr" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="50" maxlength="100" />--%>
				<input type="hidden" id="passwordCnsr" name="passwordCnsr" value="answer">
				<div><form:errors path="passwordCnsr" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 소속기관코드 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.insttCode"/></c:set>
		<tr style="display:none;">
			<th><label for="insttCode">${title}</label></th>
			<td class="left">
                    <form:select path="insttCode" id="insttCode" title="${title} ${inputSelect}" >
                       <form:option value="" label="${inputSelect}"/>
                       <form:options items="${insttCode_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="insttCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 조직아이디 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.orgnztId"/></c:set>
		<tr style="display:none;">
			<th><label for="orgnztId">${title}</label></th>
			<td class="left">
                  <form:select path="orgnztId" id="orgnztId" title="${title} ${inputSelect}" >
                       <form:option value="" label="${inputSelect}"/>
                       <form:options items="${orgnztId_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
                    <div><form:errors path="orgnztId" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 직위 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.ofcps"/></c:set>
		<tr style="display:none;">
			<th><label for="ofcpsNm">${title}</label></th>
			<td class="left">
                    <form:input path="ofcpsNm" id="ofcpsNm" title="${title} ${inputTxt}" size="20" maxlength="50" />
                    <div><form:errors path="ofcpsNm" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 사번 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.emplNum"/></c:set>
		<tr style="display:none;">
			<th><label for="emplNo">${title}</label></th>
			<td class="left">
                    <form:input path="emplNo" id="emplNo" title="${title} ${inputTxt}" size="20" maxlength="20" />
                    <div><form:errors path="emplNo" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 성별구분코드 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.saxTypeCode"/></c:set>
		<tr style="display:none;">
			<th><label for="sexdstnCode">${title}</label></th>
			<td class="left">
				<form:select path="sexdstnCode" id="sexdstnCode" title="${title} ${inputSelect}">
					<form:option value="" label="${inputSelect}"/>
					<form:options items="${sexdstnCode_result}" itemValue="code" itemLabel="codeNm"/>
				</form:select>
				 <div><form:errors path="sexdstnCode" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 생일 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.brth"/></c:set>
		<tr style="display:none;">
			<th><label for="brth">${title}</label></th>
			<td class="left">
				<form:input path="brth" id="brth"  title="${title} ${inputTxt}" size="20" maxlength="8" />
                <div><form:errors path="brth" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 집전화번호(지역번호 사무실번호에서 사용중) -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.tel"/></c:set>
		<tr>
			<th><label for="areaNo">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
<%--                    <form:input path="areaNo" id="areaNo" title="${title} ${inputSelect}" size="5" maxlength="5" style="width:40px;" val="02"/>--%>
<%--                    - <form:input path="homemiddleTelno" id="homemiddleTelno" size="5" maxlength="5" style="width:40px;" val="000"/>--%>
<%--                    - <form:input path="homeendTelno" id="homeendTelno"  size="5" maxlength="5" style="width:40px;" val="0000"/>--%>



				<input type="text" id="areaNo" name="areaNo" value="" maxlength="3" class="w100"/>-
				<input type="text" id="homemiddleTelno" name="homemiddleTelno" value="" maxlength="4" class="w100"/>-
				<input type="text" id="homeendTelno" name="homeendTelno" value="" maxlength="4" class="w100"/>
                    <div><form:errors path="areaNo" cssClass="error" /></div>
                    <div><form:errors path="homemiddleTelno" cssClass="error" /></div>
                    <div><form:errors path="homeendTelno" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 전화번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.areaNo"/></c:set>
		<tr style="display:none;" >
			<th><label >${title}</label></th>
			<td class="left">
<%--                    <form:input path="offmTelno" id="offmTelno" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20"  maxlength="15" />--%>


				<div><form:errors path="offmTelno" cssClass="error" /></div>
			</td>
		</tr>

		<!-- 헨드폰번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.phone"/></c:set>
		<tr >
			<th><label for="moblphonNo">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
				<input type="text" id="startMoblno" onkeyup="checkType(this.id)" minlength="3" maxlength="3" class="w100"> <span>-</span>
				<input type="text" id="mobile2" onkeyup="checkType(this.id)" minlength="3" maxlength="4" class="w100"> <span>-</span>
				<input type="text" id="mobile3" onkeyup="checkType(this.id)" minlength="4" maxlength="4" class="w100">
				<input type="hidden" id="moblphonNo" name="moblphonNo">
<%--                    <form:input path="moblphonNo" id="moblphonNo" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="20" maxlength="15" />--%>
                    <div><form:errors path="moblphonNo" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 이메일주소 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.email"/></c:set>
		<tr>
			<th><label for="emailAdres">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="emailAdres" id="mberEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" />
                    <div><form:errors path="emailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 우번번호 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.post"/></c:set>
		<tr style="display:none;">
			<th><label for="zip">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
<%--                    <input name="zip" id="zip" title="${title} ${inputTxt}" type="text" size="20" value="" maxlength="8" style="width:60px;" value="00000"/>--%>
				<input type="hidden" id="zip" name="zip" value="00000"/>
                    <!-- form:hidden path="zip" id="zip" --> 
                    <!-- <button class="btn_s2" onClick="fn_egov_ZipSearch(document.mberManageVO, document.mberManageVO.zip, document.mberManageVO.zip_view, document.mberManageVO.adres);return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />">우번번호검색</button>  -->
                    <div><form:errors path="zip" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 주소 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.addr"/></c:set>
		<tr style="display:none;">
			<th><label for="homeadres">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
<%--                    <form:input path="homeadres" id="homeadres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" val="address1"/>--%>
                   	<input type="hidden" id="homeadres" name="homeadres" value="address"/>
                    <div><form:errors path="homeadres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 상세주소 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.addrDetail"/></c:set>
		<tr style="display:none;">
			<th><label for="detailAdres">${title}</label></th>
			<td class="left">
                    <form:input path="detailAdres" id="detailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="70" maxlength="100" />
                    <div><form:errors path="detailAdres" cssClass="error" /></div>
			</td>
		</tr>
		<!-- 그룹아이디 -->
		
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.groupId"/></c:set>
		<tr>
			<th><label for="groupId">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:select path="groupId" id="groupId" title="${title} ${inputSelect}" >
                        <form:option value="" label="${inputSelect}"/>
                        <form:options items="${groupId_result}" itemValue="code" itemLabel="codeNm"/>
                    </form:select>
<%--					<input type="hidden" id="groupId" name="groupId" value="GROUP_00000000000001">--%>
                    <div><form:errors path="groupId" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 일반회원상태코드 -->
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.status"/></c:set>
		<tr>
			<th><label for="emplyrSttusCode">${title}</label> <span class="pilsu">*</span></th>
			<td class="left">
                    <form:select path="emplyrSttusCode" id="emplyrSttusCode" name="emplyrSttusCode" title="${title} ${inputSelect}" >
                        <form:option value="" label="${inputSelect}"/>
                        <form:options items="${emplyrSttusCode_result}" itemValue="code" itemLabel="codeNm" />
                    </form:select>
                    <div><form:errors path="emplyrSttusCode" cssClass="error"/></div>
			</td>
		</tr>
		<!-- 사용자DN -->
		<!-- 
		<c:set var="title"><spring:message code="comUssUmt.deptUserManageRegist.subDn"/></c:set>
		<tr>
			<th>${title} <span class="pilsu">*</span></th>
			<td class="left">
                    <form:input path="subDn" id="subDn" title="${title} ${inputTxt}" size="40" maxlength="400" style="width:80%;" />
                    <button id="btnSubdn" class="btn_s2" onClick="fn_egov_inqire_cert(); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.deptUserManageRegistBtn.Search" /></button>
                    <div><form:errors path="subDn" cssClass="error" /></div>
			</td>
		</tr>
		 -->
		 <form:hidden path="subDn" />
	</tbody>
	</table>
	</div>
	<!-- 하단 버튼 --> 
	<div class="btn-set right">
		<span class="button"><a href="<c:url value='/cms/uss/umt/InfoUserManage.do?menuTargetNo=${menuInfo.menuTargetNo}' />"  title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		<input type="submit" class="button main" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
	</div><div style="clear:both;"></div>

<!-- div end(wTableFrm)  -->


	<!-- 검색조건 유지 -->
	<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
	<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
	<!-- 우편번호검색 -->
	<input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">


</form:form>
</div>
<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

</body>
</html>
