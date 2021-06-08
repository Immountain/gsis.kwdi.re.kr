<%
 /**
  * @Class Name : EgovGroupManage.java
  * @Description : EgovGroupManage List 화면
  * @Modification Information
  * @
  * @  수정일                     수정자               수정내용
  * @ ----------    --------    ---------------------------
  * @ 2009.02.01    lee.m.j     최초 생성
  *   2016.06.13    장동한        표준프레임워크 v3.6 개선
  *
  *  @author lee.m.j
  *  @since 2009.03.11
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
<c:set var="pageTitle">그룹관리</c:set>

<title>${pageTitle} <spring:message code="title.list" /></title><!-- 그룹관리 목록 -->
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
                alert("<spring:message code="comCopSecGmt.validate.groupSelect" />");//선택된 그룹이 없습니다.
                returnBoolean = false;
            }
        } else {
        	 if(document.listForm.delYn.checked == false) {
                alert("<spring:message code="comCopSecGmt.validate.groupSelect" />");//선택된 그룹이 없습니다.
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    } else {
        alert("<spring:message code="comCopSecGmt.validate.groupSelectResult" />");//조회된 결과가 없습니다.
    }

    document.listForm.groupIds.value = returnValue;

    return returnBoolean;
}

function fncSelectGroupList(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroupList.do'/>";
    document.listForm.submit();
}

function fncSelectGroup(groupId) {
    document.listForm.groupId.value = groupId;
    document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroup.do'/>";
    document.listForm.submit();
}

function fncAddGroupInsert() {
	document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroupInsertView.do'/>";
	document.listForm.submit();

    //location.replace("<c:url value='/cms/sec/gmt/InfoGroupInsertView.do'/>");
}

function fncGroupListDelete() {
	if(fncManageChecked()) {
	    if(confirm("<spring:message code="comCopSecGmt.validate.confirm.delete" />")) {//삭제하시겠습니까?
            document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroupListDelete.do'/>";
            document.listForm.submit();
	    }
	}
}

function linkPage(pageNo){
    document.listForm.searchCondition.value = "1";
    document.listForm.pageIndex.value = pageNo;
    document.listForm.action = "<c:url value='/cms/sec/gmt/InfoGroupList.do'/>";
    document.listForm.submit();
}

function press() {

    if (event.keyCode==13) {
    	fncSelectGroupList('1');
    }
}
</script>
<div class="sub subView">
	<!-- javascript warning tag  -->
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>
	<h3 class="btitle">
		검색
	</h3>
<form:form name="listForm" action="${pageContext.request.contextPath}/cms/sec/gmt/InfoGroupList.do" method="post">

	<div class="white-box">
	<!-- 검색영역 -->
		<div class="rows">
			<input type="text" value="그룹&nbsp;명&nbsp;&nbsp;:" class="w100" size="1"  onkeypress="press();" readonly="readonly" /><!-- 부서코드 -->
				<input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${infoGroupManageVO.searchKeyword}"/>'  maxlength="155" >
				<input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" /><!-- 조회 -->
				<span class="button"><a href="<c:url value='/cms/sec/gmt/InfoGroupInsertView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>" onClick="javascript:fncAddGroupInsert();"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"><spring:message code="button.create" /></a></span><!-- 등록 -->
		</div>
	</div>
	<h3 class="btitle"><spring:message code="title.list" /></h3>
	<!-- 목록영역 -->
	<div class="rows white-box">
		<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<thead>
				<tr>
					<%--<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 번호 -->--%>
					<th class="board_th_link"><spring:message code="comCopSecGmt.list.groupId" /></th><!-- 그룹 ID -->
					<th><spring:message code="comCopSecGmt.list.groupNm" /></th><!-- 그룹 명 -->
					<th><spring:message code="comCopSecGmt.list.groupDc" /></th><!-- 설명 -->
					<th>기관사용여부</th><!-- 설명 -->
					<th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
					<th></th>
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
			    <%--<td class="center"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${group.groupId}"/>" /></td>--%>
			    <td class="center"><a href="<c:url value='/cms/sec/gmt/InfoGroup.do?groupId=${group.groupId}&menuTargetNo=${menuInfo.menuTargetNo}'/>" onclick="javascript:fncSelectGroup('<c:out value="${group.groupId}"/>')"><c:out value="${group.groupId}"/></a></td>
			    <td class="center"><c:out value="${group.groupNm}"/></td>
			    <td class="center"><c:out value="${group.groupDc}"/></td>
				<td class="center"><c:out value="${group.groupOrganYn}"/></td>
			    <td class="center"><c:out value="${fn:substring(group.groupCreatDe,0,10)}"/></td>
				<td class="center"><a href="<c:url value='/cms/sec/gmt/InfoGroup.do?groupId=${group.groupId}&menuTargetNo=${menuInfo.menuTargetNo}'/>" onclick="javascript:fncSelectGroup('<c:out value="${group.groupId}"/>')"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>"  align="middle" alt="<spring:message code="title.detail" />"  title="<spring:message code="title.detail" />"></a></td>
			</tr>
		</c:forEach>
			</tbody>
		</table>

		<!-- paging navigation -->
		<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
		</article>


	</div><!-- end div board -->




<input type="hidden" name="groupId"/>
<input type="hidden" name="groupIds"/>
<input type="hidden" name="pageIndex" value="<c:out value='${infoGroupManageVO.pageIndex}'/>"/>
<input type="hidden" name="searchCondition" value="1"/>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>

