<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:set var="pageTitle">기업/기관 회원조회</c:set>

<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

<script type="text/javascript">

$(document).ready(function() {
	var searchAllYn ='${searchAllYn}';

	selectBoxInit();
	selectBoxCloudIdInit("${orgVo.jtpOrganCd}");

	$("#schJtpCloudId").val("${orgVo.jtpCloudId}");

	if (searchAllYn == "N") {
		$("#schJtpOrganCd").disableObj(true);
		$("#schJtpCloudId").disableObj(true);
	}

	$("#schJtpOrganCd").change(function(e) {
		e.preventDefault();
		selectBoxCloudIdInit($(this).val());
	});
});


function fn_pagedetail(userId, userNm, zeusId) {
	var param = new Object();
	param.userId = userId;
	param.userNm = userNm;
	param.zeusId = zeusId;

	ax5modal.callback(param);
	ax5modal.close();
}


/* ********************************************************
* selectBox
******************************************************** */
function selectBoxInit() {

	ajaxLoadSelect({
		url: "<c:url value='/jtp/cms/ext/selectOrganList.do' />",
		async : false,
		selectboxNm: 'schJtpOrganCd'
	});
}

function selectBoxCloudIdInit(organCd) {

	ajaxLoadSelect({
		url: "<c:url value='/jtp/cms/ext/selectCloudListByOrganCd.do' />",
		async : false,
		params: [
			{name: 'code', value: organCd}
		],
		selectboxNm: 'schJtpCloudId'
	});
}


/*********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function fn_info_select_linkPage(pageNo){
	document.searchVO.pageIndex.value = pageNo;
	document.searchVO.action = "<c:url value='/cms/uss/umt/popEntrprsMberList.do'/>";
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
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
		검색
	</h3>

	<form name="userSearchVO" action="<c:url value='/cms/uss/umt/popEntrprsMberList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					<info:select name="schJtpOrganCd" id="schJtpOrganCd" style="width:150px;" val="${userSearchVO.schJtpOrganCd}" first="기업/기관" />
					<info:select name="schJtpCloudId" id="schJtpCloudId" style="width:200px;" val="${userSearchVO.schJtpCloudId}" first="서비스활용기관" />

					<select name="searchCondition" title="<spring:message code="title.searchCondition" />">
						<option value="0" <c:if test="${userSearchVO.searchCondition == '0'}">selected="selected"</c:if>>아이디</option>
						<option value="1" <c:if test="${userSearchVO.searchCondition == '1'}">selected="selected"</c:if>>성명</option>
					</select>
				</span>
				<input type="text" class="w200" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${userSearchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-search'></i><spring:message code="title.inquire" /></button>
			</div>
		</div>

		<h3 class="btitle"><spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>성명</th>
						<th>기업/기관</th>
						<th>서비스활용기관</th>
						<th style="display:none;">고유ID</th>
						<th style="display:none;">기업/기관코드</th>
						<th style="display:none;">서비스활용기관ID</th>
					</tr>
				</thead>

				<!-- 목록영역 -->
				<tbody>
					<c:if test="${fn:length(resultList) == 0}">
						<tr>
							<td colspan="8"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
					<c:forEach items="${resultList}" var="item" varStatus="status">
						<tr>
							<td class="center"><c:out value="${(userSearchVO.pageIndex-1) * userSearchVO.pageSize + status.count}"/></td>
							<td class="left">
								<a href="#" onClick="fn_pagedetail('<c:out value="${item.entrprsMberId}"/>', '<c:out value="${item.applcntNm}"/>', '<c:out value="${item.zeusId}"/>');return false;">
									<c:out value='${item.entrprsMberId}'/>
								</a>
							</td>
							<td class="left"><c:out value='${item.applcntNm}'/></td>
							<td class="left"><c:out value='${item.organNm}'/></td>
							<td class="left"><c:out value='${item.cloudNm}'/></td>
							<td class="center"  style="display:none;"><c:out value='${item.esntlId}'/></td>
							<td class="center"  style="display:none;"><c:out value='${item.jtpOrganCd}'/></td>
							<td class="center"  style="display:none;"><c:out value='${item.jtpCloudId}'/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>

			</div>

		<input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
