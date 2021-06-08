<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<c:set var="pageTitle">게시판 댓글 옵션 관리</c:set>

<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
<script src="<c:url value='/js/infomind/com/jquery.ext.js' />"></script>

<script src="<c:url value="/assets/ax5/ax5core/ax5core.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-formatter/ax5formatter.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">
<script type="text/javascript">
	var BOARD_ID = '${searchVO.boardId}';
	$(document.body).ready(function(){
	});
	function fn_update_page() {
		var p = $('#resultVO').serializeObject();
		$ifx.ajax('<c:url value="/cms/info/board/boardCommentOptionUpdateObject.do"/>', {
			method: "POST",
			data: JSON.stringify(p),
			success: function (res) {
				alert(res.message)
			}
		})
	}
</script>
<div class="sub subView">
	<!-- 타이틀 -->
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.update" />
	</h2>
	<form:form commandName="resultVO" name="resultVO" id="resultVO" action="" method="post" onSubmit="return false;">
		<%--hidden text--%>
		<h3 class="btitle">
			수정 내역
		</h3>
		<div class="rows white-box">
			<form:hidden path="boardId"/>
			<form:hidden path="__modified__"/>
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<colgroup>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
				</colgroup>
				<tbody>
					<tr>
						<th>제목 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="titleUseYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="titleUseYn" cssClass="error" /></div>
						</td>

						<th>본문 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="txtUsrYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="txtUsrYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>비밀번호 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="pwUseYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="pwUseYn" cssClass="error" /></div>
						</td>

						<th>파일 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="fileUsrYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="fileUsrYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>별점 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="scoreYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="scoreYn" cssClass="error" /></div>
						</td>

						<th>비밀글 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="secretYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="secretYn" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
			</table>

		</div>
	</form:form>
	<!-- 하단 버튼 -->
	<div class="btn-set center">
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="fn_update_page(document.forms[0])">수정</button>
		<%--<button type="button" class="button sub" id="modal-close"  /> 닫기</button>--%>
	</div>
</div>