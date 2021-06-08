<%

	/**
	 * @Class Name : InfoUserManage.jsp
	 * @Description : 업무사용자관리(조회,삭제) JSP
	 * @Modification Information
	 * @
	 * @  수정일         수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2020.12.01   이기선              최초 생성
	 *  @author 인포마인드 개발팀
	 *  @since 2020.12.01
	 *  @version 1.0
	 *  @see
	 *
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comUssUmt.deptUserManage.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
<title>${pageTitle} <spring:message code="title.list" /></title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/com.css' />"-->
<script type="text/javaScript" language="javascript" defer="defer">

/* 2020.12.01 이기선 list delete 기능 삭제요청(by 양진혁)으로 주석처리 */
/*
function fncCheckAll() {
    var checkField = document.listForm.checkField;
    if(document.listForm.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}

function fnDeleteUser() {
    var checkField = document.listForm.checkField;
    var id = document.listForm.checkId;
    var checkedIds = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
            }
        }
    }
    if(checkedIds.length > 0) {
    	//alert(checkedIds);
        if(confirm("<spring:message code="common.delete.msg" />")){
        	document.listForm.checkedIdForDel.value=checkedIds;
            document.listForm.action = "<c:url value='/cms/uss/umt/InfoUserDelete.do'/>";
            document.listForm.submit();
        }
    }
}
*/


function fnLinkPage(pageNo){
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/uss/umt/InfoUserManage.do'/>";
    document.listForm.submit();
}
function fnSearch(){
	document.listForm.pageIndex.value = 1;
	document.listForm.action = "<c:url value='/cms/uss/umt/InfoUserManage.do'/>";
    document.listForm.submit();
}
function fnViewCheck(){
    if(insert_msg.style.visibility == 'hidden'){
    	insert_msg.style.visibility = 'visible';
    }else{
    	insert_msg.style.visibility = 'hidden';
    }
}



<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
</script>
</head>

<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="sub subView">


	<h3 class="btitle">
		검색
	</h3>

	<form name="listForm" action="<c:url value='/cms/uss/umt/InfoUserManage.do'/>" method="post">

	<!-- 검색조건선택 -->
	<div class="white-box">
		<div class="rows">
		 <span class="select-outline">
			   <select name="sbscrbSttus" id="sbscrbSttus" title="<spring:message code="comUssUmt.userManageSsearch.sbscrbSttusTitle" />">
				<option value="0" <c:if test="${empty userSearchVO.sbscrbSttus || userSearchVO.sbscrbSttus == '0'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusAll" /></option><!-- 상태(전체) -->
				<option value="A" <c:if test="${userSearchVO.sbscrbSttus == 'A'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusA" /></option><!-- 가입신청 -->
				<option value="D" <c:if test="${userSearchVO.sbscrbSttus == 'D'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusD" /></option><!-- 삭제 -->
				<option value="P" <c:if test="${userSearchVO.sbscrbSttus == 'P'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.sbscrbSttusP" /></option><!-- 승인 -->
			   </select>
		 </span>

			<span class="select-outline">
			<select name="searchCondition" id="searchCondition" title="<spring:message code="comUssUmt.userManageSsearch.searchConditioTitle" />"><!--  -->
				<option value="0" <c:if test="${userSearchVO.searchCondition == '0'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.searchConditionId" /></option><!-- ID  -->
				<option value="1" <c:if test="${empty userSearchVO.searchCondition || userSearchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUssUmt.userManageSsearch.searchConditionName" /></option><!-- Name -->
			</select>
		 </span>
			<input class="button" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${userSearchVO.searchKeyword}"/>'  maxlength="255" >
			<button type="submit" class="button" name="btn_search" id="btn_search"
					value="<spring:message code="button.inquire" />"
					title="<spring:message code="title.inquire" /> <spring:message code="input.button" />"><i
					class='bx bx-slider-alt'></i><spring:message code="title.inquire"/></button>
		</div>


	</div>

	<h3 class="btitle">${pageTitle}<spring:message code="title.list" /></h3>

	<div class="rows white-box">

	<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<colgroup>
		<col style="width: 5%;">
		<col style="width: 3%;">
        <col style="width: 5%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 15%;">
		<col style="width: 13%;">
		<col style="width: 10%;">
		<col style="width: ;">
	</colgroup>
	<thead>
	<tr>
		<th><spring:message code="table.num" /></th><!-- 번호 -->
		<!--th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th--><!-- 전체선택 -->
		
		<th class="board_th_link"><spring:message code="comUssUmt.userManageList.id" /></th><!--아이디 -->
        <th><spring:message code="comUssUmt.common.lockAt" /></th><!--아이디 -->
		<th><spring:message code="comUssUmt.userManageList.name" /></th><!-- 사용자이름 -->
		<th><spring:message code="comUssUmt.userManageList.email" /></th><!-- 사용자이메일 -->
		<th><spring:message code="comUssUmt.userManageList.phone" /></th><!-- 전화번호 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
		<th><spring:message code="comUssUmt.userManageList.sbscrbSttus" /></th><!-- 가입상태 -->

	</tr>
	</thead>




	<tbody class="ov">
	<c:if test="${fn:length(resultList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="result" items="${resultList}" varStatus="status">
	<tr>
		<td class="center" style = "cursor:pointer;"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
	    <!--th class="center">
	        <input name="checkField" title="checkField <c:out value="${status.count}"/>" type="checkbox"/>
	        <input name="checkId" type="hidden" value="<c:out value='${result.userTy}'/>:<c:out value='${result.uniqId}'/>"/>
	    </th-->
		<td class="center" style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');"><c:out value="${result.userId}"/></td>
        <td class="center" ><c:if test="${result.lockAt == 'Y'}"><button class="button grid-width-40" onClick="fnLockIncorrect('${result.uniqId}'); return false;" title="<spring:message code="comUssUmt.common.lockAtBtn" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.common.lockAtBtn" /></button></c:if> </td>
	    <td class="center" style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');"><c:out value="${result.userNm}"/></td>
	    <td class="center" style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');"><c:out value="${result.emailAdres}"/></td>
	    <td class="center" style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');"><c:out value="${result.moblphonNo}"/></td>
	    <td class="center" style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');"><c:out value="${fn:substring(result.sbscrbDe,0,10)}"/></td>
	    <td class="center" style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');">
          <c:forEach var="emplyrSttusCode_result" items="${emplyrSttusCode_result}" varStatus="status">
              <c:if test="${result.sttus == emplyrSttusCode_result.code}"><c:out value="${emplyrSttusCode_result.codeNm}"/></c:if>
          </c:forEach>
	    </td>
	</tr>
	</c:forEach>
	</tbody>
	</table>



		<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fnLinkPage"/>
		</article>

	</div>

		<input name="selectedId" type="hidden" />
		<input name="checkedIdForDel" type="hidden" />
		<input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
        <input name="uniqId" type="hidden"/>

	</form>
</div>


</body>
</html>
