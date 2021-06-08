<!DOCTYPE html>
<%--
 /**
  * @Class Name : InfoProgramListDetailSelectUpdt.jsp
  * @Description : 프로그램목록 상세조회및 수정 화면
  * @Modification Ination
  * @
  * @  수정일              수정자            수정내용
  * @ ----------   --------   ---------------------------
  * @ 2020.10.21   양진혁               최초 생성

  *
  *  @author 인포마인드 개발팀 양진혁
  *  @since 2020.10.21
  *  @version 1.0
  *  @see
  *
  */

--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageTitle"><spring:message code="comSymPrm.programListDetailSelectUpdt.pageTop.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="progrmManageVO" staticJavascript="false" xhtml="true" cdata="false"/>
<script language="javascript1.2" type="text/javaScript">


<!--
/* ********************************************************
 * 수정처리 함수
 ******************************************************** */
function updateProgramListManage(form) {
	if(confirm("<spring:message code="common.save.msg" />")){
		if(!validateProgrmManageVO(form)){
			return;
		}else{
            form.action="<c:url value='/cms/sym/prm/ProgramListDetailSelectUpdt.do' />";
			form.submit();
		}
	}
}

/* ********************************************************
 * 삭제처리함수
 ******************************************************** */
function deleteProgramListManage(form) {
	if(confirm("<spring:message code="common.delete.msg" />")){
        form.action="<c:url value='/cms/sym/prm/ProgramListManageDelete.do' />";
		form.submit();
	}
}

/* ********************************************************
 * 목록조회 함수
 ******************************************************** */
function selectList(){
	
    var varForm = document.getElementById("progrmManageVO");
    varForm.action = "<c:url value='/cms/sym/prm/programListManageSelect.do' />";
    varForm.submit();

}
<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
-->
</script>

<c:set var="vprogrmFileNm"><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmFileNm"/></c:set>
<c:set var="vprogrmStrePath"><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmStrePath"/></c:set>
<c:set var="vprogrmKoreanNm"><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmKoreanNm"/></c:set>
<c:set var="vprogrmDc"><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmDc"/></c:set>
<c:set var="vurl"><spring:message code="comSymPrm.programListDetailSelectUpdt.url"/></c:set>

<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>



<div class="sub subView">
	<!-- 타이틀 -->
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.update" />
	</h2>

		<form:form commandName="progrmManageVO" method="post">
			<h3 class="btitle">
				수정 내역
			</h3>
				<!-- 검색조건 유지 -->
				<input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
				<input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
				<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}' default='1' />"/>

			<div class="rows white-box">
				<!-- 등록폼 -->
				<table class="landscape">
					<tr>
						<th><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmFileNm"/> <span class="pilsu">*</span></th><!-- 프로그램파일명 -->
						<td class="left">
							<form:input  path="progrmFileNm" size="50"  maxlength="50" title="${vprogrmFileNm}"/><!-- 프로그램파일명 -->
							<form:errors path="progrmFileNm"/>
						</td>
					</tr>
					<tr>
						<th><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmStrePath"/> <span class="pilsu">*</span></th><!-- 저장경로 -->
						<td class="left">
							<form:input  path="progrmStrePath" size="50"  maxlength="50" title="${vprogrmStrePath}"/><!-- 저장경로 -->
							<form:errors path="progrmStrePath"/>
						</td>
					</tr>
					<tr>
						<th><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmKoreanNm"/> <span class="pilsu">*</span></th><!-- 한글명 -->
						<td class="left">
							<form:input path="progrmKoreanNm" size="60"  maxlength="60"  title="${vprogrmKoreanNm}"/><!-- 한글명 -->
							<form:errors path="progrmKoreanNm" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="comSymPrm.programListDetailSelectUpdt.url"/> <span class="pilsu">*</span></th>
						<td class="left">
							<form:input path="URL" size="60"  maxlength="60" title="${vurl}" />
							<form:errors path="URL" />
						</td>
					</tr>
					<tr>
						<th><spring:message code="comSymPrm.programListDetailSelectUpdt.progrmDc"/> <span class="pilsu">*</span></th><!-- 프로그램설명 -->
						<td class="left">
							<form:textarea path="progrmDc" rows="10" cols="75" title="${vprogrmDc}"/><!-- 프로그램설명 -->
							<form:errors path="progrmDc"/>
						</td>
					</tr>
				</table>

			</div>

			<input name="cmd" type="hidden" value="<c:out value='update'/>"/>
			<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
			</form:form>


	<!-- 하단 버튼 -->
	<div class="btn-set right">

		<button class="button"><a onclick="selectList(); return false;"><spring:message code="button.list"/></a></button><!-- 목록 -->
		<input class="button" type="submit" value='<spring:message code="button.update" />' onclick="updateProgramListManage(document.forms[0]); return false;" /><!-- 수정 -->
		<button class="button"><a href="<c:url value='/cms/sym/prm/ProgramListManageDelete.do'/>?progrmFileNm=<c:out value="${progrmManageVO.progrmFileNm  }"/>" onclick="deleteProgramListManage(document.forms[0]); return false;"><spring:message code="button.delete" /></a></button><!-- 삭제 -->
	</div>
</div>