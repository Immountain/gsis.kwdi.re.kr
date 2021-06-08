<%
 /**
  * @Class Name : EgovGroupSearch.java
  * @Description : EgovGroupSearch Search 화면
  * @Modification Information
  * @
  * @  수정일                     수정자               수정내용
  * @ ----------    --------    ---------------------------
  * @ 2009.03.23    lee.m.j     최초 생성
  *   2016.07.06    장동한          표준프레임워크 v3.6 개선
  *
  *  @author lee.m.j
  *  @since 2009.03.23
  *  @version 1.0
  *  @see
  *
  */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comCopSecGmt.groupPopup.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title><!-- 그룹 조회 팝업 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<script type="text/javaScript" language="javascript" defer="defer">
function fncManageChecked() {


    var returnValue = "";
    var returnBoolean = true;

    document.listForm.groupId.value = returnValue;

    return returnBoolean;

}

function fncSelectGroupList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
 // document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroupSearchList.do'/>";
    document.listForm.submit();
}

function fncSelectGroup(groupId) {
 // window.returnValue = groupId;
	if(typeof parent.window['dialogCallback'] === 'function') {
		parent.window['dialogCallback'](groupId)
	}else{
    	opener.listForm.searchKeyword.value = groupId;
	}
    window.close();
}

function fncSelectGroupConfirm(data) {

	ax5modal.callback(data);
	ax5modal.close();

}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
 // document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroupSearchList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectGroupList('1');
    }
}
</script>

	<div class="sub subView">
		<h3 class="btitle">
			검색
		</h3>
<form:form name="listForm" action="${pageContext.request.contextPath}/cms/sec/gmt/InfoGroupSearchList.do" method="post">
	<div class="white-box">
	<!-- 검색영역 -->
			<!-- 검색키워드 및 조회버튼 -->
		<div class="rows">
			그룹 명 :
					<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${infoGroupManageVO.searchKeyword}"/>'  maxlength="155" >
					<input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
			</div>
	</div>
	
	<!-- 목록영역 -->
	<h3 class="btitle"><spring:message code="title.list" /></h3>
	<div class="rows white-box">
		<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<thead>
	<tr>
		<th class="board_th_link"><spring:message code="comCopSecGmt.groupPopupList.groupId" /></th><!-- 그룹 ID -->
		<th><spring:message code="comCopSecGmt.groupPopupList.groupNm" /></th><!-- 그룹 명 -->
	</tr>
	</thead>
	<tbody>
	<c:if test="${fn:length(groupList) == 0}">
	<tr>
		<td colspan="6"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="group" items="${groupList}" varStatus="status">
	<tr>
<%--		<td class="center" ><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${group.groupId}"/>" /></td>--%>
		<td class="center" ><a href="#LINK" onclick="javascript:fncSelectGroupConfirm('<c:out value="${group.groupId}"/>')"><c:out value="${group.groupId}"/></a></td>
		<td class="center" ><c:out value="${group.groupNm}"/></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	
	<c:if test="${!empty infoGroupManageVO.pageIndex }">
		<!-- paging navigation -->
		<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
		</article>
	</c:if>


</div><!-- end div board -->


<input type="hidden" name="groupId"/>
<input type="hidden" name="groupIds"/>
<input type="hidden" name="pageIndex" value="<c:out value='${infoGroupManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
</form:form>


</div>

