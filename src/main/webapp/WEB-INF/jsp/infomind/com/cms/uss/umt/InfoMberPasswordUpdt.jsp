<%
 /**
  * @Class Name : InfoMberPasswordUpdt.jsp
  * @Description : 일반회원암호수정 JSP
  * @Modification Information
  * @
  * @  수정일         수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.12.02   이기선              최초 생성
  *  @author 인포마인드 개발팀
  *  @since 2020.12.02
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
<title>${pageTitle} <spring:message code="title.create" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="passwordChgVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script type="text/javaScript" language="javascript" defer="defer">

$(document).ready(function(){
	$('#btn_update_save').click(function(){

		var newPassword = $('#newPassword').val();
		var newPassword2 = $('#newPassword2').val();

		if(!newPassword){
			alert("비밀번호입력하세요");
			return;
		}

		if(!newPassword2){
			alert("비밀번호확인입력하세요");
			return;
		}

		if(newPassword !== newPassword2){
			alert("<spring:message code="fail.user.passwordUpdate2" />");
			return;
		}

		var API_SERVER = '<c:url value="/cms/uss/umt/InfoMberPasswordUpdtObject.do"/>';
		var formData = {

			uniqId : '${mberManageVO.mberId}',
			password: $('#newPassword').val(),
			newPassword : $('#newPassword').val(),
			newPassword2 : $('#newPassword2').val()

		};

		$.ajax({
			url : API_SERVER,
			type : 'post',
			data : JSON.stringify(formData),
			dataType : 'json',
			contentType : 'application/json' ,
			success : function(res){

				alert("변경되었습니다");
				ax5modal.close();

			},
			error(){
				submitFlag = false;

				alert("error");
			}
		})
	});
});

function fnUpdate(form){
    if(validatePasswordChgVO(form)){
        if(form.newPassword.value != form.newPassword2.value){
            alert("<spring:message code="fail.user.passwordUpdate2" />");
            return false;
        }
        document.passwordChgVO.submit();
        return  true;
    }else{
    	return false;
    }
}
<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>
</head>
<body>
<form name="passwordChgVO" method="post" action="<c:url value="/cms/uss/umt/InfoMberPasswordUpdt.do"/>" onsubmit="fnUpdate(document.forms[0]); return false;">
<!-- 상세정보 사용자 삭제시 prameter 전달용 input -->
<input name="checkedIdForDel" type="hidden" />
<!-- 검색조건 유지 -->
<input type="hidden" name="searchCondition" value="<c:out value='${userSearchVO.searchCondition}'/>"/>
<input type="hidden" name="searchKeyword" value="<c:out value='${userSearchVO.searchKeyword}'/>"/>
<input type="hidden" name="sbscrbSttus" value="<c:out value='${userSearchVO.sbscrbSttus}'/>"/>
<input type="hidden" name="pageIndex" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
<!-- 우편번호검색 -->
<input type="hidden" name="url" value="<c:url value='/sym/ccm/zip/EgovCcmZipSearchPopup.do'/>" />


	<!-- content start -->
	<div class="sub subView">
		<h2 class="stitle">
			<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="comUssUmt.userManage.title" />
		</h2>
		<h3 class="btitle">
			<caption>${pageTitle} <spring:message code="comUssUmt.userManage.title" /></caption>
		</h3>


		<div class="rows white-box">

		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
		<colgroup>
			<col style="width: 16%;"><col style="width: ;">
		</colgroup>
		<tbody>
			<!-- 입력 -->
			<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
			<!-- 일반회원아이디 -->
			<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.id" /></c:set>
			<tr>
				<th>${title}</th>
				<td class="left">
					<input name="mberId" id="mberId" type="text" size="20" value="<c:out value='${mberManageVO.mberId}'/>"  maxlength="20" readonly >
					<input name="userTy" id="userTy" type="hidden" size="20" value="<c:out value='${mberManageVO.userTy}'/>" >
				</td>
			</tr>

			<!-- 기존 비밀번호 -->
			<c:set var="title"><spring:message code="comUssUmt.userManagePasswordUpdt.oldPass" /></c:set>
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
			<button type="button" class="button main" name="btn_update_save" id="btn_update_save" return false; title="<spring:message code="button.update" /> <spring:message code="input.button" />"  ><spring:message code="button.update" /></button>
		</div><div style="clear:both;"></div>
	
</div>

</form>

</body>
</html>
