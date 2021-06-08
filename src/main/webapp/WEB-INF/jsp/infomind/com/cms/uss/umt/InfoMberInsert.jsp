<%
 /**
  * @Class Name : InfoMberInsert.jsp
  * @Description : 일반회원등록 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.12.01   이기선              최초 생성
  * @ 2020.12.16   이기선      JTP 일반회원 UI로 변경
  *  @author 인포마인드 개발팀
  *  @since 2020.12.01
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="mberManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
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
	selectBoxInit();

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
					
					$("input[name=mberId]").val(returnData.checkId);
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
    varParam.checkId = document.mberManageVO.mberId.value;
    var openParam = "dialogWidth:303px;dialogHeight:250px;scroll:no;status:no;center:yes;resizable:yes;";
        
    alert(1);
    return false;
    retVal = window.showModalDialog(url, varParam, openParam);
    if(retVal) {
    	document.mberManageVO.mberId.value = retVal;
    }
}

function showModalDialogCallback(retVal) {
	if(retVal) {
	    document.mberManageVO.mberId.value = retVal;
	}
}

function fnListPage(){
    document.mberManageVO.action = "<c:url value='/cms/uss/umt/InfoMberManage.do'/>";
    document.mberManageVO.submit();
}

function fnInsert(form){


	if($("#mberId").val()=="") {

		alert('아이디는 필수항목입니다.');
		$("#mberId").focus();
		return false;

	}

	if($("#mberNm").val()=="") {

		alert('회원이름은 필수항목입니다.');
		$("#mberNm").focus();
		return false;

	}


	var pw = $("#password").val();
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

	if($("#password").val()!=$("#password2").val()) {

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

	if($("#middleTelno").val()=="") {

		alert('전화번호는 필수항목입니다.');
		$("#middleTelno").focus();
		return false;

	}else if($("#endTelno").val()=="") {

		alert('전화번호는 필수항목입니다.');
		$("#endTelno").focus();
		return false;

	}

	if($("#emailAdres").val()=="") {

		alert('이메일은 필수항목입니다.');
		$("#emailAdres").focus();
		return false;

	}

	if($("#jtpOrganCd").val()=="") {

		alert('소속기업/기관은 필수항목입니다.');
		fnSearch_OrganNm();
		return false;

	}

	if($("#mberSttus").val()=="") {

		alert('회원상태코드는 필수항목입니다.');
		$("#mberSttus").focus();
		return false;

	}


	if(confirm("<spring:message code="common.regist.msg" />")){	
		if(validateMberManageVO(form)){
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
     * selectBox
     ******************************************************** */
