<!DOCTYPE html>
<%--
 /**
  * @Class Name : EgovProgramListRegist.jsp
  * @Description : 프로그램목록 등록 화면
  * @Modification Information
  * @
  * @  수정일              수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2009.03.10   이용              최초 생성
  *   2018.09.03   신용호            공통컴포넌트 3.8 개선
  *
  *  @author 공통서비스 개발팀 이용
  *  @since 2009.03.10
  *  @version 1.0
  *  @see
  *
  */
  /* Image Path 설정 */
  //String imagePath_icon   = "/images/egovframework/com/sym/prm/icon/";
 // String imagePath_button = "/images/egovframework/com/sym/prm/button/";
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageTitle"><spring:message code="comSymPrm.programListRegist.pageTop.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do" />"></script>
<validator:javascript formName="progrmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 입력 처리 함수
 ******************************************************** */
function insertProgramListManage(form) {
	if(confirm("<spring:message code="common.save.msg"/>")){
		if(!validateProgrmManageVO(form)){
			return;
		}else{

			form.action="<c:url value='/cms/sym/prm/RegistProgramList.do'/>";
			form.submit();
		}
	}
}
/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function selectList(){
	// location.href = "<c:url value='/sym/prm/EgovProgramListManageSelect.do' />";
}

/* ********************************************************
 * focus 시작점 지정함수
 ******************************************************** */
 function fn_FocusStart(){
		var objFocus = document.getElementById('F1');
		objFocus.focus();
	}


<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>
</head>
<body>
<c:set var="vprogrmFileNm"><spring:message code="comSymPrm.programListRegist.progrmFileNm"/></c:set>
<c:set var="vprogrmStrePath"><spring:message code="comSymPrm.programListRegist.progrmStrePath"/></c:set>
<c:set var="vprogrmKoreanNm"><spring:message code="comSymPrm.programListRegist.progrmKoreanNm"/></c:set>
<c:set var="vprogrmDc"><spring:message code="comSymPrm.programListRegist.progrmDc"/></c:set>
<c:set var="vurl"><spring:message code="comSymPrm.programListDetailSelectUpdt.url"/></c:set>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>



<div class="sub subView">
		<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle}
		</h2>

<!-- ********** 여기서 부터 본문 내용 *************** -->
<form:form commandName="progrmManageVO" method="post" >

<div class="rows white-box">

	<!-- 등록폼 -->
			<table class="landscape">

				<tbody>
				<tr>
					<th><spring:message code="comSymPrm.programListRegist.progrmFileNm"/><span class="pilsu">*</span></th><!-- 프로그램파일명 -->
					<td class="left">
						<form:input path="progrmFileNm" size="50"  maxlength="50" id="F1" title="${vprogrmFileNm}"/><!-- 프로그램파일명 -->
						<form:errors path="progrmFileNm" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="comSymPrm.programListRegist.progrmStrePath"/><span class="pilsu">*</span></th><!-- 저장경로 -->
					<td class="left">
						<form:input path="progrmStrePath"  size="60"   maxlength="60" title="${vprogrmStrePath}"/><!-- 저장경로 -->
						<form:errors path="progrmStrePath" />
					</td>
				</tr>
				<tr>
					<th><spring:message code="comSymPrm.programListRegist.progrmKoreanNm"/> <span class="pilsu">*</span></th><!-- 한글명 -->
					<td class="left">
						<form:input path="progrmKoreanNm" size="60"  maxlength="60" title="${vprogrmKoreanNm}"/><!-- 한글명 -->
						<form:errors path="progrmKoreanNm"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comSymPrm.programListRegist.url"/> <span class="pilsu">*</span></th>
					<td class="left">
						<form:input path="URL" size="60"    maxlength="60" title="${vurl}"/>
						<form:errors path="URL"/>
					</td>
				</tr>
				<tr>
					<th><spring:message code="comSymPrm.programListRegist.progrmDc"/></th><!-- 프로그램설명 -->
					<td class="left">
						<form:textarea path="progrmDc" rows="10" cols="75" cssClass="txaClass" title="${vprogrmDc}"/><!-- 프로그램설명 -->
						<form:errors path="progrmDc"/>
					</td>
				</tr>

				</tbody>

			</table>




</div>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	<input name="cmd" type="hidden" value="<c:out value='insert'/>"/>
</form:form>
	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<input class="button" type="submit" value='<spring:message code="button.save" />' onclick="insertProgramListManage(document.forms[0]); return false;" /><!-- 저장 -->

		<button type="button" class="button" onclick="location.href='<c:url value='/cms/sym/prm/programListManageSelect.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="title.list" /> <spring:message code="input.button" />" ><spring:message code="button.list" /></button>
	</div>
</div>
