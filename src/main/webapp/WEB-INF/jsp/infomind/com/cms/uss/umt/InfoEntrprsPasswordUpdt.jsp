<%
 /**
  * @Class Name : InfoEntrprsPasswordUpdt.jsp
  * @Description : 기업회원 암호수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.11.27   이기선              최초 생성
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManagePasswordUpdt.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.create" /></title><!-- 기업회원 비밀번호변경 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">

function fnListPage(){
    document.passwordChgVO.action = "<c:url value='/cms/uss/umt/InfoEntrprsMberManage.do'/>";
    document.passwordChgVO.submit();
}
function fnUpdate(form){
    var oldPassword = $('#oldPassword').val();
	var newPassword = $('#newPassword').val();
	var newPassword2 = $('#newPassword2').val();

	if(!oldPassword){

		alert("기존 비밀번호 입력하세요")
		return;
	}

	if(!newPassword){

		alert("새비밀번호 입력하세요")
		return;
	}

	if(!newPassword2){
		alert("비밀번호확인 입력하세요")
		return;
	}

	if(newPassword !== newPassword2){
		alert("<spring:message code="fail.user.passwordUpdate2" />");
		return;
	}

	var API_SERVER = "<c:url value='/cms/uss/umt/InfoEntrprsPasswordUpdtObject.do'/>
	var formData = {
		uniqId : '${entrprsManageVO.uniqId}',
		entrprsmberId: '${entrprsManageVO.entrprsmberId}',
		password: newPassword,
		newPassword : $('#newPassword').val(),
		newPassword2 : document.getElementById("newPassword2") ,
		oldPassword : document.getElementById("oldPassword")
 	}

	$.ajax({
		url : API_SERVER,
		type : 'post',
		data : JSON.stingify(formData),
		dataType : 'json',
		contentType : 'application/json',
		success : function(res){
			if(res.mesage===""){
				alert(res.mesage);
				ax5modal.close();
			} else{
				alert(res.mesage);
			}
		},
		error(res){
			alert("error")
		}
	})

}
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>
</head>
<body>
<!-- content start -->
        <form name="passwordChgVO" method="post" action="<c:url value='/cms/uss/umt/InfoEntrprsPasswordUpdt.do"'/>" onsubmit="fnUpdate(this); return false;">
              <!-- onsubmit="javascript:return FormValidation(document.passwordChgVO);" >  -->
        <!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
        <input name="checkedIdForDel" type="hidden" />
        <!-- 검색조건 유지 -->
        <input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
        <input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
        <input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
        <input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
        <!-- 우편번호검색 -->
        <input type="hidden" name="url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />
	    <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">

			<!-- content start -->
			<div class="sub subView">
				<h2 class="stitle">
					<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="comUssUmt.entrprsUserManage.title" />
				</h2>
				<h3 class="btitle">
					<caption>${pageTitle} <spring:message code="comUssUmt.entrprsUserManage.title" /></caption>
				</h3>

				<div class="rows white-box">

					<!-- 등록폼 -->
					<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
						<colgroup>
							<col width="9%"/>
							<col width="91%"/>
						</colgroup>
						<tbody>

						<!-- 입력 -->
						<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
						<!-- 일반회원아이디 -->
						<c:set var="title"><spring:message code="comUssUmt.entrprsUserManageRegist.id" /></c:set>
						<tr>
							<th>${title}</th>
							<td class="left">
								<input name="entrprsmberId" id="entrprsmberId" type="text" size="20" value="<c:out value='${entrprsManageVO.entrprsmberId}'/>"  maxlength="20" readonly>
								<input name="uniqId" id="uniqId" type="hidden" size="20" value="<c:out value='${entrprsManageVO.uniqId}'/>">
								<input name="userTy" id="userTy" type="hidden" size="20" value="<c:out value='${entrprsManageVO.userTy}'/>">
							</td>
						</tr>

						<!-- 기존 비밀번호 -->
						<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.oldPass" /></c:set>
						<tr>
							<th>${title}<span class="pilsu">*</span></th>
							<td class="left">
								<input name="oldPassword" id="oldPassword" type="password" size="20" value=""  maxlength="100" >
							</td>
						</tr>
						<!-- 비밀번호 -->
						<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.pass" /></c:set>
						<tr>
							<th>${title}<span class="pilsu">*</span></th>
							<td class="left">
								<input name="newPassword" id="newPassword" type="password" size="20" value=""  maxlength="100" >
							</td>
						</tr>
						<!-- 비밀번호확인 -->
						<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.passConfirm" /></c:set>
						<tr>
							<th>${title}<span class="pilsu">*</span></th>
							<td class="left">
								<input name="newPassword2" id="newPassword2" type="password" size="20" value=""  maxlength="100" >
							</td>
						</tr>
						</tbody>
					</table>
				</div>

	<!-- 하단 버튼 -->
	<div class="btn-set right">

		<c:if test="${menuInfo.authVO.modifyYn >0}">
			<input type="submit" class="button" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
		</c:if>
	<span class="button"><a href="<c:url value='/cms/uss/umt/InfoEntrprsMberManage.do?menuTargetNo=${menuInfo.menuTargetNo}' />"  title="<spring:message code="button.list" /> <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>


		<%--<button class="button" onClick="document.passwordChgVO.reset();return false;" title="<spring:message code="button.reset" /> <spring:message code="input.button" />"><spring:message code="button.reset" /></button>--%>
	</div><div style="clear:both;"></div>
	
</div>
</body>
</html>
