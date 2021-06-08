<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	/**
	 * @Class Name : EgovMenuCreatManage.jsp
	 * @Description : 메뉴생성관리 조회 화면
	 * @Modification Information
	 * @
	 * @ 수정일               수정자             수정내용
	 * @ ----------   --------   ---------------------------
	 * @ 2009.03.10   이용               최초 생성
	 *   2018.09.10   신용호            표준프레임워크 v3.8 개선
	 *
	 *  @author 공통서비스 개발팀 이용
	 *  @since 2009.03.10
	 *  @version 1.0
	 *  @see
	 *
	 */

	/* Image Path 설정 */
	String imagePath_icon   = "/images/egovframework/com/sys/mnu/mcm/icon/";
	String imagePath_button = "/images/egovframework/com/sys/mnu/mcm/button/";
%>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
	<title><spring:message code="comSymMnuMpm.menuCreatManage.title" /></title><!-- 메뉴생성관리 -->

	<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
	<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
	<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

	<script  language="javascript1.2" type="text/javaScript">
		$(document).ready(function () {
			// 파일검색 화면 호출 함수
			$('.menuCreat').click(function (e) {
				e.preventDefault();
				// cms/mnu/mcm/InfoMenuCreatSelect.do'/>?authorCode='<c:out value="${result.authorCode}"/>
				//var page = $(this).attr("href");

				var pagetitle = $(this).attr("title");
				var page = "<c:url value='/cms/mnu/mcm/InfoMenuCreatSelect.do'/>?authorCode=" + $(e.target).attr('authorCode');
				var $dialog = $('<div></div>')
						.html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
						.dialog({
							autoOpen: false,
							modal: true,
							width: 800,
							height: 650,
							title: pagetitle
						});
				$dialog.dialog('open');
			});
		});
		<!--
		/* ********************************************************
         * 최초조회 함수
         ******************************************************** */
		function fMenuCreatManageSelect(){
			document.menuCreatManageForm.action = "<c:url value='/cms/mnu/mcm/InfoMenuCreatManageSelect.do'/>";
			document.menuCreatManageForm.submit();
		}

		/* ********************************************************
         * 페이징 처리 함수
         ******************************************************** */
		function linkPage(pageNo){
			document.menuCreatManageForm.pageIndex.value = pageNo;
			document.menuCreatManageForm.action = "<c:url value='/cms/mnu/mcm/InfoMenuCreatManageSelect.do'/>";
			document.menuCreatManageForm.submit();
		}

		/* ********************************************************
         * 조회 처리 함수
         ******************************************************** */
		function selectMenuCreatManageList() {
			document.menuCreatManageForm.pageIndex.value = 1;
			document.menuCreatManageForm.action = "<c:url value='/cms/mnu/mcm/InfoMenuCreatManageSelect.do'/>";
			document.menuCreatManageForm.submit();
		}

		/* ********************************************************
         * 메뉴생성 화면 호출
         ******************************************************** */
		function selectMenuCreat(vAuthorCode) {
			document.menuCreatManageForm.authorCode.value = vAuthorCode;
			document.menuCreatManageForm.action = "<c:url value='/cms/mnu/mcm/InfoMenuCreatSelect.do'/>";
			document.menuCreatManageForm.submit();
		}
		<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
		-->
	</script>
</head>
<body>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript><!-- 자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다. -->

<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}</a>
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>
	<h3 class="btitle">
		검색
	</h3>

	<form name="menuCreatManageForm" action ="<c:url value='/cms/mnu/mcm/InfoMenuCreatManageSelect.do'/>" method="post">
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					<select name="searchCondition" title="<spring:message code="title.searchCondition" />">
						<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
						<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId" /></option><!-- 코드ID -->
						<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code" /></option><!-- 코드ID -->
						<option value="3"  <c:if test="${searchVO.searchCondition == '3'}">selected="selected"</c:if> ><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm" /></option><!-- 코드명 -->
					</select>
				</span>
				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
			</div>
		</div>

		<h3 class="btitle">
			${pageTitle}<spring:message code="title.list" />
		</h3>
		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
				<tr>
					<th><spring:message code="comSymMnuMpm.menuCreatManage.authCode" /></th><!-- 권한코드 -->
					<th><spring:message code="comSymMnuMpm.menuCreatManage.authName" /></th><!-- 권한명 -->
					<th><spring:message code="comSymMnuMpm.menuCreatManage.authDesc" /></th><!-- 권한 설명 -->
					<th><spring:message code="comSymMnuMpm.menuCreatManage.creationStatus" /></th><!-- 메뉴생성여부 -->
					<th><spring:message code="comSymMnuMpm.menuCreatManage.createMenu" /></th><!-- 메뉴생성 -->
				</tr>
				</thead>
				<tbody>
				<c:if test="${fn:length(list_menumanage) == 0}">
				<tr>
					<td colspan="5"><spring:message code="common.nodata.msg" /></td>
				</tr>
				</c:if>
				<c:forEach var="result" items="${list_menumanage}" varStatus="status">
					<tr>
						<td><c:out value="${result.authorCode}"/></td>
						<td><c:out value="${result.authorNm}"/></td>
						<td><c:out value="${result.authorDc}"/></td>
						<td>
							<c:if test="${result.chkYeoBu > 0}">Y</c:if>
							<c:if test="${result.chkYeoBu == 0}">N</c:if>
						</td>
						<td>
							<button class="button menuCreat" href="javascript:;" authorCode="<c:out value="${result.authorCode}"/>">메뉴생성</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_egov_select_linkPage"/>
			</article>
		</div>
		<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
		<input type="hidden" name="menuTargetNo" id="menuTargetNo" value="${menuInfo.menuTargetNo}">
		<input type="hidden" name="req_menuNo">
		<input type="hidden" name="checkedMenuNoForDel"/>
		<input type="hidden" name="authorCode"/>
	</form>

</div>
</body>
</html>

