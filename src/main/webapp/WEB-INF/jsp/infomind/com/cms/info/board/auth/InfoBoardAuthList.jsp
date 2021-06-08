<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="pageTitle">게시판 권한 관리</c:set>

<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>

<script src="<c:url value="/assets/ax5/ax5core/ax5core.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.min.js"/>"></script>
<script src="<c:url value="/assets/ax5/ax5ui-formatter/ax5formatter.min.js"/>"></script>
<link rel="stylesheet" href="<c:url value="/assets/ax5/ax5ui-grid/ax5grid.css"/>">
<script type="text/javascript">
	var BOARD_ID = '${searchVO.boardId}';
	var layout$ = $('[data-ax5layout="ax1"]');
	$(document.body).ready(function(){
		genGrid();
		searchGrid();
	});
	var BOARD_ID = '${searchVO.boardId}';
	var firstGrid = new ax5.ui.grid();
	var code = {
		'group': {},
		'orgnzt': {}
	}
	var authType = {
		'common_login': '로그인 이용자 권한',
		'common_not_login': '비 로그인 이용자 권한',
		'group': '그룹별 권한',
		'orgnzt': '부서별 권한',
		'role': '역할별 권한'
	}
	function genGrid() {
		firstGrid.setConfig({
			target: $('[data-ax5grid="first-grid"]'),
			columns: [
				{key: "authType", label: "authType", align: "left", width: 130, formatter: function () {
					return authType[this.item.authType]
				}},
				{key: "authTypeId", label: "authTypeId", align: "left", width: 180, formatter: function (){
					return authType[this.item.authTypeId] || code['group'][this.item.authTypeId] || code['orgnzt'][this.item.authTypeId] || this.item.authTypeId
				}},

				{key: "list", label: "목록", align: "center", width: 60, editor: {type: "checkbox", config: {height: 17, trueValue: true, falseValue: false}}},
				{key: "read", label: "읽기", align: "center", width: 60, editor: {type: "checkbox", config: {height: 17, trueValue: true, falseValue: false}}},
				{key: "write", label: "쓰기", align: "center", width: 60, editor: {type: "checkbox", config: {height: 17, trueValue: true, falseValue: false}}},
				{key: "comment", label: "댓글", align: "center", width: 60, editor: {type: "checkbox", config: {height: 17, trueValue: true, falseValue: false}}},
				{key: "reply", label: "답글", align: "center", width: 60, editor: {type: "checkbox", config: {height: 17, trueValue: true, falseValue: false}}},
				{key: "notice", label: "공지", align: "center", width: 60, editor: {type: "checkbox", config: {height: 17, trueValue: true, falseValue: false}}},
				{key: "notice", label: "삭제", align: "center", width: 80, formatter: function() {
					var html = "";
					if(this.item.authType === 'group' || this.item.authType === 'orgnzt') {
						html = "<button class=\"btn btn-xs sub\" onclick=\"deleteRow(" + this.dindex +")\">삭제</button>"
					}
					return html;
				}}
			]
		});
	}
	function searchGrid() {
		$ifx.promise()
		.then(function (ok, fail, data) {
			$ifx.ajax('<c:url value='/cms/info/board/boardAuthNameCode.do' />', {
				method: "GET",
				success: function (res) {
					res.group.forEach(function(v) {
						code['group'][v.groupId] = v.groupNm
					})
					res.orgnzt.forEach(function(v) {
						code['orgnzt'][v.deptCode] = v.deptNm
					})
					ok()
				}
			})
		}).then(function(ok, fail, data) {
			var p = {
				boardId: BOARD_ID
			}
			$ifx.ajax('<c:url value='/cms/info/board/boardAuthConfigListObject.do' />', {
				method: "GET",
				data: p,
				success: function (res) {
					res.list.forEach(function(v) {
						v['list'] = v.auths.find(function(d) {return d.boardAuthId && d.authAccess === 'list'}) ? true : false
						v['read'] = v.auths.find(function(d) {return d.boardAuthId && d.authAccess === 'read'}) ? true : false
						v['write'] = v.auths.find(function(d) {return d.boardAuthId && d.authAccess === 'write'}) ? true : false
						v['comment'] = v.auths.find(function(d) {return d.boardAuthId && d.authAccess === 'comment'}) ? true : false
						v['reply'] = v.auths.find(function(d) {return d.boardAuthId && d.authAccess === 'reply'}) ? true : false
						v['notice'] = v.auths.find(function(d) {return d.boardAuthId && d.authAccess === 'notice'}) ? true : false
					})
					firstGrid.setData(res.list);
				}
			})
		})
	}
	function deleteRow(_idx) {
		firstGrid.removeRow(_idx);
	}
	function updateGrid() {
		if(!confirm('정말로 수정하시겠습니까?')) return false;
		var p = firstGrid.getList();
		$ifx.ajax('<c:url value='/cms/info/board/boardAuthConfigUpdateObject.do' />?boardId=' + BOARD_ID, {
			method: "POST",
			data: JSON.stringify(p),
			success: function (res) {
				alert(res.message)
			}
		})
	}
	function searchGroupPop() {
		var pagetitle = $(this).attr("title");
		var page = "<c:url value='/sec/gmt/EgovGroupSearchList.do'/>";
		var $dialog = $('<div></div>')
				.html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
				.dialog({
					autoOpen: false,
					modal: true,
					width: 550,
					height: 650,
					title: pagetitle,
					create: function( event, ui ) {
						window.dialogCallback = function(_data) {
							var isCheck = firstGrid.getList().find(function(v) { return v.authTypeId === _data}) ? true : false
							if(isCheck) {
								alert('이미 존재하는 그룹입니다.');
								return false;
							}
							firstGrid.addRow($.extend({}, {
								boardId: BOARD_ID,
								authType: 'group',
								authTypeId: _data,
								'__created__': true,
								'__modified__': true
							}), "last", {focus: "END"});

							$dialog.dialog('close');
						}
					},
					close: function( event, ui ) {
						delete window.dialogCallback;
					}
				});
		$dialog.dialog('open');
	}
	function searchOrgnztPop() {
		var pagetitle = $(this).attr("title");
		var page = "<c:url value='/sec/drm/EgovDeptSearchList.do'/>";
		var $dialog = $('<div></div>')
				.html('<iframe style="border: 0px; " src="' + page + '" width="100%" height="100%"></iframe>')
				.dialog({
					autoOpen: false,
					modal: true,
					width: 550,
					height: 650,
					title: pagetitle,
					create: function( event, ui ) {
						window.dialogCallback = function(_data) {
							var isCheck = firstGrid.getList().find(function(v) { return v.authTypeId === _data.deptCode}) ? true : false
							if(isCheck) {
								alert('이미 존재하는 부서입니다.');
								return false;
							}
							firstGrid.addRow($.extend({}, {
								boardId: BOARD_ID,
								authType: 'orgnzt',
								authTypeId: _data.deptCode,
								'__created__': true,
								'__modified__': true
							}), "last", {focus: "END"});

							$dialog.dialog('close');
						}
					},
					close: function( event, ui ) {
						delete window.dialogCallback;
					}
				});
		$dialog.dialog('open');
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
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="searchGroupPop()">그룹 추가</button>
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="searchOrgnztPop()">부서 추가</button>
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="">역할 추가</button>
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="">담당사용자 추가</button>
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="updateGrid()">수정</button>
		<%--<button type="button" class="button sub" id="modal-close"  /> 닫기</button>--%>
	</div>
</div>