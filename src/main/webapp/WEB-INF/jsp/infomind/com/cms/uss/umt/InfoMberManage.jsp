<%
	/**
	 * @Class Name : InfoUserManage.jsp
	 * @Description : 일반회원관리(조회) JSP
	 * @Modification Information
	 * @
	 * @  수정일         수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2020.12.01   이기선              최초 생성
	 * @ 2020.12.16   이기선      JTP 일반회원 UI로 변경
	 * @ 2020.12.18   이기선      tr에 onclick을 걸면 로그인해제 이벤트와 충돌로 td마다 onclick 변경
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
<c:set var="pageTitle"><spring:message code="comUssUmt.userManage.title"/></c:set>
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
            document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberDelete.do'/>";
            document.listForm.submit();
        }
    }
}
*/
		function fnSelectUser(id) {
			var  readYnCnt = '${menuInfo.authVO.readYn}';
			if(readYnCnt>0) {
				document.listForm.selectedId.value = id;
				array = id.split(":");
				if(array[0] == "") {
				} else {
					userTy = array[0];
					userId = array[1];
				}
				document.listForm.selectedId.value = userId;
				document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberSelectUpdtView.do'/>";
				document.listForm.submit();

			}else{

				alert("보기 권한이 없습니다.");
				return;
			}




		}

		function fnSelectUpdateUser(id) {
			document.listForm.selectedId.value = id;
			array = id.split(":");
			if(array[0] == "") {
			} else {
				userTy = array[0];
				userId = array[1];
			}
			document.listForm.selectedId.value = userId;
			document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberUpdtUserView.do'/>";
			document.listForm.submit();
		}
		<%--function fnAddUserView() {--%>
		<%--    document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberInsertView.do'/>";--%>
		<%--    document.listForm.submit();--%>
		<%--}--%>
		function fnLinkPage(pageNo){
			document.listForm.pageIndex.value = pageNo;
			document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberManage.do'/>";
			document.listForm.submit();
		}
		function fnSearch(){
			document.listForm.pageIndex.value = 1;
			document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberManage.do'/>";
			document.listForm.submit();
		}

		function fnLockIncorrect(id){
			if(confirm("<spring:message code="comUssUmt.common.lockAtConfirm" />")){
				document.listForm.action = "<c:url value='/cms/uss/umt/InfoMberLockIncorrect.do'/>";
				document.listForm.uniqId.value=id;
				document.listForm.submit();
			}
		}

		<c:if test="${!empty resultMsg}">alert("<spring:message code="${resultMsg}" />");</c:if>
	</script>
</head>
<body>
<!-- javascript warning tag  -->
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>

<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
	</h2>

	<h3 class="btitle">검색</h3>

	<form name="listForm" action="<c:url value='/cms/uss/umt/InfoMberManage.do'/>" method="post">
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
				<input class="w200" name="searchKeyword" type="text"  title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${userSearchVO.searchKeyword}"/>'  maxlength="255" >
				<button type="submit" class="button" name="btn_search" id="btn_search" value="<spring:message code="button.inquire" />"
						title="<spring:message code="title.inquire" /> <spring:message code="input.button" />">
					<i class='bx bx-slider-alt'></i>
					<spring:message code="title.inquire"/>
				</button>
			</div>
		</div>

		<h3 class="btitle">${pageTitle}<spring:message code="title.list" /></h3>

		<div class="rows white-box">

			<table class="board_list" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<colgroup>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
				</colgroup>
				<thead>
				<tr>
					<th><spring:message code="table.num" /></th><!-- 번호 -->
					<th><spring:message code="comUssUmt.common.lockAt"></spring:message></th><!-- 로그인잠김-->
					<th class="board_th_link"><spring:message code="comUssUmt.userManageList.id" /></th><!--아이디 -->
					<th><spring:message code="comUssUmt.userManageList.name" /></th><!-- 사용자이름 -->
					<th><spring:message code="comUssUmt.userManageList.email" /></th><!-- 사용자이메일 -->
					<th><spring:message code="comUssUmt.userManageList.phone" /></th><!-- 전화번호 -->
					<th><spring:message code="table.regdate" /></th><!-- 등록일 -->
					<th><spring:message code="comUssUmt.userManageList.sbscrbSttus" /></th><!-- 가입상태 -->
					<%--<th>그룹아이디</th>--%>

				</tr>
				</thead>

				<tbody class="ov">
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="11"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="center"><c:out value="${(userSearchVO.pageIndex-1) * userSearchVO.pageSize + status.count}"/></td>
						<td class="center"><c:if test="${result.lockAt == 'Y'}"><button class="button" onClick="fnLockIncorrect('${result.uniqId}'); return false;" title="<spring:message code="comUssUmt.common.lockAtBtn" /> <spring:message code="input.button" />"><spring:message code="comUssUmt.common.lockAtBtn" /></button></c:if> </td>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="left"><c:out value="${result.userId}"/></td>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="center"><c:out value="${result.userNm}"/></td>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="left"><c:out value="${result.emailAdres}"/></td>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="center"><c:out value="${result.mbtlnum}"/></td>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="center"><c:out value="${fn:substring(result.sbscrbDe,0,10)}"/></td>
						<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="center">

							<c:forEach var="mberSttus_result" items="${mberSttus_result}" varStatus="status">
								<c:if test="${result.sttus == mberSttus_result.code}"><c:out value="${mberSttus_result.codeNm}"/></c:if>
							</c:forEach>
						</td>
						<%--<td style = "cursor:pointer;" onclick="javascript:fnSelectUser('<c:out value="${result.userTy}"/>:<c:out value="${result.uniqId}"/>');" class="center"><c:out value="${result.groupId}"/></td>--%>

					</tr>
				</c:forEach>
				</tbody>
			</table>

			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fnLinkPage"/>
			</article>
		</div>

		<input name="uniqId" type="hidden" />
		<input name="selectedId" type="hidden" />
		<input name="checkedIdForDel" type="hidden" />
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="pageIndex" type="hidden" value="<c:out value='${userSearchVO.pageIndex}'/>"/>
	</form>
</div>
</body>
</html>
