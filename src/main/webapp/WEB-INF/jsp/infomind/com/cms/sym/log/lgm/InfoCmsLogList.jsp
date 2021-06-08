<%
	/**
	 * @Class Name : EgovSysLogList.jsp
	 * @Description : 시스템 로그 정보목록 화면
	 * @Modification Information
	 * @
	 * @  수정일      수정자          수정내용
	 * @  -------     --------    ---------------------------
	 * @ 2009.03.11   이삼섭          최초 생성
	 * @ 2011.07.08   이기하          패키지 분리, 경로수정(sym.log -> sym.log.lgm)
	 * @ 2011.09.14   서준식          검색 후 화면에 검색일자 표시안되는 오류 수정
	 * @ 2017.09.17   이정은          표준프레임워크 v3.7 개선
	 * @author 공통서비스 개발팀 이삼섭
	 * @since 2009.03.11
	 * @version 1.0
	 * @see
	 *
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle"><spring:message code="comSymLogLgm.sysLog.title"/></c:set>
<!DOCTYPE html>
<html>
<head>
	<title>${pageTitle} <spring:message code="title.list" /></title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/com/cmm/jqueryui.css' />">
	<script src="<c:url value='/js/egovframework/com/cmm/jquery.js' />"></script>
	<script src="<c:url value='/js/egovframework/com/cmm/jqueryui.js' />"></script>
	<script type="text/javascript">
		/*********************************************************
		 * 초기화
		 ******************************************************** */
		function fn_egov_init(){
			// 첫 입력란에 포커스..
			document.SysLogForm.searchKeyword.focus();
		}
		/* ********************************************************
         * 달력
         ******************************************************** */
		function fn_egov_init_date(){

			$("#strDay").datepicker(
					{dateFormat:'yy-mm-dd'
						, showOn: 'button'
						, buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
						, buttonImageOnly: true

						, showMonthAfterYear: true
						, showOtherMonths: true
						, selectOtherMonths: true

						, changeMonth: true // 월선택 select box 표시 (기본은 false)
						, changeYear: true  // 년선택 selectbox 표시 (기본은 false)
						, showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
					});


			$("#endDay").datepicker(
					{dateFormat:'yy-mm-dd'
						, showOn: 'button'
						, buttonImage: '<c:url value='/images/egovframework/com/cmm/icon/bu_icon_carlendar.gif'/>'
						, buttonImageOnly: true

						, showMonthAfterYear: true
						, showOtherMonths: true
						, selectOtherMonths: true

						, changeMonth: true // 월선택 select box 표시 (기본은 false)
						, changeYear: true  // 년선택 selectbox 표시 (기본은 false)
						, showButtonPanel: true // 하단 today, done  버튼기능 추가 표시 (기본은 false)
					});
		}
		/*********************************************************
		 * 페이징 처리 함수
		 ******************************************************** */
		function fn_egov_select_linkPage(pageNo){
			document.SysLogForm.pageIndex.value = pageNo;
			document.SysLogForm.action = "<c:url value='/cms/sym/log/lgm/selectInfoCmsLogList.do'/>";
			document.SysLogForm.submit();
		}
		/*********************************************************
		 * 조회 처리 함수
		 ******************************************************** */
		function fn_egov_search_sysLog(){
			var vFrom = document.SysLogForm;

			if(vFrom.endDay.value != ""){
				if(vFrom.strDay.value > vFrom.endDay.value){
					alert("<spring:message code="comSymLogLgm.validate.dateCheck" />"); //검색조건의 시작일자가 종료일자보다  늦습니다. 검색조건 날짜를 확인하세요!
					return;
				}
			}else{
				vFrom.endDay.value = "";
			}

			vFrom.pageIndex.value = "1";
			vFrom.action = "<c:url value='/cms/sym/log/lgm/selectInfoCmsLogList.do'/>";
			vFrom.submit();
		}
		/* ********************************************************
         * 상세회면 처리 함수
         ******************************************************** */
		function fn_egov_inquire_sysLog(cmsLogSno) {
			var p = {
				cmsLogSno: cmsLogSno
			};
			var API_SERVER = "<c:url value='/cms/sym/log/lgm/InfoCmsLogDetail.do' />?cmsLogSno="+cmsLogSno;
			ax5modal.open({
				theme: "primary",
				height: 600,
				width: 1100,
				header: {
					title: '로그 상세조회',
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
	</script>
	<div class="sub subView">
		<nav class="navigation">
			<i class='bx bxs-home'></i>${menuInfo.depthFullname}
		</nav>
		<h2 class="stitle">
			<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.list" />
		</h2>

		<h3 class="btitle">
			검색
		</h3>
		<form name="SysLogForm" action="<c:url value='/cms/sym/log/lgm/selectInfoCmsLogList.do'/>" method="post" onSubmit="fn_egov_search_sysLog(); return false;">
			<div class="white-box">
				<div class="rows">
					<div>
						<div style="line-height:6px;">&nbsp;&nbsp;&nbsp;&nbsp;</div>발생일자 :  <!-- 로그유형-->
						<input type="date" id="strDay" name="strDay" value="${searchVO.strDay}"><u class="be">~</u><input type="date" id="endDay" name="endDay" value="${searchVO.endDay}">
					</div>
					<div style="line-height:6px;">&nbsp;&nbsp;&nbsp;&nbsp;</div><spring:message code="comSymLogLgm.sysLog.processSeCode" /> :  <!-- 처리구분-->
					<!-- 검색키워드 및 조회버튼 -->
					<span class="select-outline">
						<select name="searchKeyword" id="searchKeyword" title="사용여부">
                    	   <option selected value=''>전체</option><!-- 선택하세요 -->
                    	   <option value="L">목록</option>
                    	   <option value="U">수정</option>
                    	   <option value="I">등록</option>
                    	   <option value="D">삭제</option>
               			</select>
					</span>
					<input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
				</div>
			</div>
			<h3 class="btitle"><spring:message code="title.list" /></h3>
			<div class="rows white-box">

				<!-- 목록영역 -->
				<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
					<thead>
					<tr>
						<th><spring:message code="table.num" /></th><!-- 번호 -->
						<th><spring:message code="comSymLogLgm.sysLog.occrrncDe" /></th><!-- 발생일자 -->
						<th><spring:message code="comSymLogLgm.sysLog.methodNm" /></th><!-- 메소드명 -->
						<th><spring:message code="comSymLogLgm.sysLog.processSeCode" /></th><!-- 처리구분 -->
						<th><spring:message code="comSymLogLgm.sysLog.processTime" /></th><!-- 처리시간 -->
						<th>유저ID</th>
						<th>유저</th>
						<th>메뉴명</th>
						<th><spring:message code="comSymLogLgm.sysLog.detail" /></th><!-- 상세보기 -->

					</tr>
					</thead>
					<tbody class="ov">
					<c:if test="${fn:length(list) == 0}">
						<tr>
							<td colspan="8"><spring:message code="common.nodata.msg" /></td>
						</tr>
					</c:if>
					<c:forEach items="${list}" var="result" varStatus="status">
						<tr>
							<td><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
							<td><c:out value='${result.logOccrrncDe}'/></td>
							<td><c:out value='${result.logMethod}'/></td>
							<td><c:out value='${result.logProcessSeCode}'/></td>  <%--처리구분--%>
							<td><c:out value='${result.logProcessTime}'/></td>  <%--처리시간--%>
							<td><c:out value='${result.userId}'/></td>
							<td><c:out value='${result.userNm}'/></td>
							<td><c:out value='${result.menuNm}'/></td>
							<td>
								<img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" class="cursor" onclick="fn_egov_inquire_sysLog('<c:out value="${result.cmsLogSno}"/>')" title="<spring:message code="title.detail" />"></td>

							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_egov_select_linkPage"/>
			</article>

			<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
			<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		</form>
	</div>
