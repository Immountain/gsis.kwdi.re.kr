<%
 /**
  * @Class Name : InfoEntrprsMberInsert.jsp
  * @Description : 기업회원등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.11.27   이기선              최초 생성
  * @ 2020.12.18   이기선      JTP 일반회원 UI로 변경
  *  @author 인포마인드 개발팀
  *  @since 2020.11.27
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.entrprsUserManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 기업회원관리 등록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="entrprsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	//모달 셋팅
	fn_modal_setting();


}
/*********************************************************
 * 모달셋팅
 ******************************************************** */
function fn_modal_setting(){
	//버튼에 모달 연결
	$("#btnMbrId").egovModal( "egovModal" );

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

	footer += "<span class='button' id='btnModalOk' onclick='fn_id_checkOk()'><a href='#'><spring:message code="button.confirm" /></a></span>&nbsp;";//확인
	footer += "<span class='button' id='btnModalSelect' onclick='fn_id_check()'><a href='#'><spring:message code="button.inquire" /></a></span>&nbsp;";//조회

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
			}else{ alert("ERROR!");return;}
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
					return;
				}else{

					$("input[name=entrprsmberId]").val(returnData.checkId);
					$("#egovModal").setEgovModalClose();

				}
			}else{ alert("ERROR!");return;}
		}
		});
}


function fnIdCheck1(){
    var retVal;
    var url = "<c:url value='/cms/uss/umt/InfoIdDplctCnfirmView.do'/>";
    var varParam = new Object();
    varParam.checkId = document.entrprsManageVO.entrprsmberId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
        document.entrprsManageVO.entrprsmberId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.entrprsManageVO.entrprsmberId.value = retVal;
	}
}


function fnListPage(){
    document.entrprsManageVO.action = "<c:url value='/cms/uss/umt/InfoEntrprsMberManage.do'/>";
    document.entrprsManageVO.submit();
}

function fnInsert(form){

	if($("#entrprsmberId").val()=="") {

		alert('아이디는 필수항목입니다.');
		$("#entrprsmberId").focus();
		return false;

	}

	if($("#cmpnyNm").val()=="") {

		alert('회원이름은 필수항목입니다.');
		$("#cmpnyNm").focus();
		return false;

	}


	var pw = $("#entrprsMberPassword").val();
	var num = pw.search(/[0-9]/g);
	var eng = pw.search(/[a-z]/ig);
	var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	if(pw.length < 8 || pw.length > 20){

		alert("8자리 ~ 20자리 이내로 입력해주세요.");
		pw.focus;
		return false;
	}else if(pw.search(/\s/) != -1){
		alert("비밀번호는 공백 없이 입력해주세요.");
		pw.focus;
		return false;
	}else if(num < 0 || eng < 0 || spe < 0 ){
		alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		pw.focus;
		return false;
	}

	if($("#entrprsMberPassword").val()!=$("#entrprsMberPassword2").val()) {

		alert('비밀번호는 일치해야합니다.');
		$("#password2").focus();
		return false;

	}

	if($("#middleMoblno").val()=="") {

		alert('핸드폰번호는 필수항목입니다.');
		$("#middleMoblno").focus();
		return false;

	}else if($("#endMoblno").val()=="") {

		alert('핸드폰번호는 필수항목입니다.');
		$("#endMoblno").focus();
		return false;

	}

	if($("#entrprsMiddleTelno").val()=="") {

		alert('전화번호는 필수항목입니다.');
		$("#entrprsMiddleTelno").focus();
		return false;

	}else if($("#entrprsEndTelno").val()=="") {

		alert('전화번호는 필수항목입니다.');
		$("#entrprsEndTelno").focus();
		return false;

	}

	if($("#applcntEmailAdres").val()=="") {

		alert('이메일은 필수항목입니다.');
		$("#applcntEmailAdres").focus();
		return false;

	}

/*
	if($("#jtpCloudId").val()=="") {

		alert('소속부서는 필수항목입니다.');
		fnSearch_CloudNm();
		return false;

	}
*/
	if($("#entrprsMberSttus").val()=="") {

		alert('회원상태코드는 필수항목입니다.');
		$("#entrprsMberSttus").focus();
		return false;

	}


	if(confirm("<spring:message code="common.regist.msg" />")){
		if(validateEntrprsManageVO(form)){
			if(form.password.value != form.password2.value){
	            alert("<spring:message code="fail.user.passwordUpdate2" />");
	            return false;
	        }
			form.submit();
			return true;
	    }
	}
}



/* ********************************************************
 * 소속기업/기관 팝업
 ******************************************************** */
function fnSearch_OrganNm() {


	var p = {};
	var API_SERVER = "<c:url value='/jtp/cms/organ/popOrganMstList.do' />";
	ax5modal.open({
		theme: "primary",
		height: 500,
		width: 1000,
		header: {
			title: '기업/기관찾기',
			btns: {
				close: {
					label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
						// modal.close();
						ax5modal.close();
					}
				}
			}
		},
		iframe: {
			method: "get",
			url: API_SERVER,
			param: p
		},

	}, function (data) {


		var jsonObj = JSON.parse(data);
		console.log(jsonObj);

		$('#jtpOrganCd').val(jsonObj.jtpOrganCd);
		$('#jtpOrganNm').val(jsonObj.organNm);

	});
}

	function fnSearch_CloudNm() {


		var p = {};
		var API_SERVER = "<c:url value='/jtp/cms/cloud/popcloudMstList.do' />";
		ax5modal.open({
			theme: "primary",
			height: 500,
			width: 1000,
			header: {
				title: '부서(서비스활용기관)찾기',
				btns: {
					close: {
						label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
							// modal.close();
							ax5modal.close();
						}
					}
				}
			},
			iframe: {
				method: "get",
				url: API_SERVER,
				param: p
			},

		}, function (data) {


			var jsonObj = JSON.parse(data);
			console.log(jsonObj);

			$('#jtpCloudId').val(jsonObj.jtpCloudId);
			$('#jtpCloudNm').val(jsonObj.jtpCloudNm);

		});


}


