<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">

<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<c:set var="pageTitle">주요프로그램</c:set>
<script type="text/javascript">

	$(document).ready(function() {

		fncShowMessg();
		fieldErrors();

	});


	/* ********************************************************
    * 서버 처리 후 메세지 화면에 보여주기
    ******************************************************** */
	function fncShowMessg(){
		if("<c:out value='${message}'/>" != ''){
			alert("<c:out value='${message}'/>");
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


</script>



<div class="sub subView">

<form:form commandName="resultVO">
	<h3 class="btitle">
		기본정보
	</h3>

	<div class="rows white-box">
		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<tr>
				<th width="30" rowspan="3">사진<span class="pilsu">*</span></th>
				<td  width="41%" rowspan="3">
					<form:input path="pic" readonly="true"/>
				</td>
				<th width="30"  rowspan="3">성명<span class="pilsu">*</span></th>
				<td class="left" style="border-bottom: none;">
					<spring:message code="제주인.한글"/>
					<form:input path="kname" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td style="border-bottom: none;">
					<spring:message code="제주인.영문"/>
					<form:input path="ename" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td>
					<spring:message code="제주인.한자"/>
					<form:input path="cname" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">성별<span class="pilsu">*</span></th>
				<td>
					<form:input path="sex" readonly="true"/>
				</td>
				<th width="30">생년월일<span class="pilsu">*</span></th>
				<td>
						${resultVO.birthYear} ${resultVO.birthMonth} ${resultVO.birthDay}
				</td>
			</tr>
			<tr>
				<th width="30">연령대<span class="pilsu">*</span></th>
				<td>
					<form:input path="age" readonly="true"/>
				</td>
				<th width="30">국적<span class="pilsu">*</span></th>
				<td>
					<form:input path="nationality" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">한국어사용여부<span class="pilsu">*</span></th>
				<td>
					<form:input path="korean" readonly="true"/>
				</td>
				<th width="30">사용언어<span class="pilsu">*</span></th>
				<td>
					<form:input path="language" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">출생지<span class="pilsu">*</span></th>
				<td>
					<form:input path="address" readonly="true"/>
					<form:input path="addressEtc" readonly="true"/>
				</td>
				<th width="30">거주지<span class="pilsu">*</span></th>
				<td>
					<form:input path="country" readonly="true"/>
					<form:input path="countryEtc" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">해외거주기간<span class="pilsu">*</span></th>
				<td>
					<form:input path="stayAbroad" readonly="true"/>
				</td>
				<th width="30">휴대폰<span class="pilsu">*</span></th>
				<td>
					<form:input path="hp" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">국내연락처<span class="pilsu">*</span></th>
				<td>
					<form:input path="tel3" readonly="true"/>
				</td>
				<th width="30">전화번호<span class="pilsu"/></th>
				<td>
					자택
					<form:input path="tel1" readonly="true"/>
					직장
					<form:input path="tel2" readonly="true"/>
				</td>
			</tr>
		</table>
	</div>
		<h3 class="btitle">
			기타정보
		</h3>
	<div class="rows white-box">

	<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<tr>
				<th width="30">이메일<span class="pilsu">*</span></th>
				<td width="41%">
					<form:input path = "email" readonly="true"/>
				</td>
				<th width="30">소속기관 및 직위<span class="pilsu">*</span></th>
				<td>
					기관<form:input path="company" readonly="true"/>
					직위<form:input path="spot" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30"><span class="pilsu">*</span>예상입도날짜</th>
				<td>
					${resultVO.arriveMonth}${resultVO.arriveDay}
				</td>
				<th width="30"><span class="pilsu">*</span>예상출도날짜</th>
				<td>
						${resultVO.departureMonth}${resultVO.departureDay}
				</td>
			</tr>
			<tr>
				<th width="30">호텔객실이용<span class="pilsu">*</span></th>
				<td>
					<form:input path="hotelUse" readonly="true"/>
				</td>
				<th width="30">동반 투숙객<span class="pilsu">*</span></th>
				<td>
					<form:input path="guestsName" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">2일참가선택<span class="pilsu">*</span></th>
				<td>
					<form:input path="day2Program" readonly="true"/>
				</td>
				<th width="30">화합의밤 참가여부<span class="pilsu">*</span></th>
				<td>
					<form:input path="day2ProgramDinner" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">3일차 참가선택<span class="pilsu">*</span></th>
				<td>
					<form:input path="day3Program" readonly="true"/>
				</td>
				<th width="30">재외도민증가입여부<span class="pilsu">*</span></th>
				<td>
					<form:input path="overseas" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th width="30">참여단체<span class="pilsu">*</span></th>
				<td>
					<form:input path="overseasGroup" readonly="true"/>
				</td>
				<th width="30">경력사항<span class="pilsu">*</span></th>
				<td>
					<form:input path="career" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th>자기소개<span class="pilsu">*</span></th>
				<td colspan="3">
					<form:input path="agree" readonly="true"/>
				</td>
			</tr>

		</table>
	</div>
	</div>

	<!-- 하단 버튼 -->
<%--	<div class="btn-set right">--%>
<%--		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="fnUpdt()">수정</button>--%>
<%--	</div>--%>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>

`