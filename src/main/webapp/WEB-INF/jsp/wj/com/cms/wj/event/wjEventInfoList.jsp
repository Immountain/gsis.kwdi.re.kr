<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">제주인</c:set>

<script type="text/javascript">

	$(document).ready(function() {
		fn_egov_init();
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
		document.searchVO.action = "<c:url value='/cms/wj/event/wjEventInfoList.do'/>";
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


	//이벤트 등록

	function fnEventRegist() {
		var p = {

		};
		var API_SERVER = "<c:url value='/cms/wj/event/wjEventInfoRegistView.do' />"
		ax5modal.open({
			theme: "primary",
			height: 599,
			width: 891,
			header: {
				title: '이벤트등록',
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


	function fnScheduleRegist(festivityId) {
		var p = {

			festivityId : festivityId

		};
		var API_SERVER = "<c:url value='/cms/wj/festivity/wjFestivityScheduleList.do' />?festivityId="+festivityId;
		ax5modal.open({
			theme: "primary",
			height: 800,
			width: 1400,
			header: {
				title: '대회일정등록',
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

	function fnEventUpdate(eventId) {
		var p = {

			eventId : eventId

		};
		var API_SERVER = "<c:url value='/cms/wj/event/wjEventInfoUpdtView.do' />?eventId="+eventId;
		ax5modal.open({
			theme: "primary",
			height: 641,
			width: 881,
			header: {
				title: '이벤트수정',
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
		var API_SERVER = "<c:url value='/cms/wj/program/WjProgramInfoList.do' />?festivityId="+festivityId;
		ax5modal.open({
			theme: "primary",
			height: 800,
			width: 1400,
			header: {
				title: '주요프로그램목록',
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

	function fnSubProgramRegist(festivityId) {
		var p = {
			festivityId : festivityId
		};
		var API_SERVER = "<c:url value='/cms/wj/program/RegistWjSubProgramView.do' />?festivityId="+festivityId;
		ax5modal.open({
			theme: "primary",
			height: 800,
			width: 1400,
			header: {
				title: '부대프로그램등록',
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

		var festivityId = id;

		var API_SERVER = '<c:url value="/cms/wj/festivity/deleteWjFestivityInfo.do"/>';
		var formData = {
			festivityId : festivityId
		};
		$.ajax({
			type : 'post',
			url : API_SERVER,
			data : formData,
			dataType : 'json',
			success : function(res){

				alert('삭제되었습니다');
				ax5modal.close();

			},
			error(){
				alert("error");
			}

		})

	};
</script>

<div class="sub subView">
	<nav class="navigation">
		<i class='bx bxs-home'></i>${menuInfo.depthFullname}
	</nav>
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>이벤트<spring:message code="title.list" />
	</h2>

	<h3 class="btitle">
		검색
	</h3>

	<form name="searchVO" action="<c:url value='/cms/wj/event/wjEventInfoList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
							<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
							<option value="0"  <c:if test="${searchVO.searchCondition == '0'}">selected="selected"</c:if> >이벤트아이디</option><!-- 코드ID -->
					</select>
				</span>

				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
				<button type="button" class="button main" onclick="fnEventRegist(); return false;"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
			</div>
		</div>

		<h3 class="btitle"><spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
				<tr>
					<th>번호</th>
					<th>이벤트아이디</th>
					<th>시작일</th>
					<th>종료일</th>
					<th>시작일시</th>
					<th>종료일시</th>
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
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnEventUpdate('<c:out value="${item.eventId}"/>');"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnEventUpdate('<c:out value="${item.eventId}"/>');"><c:out value="${item.eventId}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnEventUpdate('<c:out value="${item.eventId}"/>');"><c:out value="${item.eventStrDay}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnEventUpdate('<c:out value="${item.eventId}"/>');"><c:out value="${item.eventEndDay}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityInfoUpdate('<c:out value="${item.eventId}"/>');"><c:out value="${item.eventStrTime}"/></td>
						<td class="center" style = "cursor:pointer;" onclick="javascript:fnFestivityInfoUpdate('<c:out value="${item.eventId}"/>');"><c:out value="${item.eventEndTime}"/></td>
						<td class="center">
							<button type="button" class="button main" onclick="fnDelete('<c:out value="${item.eventId}"/>'); return false;"  title="<spring:message code="button.create" /> <spring:message code="input.button" />">삭제</button>
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
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
