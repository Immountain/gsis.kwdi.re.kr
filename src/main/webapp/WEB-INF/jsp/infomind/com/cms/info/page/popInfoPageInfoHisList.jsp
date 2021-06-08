<%
	/**
	 * @Class Name : InfoCcmCmmnCodeList.jsp
	 * @Description : 공통코드 목록 화면
	 * @Modification Information
	 * @
	 * @  수정일             수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2020.10.19   양진혁              최초 생성
	 *  @author 인포마인드 개발팀
	 *  @since 2020.10.19
	 *  @version 1.0
	 *  @see
	 *
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle">페이지관리</c:set>

<script type="text/javascript">

	$(document).ready(function() {


		fn_egov_init();

	});

	//history 상세
	function fnHistoryDetail(_pageHisSno) {

		var menuTargetNo = $('#menuTargetNo').val();

		var p = {
			menuTargetNo : menuTargetNo,
			pageHisSno : _pageHisSno,
		};
		var API_SERVER = "<c:url value='/cms/info/page/popInfoPageInfoHisUpdtView.do' />";
		ax5modal.open({
			theme: "primary",
			height: 682,
			width: 1323,
			header: {
				title: 'historyDetail',
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
		}, function (d) {

		});
	}
	/*********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init(){
		// 첫 입력란에 포커스..
		// document.searchVO.searchKeyword.focus();
	}

	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fn_info_select_linkPage(pageNo){
		document.searchVO.pageIndex.value = pageNo;
		document.searchVO.action = "<c:url value='/cms/info/page/popInfoPageInfoHisList.do'/>";
		document.searchVO.submit();
	}
	/*********************************************************
	 * 조회 처리 함수
	 ******************************************************** */
	function fn_info_search_page(){
		document.searchVO.pageIndex.value = 1;
		document.searchVO.submit();
	}

	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
</script>

<div class="sub subView">
	<h3 class="btitle">
		검색

	</h3>

	<form name="searchVO" action="<c:url value='/cms/info/page/popInfoPageInfoHisList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">


		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
						<span class="select-outline">
							 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
								 	<option value="" selected="selected">타입</option>
									<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >등록타입</option><!-- 코드ID -->
									<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >수정타입</option><!-- 코드ID -->
							 </select>
						</span>

				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
			</div>

		</div>
		<h3 class="btitle">
			<spring:message code="title.list" />
		</h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
				<tr>
					<th>번호</th><!-- 번호 -->
					<th>페이지 아이디</th>
					<th>등록/수정타입</th><!-- 코드ID -->
					<th>최초등록시점</th><!-- 코드ID -->
				</tr>
				</thead>
				<!-- 목록영역 -->
				<tbody>
				<c:if test="${fn:length(list_progrmmanage) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<c:forEach items="${list_progrmmanage}" var="resultInfo" varStatus="status">
					<tr>
						<td class="center" onclick="javascript:fnHistoryDetail('<c:out value="${resultInfo.pageHisSno}"/>');"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td class="center" onclick="javascript:fnHistoryDetail('<c:out value="${resultInfo.pageHisSno}"/>');"><c:out value='${resultInfo.pageId}'/></td>
						<td class="center" onclick="javascript:fnHistoryDetail('<c:out value="${resultInfo.pageHisSno}"/>');"><c:out value='${resultInfo.modType}'/></td>
						<td class="center" onclick="javascript:fnHistoryDetail('<c:out value="${resultInfo.pageHisSno}"/>');"><c:out value='${resultInfo.regDt}'/></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>

		</div>
		<input name="pageId" type="hidden" value="${searchVO.pageId}">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