</script>
<style>
.modal-content {width: 400px;}
</style>
</head>
<body onload="fn_egov_init()">
<!-- content start -->
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>

	 <form:form commandName="entrprsManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoEntrprsMberInsert.do" name="entrprsManageVO" method="post" onSubmit="fnInsert(document.forms[0]); return false;">

	<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
	<input name="checkedIdForDel" type="hidden" />
	<!-- 검색조건 유지 -->
	<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
	<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
	<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
	<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">

	<!-- 우편번호검색 -->
	<input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />

	<h3 class="btitle">
		<caption>${pageTitle} <spring:message code="title.create" /></caption>
	</h3>

	<div class="rows white-box">

		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<colgroup>
				<col width="10%"/>
				<col width=""/>
				<col width="10%"/>
				<col width=""/>
			</colgroup>
			<tbody>

				<!-- 입력/선택 -->
					<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
					<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>


				<tr>
					<!-- 기업회원아이디 -->
					<th><label for="entrprsmberId"><spring:message code="comUssUmt.entrprsUserManageRegist.id"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<form:input path="entrprsmberId" id="entrprsmberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" />
						<button id="btnMbrId" type="button" class="button" onClick="return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.entrprsUserManageRegistBtn.idSearch" /></button>
						<div><form:errors path="entrprsmberId" cssClass="error" /></div>
					</td>
					<!-- 회사명 -->
					<th><label for="cmpnyNm"><spring:message code="comUssUmt.entrprsUserManageRegist.name"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<form:input path="cmpnyNm" title="${title} ${inputTxt}" size="20" maxlength="60" />
						<div><form:errors path="cmpnyNm" cssClass="error" /></div>
					</td>

				</tr>

				<tr><!-- 비밀번호 -->
					<th><label for="entrprsMberPassword"><spring:message code="comUssUmt.entrprsUserManageRegist.pass"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<form:password path="entrprsMberPassword" title="${title} ${inputTxt}" size="50" maxlength="20" />
						<div><form:errors path="entrprsMberPassword" cssClass="error" /></div>
					</td>
					<!-- 비밀번호확인 -->
					<th><label for="entrprsMberPassword2"><spring:message code="comUssUmt.entrprsUserManageRegist.passConfirm"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<input name="entrprsMberPassword2" id="entrprsMberPassword2" title="${title} ${inputTxt}" type="password" size="50" maxlength="20" />
					</td>
				</tr>

				<tr><!-- 핸드폰번호 -->
					<th><spring:message code="comUssUmt.userManageRegist.phone"/> <span class="pilsu">*</span></th>
					<td class="left">

						  <form:input path="startMoblno" id="startMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
						- <form:input path="middleMoblno" id="middleMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
						- <form:input path="endMoblno" id="endMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
						<div><form:errors path="startMoblno" cssClass="error" /></div>
						<div><form:errors path="middleMoblno" cssClass="error" /></div>
						<div><form:errors path="endMoblno" cssClass="error" /></div>
					</td>
					<!-- 회사전화번호 -->
					<th><label for="areaNo"><spring:message code="comUssUmt.entrprsUserManageRegist.tel"/></label> <span class="pilsu">*</span></th>
					<td class="left">

						  <form:input path="areaNo" id="areaNo" size="5" maxlength="5" style="width:60px;"/>
						- <form:input path="entrprsMiddleTelno" id="entrprsMiddleTelno" size="5" maxlength="5" style="width:60px;"/>
						- <form:input path="entrprsEndTelno" id="entrprsEndTelno" size="5" maxlength="5" style="width:60px;"/>
						<div><form:errors path="areaNo" cssClass="error" /></div>
						<div><form:errors path="entrprsMiddleTelno" cssClass="error" /></div>
						<div><form:errors path="entrprsEndTelno" cssClass="error" /></div>
					</td>
				</tr>

				<!-- 신청자이메일주소 -->
					<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageList.regMail"/></c:set>
				<tr>
					<th><label for="applcntEmailAdres">${title}</label> <span class="pilsu">*</span></th>
					<td class="left" clospan="3">
						<form:input path="applcntEmailAdres" id="applcntEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" />
						<div><form:errors path="applcntEmailAdres" cssClass="error" /></div>
					</td>
				</tr>
				<!-- 기업회원상태코드 -->
					<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.status"/></c:set>
				<tr>
					<th><label for="entrprsMberSttus">${title}</label> <span class="pilsu">*</span></th>
					<td class="left" colspan="3">
						<form:select path="entrprsMberSttus" id="entrprsMberSttus" title="${title} ${inputSelect}">
							<form:option value="" label="${inputSelect}"/>
							<form:options items="${entrprsMberSttus_result}" itemValue="code" itemLabel="codeNm"/>
						</form:select>
						<div><form:errors path="entrprsMberSttus" cssClass="error"/></div>

					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<button type="submit" name="btn_save" id="btn_save" class="button"
									   title="<spring:message code="title.create" /> <spring:message code="input.button" />" ><spring:message code="button.create" /></button>
		<button type="button" class="button" onClick="fnListPage(); return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></button>

	</div>

</div>
</form:form>

<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

</body>
</html>
