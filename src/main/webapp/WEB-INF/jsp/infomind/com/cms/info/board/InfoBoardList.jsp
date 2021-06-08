<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:set var="pageTitle">게시판</c:set>

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
	document.searchVO.action = "<c:url value='/cms/info/board/boardList.do'/>";
   	document.searchVO.submit();
}
/*********************************************************
 * 조회 처리 함수
 ******************************************************** */
function fn_info_search_page(){
	document.searchVO.pageIndex.value = 1;
	document.searchVO.submit();
}
/* ********************************************************
 * 상세회면 처리 함수
 ******************************************************** */
function fn_pagedetail(id) {
	// 사이트 키값(siteId) 셋팅.
	document.searchVO.boardId.value = id;
  	document.searchVO.action = "<c:url value='/cms/info/board/UpdateBoardView.do'/>";
  	document.searchVO.submit();
}

/* ********************************************************
 * 권한
 ******************************************************** */
function fn_auth_popup(_boardId) {
	var p = {
		boardId: _boardId
	};
	var API_SERVER = "<c:url value='/cms/info/board/boardAuthList.do' />";
	ax5modal.open({
		theme: "primary",
		height: 500,
		width: 800,
		header: {
			title: '권한 관리',
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

/* ********************************************************
 * 카테고리
 ******************************************************** */
function fn_category_popup(_boardId) {
	var p = {
		boardId: _boardId
	};
	var API_SERVER = "<c:url value='/cms/info/board/boardCategoryList.do' />";
	ax5modal.open({
		theme: "primary",
		height: 500,
		width: 700,
		header: {
			title: '카테고리 관리',
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

/* ********************************************************
 * 카테고리
 ******************************************************** */
function fn_commentOption_popup(_boardId) {
	var p = {
		boardId: _boardId
	};
	var API_SERVER = "<c:url value='/cms/info/board/boardCommentOption.do' />";
	ax5modal.open({
		theme: "primary",
		height: 500,
		width: 700,
		header: {
			title: '댓글 옵션 관리',
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

<c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
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

	<form name="searchVO" action="<c:url value='/cms/info/board/boardList.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
		<!-- 검색영역 -->
		<!-- 검색조건선택 -->
		<div class="white-box">
			<div class="rows">
				<span class="select-outline">
					 <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
							<option selected value=''><spring:message code="input.select" /></option><!-- 선택하세요 -->
							<option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >아이디</option><!-- 코드ID -->
							<option value="2"  <c:if test="${searchVO.searchCondition == '2'}">selected="selected"</c:if> >명칭</option><!-- 코드ID -->

					</select>
				</span>

				<input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
				<button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
				<button type="button" class="button main" onclick="location.href='<c:url value='/cms/info/board/RegistBoardView.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
			</div>
		</div>

		<h3 class="btitle"><spring:message code="title.list" /></h3>

		<div class="rows white-box">
			<table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<thead>
					<tr>
						<%--
						BOARD_ID is '게시판아이디'
						BOARD_SKIN_ID is '게시판스킨아이디'
						BOARD_NM is '게시판 명'
						BOARD_TXT is '게시판 내용'
						USE_COMMENT is '댓글 사용여부'
						USE_REPLY is '답글 사용여부'
						USE_NOTICE is '공지기능 사용여부'
						CATEGORIES is '카테고리 목록'
						LIST_ROW is '게시판 한페이지 보여주는 갯수'
						LOGIN_USER_LIST_YN is '자신쓴글만 보여주는 여부'
						WR_SECRET_YN is '비밀글여부'
						SECRET_PW_YN is '패스워드 입력 여부'
						FILE_ATCH_POSBL_AT is '파일첨부가능여부'
						ATCH_POSBL_FILE_NUMBER is '첨부가능파일숫자'
						ATCH_POSBL_FILE_SIZE is '첨부가능파일사이즈'
						STSFDG_AT is '만족도여부'
						INQUIRE_YN is '문의여부'
						BOARD_USE_USER is '담당자'
						USE_YN is '사용여부'
						TEMP1 is '임시필드1' / TEMP2 is '임시필드2' / TEMP3 is '임시필드3' / TEMP4 is '임시필드4' / TEMP5 is '임시필드5' /
						REG_ID is '등록자' / REG_DT is '등록일' / MOD_ID is '수정자' / MOD_DT is '수정일'
						--%>
						<th>번호</th>
						<th>게시판 아이디</th>
						<th>게시판 명</th>
						<th>설정</th>
						<th>사용여부</th>
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
							<td class="center"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>
							<td class="center">
								<a href="<c:url value='/cms/info/board/UpdateBoardView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&boardId=${item.boardId}" onClick="fn_pagedetail('<c:out value="${item.boardId}"/>','<c:out value="${item.boardId}"/>');return false;">
									<c:out value='${item.boardId}'/>
								</a>
							</td>
							<td class="center"><c:out value='${item.boardNm}'/></td>
							<td class="center">
								<button type="button" class="button btn-xs main" onclick="fn_auth_popup('${item.boardId}')" title="설정 버튼">권한</button>
								<button type="button" class="button btn-xs main" onclick="fn_category_popup('${item.boardId}')" title="설정 버튼">카테고리</button>
								<button type="button" class="button btn-xs main" onclick="fn_commentOption_popup('${item.boardId}')" title="설정 버튼">댓글설정</button>
							</td>
							<td class="center"><c:out value='${item.useYn}'/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<!-- paging navigation -->
			<article class="pagenation">
				<ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
			</article>

			</div>
		<input name="boardId" type="hidden" value="">
		<input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</form>
</div>
