<%
 /**
  * @Class Name : EgovCcmCmmnCodeRegist.jsp
  * @Description : 공통코드 등록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2009.02.01   박정규              최초 생성
  *   2017.08.18   이정은              표준프레임워크 v3.7 개선
  *
  *  @author 공통서비스팀 
  *  @since 2009.02.01
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle">레이아웃 관리</c:set>

<!-- 테스트용 임시 -->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
$ifx.contextPath='<c:url value="/"/>';
</script>
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.src.js"/>"></script>
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<script type="text/javascript">

$(document).ready(function() {
	fncShowMessg();
	fieldErrors();

	var codeEditor = $ifx.generateCodeEditor(infoLayoutInfoVO.contentInfo, {
		useThemeSelector: true
	});
});


/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_regist_page(form){
	//input item Client-Side validate
	if(confirm("<spring:message code="common.regist.msg" />")){
		form.submit();
	}
}

	function fieldErrors() {
		var msg ="";
		<spring:hasBindErrors name="infoLayoutInfoVO">
		<c:forEach items="${errors.fieldErrors}" var="error">
		msg =msg+'${error.defaultMessage}'+"\n";
		</c:forEach>
		</spring:hasBindErrors>
		if(msg){

			alert(msg);
		}
	}


/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
function fncShowMessg(){
	if("<c:out value='${message}'/>" != ''){
		alert("<c:out value='${message}'/>");
	}
}


</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="infoLayoutInfoVO" action="${pageContext.request.contextPath}/cms/info/layout/insertLayoutInfo.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">

		<h3 class="btitle">
		등록 내역
		</h3>



		<div class="rows white-box">
				<!-- 등록폼 -->
				<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							<label for="layoutId">레이아웃 아이디<span class="pilsu">*</span></label>
                        </th>
						<td class="left">

							<form:input path="layoutId" size="20" maxlength="100" />
							<%--<form:select path="layoutId">--%>
								<%--<form:option value="" label="선택하세요"/>--%>
								<%--<form:options items="${layoutList}"  itemValue="layoutId" itemLabel="layoutId"/>--%>
							<%--</form:select>--%>

							<%--<form:input path="layoutId"  size="70" maxlength="70" />--%>
							<div><form:errors path="layoutId" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="layoutNm">레이아웃 명칭  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="layoutNm" size="70" maxlength="100" />
							<div><form:errors path="layoutNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
							<th><label for="layoutType">환경 <span class="pilsu">*</span></label></th>
							<td class="left">
									<select name="layoutType" id="layoutType">
										<option value="JSP" <c:if test="${infoLayoutInfoVO.layoutType == 'JSP'}">selected</c:if> >JSP 실행</option>
										<option value="FOLDER" <c:if test="${infoLayoutInfoVO.layoutType == 'FOLDER'}">selected</c:if>>사용자정의</option>
									</select>
							</td>
					</tr>
					<tr>
						<th><label for="contentInfo">내용 <span class="pilsu">*</span></label></th>
						<td class="nopd">

							<textarea name="contentInfo" id="contentInfo" rows="30" cols="80"><c:out value="${contentInfo.contentInfoDecode}"/></textarea>
							<div><form:errors path="contentInfo" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
				</table>
		</div>
				<!-- 하단 버튼 -->
			<div class="btn-set right">
				<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
				<span class="button"><a href="<c:url value='/cms/info/layout/selectLayoutInfoList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
			</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>