<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="pageTitle">게시판 카테고리 관리</c:set>

<script src="<c:url value="/assets/ax5/ax5core/ax5core.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-formatter/ax5formatter.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">
<script type="text/javascript">
	$(document.body).ready(function(){
		genGrid();
		searchGrid();
	});
	var BOARD_ID = '${searchVO.boardId}';
	var firstGrid = new ax5.ui.grid();
	function genGrid() {
		firstGrid.setConfig({
			target: $('[data-ax5grid="first-grid"]'),
			columns: [
				{key: "boardCategoryNm", label: "카테고리명", align: "center", width: 200, treeControl: true, editor: {type: "text"}},
				{key: "ord", label: "정렬순서", align: "center", width: 70, editor: {type: "number"}},
				{key: "useYn", label: "사용여부", align: "center", width: 70, editor: {type: "checkbox", config: {height: 17, trueValue: "Y", falseValue: "N"}}}
			],
			tree: {
				use: true,
				indentWidth: 10,
				arrowWidth: 15,
				iconWidth: 18,
				icons: {
					openedArrow: '<i class="bx bx-tag-alt" aria-hidden="true"></i>',
					collapsedArrow: '<i class="bx bx-tag-alt" aria-hidden="true"></i>',
					groupIcon: '<i class="bx bx-folder-open" aria-hidden="true"></i>',
					collapsedGroupIcon: '<i class="bx bx-folder" aria-hidden="true"></i>',
					itemIcon: '<i class="bx bx-tag-alt" aria-hidden="true"></i>'
				},
				columnKeys: {
					parentKey: "parentId",
					selfKey: "boardCategoryId"
				}
			}
		});
	}
	function searchGrid() {
		var p = {
			boardId: BOARD_ID
		}
		$ifx.ajax('<c:url value='/cms/info/board/boardCategoryListObject.do' />', {
			method: "GET",
			data: p,
			success: function (res) {
				firstGrid.setData(res.list);
			}
		})
	}
	function addGrid() {
		firstGrid.addRow($.extend({}, {
			boardId: BOARD_ID,
			useYn: 'Y',
			'__created__': true,
			'__modified__': false
		}), "last", {focus: "END"});
	}
	function updateGrid() {
		if(!confirm('정말로 수정하시겠습니까?')) return false;
		var p = firstGrid.getList();
		p.map(function(v) {
			if(v['parentId'] === 'top') v['parentId'] = null;
			return v;
		});
		$ifx.ajax('<c:url value='/cms/info/board/boardCategoryUpdateObject.do' />', {
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

	<div class="rows white-box">
		<div data-ax5grid="first-grid" data-ax5grid-config="{}" style="height: 300px;"></div>
	</div>

	<!-- 하단 버튼 -->
	<div class="btn-set center">
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="addGrid()">추가</button>
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="updateGrid()">수정</button>
		<%--<button type="button" class="button sub" id="modal-close"  /> 닫기</button>--%>
	</div>
</div>