<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>


<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">

<!-- javaScript -->
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<c:set var="pageTitle">주요프로그램</c:set>
<script type="text/javascript">

	$(document).ready(function() {

		fncShowMessg();
		fieldErrors();
		ajaxLoadSelect({
			url: "<c:url value='/site/country/code/List.do' />",
			async : true,
			params: [
				{name: 'parentCode', value: 'root'},
				{name: 'langCode', value: '${SITEINFO.langCd}'}
			],
			selectboxNm: 'city',
			callback: function(i, j) {
			}
		});

		$("#city").change(function() {
			$('#town').attr('disabled', true);
			$('#town').css('background', '#ccc');
			ajaxLoadSelect({
				url: "<c:url value='/site/country/code/List.do' />",
				async : true,
				params: [
					{name: 'parentCode', value: this.value},
					{name: 'langCode', value: '${SITEINFO.langCd}'}
				],
				selectboxNm: 'town',
				callback: function(i, j) {
					$('#town').attr('disabled', false);
					$('#town').css('background', '');
				}
			});
		});

		$("#town").change(function() {
			$('#village').attr('disabled', true);
			$('#village').css('background', '#ccc');
			ajaxLoadSelect({
				url: "<c:url value='/site/country/code/List.do' />",
				async : true,
				params: [
					{name: 'parentCode', value: this.value},
					{name: 'langCode', value: '${SITEINFO.langCd}'}
				],
				selectboxNm: 'village',
				callback: function(i, j) {
					$('#village').attr('disabled', false);
					$('#village').css('background', '');
				}
			});
		});
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
				<th width="20">한글</th>
				<td width="50%" class="left">
					${resultVO.korName}
				</td>
				<th width="20" height="10%" rowspan="3"><spring:message code="제주인.사진첨부"/></th>
				<td width="50%" rowspan="3">
					<div id="pr_pic"><img src="/jeju/people/preview.do?userId=${resultVO.userId}" width="150" height="200"></div>
					<span class="info"></span>
				</td>
			</tr>
			<tr>
				<th width="20">영문</th>
				<td width="50%" >
					${resultVO.enName}
				</td>
			</tr>
			<tr>
				<th width="20">한자</th>
				<td width="50%">
					${resultVO.cnName}
				</td>
			</tr>

			<tr>
				<th width="20"><spring:message code="제주인.성별"/></th>
				<td width="50%">
					<c:choose>
						<c:when test="${resultVO.sex == 'M'}">
							<spring:message code="제주인.남"/>
						</c:when>
						<c:otherwise>
							<spring:message code="제주인.여"/>
						</c:otherwise>
					</c:choose>
				</td>
				<th width="20">생년월일</th>
				<td width="50%">
					${resultVO.birth}
				</td>
			</tr>
			<tr>
				<th width="20">이메일</th>
				<td width="50%">
					${resultVO.emaill}
				</td>
				<th width="20">휴대폰</th>
				<td width="50%">
						${resultVO.phone}
				</td>
			</tr>
			<tr>
				<th width="20">자택<span class="pilsu"/></th>
				<td width="50%">
					${resultVO.tel1}
				</td>
				<th width="20">직장</th>
				<td width="50%">
					${resultVO.tel2}
				</td>
			</tr>
			<tr>
				<th width="20"><spring:message code="제주인.출신지"/></th>
				<td width="50%" colspan="3">
					${resultVO.cityNm} ${resultVO.townNm} ${resultVO.villageNm}
				</td>
			</tr>
			<tr>
				<th>거주<spring:message code="제주인.지역"/></th>
				<td width="50%">
					${resultVO.countryCity}
				</td>
				<th width="20">거주<spring:message code="제주인.국가"/></th>
				<td width="50%">
						${resultVO.countryNm}
					</span>
						${resultVO.countryEtc}
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
				<th width="20">초등학교</th>
				<td width="50%">
					${resultVO.schoolE}
				</td>
				<th width="20">졸업년도</th>
				<td width="50%">
					${resultVO.schoolEYear}
				</td>
			</tr>
			<tr>
				<th width="20">중학교</th>
				<td width="50%">
					${resultVO.schoolM}
				</td>
				<th width="20">졸업년도</th>
				<td width="50%">
						${resultVO.schoolMYear}
				</td>
			</tr>
			<tr>
				<th width="20">고등학교</th>
				<td width="50%">
						${resultVO.schoolH}
				</td>
				<th width="20">졸업년도</th>
				<td width="50%">
						${resultVO.schoolHYear}
				</td>
			</tr>
			<tr>
				<th width="20">대학교</th>
				<td width="50%">
						${resultVO.schoolU}
				</td>
				<th width="20">졸업년도</th>
				<td width="50%">
						${resultVO.schoolUYear}
				</td>
			</tr>
			<tr>
				<th width="20">대학원이상</th>
				<td width="50%">
						${resultVO.schoolG}
				</td>
				<th width="20">졸업년도</th>
				<td width="50%">
						${resultVO.schoolGYear}
				</td>
			</tr>
			<tr>
				<th width="20">최종학력</th>
				<td width="50%">
					${resultVO.eduNm}
				</td>
				<th>활동분야</th>
				<td colspan="3">
					<c:forEach items="${wjBlist}" var="item" varStatus="vs">
						<c:if test="${!empty item.checkCode}">${item.codeNm},</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th width="20">소속기관</th>
				<td width="50%">
					${resultVO.company}
				</td>
				<th width="20">직위</th>
				<td width="50%">
					${resultVO.spot}
				</td>
			</tr>
			<tr>
				<th width="20">종교</th>
				<td width="50%">
						${resultVO.religionNm}
				</td>
				<th width="20">메일수신여부</th>
				<td width="50%" style="border-bottom: none;">
					<c:choose>
						<c:when test="${resultVO.sendEmaillYn == 'Y'}">
							수신
						</c:when>
						<c:otherwise>
							거부
						</c:otherwise>
					</c:choose>
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