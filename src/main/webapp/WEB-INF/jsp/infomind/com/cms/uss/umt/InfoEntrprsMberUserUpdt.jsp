
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script type="text/javaScript" language="javascript" defer="defer">

/*********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){


	selectBoxInit();

}

function fnListPage(){
    document.entrprsManageVO.action = "<c:url value='/jtp/cms/jtpmberreq/selectUserReqChngGb1List.do'/>";
    document.entrprsManageVO.submit();
}



function fnUpdate(form){
	if(confirm("<spring:message code="common.save.msg" />")){
	    if(validateEntrprsManageVO(form)){
	    	form.submit();
	        return true;
	    }else{
	    	return false;
	    }
	}
}

/*********************************************************
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


</script>
</head>
<body onload="fn_egov_init()">


<!-- content start -->
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>서비스활용 변경요청
	</h2>

	<form:form commandName="entrprsManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoEntrprsMberUpdtUser.do" name="entrprsManageVO" method="post" onSubmit="fnUpdate(document.forms[0]); return false;">
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
	<!-- 사용자유형정보 : password 수정화면으로 이동시 타겟 유형정보 확인용, 만약검색조건으로 유형이 포함될경우 혼란을 피하기위해 userTy명칭을 쓰지 않음-->
	<input type="hidden" name="userTyForPassword" value="<c:out value='${entrprsManageVO.userTy}'/>" />
	<!-- for validation -->
	<input type="hidden" name="entrprsMberPassword" value="Test#$123">
	<input type="hidden" name="selectedId"  value="<c:out value='${entrprsManageVO.entrprsmberId}'/>"/>
	<input type="hidden" name="uniqId" value="<c:out value='${entrprsManageVO.uniqId}'/>"/>

		<h3 class="btitle">
			<caption>기존정보</caption>
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
				<div class="wTableFrm">

					<!-- 입력/선택 -->
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<c:set var="inputSelect"><spring:message code="input.cSelect" /></c:set>
					<tr>
						<!-- 기업회원아이디 -->
						<th><label for="entrprsmberId"><spring:message code="comUssUmt.entrprsUserManageRegist.id"/></label> <span class="pilsu">*</span></th>
						<td class="left">
							<form:input path="entrprsmberId" id="entrprsmberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" />
						</td>
						<!-- 회사명 -->
						<th><label for="cmpnyNm"><spring:message code="comUssUmt.entrprsUserManageRegist.name"/></label> <span class="pilsu">*</span></th>
						<td class="left">
							<form:input path="cmpnyNm" title="${title} ${inputTxt}" size="20" maxlength="60" readonly="true" />
						</td>

					</tr>


					<tr><!-- 핸드폰번호 -->
						<th><spring:message code="comUssUmt.userManageRegist.phone"/> <span class="pilsu">*</span></th>
						<td class="left">
							<info:select name="startMoblno" id="startMoblno"  width="150"  val="${entrprsManageVO.startMoblno}"  disabled="true" />
							- <form:input path="middleMoblno" id="middleMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;" readonly="true"/>
							- <form:input path="endMoblno" id="endMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;" readonly="true"/>
							<div><form:errors path="startMoblno" cssClass="error" /></div>
							<div><form:errors path="middleMoblno" cssClass="error" /></div>
							<div><form:errors path="endMoblno" cssClass="error" /></div>
						</td>
						<!-- 회사전화번호 -->
						<th><label for="areaNo"><spring:message code="comUssUmt.entrprsUserManageRegist.tel"/></label> <span class="pilsu">*</span></th>
						<td class="left">
							<info:select name="areaNo" id="areaNo"  width="150"  val="${entrprsManageVO.areaNo}"  disabled="true" />
							- <form:input path="entrprsMiddleTelno" id="entrprsMiddleTelno" size="5" maxlength="5" style="width:60px;" readonly="true"/>
							- <form:input path="entrprsEndTelno" id="entrprsEndTelno" size="5" maxlength="5" style="width:60px;" readonly="true"/>
						</td>
					</tr>

					<!-- 신청자이메일주소 -->
						<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageList.regMail"/></c:set>
					<tr>
						<th><label for="applcntEmailAdres">${title}</label> <span class="pilsu">*</span></th>
						<td class="left" clospan="6">
							<form:input path="applcntEmailAdres" id="applcntEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" readonly="true" />

						</td>
					</tr>
					<tr><!-- 소속기업/기관 -->
						<th style="width:100px"  ><label for="jtpOrganCd"><spring:message code="comUssUmt.userManageRegist.jtporgancd"/>  <span class="pilsu">*</span></label></th>
						<td class="left"><form:input path="jtpOrganCd" title="${title} ${inputTxt}" maxlength="30"  readonly="true" />
							<form:input path="jtpOrganNm" size="30" maxlength="100"  readonly="true" />

						</td>
						<!-- 부서 -->
						<th style="width:100px"  ><label for="jtpCloudId">서비스활용기관 <span class="pilsu">*</span></label></th>
						<td class="left">

							    <form:input path="jtpCloudId" title="${title} ${inputTxt}" maxlength="30"  readonly="true" />
								<form:input path="jtpCloudNm" size="30" maxlength="100"  readonly="true" />

						</td>
					</tr>
				</tbody>
			</table>
		</div>
	<h3 class="btitle">
		<caption>서비스활용  변경요청 내용</caption>
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
			<tr><!-- 소속기업/기관 -->
				<th style="width:100px"  ><label for="jtpOrganCd"><spring:message code="comUssUmt.userManageRegist.jtporgancd"/>  <span class="pilsu">*</span></label></th>
				<td class="left">


					<input type="text" id="aftrJtpOrganCd" name="aftrJtpOrganCd" value="${jtpMberReqVO.aftrJtpOrganCd}" readonly>
					<input type="text" id="organNm" name="organNm" value="${jtpMberReqVO.organNm}" readonly>

				</td>
				<!-- 부서 -->
				<th style="width:100px"  ><label for="jtpCloudId">서비스활용기관<span class="pilsu">*</span></label></th>
				<td class="left">
					<input type="text" id="aftrJtpCloudId" name="aftrJtpCloudId" value="${jtpMberReqVO.aftrJtpCloudId}" readonly>
					<input type="text" id="aftrJtpCloudIdNm" name="aftrJtpCloudIdNm" value="${jtpMberReqVO.aftrJtpCloudIdNm}" readonly>
				</td>
			</tr>
			<tr>
				<th style="width:100px"  ><label for="remark1">신청사유<span class="pilsu">*</span></label></th>
				<td class="left">
					<input type="text" id="remark1" class="w400" name="remark1" value="${jtpMberReqVO.remark1}" readonly>
				</td>
			</tr>

			<input name="statGb" id="statGb" type="hidden" value="2">
			<input name="reqSeq" id="reqSeq" type="hidden" value="<c:out value="${jtpMberReqVO.reqSeq}" />">
			<input name="busiRegistNo" id="busiRegistNo" type="hidden" value="<c:out value="${jtpMberReqVO.busiRegistNo}" />">
			<input name="ownerNm" id="ownerNm" type="hidden" value="<c:out value="${jtpMberReqVO.ownerNm}" />">
			<input name="address1" id="address1" type="hidden" value="<c:out value="${jtpMberReqVO.address1}" />">
			<input name="address2" id="address2" type="hidden" value="<c:out value="${jtpMberReqVO.address2}" />">
			<input name="carrierTob" id="carrierTob" type="hidden" value="<c:out value="${jtpMberReqVO.carrierTob}" />">
			<input name="zipNo" id="zipNo" type="hidden" value="<c:out value="${jtpMberReqVO.zipNo}" />">
			<input name="esntlId" id="esntlId" type="hidden" value="<c:out value="${jtpMberReqVO.esntlId}" />">


			</tbody>
		</table>
	</div>
		<!-- 하단 버튼 -->
		<div class="btn-set right">

			<c:if test="${menuInfo.authVO.modifyYn >0}">
					<button type="submit" name="btn_modify" id="btn_modify" class="button main">변경승인</button>
			</c:if>

		    <button type="button" class="button" onClick="fnListPage(); return false;"><spring:message code="button.list" /></button>


		</div>



</div>
</form:form>
<!-- content end -->
