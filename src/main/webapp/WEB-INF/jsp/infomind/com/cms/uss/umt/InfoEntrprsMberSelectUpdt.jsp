<%
	/**
	 * @Class Name : InfoEntrprsMberSelectUpdt.jsp
	 * @Description : 사용자상세조회, 수정 JSP
	 * @Modification Information
	 * @
	 * @  수정일         수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2020.11.26   이기선              최초 생성
	 * @ 2020.12.18   이기선      JTP 일반회원 UI로 변경
	 *  @author 인포마인드 개발팀
	 *  @since 2020.11.26
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
	<title>${pageTitle} <spring:message code="title.update" /></title><!-- 기업회원관리 수정 -->
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
	<validator:javascript formName="entrprsManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
	<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
	<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
	<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">
	<script type="text/javaScript" language="javascript" defer="defer">

		/*********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_init(){


		}

		function fnListPage(){
			document.entrprsManageVO.action = "<c:url value='/cms/uss/umt/InfoEntrprsMberManage.do'/>";
			document.entrprsManageVO.submit();
		}


		function fnDeleteEntrprs(checkedIds) {
			if(confirm("<spring:message code="common.delete.msg" />")){
				document.entrprsManageVO.checkedIdForDel.value=checkedIds;
				document.entrprsManageVO.action = "<c:url value='/cms/uss/umt/InfoEntrprsMberDelete.do'/>";
				document.entrprsManageVO.submit();
			}
		}

		<%--function fnPasswordMove1(){--%>
		<%--	document.entrprsManageVO.action = "<c:url value='/cms/uss/umt/InfoEntrprsPasswordUpdtView.do'/>";--%>
		<%--	document.entrprsManageVO.submit();--%>
		<%--}--%>


		function fnPassword(entrprsMberId) {



			if(confirm("비밀번호 초기화 하겠습니까?")){

				$.ajax({
					type:"POST",
					url:"<c:url value='/cms/user/mberPasswordUpdt.do' />",
					data:{
						"esntlId": entrprsMberId
					},
					dataType:'json',
					timeout:(1000*30),
					success:function(returnData, status){

						var successCode =returnData.info.successCode;

						if(successCode=="0000"){

							alert("초기화 하였습니다.");
							return;
						}else{

							alert("초기화 실패하였습니다. 관리자 문의 바랍니다.");
							return;
						}

					}
				});


			}

		}


		function fnPasswordMove(entrprsmberId) {
			var p = {
				entrprsmberId : '${entrprsManageVO.entrprsmberId}',
				menuTargetNo : ${menuInfo.menuTargetNo},
				uniqId : '${entrprsManageVO.uniqId}'


			};
			var API_SERVER = "<c:url value='/cms/uss/umt/InfoEntrprsPasswordUpdtView.do' />?entrprsmberId="+entrprsmberId;
			ax5modal.open({
				theme: "primary",
				height: 600,
				width: 1100,
				header: {
					title: '비밀번호 변경',
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
			}, function (d) {

			});
		}

		function fnLockIncorrect(){
			if(confirm("<spring:message code="comUssUmt.common.lockAtConfirm" />")){
				document.entrprsManageVO.action = "<c:url value='/cms/uss/umt/InfoEntrprsMberLockIncorrect.do'/>";
				document.entrprsManageVO.submit();
			}
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


	</script>
</head>
<body onload="fn_egov_init()">


<!-- content start -->
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.update" />
	</h2>

	<form:form commandName="entrprsManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoEntrprsMberSelectUpdt.do" name="entrprsManageVO" method="post" onSubmit="fnUpdate(document.forms[0]); return false;">
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
		<caption>${pageTitle} <spring:message code="title.update" /></caption>
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
							<%--<button id="btnMbrId" type="button" class="button" onClick="return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.entrprsUserManageRegistBtn.idSearch" /></button>--%>
						<div><form:errors path="entrprsmberId" cssClass="error" /></div>
					</td>
					<!-- 회사명 -->
					<th><label for="applcntNm"><spring:message code="comUssUmt.entrprsUserManageRegist.name"/></label> <span class="pilsu">*</span></th>
					<td class="left">
						<form:input path="applcntNm" title="${title} ${inputTxt}" size="20" maxlength="60" />
						<div><form:errors path="applcntNm" cssClass="error" /></div>
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
					<th><label for="areaNo"><spring:message code="comUssUmt.entrprsUserManageRegist.tel"/></label></th>
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
					<td class="left" colspan="3">
						<form:input path="applcntEmailAdres" id="applcntEmailAdres" title="${title} ${inputTxt}" cssClass="txaIpUmt" size="30" maxlength="50" />
						<div><form:errors path="applcntEmailAdres" cssClass="error" /></div>
					</td>
				</tr>
				<tr>
					<th><label for="groupId">그룹</label> <span class="pilsu">*</span></th>
					<td class="left" colspan="3">

						<form:select path="groupId">
							<form:options items="${groupIdList}" itemValue="groupId" itemLabel="groupNm"/>
						</form:select>

					</td>
				</tr>




				<!-- 기업회원상태코드 disabled="${searchUsrCheck}-->
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

		<c:if test="${menuInfo.authVO.modifyYn >0}">
			<button type="submit" name="btn_modify" id="btn_modify" class="button main" title="<spring:message code="title.update" /> <spring:message code="input.button" />" ><spring:message code="button.update" /></button>
			<button type="button" class="button" onClick="fnPassword('${entrprsManageVO.uniqId}'); return false;" title="비밀번호초기화">비밀번호초기화</button>
			<button type="button" class="button" onClick="fnPasswordMove('${entrprsManageVO.uniqId}'); return false;" title="비밀번호변경">비밀번호변경</button>
		</c:if>

		<button type="button" class="button" onClick="fnListPage(); return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></button>


	</div>
	<input name="clCode" id="clCode" type="hidden" value="<c:out value="${result.clCode}" />">


</div>
</form:form>
<!-- content end -->
</body>
</html>
