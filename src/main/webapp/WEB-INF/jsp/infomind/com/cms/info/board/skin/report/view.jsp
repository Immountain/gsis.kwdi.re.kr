<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<!-- 테스트용 임시 -->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>




<c:set var="pageTitle">보고서 게시판</c:set>
<script type="text/javascript">
$(document).ready(function() {

});


function fn_list() {


	document.resultVO.action = "<c:url value='/cms/info/board/c/boardItemList.do'/>";
	document.resultVO.submit();
}

/*********************************************************
 * 수정 페이지  함수
 ******************************************************** */
function fn_board_upd_page(){
	document.resultVO.action = "<c:url value='/cms/info/board/c/updataBoardItemView.do'/>";
	document.resultVO.submit();
}


</script>
<div class="sub subView">

	<c:if test="${popYn!='Y'}">
		<h2 class="stitle">
			<i class='bx bxs-dashboard' ></i>상세
		</h2>

	</c:if>



	<form:form commandName="resultVO" id="resultVO" name="resultVO" action="${pageContext.request.contextPath}/cms/info/board/c/insertBoardItem.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">${pageTitle} 상세</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							제목
						</th>
						<td class="left">
								${resultVO.title}
						</td>
					</tr><tr>
						<th style="width: 9%;">
							메모
						</th>
						<td class="left">
								${resultVO.memo}
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="nopd">
							<textarea name="contentInfo" id="contentInfo" rows="10" cols="80" readonly>${resultVO.contentInfoDecode}</textarea>
						</td>
					</tr>
					<tr>
						<th>파일</th>
						<td class="nopd">
							<c:if test="${fn:length(fileList) != 0}">
								<ul class="file">
									<c:forEach items="${fileList}" var="item" varStatus="status">
										<li>
											<i class='bx bx-file'></i>
											<a href="/site/info/file/fileDown.do?atchFileId=${item.atchFileId}&fileSn=${item.fileSn}" title="${item.orignlFileNm}">${item.orignlFileNm}</a>
										</li>
									</c:forEach>
								</ul>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->

		<c:if test="${popYn!='Y'}">
			<div class="btn-set right">

		<c:if test="${menuInfo.authVO.modifyYn >0}">
			<button type="button" class="button main" onclick="fn_board_upd_page()"  title="수정하기"  >수정하기</button>
		</c:if>


				<button type="button" class="button main" onclick="fn_list()"  title="목록"  >목록</button>


			</div>
		</c:if>




		<input name="itemId" id="itemId" type="hidden" value="${resultVO.itemId}">
		<input name="boardId" id="boardId" type="hidden" value="${resultVO.boardId}">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="boardFile" id="boardFile" type="hidden" value="${resultVO.boardFile}">
		<%--<input name="searchCondition" id="searchCondition" type="hidden" value="${resultVO.searchCondition}">--%>
		<%--<input name="searchKeyword" id="searchKeyword" type="hidden" value="${resultVO.searchKeyword}">--%>
		<%--<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">--%>
		<input name="cmd" type="hidden" value="<c:out value='view'/>">
	</form:form>
</div>