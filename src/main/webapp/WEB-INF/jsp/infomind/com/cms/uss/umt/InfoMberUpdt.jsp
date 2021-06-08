<%
	/**
	 * @Class Name : InfoMberSelectUpdt.jsp
	 * @Description : 일반회원상세조회, 수정 JSP
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
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>

	<title>${pageTitle} 정보</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
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
			document.mberManageVO.action = "<c:url value='/jtp/cms/jtpmberreq/selectUserReqChngGb0List.do'/>";
			document.mberManageVO.submit();
		}



		function fnUpdate(form){
			if(confirm("변경 하시겠습니까?")){

					document.mberManageVO.submit();
					return true;


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

	 /**********************************************************
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
</head>
<body onload="fn_egov_init()">



<!-- content start -->
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} 정보
	</h2>
	<!-- content start -->
	<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoMberUpdtUser.do" name="mberManageVO"  method="post" onSubmit="fnUpdate(document.forms[0]); return false;">

	<div class="wTableFrm">
		<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
		<input name="checkedIdForDel" type="hidden" />
		<!-- 검색조건 유지 -->
		<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
		<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
		<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
		<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<!-- 사용자유형정보 : password 수정화면으로 이동시 타겟 유형정보 확인용, 만약검색조건으로 유형이 포함될경우 혼란을 피하기위해 userTy명칭을 쓰지 않음-->
		<input type="hidden" name="userTyForPassword" value="<c:out value='${mberManageVO.userTy}'/>" />
		<!-- for validation -->
		<!-- 타이틀 -->
		<h3 class="btitle">
			<caption>${pageTitle} 정보</caption>
		</h3>

		<div class="rows white-box">

		<!-- 수정폼 -->
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
			<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
				<tr>
					<!-- 일반회원아이디 -->
					<th><label for="mberId"><spring:message code="comUssUmt.userManageRegist.id"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<form:input path="mberId" id="mberId" title="${title} ${inputTxt}" size="20" readonly="true" maxlength="20" />
						<form:errors path="mberId" cssClass="error" />
						<form:hidden path="uniqId" />
					</td>
					<!-- 일반회원이름 -->
					<th><label for="mberNm"><spring:message code="comUssUmt.userManageRegist.name"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<form:input path="mberNm" title="${title} ${inputTxt}" size="20" maxlength="60" readonly="true"/>
						<div><form:errors path="mberNm" cssClass="error" /></div>
					</td>
				</tr>



				<tr><!-- 핸드폰번호 -->
					<th><spring:message code="comUssUmt.userManageRegist.phone"/> <span class="pilsu">*</span></th>
					<td class="left">
						<info:select name="startMoblno" id="startMoblno" title="핸드폰번호"  width="150"   disabled="true"/>
						- <form:input path="middleMoblno" id="middleMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"  readonly="true"/>
						- <form:input path="endMoblno" id="endMoblno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"  readonly="true"/>
					</td>
					<!-- 전화번호 -->
					<th><label for="areaNo"><spring:message code="comUssUmt.userManageRegist.tel"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<info:select name="areaNo" id="areaNo"  width="150" title="전화번호" disabled="true"    />
						- <form:input path="middleTelno" id="middleTelno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;" readonly="true"/>
						- <form:input path="endTelno" id="endTelno" cssClass="txaIpUmt"  maxlength="4" style="width:60px;" readonly="true"/>
						<div><form:errors path="areaNo" cssClass="error" /></div>
						<div><form:errors path="middleTelno" cssClass="error" /></div>
						<div><form:errors path="endTelno" cssClass="error" /></div>
					</td>
				</tr>


				<!-- 이메일주소 -->
					<c:set var="title"><spring:message code="comUssUmt.userManageRegist.email"/></c:set>
				<tr>
					<th><label for="mberEmailAdres">${title}</label> <span class="pilsu">*</span></th>
					<td class="left" colspan="3">
						<form:input path="mberEmailAdres" id="mberEmailAdres" title="${title} ${inputTxt}" size="30" maxlength="50" readonly="true"/>
						<div><form:errors path="mberEmailAdres" cssClass="error" /></div>
					</td>
				</tr>
				<!-- 소속기업/기관 -->
				<c:set var="title"><spring:message code="comUssUmt.userManageRegist.jtporgancd"/> </c:set>
				<tr>
					<th style="width:100px"  ><label for="jtpOrganCd">${title} <span class="pilsu">*</span></label></th>
					<td colspan="3" class="left"><form:input path="jtpOrganCd" title="${title} ${inputTxt}" maxlength="30"  readonly="true" />
						<form:input path="jtpOrganNm" size="30" maxlength="100"  readonly="true" />
						<div><form:errors path="jtpOrganCd" cssClass="error" /></div>
					</td>
				</tr>
			</tbody>

		</table>

	</div>

		<h3 class="btitle">
			<caption>소속기업/기관  변경요청 내용</caption>
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
					<th style="width:100px"  ><label for="aftrJtpCloudId">서비스활용기관<span class="pilsu">*</span></label></th>
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
				</tbody>
			</table>
		</div>

		<h3 class="btitle">
			<caption>승인여부</caption>
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
					<th>승인여부<span class="pilsu">*</span></th>
					<td class="left">
						<select id="statGb" name="statGb">
							<option value="1">승인</option>
							<option value="2">거절</option>
						</select>
					</td>
					<th style="width:100px"  ><label for="remark2">사유<span class="pilsu">*</span></label></th>
					<td class="left">
						<input type="text" id="remark2" class="w400" name="remark2" value="${jtpMberReqVO.remark2}" >
					</td>
				</tr>

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
	<input name="reqSeq" id="reqSeq" type="hidden" value="<c:out value="${jtpMberReqVO.reqSeq}" />">
	<input name="busiRegistNo" id="busiRegistNo" type="hidden" value="<c:out value="${jtpMberReqVO.busiRegistNo}" />">
	<input name="ownerNm" id="ownerNm" type="hidden" value="<c:out value="${jtpMberReqVO.ownerNm}" />">
	<input name="address1" id="address1" type="hidden" value="<c:out value="${jtpMberReqVO.address1}" />">
	<input name="address2" id="address2" type="hidden" value="<c:out value="${jtpMberReqVO.address2}" />">
	<input name="carrierTob" id="carrierTob" type="hidden" value="<c:out value="${jtpMberReqVO.carrierTob}" />">
	<input name="zipNo" id="zipNo" type="hidden" value="<c:out value="${jtpMberReqVO.zipNo}" />">
	<input name="esntlId" id="esntlId" type="hidden" value="<c:out value="${jtpMberReqVO.esntlId}" />">




</form:form>

