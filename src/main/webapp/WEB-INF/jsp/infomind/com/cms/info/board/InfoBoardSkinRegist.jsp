<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageTitle">게시판 스킨</c:set>
<script type="text/javascript">
$(document).ready(function() {
	fncShowMessg();
	fieldErrors();
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
	<spring:hasBindErrors name="resultVO">
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
	<form:form commandName="resultVO" action="${pageContext.request.contextPath}/cms/info/board/insertBoardSkin.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">등록 내역</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							<label for="boardSkinId">스킨 아이디<span class="pilsu">*</span></label>
                        </th>


						<td class="left">

							<form:select path="boardSkinId">
								<form:option value="" label="선택하세요"/>
								<form:options items="${skinList}"  itemValue="boardSkinId" itemLabel="boardSkinId"/>
							</form:select>
							<div><form:errors path="boardSkinId" cssClass="error" /></div>
						</td>



					</tr>
					<tr>
						<th><label for="boardSkinNm">게시판 스킨 명  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="boardSkinNm" size="70" maxlength="25" />
							<div><form:errors path="boardSkinNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="skinType">스킨스타일 </label></th>
						<td class="left">
							<form:select path="skinType">
								<form:option value="">없음</form:option>
								<form:option value="BASIC">기본CSS</form:option>
								<form:option value="BOARD">게시판CSS</form:option>

							</form:select>
							<div><form:errors path="skinType" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="styleClass">공통 div 스타일</label></th>
						<td class="left">
							<form:input path="styleClass" size="70" maxlength="25" />
							<div><form:errors path="styleClass" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="listClass">리스트 div 스타일 </label></th>
						<td class="left">
							<form:input path="listClass" size="70" maxlength="25" />
							<div><form:errors path="listClass" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="viewClass">뷰 div 스타일 </label></th>
						<td class="left">
							<form:input path="viewClass" size="70" maxlength="25" />
							<div><form:errors path="viewClass" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="writeClass">등록 div 스타일 </label></th>
						<td class="left">
							<form:input path="writeClass" size="70" maxlength="25" />
							<div><form:errors path="writeClass" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label for="cssUrl">스타일 파일명 </label></th>
						<td class="left">
							<form:input path="cssUrl" size="70" maxlength="250" />
							<div><form:errors path="cssUrl" cssClass="error" /></div>
						</td>
					</tr>





					<tr>
						<!-- 사용여부 -->
						<th>사용여부<span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="useYn" cssClass="txt">
								<form:option value="Y"  label="사용"/>
								<form:option value="N" label="사용안함"/>
							</form:select>
							<div><form:errors path="useYn" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
			<span class="button"><a href="<c:url value='/cms/info/board/boardSkinList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>

		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>