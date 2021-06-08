<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">주요프로그램</c:set>

<script type="text/javascript">

	$(document).ready(function() {
		fn_egov_init();

		$('#btn_regist_save').click(function(){

			var programSno = $('#programSno').val();
			var festivityId = $('#festivityId').val();

			var p = {
				programSno : programSno,
				festivityId : festivityId,
				menuTargetNo : $('#menuTargetNo').val()

			};
			var API_SERVER = "<c:url value='/cms/wj/program/RegistWjProgramSubView.do' />?programSno="+programSno;
			ax5modal.open({
				theme: "primary",
				height: 600,
				width: 1157,
				header: {
					title: '프로그램서브등록',
					btns: {
						close: {
							label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
								// modal.close();
								ax5modal.close();
							}
						}
					}
				},
				iframe: {
					method: "get",
					url: API_SERVER,
					param: p
				},
			}, function (d) {

			});

		})
	});


	/*********************************************************
	 * 초기화
	 ******************************************************** */
	function fn_egov_init(){
		// 첫 입력란에 포커스..
		document.searchVO.searchKeyword.focus();
	}

	/*********************************************************
	 * 페이징 처리 함수
	 ******************************************************** */
	function fn_info_select_linkPage(pageNo){
		document.searchVO.pageIndex.value = pageNo;
		document.searchVO.action = "<c:url value='/cms/wj/program/WjProgramSubList.do'/>"
		document.searchVO.submit();
	}
	/*********************************************************
	 * 조회 처리 함수
	 ******************************************************** */
	function fn_info_search_page(){
		document.searchVO.pageIndex.value = 1;
		document.searchVO.submit();
	}


	<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>





	//다국어수정
	function fnFestivityScheduleUpdateLang(programSubSno) {

		var menuTargetNo = $('#menuTargetNo').val();
		var programSno = $('#programSno').val();
		var festivityId = $('#festivityId').val();
		var programSubSno = programSubSno;

		var p = {
			festivityId : festivityId,
			programSno : programSno,
			menuTargetNo : menuTargetNo,
			programSubSno : programSubSno
		};
		var API_SERVER = "<c:url value='/cms/wj/program/UpdtWjProgramSubView.do' />?festivityId="+festivityId;
		ax5modal.open({
			theme: "primary",
			height: 600,
			width: 1157,
			header: {
				title: '프로그램서브수정',
				btns: {
					close: {
						label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
							// modal.close();
							ax5modal.close();
						}
					}
				}
			},
			iframe: {
				method: "get",
				url: API_SERVER,
				param: p
			},
		}, function (d) {

		});
	}

	function fnFestivityInfoUpdate(festivityId) {
		var p = {

			festivityId : festivityId

		};
		var API_SERVER = "<c:url value='/cms/wj/festivity/UpdateWjFestivityInfoView.do' />?festivityId="+festivityId;
		ax5modal.open({
			theme: "primary",
			height: 800,
			width: 1400,
			header: {
				title: '대회일정수정',
				btns: {
					close: {
						label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
							// modal.close();
							ax5modal.close();
						}
					}
				}
			},
			iframe: {
				method: "get",
				url: API_SERVER,
				param: p
			},
		}, function (d) {

		});
	}

	function fnProgramInfoRegist(festivityId) {
		var p = {
			festivityId : festivityId
		};
		var API_SERVER = "<c:url value='/cms/wj/program/RegistWjProgramInfoView.do' />?festivityId="+festivityId;
		ax5modal.open({
			theme: "primary",
			height: 800,
			width: 1400,
			header: {
				title: '주요프로그램등록',
				btns: {
					close: {
						label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
							// modal.close();
							ax5modal.close();
						}
					}
				}
			},
			iframe: {
				method: "get",
				url: API_SERVER,
				param: p
			},
		}, function (d) {

		});
	}

	function fnDelete(id){

		var programSubSno = id;

		var API_SERVER = '<c:url value="/cms/wj/program/deleteWjProgramSub.do"/>';
		var formData = {
			programSubSno : programSubSno
		};
		$.ajax({
			type : 'post',
			url : API_SERVER,
			data : formData,
			dataType : 'json',
			success : function(res){

				alert('삭제되었습니다');
				ax5modal.reload();

			},
			error(){
				alert("error");
			}

		})

	};
</script>

<div class="sub subView">
	<h3 class="btitle">
		대회포럼 검색
	</h3>

	<form name="searchVO" action="<c:url value='/cms/wj/program/WjProgramSubList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
							<option selected value=''>전체</option><!-- 선택하세요 -->
							<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >컨텐츠타이틀</option><!-- 코드ID -->
							<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >메모</option><!-- 코드ID -->
					</select>
				</span>
				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
				<button type="button" class="button main" name="btn_regist_save" id="btn_regist_save" return false;  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
			</div>
		</div>

		<h3 class="btitle">대회포럼 <spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
				<tr>
					<th>번호</th>
					<th>컨텐츠타이틀</th>
					<th>메모</th>
					<th>대표여부</th>
					<th>사용여부</th>
					<th>삭제</th>
				</tr>
				</thead>

				<!-- 목록영역 -->
				<tbody>
				<c:if test="${fn:length(list) == 0}">
					<tr>
						<td colspan="6"><spring:message code="common.nodata.msg" /></td>
					</tr>
				</c:if>
				<c:forEach items="${list}" var="item" varStatus="status">
					<tr>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityScheduleUpdateLang('<c:out value="${item.programSubSno}"/>');"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityScheduleUpdateLang('<c:out value="${item.programSubSno}"/>');"><c:out value="${item.title}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityScheduleUpdateLang('<c:out value="${item.programSubSno}"/>');"><c:out value="${item.memo}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityScheduleUpdateLang('<c:out value="${item.programSubSno}"/>');"><c:out value="${item.leaderYn}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityScheduleUpdateLang('<c:out value="${item.programSubSno}"/>');"><c:out value="${item.useYn}"/></td>
						<td class="center">
							<button type="button" class="button main" onclick="fnDelete('<c:out value="${item.programSubSno}"/>'); return false;"  title="삭제 <spring:message code="input.button" />">삭제</button>
						</td>
					</tr>

				</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>
		</div>
		<input name="programSno" type="hidden" id="programSno" value="<c:out value="${searchVO.programSno}"/>">
		<input name="festivityId" type="hidden" id="festivityId" value="<c:out value="${searchVO.festivityId}"/>">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
