<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="pageTitle"><spring:message code="comSymPrm.programListManage.pageTop.title"/></c:set>
<script language="javascript1.2" type="text/javaScript">
<!--
/* ********************************************************
 * 모두선택 처리 함수
 ******************************************************** */
function fCheckAll() {
    var checkField = document.progrmManageForm.checkField;
    if(document.progrmManageForm.checkAll.checked) {
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
function fDeleteProgrmManageList() {
    var checkField = document.progrmManageForm.checkField;
    var ProgrmFileNm = document.progrmManageForm.checkProgrmFileNm;
    var checkProgrmFileNms = "";
    var checkedCount = 0;
    if(checkField) {
    	if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkProgrmFileNms += ((checkedCount==0? "" : ",") + ProgrmFileNm[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
            	checkProgrmFileNms = ProgrmFileNm.value;
            }
        }
    }




    document.progrmManageForm.checkedProgrmFileNmForDel.value=checkProgrmFileNms;
    document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgrmManageListDelete.do'/>";
    document.progrmManageForm.submit();
}

/* ********************************************************
 * 페이징 처리 함수
 ******************************************************** */
function linkPage(pageNo){
//	document.menuManageForm.searchKeyword.value =
	document.progrmManageForm.pageIndex.value = pageNo;
	document.progrmManageForm.action = "<c:url value='/cms/sym/prm/programListManageSelect.do'/>";
   	document.progrmManageForm.submit();
}

/* ********************************************************
 * 조회 처리 함수
 ******************************************************** */
function selectProgramListManage() {
	document.progrmManageForm.pageIndex.value = 1;
	document.progrmManageForm.action = "<c:url value='/cms/sym/prm/programListManageSelect.do'/>";
	document.progrmManageForm.submit();
}
/* ********************************************************
 * 입력 화면 호출 함수
 ******************************************************** */
function insertProgramListManage() {
   	document.progrmManageForm.action = "<c:url value='/sym/prm/EgovProgramListRegist.do'/>";
   	document.progrmManageForm.submit();
}
/* ********************************************************
 * 상세조회처리 함수
 ******************************************************** */
function selectUpdtProgramListDetail(progrmFileNm) {
	document.progrmManageForm.tmp_progrmNm.value = progrmFileNm;
   	document.progrmManageForm.action = "<c:url value='/cms/sym/prm/ProgramListDetailSelect.do '/>";
   	document.progrmManageForm.submit();
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



	<form name="progrmManageForm" action ="<c:url value='/cms/sym/prm/programListManageSelect.do' />" method="post">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>"/>
		<input name="checkedProgrmFileNmForDel" type="hidden" />

		<div class="white-box">
			<div class="rows">
				<label class="before"> <spring:message code="comSymPrm.programListManage.programName" /> :</label> <!-- 프로그램명 -->
				<input type="text" id="F1" class="w200" class="main"  name="searchKeyword"  value="<c:out value='${searchVO.searchKeyword}'/>" size="35" maxlength="60" onkeypress="press();" title="<spring:message code="title.searchCondition" />" /><!-- 검색조건 -->
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
				<button type="button" class="button main" onclick="location.href='<c:url value='/cms/sym/prm/RegistProgramListView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
				<button type="button" class="button main" onclick="fDeleteProgrmManageList(); return false;" title='<spring:message code="button.delete" />'"  title="<spring:message code="button.delete" /> <spring:message code="input.button" />"  ><spring:message code="button.delete" /></button>

				<%--<button type="button" class="button main"><a href="<c:url value='/sym/prm/EgovProgramListRegist.do'/>" onclick="insertProgramListManage(); return false;" title='<spring:message code="button.create" />'><spring:message code="button.create" /></a></button><!-- 등록 -->--%>
				<%--<button type="button" class="button main"><a href="#LINK" onclick="fDeleteProgrmManageList(); return false;" title='<spring:message code="button.delete" />'><spring:message code="button.delete" /></a></button><!-- 삭제 -->--%>

			</div>

		</div>
		<h3 class="btitle">
			${pageTitle}<spring:message code="title.list" />
		</h3>
		<div class="rows white-box">
			<table class="basic">

				<thead>
				<tr>
					<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="fCheckAll();" title="전체선택" /></th>
					<th scope="col"><spring:message code="comSymPrm.programListManage.programFileName" /></th><!-- 프로그램파일명 -->
					<th scope="col"><spring:message code="comSymPrm.programListManage.programName" /></th><!-- 프로그램명 -->
					<th scope="col">URL</th>
					<th scope="col"><spring:message code="comSymPrm.programListManage.ProgramDescription" /></th><!-- 프로그램설명 -->
				</tr>
				</thead>
				<tbody>
				<%-- 데이터를 없을때 화면에 메세지를 출력해준다 --%>
				<c:if test="${fn:length(list_progrmmanage) == 0}">
					<tr>
						<td colspan="5">
							<spring:message code="common.nodata.msg" />
						</td>
					</tr>
				</c:if>

				<c:forEach var="result" items="${list_progrmmanage}" varStatus="status">
					<tr>
						<td class="center">
							<input type="checkbox" name="checkField" class="check2" title="선택">
							<input name="checkProgrmFileNm" type="hidden" value="<c:out value='${result.progrmFileNm}'/>"/>
						</td>
						<td class="center">
		            <span class="link"><a href="<c:url value='/cms/sym/prm/ProgramListDetailSelect.do'/>?tmp_progrmNm=<c:out value="${result.progrmFileNm}"/>"  onclick="selectUpdtProgramListDetail('<c:out value="${result.progrmFileNm}"/>'); return false;">

		            <c:if test="${fn:length(result.progrmFileNm)> 22}">
						<c:out value="${fn:substring(result.progrmFileNm,0, 22)}"/>...
					</c:if>
				    <c:if test="${fn:length(result.progrmFileNm)<= 22}">
						<c:out value="${result.progrmFileNm}"/>
					</c:if>

		            </a></span>
						</td>
						<td class="center">
							<c:if test="${fn:length(result.progrmKoreanNm)> 12}">
								<c:out value="${fn:substring(result.progrmKoreanNm,0, 12)}"/>...
							</c:if>
							<c:if test="${fn:length(result.progrmKoreanNm)<= 12}">
								<c:out value="${result.progrmKoreanNm}"/>
							</c:if>
						</td>
						<td class="center">
							<c:if test="${fn:length(result.URL)> 35}">
								<c:out value="${fn:substring(result.URL,0, 35)}"/>...
							</c:if>
							<c:if test="${fn:length(result.URL)<= 35}">
								<c:out value="${result.URL}"/>
							</c:if>

						</td>
						<td><c:out value="${result.progrmDc}"/></td>
					</tr>
				</c:forEach>
				</tbody>

			</table>

		</div>
		<article class="pagenation">
			<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
		</article>

		<input type="hidden" name="cmd">
		<input type="hidden" name="tmp_progrmNm">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>