function selectBoxInit() {
	/** 처음휴대폰번호 selectBox */
	var API_SERVER = "<c:url value='/cms/code/search/selectComtccmmndetailcodeList.do' />";
	ajaxLoadSelect({
		url: API_SERVER,
		params: [
			{name: 'codeId', value: 'MOBILE_NUM'}

		],
		selectboxNm: 'startMoblno'
	});

	/** 지역전화번호 selectBox */
	var API_SERVER = "<c:url value='/cms/code/search/selectComtccmmndetailcodeList.do' />";
	ajaxLoadSelect({
		url: API_SERVER,
		params: [
			{name: 'codeId', value: 'AREA_NUM'}

		],
		selectboxNm: 'areaNo'
	});
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

	<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoMberInsert.do" name="mberManageVO"  method="post" onSubmit="fnInsert(document.forms[0]); return false;">

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

			<tr><!-- 일반회원아이디 -->
				<th><label for="mberId"><spring:message code="comUssUmt.userManageRegist.id"/></label> <span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="mberId" id="mberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" style="width:20" />
					<button id="btnMbrId" class="button" onClick="return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.userManageRegistBtn.idSearch" /></button>
					<div><form:errors path="mberId" cssClass="error" /></div>
				</td>
				<!-- 일반회원이름 -->
				<th><label for="mberNm"><spring:message code="comUssUmt.userManageRegist.name"/></label> <span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="mberNm" title="${title} ${inputTxt}" size="20" maxlength="60" />
					<div><form:errors path="mberNm" cssClass="error" /></div>
				</td>

			</tr>

			<tr><!-- 비밀번호 -->
				<th><label for="password"><spring:message code="comUssUmt.userManageRegist.pass"/></label> <span class="pilsu">*</span></th>
				<td class="left">
					<form:password path="password" title="${title} ${inputTxt}" size="50" maxlength="20" />
					<div><form:errors path="password" cssClass="error" /></div>
				</td>
				<!-- 비밀번호확인 -->
				<th><label for="password2"><spring:message code="comUssUmt.userManageRegist.passConfirm"/></label> <span class="pilsu">*</span></th>
				<td class="left">
					<input name="password2" id="password2" title="${title} ${inputTxt}" type="password" size="50" maxlength="20" />
				</td>
			</tr>

			<tr><!-- 핸드폰번호 -->
				<th><spring:message code="comUssUmt.userManageRegist.phone"/> <span class="pilsu">*</span></th>
				<td class="left">
					<info:select name="startMoblno" id="startMoblno"  width="150"  val="${mberManageVO.startMoblno}"  />
					- <form:input path="middleMoblno" id="middleMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
					- <form:input path="endMoblno" id="endMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
					<div><form:errors path="startMoblno" cssClass="error" /></div>
					<div><form:errors path="middleMoblno" cssClass="error" /></div>
					<div><form:errors path="endMoblno" cssClass="error" /></div>
				</td>
				<!-- 전화번호 -->
				<th><label for="areaNo"><spring:message code="comUssUmt.userManageRegist.tel"/></label> <span class="pilsu">*</span></th>
				<td class="left">
					<info:select name="areaNo" id="areaNo"  width="150"  val="${mberManageVO.areaNo}"  />
					- <form:input path="middleTelno" id="middleTelno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
					- <form:input path="endTelno" id="endTelno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
					<div><form:errors path="areaNo" cssClass="error" /></div>
					<div><form:errors path="middleTelno" cssClass="error" /></div>
					<div><form:errors path="endTelno" cssClass="error" /></div>
				</td>
			</tr>
			<!-- 이메일주소 -->
			<c:set var="title"><spring:message code="comUssUmt.userManageRegist.email"/></c:set>
			<tr>
				<th><label for="mberEmailAdres">${title}</label> <span class="pilsu">*</span></th>
				<td class="left">
						<form:input path="mberEmailAdres" id="mberEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" />
						<div><form:errors path="mberEmailAdres" cssClass="error" /></div>
				</td>
			</tr>
			<!-- 소속기업/기관 -->
			<c:set var="title"><spring:message code="comUssUmt.userManageRegist.jtporgancd"/> </c:set>
			<tr>
				<th style="width:100px"  ><label for="jtpOrganCd">${title} <span class="pilsu">*</span></label></th>
				<td colspan="3" class="left"><form:input path="jtpOrganCd" title="${title} ${inputTxt}" maxlength="30"  readonly="true" />
					<input type="button" class="button" onclick="fnSearch_OrganNm();" value="찾기">
					<form:input path="jtpOrganNm" size="30" maxlength="100"  readonly="true" />
					<div><form:errors path="jtpOrganCd" cssClass="error" /></div>
				</td>
			</tr>
			<!-- 일반회원상태코드 -->
			<c:set var="title"><spring:message code="comUssUmt.userManageRegist.status"/></c:set>
			<tr>
				<th><label for="mberSttus">${title}</label> <span class="pilsu">*</span></th>
				<td class="left">
						<form:select path="mberSttus" id="mberSttus" title="${title} ${inputSelect}">
							<form:option value="" label="${inputSelect}"/>
							<form:options items="${mberSttus_result}" itemValue="code" itemLabel="codeNm"/>
						</form:select>
						<div><form:errors path="mberSttus" cssClass="error"/></div>
				</td>
			</tr>
		</tbody>
		</table>
	</div>

	<!-- 하단 버튼 --> 
	<div class="btn-set right">
		<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
		<button class="button" onClick="fnListPage(); return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></button>
	</div><div style="clear:both;"></div>

</div><!-- div end(wTableFrm)  -->

<input name="checkedIdForDel" type="hidden" />
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">

 <!-- 우편번호검색 -->
 <input type="hidden" name="zip_url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
</form:form>

<!-- Egov Modal include  -->
<c:import url="/EgovModal.do" charEncoding="utf-8">
	<c:param name="scriptYn" value="Y" />
	<c:param name="modalName" value="egovModal" />
</c:import>

</body>
</html>
