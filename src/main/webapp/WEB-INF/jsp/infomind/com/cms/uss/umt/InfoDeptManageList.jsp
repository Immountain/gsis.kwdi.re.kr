<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="pageTitle"><spring:message code="comUssUmt.deptManage.title"/></c:set>
<script language="javascript1.2" type="text/javaScript">
	<!--
	/* ********************************************************
     * 모두선택 처리 함수
     ******************************************************** */
	function fCheckAll() {
		var checkField = document.deptManageForm.checkField;
		if(document.deptManageForm.checkAll.checked) {
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

	/* ********************************************************
     * 멀티삭제 처리 함수
     ******************************************************** */
	function fDeleteDeptManageList() {
		var checkField = document.deptManageForm.checkField;
		var ProgrmFileNm = document.deptManageForm.checkOrgnztId;
		var checkOrgnztIds = "";
		var checkedCount = 0;
		if(checkField) {
			if(checkField.length > 1) {
				for(var i=0; i < checkField.length; i++) {
					if(checkField[i].checked) {
						checkOrgnztIds += ((checkedCount==0? "" : ",") + ProgrmFileNm[i].value);
						checkedCount++;
					}
				}
			} else {
				if(checkField.checked) {
					checkOrgnztIds = ProgrmFileNm.value;
				}
			}
		}

		document.deptManageForm.checkOrgnztIds.value=checkOrgnztIds;

		document.deptManageForm.action = "<c:url value='/cms/uss/umt/removeDeptManageList.do'/>";
		document.deptManageForm.submit();
	}

	/* ********************************************************
     * 페이징 처리 함수
     ******************************************************** */
	function linkPage(pageNo){

		document.deptManageForm.pageIndex.value = pageNo;
		document.deptManageForm.action = "<c:url value='/cms/uss/umt/selectDeptManageList.do'/>";
		document.deptManageForm.submit();
	}

	/* ********************************************************
     * 조회 처리 함수
     ******************************************************** */
	function selectDeptManageList() {
		document.deptManageForm.pageIndex.value = 1;
		document.deptManageForm.action = "<c:url value='/cms/uss/umt/selectDeptManageList.do'/>";
		document.deptManageForm.submit();
	}
	/* ********************************************************
     * 입력 화면 호출 함수
     ******************************************************** */
	function addViewDeptManage() {
		document.deptManageForm.action = "<c:url value='/cms/uss/umt/addViewDeptManage.do'/>";
		document.deptManageForm.submit();
	}
	/* ********************************************************
     * 상세조회처리 함수
     ******************************************************** */
	function selectDeptManage(orgnztId) {
		document.deptManageForm.srcOrgnztId.value = orgnztId;
		document.deptManageForm.action = "<c:url value='/cms/uss/umt/selectDeptManage.do '/>";
		document.deptManageForm.submit();
	}
	/* ********************************************************
     * focus 시작점 지정함수
     ******************************************************** */
	function fn_FocusStart(){
		var objFocus = document.getElementById('F1');
		objFocus.focus();
	}

	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
	-->
</script>
<noscript class="noScriptTitle"><spring:message code="common.noScriptTitle.msg" /></noscript>


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



	<form name="deptManageForm" action ="<c:url value='/cms/uss/umt/selectDeptManageList.do' />" method="post">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		<input name="checkOrgnztIds" type="hidden" />

		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
                             <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
									<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
									<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> ><spring:message code="comUssUmt.deptManageList.deptId" /></option><!-- 부서ID -->
									<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> ><spring:message code="comUssUmt.deptManageList.deptName" /></option><!-- 부서명 -->
							 </select>
				</span>

				<input type="text" id="F1" class="w100" class="main"  name="searchKeyword"  value="<c:out value='${searchVO.searchKeyword}'/>" size="35" maxlength="60" onkeypress="press();" title="<spring:message code="title.searchCondition" />" /><!-- 검색조건 -->

				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
				<button type="button" class="button main" onclick="location.href='<c:url value='/cms/uss/umt/addViewDeptManage.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
				<%--<button type="button" class="button main" onclick="fDeleteDeptManageList(); return false;"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"  ><spring:message code="button.delete" /></button>--%>

			</div>

		</div>
		<h3 class="btitle">
			${pageTitle}<spring:message code="title.list" />
		</h3>
		<div class="rows white-box">
			<table class="basic">

				<thead>
				<tr>
				<%--	<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="fCheckAll();" title="전체선택" /></th>--%>
					<th scope="col"><spring:message code="comUssUmt.deptManageList.deptId" /></th>
					<th scope="col"><spring:message code="comUssUmt.deptManageList.deptName" /></th>
					<th scope="col"><spring:message code="comUssUmt.deptManageList.deptDc" /></th>
				</tr>
				</thead>
				<tbody>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(resultList) == 0}">
					<tr>
						<td colspan="5">
							<spring:message code="common.nodata.msg" />
						</td>
					</tr>
				</c:if>

				<c:forEach var="result" items="${resultList}" varStatus="status">
					<tr>
						<%--<td class="center">
							<input type="checkbox" name="checkField" class="check2" title="선택">
							<input name="checkOrgnztId" type="hidden" value="<c:out value='${result.orgnztId}'/>"/>
						</td>--%>
						<td class="center">
							<span class="link"><a href="<c:url value='/cms/uss/umt/selectDeptManage.do'/>?srcOrgnztId=<c:out value="${result.orgnztId}"/>"  onclick="selectDeptManage('<c:out value="${result.orgnztId}"/>'); return false;">
								<c:out value="${result.orgnztId}"/>
							</a></span>
						</td>
						<td class="center">
							<span class="link"><a href="<c:url value='/cms/uss/umt/selectDeptManage.do'/>?srcOrgnztId=<c:out value="${result.orgnztId}"/>"  onclick="selectDeptManage('<c:out value="${result.orgnztId}"/>'); return false;">
								<c:out value="${result.orgnztNm}"/>
							</a></span>
						</td>
						<td><c:out value="${result.orgnztDc}"/></td>
					</tr>
				</c:forEach>
				</tbody>

			</table>

		</div>
		<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
		</article>

		<input type="hidden" name="cmd">
		<input type="hidden" name="srcOrgnztId">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>

