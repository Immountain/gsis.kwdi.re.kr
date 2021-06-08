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
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
	<title>${pageTitle} <spring:message code="title.update" /></title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
	<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
	<validator:javascript formName="mberManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
	<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
	<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
	<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

	<script type="text/javaScript" language="javascript" defer="defer">

		$(document).ready(function(){
			$('#fnPasswordMove').click(function (){
				var p = {

					menuTargetNo : $('#menuTargetNo').val(),
					mberId : $('#mberId').val()
				};

				var API_SERVER = '<c:url value='/cms/uss/umt/InfoMberPasswordUpdtView.do'/>';
				ax5modal.open({
					theme: "primary",
					height: 462,
					width: 857,
					header: {
						title: '일반회원 비밀번호변경',
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
				}, function (d){
				});
			});
		})

		function fn_egov_init() {
		}

		function fnListPage(){
			document.mberManageVO.action = "<c:url value='/cms/uss/umt/InfoMberManage.do'/>";
			document.mberManageVO.submit();
		}

		function fnDeleteMber(checkedIds) {
			if(confirm("<spring:message code="common.delete.msg" />")){
				document.mberManageVO.checkedIdForDel.value=checkedIds;
				document.mberManageVO.action = "<c:url value='/cms/uss/umt/InfoMberDelete.do'/>";
				document.mberManageVO.submit();
			}
		}


		function fnLockIncorrect(){
			if(confirm("<spring:message code="comUssUmt.common.lockAtConfirm" />")){
				document.mberManageVO.action = "<c:url value='/cms/uss/umt/InfoMberLockIncorrect.do'/>";
				document.mberManageVO.selectedId.value=document.mberManageVO.uniqId.value;
				document.mberManageVO.submit();
			}
		}


		function fnUpdate(form){

			if(confirm("수정하시겠습니까?")){
				if(validateMberManageVO(form)){
					document.mberManageVO.submit();
					return true;
				}else{
					return false;
				}
			}
		}




		<%--function fnPassword(uniqId) {--%>


		<%--	if(confirm("비밀번호 초기화 하겠습니까?")){--%>

		<%--		$.ajax({--%>
		<%--			type:"POST",--%>
		<%--			url:"<c:url value='/cms/user/mberPasswordUpdt.do' />",--%>
		<%--			data:{--%>
		<%--				"esntlId": uniqId--%>
		<%--			},--%>
		<%--			dataType:'json',--%>
		<%--			timeout:(1000*30),--%>
		<%--			success:function(returnData, status){--%>

		<%--				var successCode =returnData.info.successCode;--%>

		<%--				if(successCode=="0000"){--%>

		<%--					alert("초기화 하였습니다.");--%>
		<%--					return;--%>
		<%--				}else{--%>

		<%--					alert("초기화 실패하였습니다. 관리자 문의 바랍니다.");--%>
		<%--					return;--%>
		<%--				}--%>

		<%--			}--%>
		<%--		});--%>


		<%--	}--%>

		<%--}--%>



	</script>
</head>
<body onload="fn_egov_init()">



<!-- content start -->
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.update" />
	</h2>
	<!-- content start -->
	<form:form commandName="mberManageVO" action="${pageContext.request.contextPath}/cms/uss/umt/InfoMberSelectUpdt.do" name="mberManageVO"  method="post" onSubmit="fnUpdate(document.forms[0]); return false;">

	<div class="wTableFrm">
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
		<input type="hidden" name="userTyForPassword" value="<c:out value='${mberManageVO.userTy}'/>" />
		<!-- for validation -->
		<input type="hidden" name="password" id="password" value="Test#$123)"/>
		<input type="hidden" name="selectedId" id="selectedId" value=""/>


		<!-- 타이틀 -->
		<h3 class="btitle">
			<caption>${pageTitle} <spring:message code="title.update" /></caption>
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
						<form:input path="mberNm" title="${title} ${inputTxt}" size="20" maxlength="60" />
						<div><form:errors path="mberNm" cssClass="error" /></div>
					</td>
				</tr>

				<tr><!-- 핸드폰번호 -->
					<th><spring:message code="comUssUmt.userManageRegist.phone"/> <span class="pilsu">*</span></th>
					<td class="left">
						<%--<info:select name="startMoblno" id="startMoblno" title="핸드폰번호" width="150" first="선택" val="${mberManageVO.startMoblno}" />--%>
						<form:input path="mbtlnum" id="mbtlnum" cssClass="w300"/>
					</td>
					<!-- 전화번호 -->
					<th><label for="areaNo"><spring:message code="comUssUmt.userManageRegist.tel"/></label></th>
					<td class="left">
						<%--<info:select name="areaNo" id="areaNo"  width="150" title="전화번호" first="선택" val="${mberManageVO.areaNo}" />--%>
						<form:input path="areaNo" id="areaNo" cssClass="txaIpUmt"  maxlength="4" style="width:60px;"/>
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
					<td class="left" colspan="3">
						<form:input path="mberEmailAdres" id="mberEmailAdres" title="${title} ${inputTxt}" size="30" maxlength="50" />
						<div><form:errors path="mberEmailAdres" cssClass="error" /></div>
					</td>
				</tr>
				<!-- 일반회원상태코드 -->
				<c:set var="title"><spring:message code="comUssUmt.userManageRegist.status"/></c:set>
				<tr>
					<th><label for="mberSttus">${title}</label> <span class="pilsu">*</span></th>
					<td class="left" colspan="3">
						<form:select path="mberSttus" id="mberSttus" title="${title} ${inputSelect}">
							<form:option value="" label="${inputSelect}"/>
							<form:options items="${mberSttus_result}" itemValue="code" itemLabel="codeNm"/>
						</form:select>
						<div><form:errors path="mberSttus" cssClass="error"/></div>
					</td>
				</tr>


				<!-- 로그인인증제한여부 -->
					<%--				<!--	<c:set var="title"><spring:message code="comUssUmt.common.lockAt"/></c:set>--%>
					<%--				<tr>--%>
					<%--					<th><label for="lockAt">${title}</label></th>--%>
					<%--					<td class="left" colspan="3">--%>
					<%--						<c:if test="${mberManageVO.lockAt eq 'Y'}">예</c:if>--%>
					<%--						<c:if test="${mberManageVO.lockAt == null || mberManageVO.lockAt eq '' || mberManageVO.lockAt eq 'N'}">아니오</c:if>--%>
					<%--					</td>--%>
					<%--				</tr-->--%>
				</tbody>

			</table>

		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">

			<c:if test="${menuInfo.authVO.modifyYn >0}">
				<input type="submit" class="button main" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
				<%--<button class="button" onClick="fnDeleteMber('<c:out value='${mberManageVO.userTy}'/>:<c:out value='${mberManageVO.uniqId}'/>'); return false;" title="<spring:message code="button.delete" /> <spring:message code="input.button" />"><spring:message code="button.delete" /></button>--%>
<%--				<button type="button" class="button" onClick="fnPassword('${mberManageVO.uniqId}'); return false;" title="비밀번호초기화">비밀번호초기화</button>--%>
				<button type="button" class="button" id="fnPasswordMove" name="fnPasswordMove" title="비밀번호변경">비밀번호변경</button>
			</c:if>
			<button class="button" onClick="fnListPage(); return false;" title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></button>
			<!--button class="button" onClick="fnLockIncorrect(); return false;" title="<spring:message code="comUssUmt.common.lockAtBtn" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.common.lockAtBtn" /></button>
		<button class="button" onClick="document.mberManageVO.reset(); return false;" title="<spring:message code="button.reset" /> <spring:message code="input.button" />"><spring:message code="button.reset" /></button-->
		</div><div style="clear:both;"></div>
	</div>
	</form:form>
	<!-- content end -->
</body>
</html>
