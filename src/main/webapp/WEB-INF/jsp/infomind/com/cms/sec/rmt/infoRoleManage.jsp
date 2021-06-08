<%
/**
 * @Class Name : EgovRoleManage.java
 * @Description : EgovRoleManage jsp
 * @Modification Information
 * @
 * @  수정일                    수정자                수정내용
 * @ ---------     --------    ---------------------------
 * @ 2009.02.01    lee.m.j     최초 생성
 *   2016.06.13    장동한        표준프레임워크 v3.6 개선
 *
 *  @author lee.m.j
 *  @since 2009.03.21
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
<c:set var="pageTitle"><spring:message code="comCopSecRmt.title"/></c:set>

<title>${pageTitle} <spring:message code="title.list" /></title><!-- 롤관리 목록 -->
<meta http-equiv="content-type" content="text/html; charset=utf-8">

<script type="text/javaScript" language="javascript" defer="defer">
function fncCheckAll() {
    var checkField = document.listForm.delYn;
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

function fncManageChecked() {

    var checkField = document.listForm.delYn;
    var checkId = document.listForm.checkId;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;
                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                returnBoolean = false;
            }
        } else {
            if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecRmt.validate.groupSelect"/>"); //선택된  롤이 없습니다.
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
    	alert("<spring:message code="comCopSecRmt.validate.groupSelectResult"/>"); //조회된 결과가 없습니다.
    }

    document.listForm.roleCodes.value = returnValue;
    return returnBoolean;
}

function fncSelectRoleList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sec/rmt/InfoRoleList.do'/>";
    document.listForm.submit();
}

function fncSelectRole(roleCode) {
    document.listForm.roleCode.value = roleCode;
    document.listForm.action = "<c:url value='/cms/sec/rmt/InfoRole.do'/>";
    document.listForm.submit();
}

function fncAddRoleInsert() {

	document.listForm.action = "<c:url value='/cms/sec/rmt/InfoRoleInsertView.do'/>";
	document.listForm.submit();


}

function fncRoleListDelete() {
	if(fncManageChecked()) {
        if(confirm("삭제하시겠습니까?")) { //삭제하시겠습니까?
            document.listForm.action = "<c:url value='/cms/sec/rmt/InfoRoleListDelete.do'/>";
            document.listForm.submit();
        }
    }
}

function fncAddRoleView() {
    document.listForm.action = "<c:url value='/cms/sec/rmt/InfoRoleUpdate.do'/>";
    document.listForm.submit();
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sec/rmt/InfoRoleList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectRoleList('1');
    }
}
</script>
<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
		검색
	</h3>
<form:form name="listForm" action="${pageContext.request.contextPath}/cms/sec/rmt/InfoRoleList.do" method="post">
	<div class="white-box">
	<!-- 검색영역 -->
		<div class="rows">
			    <input type="text" value="롤명&nbsp;&nbsp;:" size="1" class="w100"  onkeypress="press();" readonly="readonly" /><!-- 부서코드 -->
				<input class="s_input" name="searchKeyword" type="text"   size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${infoRoleManageVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" onClick="fncSelectRoleList(1);return false;"/><!-- 조회 -->


			<button class="button" type="button" onclick="fncAddRoleInsert()"> <spring:message code="button.create" /></button>




		</div>
	</div>
	<h3 class="btitle"><spring:message code="title.list" /></h3>

	<!-- 목록영역 -->
	<div class="rows white-box">
		<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
	<thead>
	<tr>
		<%--<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 번호 -->--%>
		<th class="board_th_link"><spring:message code="comCopSecRam.list.rollId" /></th><!-- 롤 ID -->
		<th><spring:message code="comCopSecRam.list.rollNm" /></th><!-- 롤 명 -->
		<th><spring:message code="comCopSecRam.list.rollType" /></th><!-- 롤 타입 -->
		<th><spring:message code="comCopSecRam.list.rollSort" /></th><!-- 롤 Sort -->
		<th><spring:message code="comCopSecRam.list.rollDc" /></th><!-- 롤 설명 -->
		<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
		<th></th><!--  -->

	</tr>
	</thead>
	<tbody>
	<c:if test="${fn:length(roleList) == 0}">
	<tr>
		<td colspan="8"><spring:message code="common.nodata.msg" /></td>
	</tr>
	</c:if>
	<c:forEach var="role" items="${roleList}" varStatus="status">
	<tr>
		<%--<td><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${role.roleCode}"/>" /></td>--%>
		<td><a href="<c:url value='/cms/sec/rmt/InfoRoleList.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&roleCode=${role.roleCode}" onclick="javascript:fncSelectRole('<c:out value="${role.roleCode}"/>');return false;"><c:out value="${role.roleCode}"/></a></td>
		<td class="left"><c:out value="${role.roleNm}"/></td>
		<td><c:out value="${role.roleTyp}"/></td>
		<td><c:out value="${role.roleSort}"/></td>
		<td class="left"><c:out value="${role.roleDc}"/></td>
		<td><c:out value="${fn:substring(role.roleCreatDe,0,10)}"/></td>
		<td><a href="<c:url value='/cms/sec/rmt/InfoRoleList.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&roleCode=${role.roleCode}" onclick="javascript:fncSelectRole('<c:out value="${role.roleCode}"/>');return false;s"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" align="middle" alt="<spring:message code="title.detail" />"  title="<spring:message code="title.detail" />"></a></td>
	</tr>
	</c:forEach>
	</tbody>
	</table>

		<!-- paging navigation -->
		<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
		</article>


</div><!-- end div board -->

<input type="hidden" name="roleCode"/>
<input type="hidden" name="roleCodes"/>
<input type="hidden" name="pageIndex" value="<c:out value='${infoRoleManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition"/>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>


</div>
