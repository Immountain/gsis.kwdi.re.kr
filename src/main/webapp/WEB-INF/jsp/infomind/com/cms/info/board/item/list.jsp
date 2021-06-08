<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">게시글</c:set>

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
        document.searchVO.action = "<c:url value='/cms/info/board/boardItemList.do'/>";
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
    * 등록회면 처리 함수
    ******************************************************** */
    function fn_Regist() {
        document.searchVO.action = "<c:url value='/cms/info/board/RegistBoardItemView.do'/>";
        document.searchVO.submit();
    }

    /* ********************************************************
     * 상세회면 처리 함수
     ******************************************************** */
    function fn_detail(itemId) {
        // 사이트 키값(siteId) 셋팅.
        document.searchVO.itemId.value = itemId;
        document.searchVO.action = "<c:url value='/cms/info/board/boardItemView.do'/>";
        document.searchVO.submit();
    }

    /* ********************************************************
     * 게시글 관리 이벤트 처리 함수
     ******************************************************** */

    function popupManageHist(id) {
        PopupCenter('/mng/board/popManageHistList.wp?id=' + id, 'w', 800, 500);
    }

    function procManageList(mode) {
        var $checkItems = $('form[name=procForm] input[type=checkbox]:checked');

        $checkItems.each(function(i, v) {
            var name = $(v).attr('name');
            name = name.replace(/\[([\d]+)\]/gi, '[' + i + ']');
            $(v).attr('name', name);
        })

        var selectedCount = $checkItems.length;
        if (selectedCount <= 0) {
            alert('게시물을 선택하시기 바랍니다.');
            return;
        }
        var message = '';

        switch (mode) {
            case 'move':
                if($('select[name=moveBoardId]').val() == '') {
                    alert('이동 대상 게시판을 선택하여주시기 바랍니다.');
                    return false;
                }
                document.procForm.targetBoardId.value = $('select[name=moveBoardId]').val();
                break;
            case 'copy':
                if($('select[name=copyBoardId]').val() == '') {
                    alert('복사 대상 게시판을 선택하여주시기 바랍니다.');
                    return false;
                }
                document.procForm.targetBoardId.value = $('select[name=copyBoardId]').val();
                break;
            case 'delete':
            case 'restore':
                message = '정말로 진행하시겠습니까?';
                break;
            case 'deleteReal':
                //message = '선택하신 작업은 취소할수 없습니다.정말로 진행하시겠습니까?';
                alert('준비중입니다.');
                return false;
                break;
        }

        if (confirm(message)) {
            document.procForm.cmd.value = mode;
            document.procForm.submit();
        }
    }

    <c:if test="${!empty resultMsg}">alert("${resultMsg}");</c:if>
</script>

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

    <form name="searchVO" action="<c:url value='/cms/info/board/boardItemList.do'/>" method="get" onSubmit="fn_info_search_page(); return false;">
        <input name="itemId" type="hidden" value="">
        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
        <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
        <input name="cmd" type="hidden" value="<c:out value='list'/>">

        <!-- 검색영역 -->
        <!-- 검색조건선택 -->
        <div class="white-box">
            <div class="rows">
                <span class="select-outline">
                     <select name="boardId" title="<spring:message code="title.searchCondition" />">
                        <option selected value=''>게시판선택</option>
                         <c:forEach var="item" items="${board}">
                             <option value="${item.boardId}"  <c:if test="${searchVO.boardId eq item.boardId}">selected="selected"</c:if> >${item.boardNm}</option>
                         </c:forEach>
                    </select>
                </span>
                <span class="select-outline">
                     <select name="searchCondition" title="<spring:message code="title.searchCondition" />">
                        <option selected value=''>검색조건</option>
                        <option value="1"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >제목</option>
                        <option value="2"  <c:if test="${searchVO.searchCondition == '1'}">selected="selected"</c:if> >메모</option>
                    </select>
                </span>

                <input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
                <button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
                <%--<button type="button" class="button main" onclick="fn_Regist()"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>--%>
            </div>
        </div>
    </form>

    <form name="procForm" action="<c:url value='/cms/info/board/boardItemProc.do'/>" method="post" onSubmit="return false;">
        <input type="hidden" name="boardId" value="<c:out value='${searchVO.boardId}'/>">
        <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>">
        <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>">
        <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>">

        <input type="hidden" name="cmd"/>
        <input type="hidden" name="targetBoardId"/>
        <h3 class="btitle">${pageTitle} <spring:message code="title.list" /></h3>
        <div class="rows white-box">
            <table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
                <colgroup>
                    <col width="5%"/>
                    <col width="15%"/>
                    <col width="*"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="5%"/>
                    <col width="10%"/>
                </colgroup>
                <thead>
                    <tr>
                        <th>선택</th>
                        <th>게시판ID</th>
                        <th>제목</th>
                        <th>등록자</th>
                        <th>등록일</th>
                        <th>삭제상태</th>
                        <th>이력</th>
                    </tr>
                </thead>

                <!-- 목록영역 -->
                <tbody>
                <c:if test="${fn:length(list) == 0}">
                    <tr>
                        <td colspan="7"><spring:message code="common.nodata.msg" /></td>
                    </tr>
                </c:if>
                <c:forEach items="${list}" var="item" varStatus="status">
                    <tr>
                        <td class="center">
                            <input type="checkbox" name="list[${status.index}].itemId" value="${item.itemId}"/>
                        </td>
                        <td class="center"><c:out value="${item.boardId}"/></td>
                        <td class=""><c:out value="${item.title}"/></td>
                        <td class="center"><c:out value="${item.regNm}"/></td>
                        <td class="center"><c:out value="${item.regDt}"/></td>
                        <td class="center">${item.deleteYn eq 'Y'? '<span style=\"color: red;\">삭제</span>' :'<span style=\"color: blue;\">정상</span>'}</td>
                        <td class="center">
                            <button type="button" class="button btn-xs" title="이력"><i class="bx bx-slider-alt"></i>이력</button>
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
    </form>

    <h3 class="btitle">${pageTitle} 관리 기능</h3>
    <div class="rows white-box">
        <table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
            <colgroup>
                <col width="15%"/>
                <col width="*"/>
                <col width="20%"/>
            </colgroup>
            <tbody>
                <tr>
                    <th>게시물 삭제</th>
                    <td>선택한 게시물 삭제</td>
                    <td>
                        <button class="btn btn-warning" type="button" onclick="procManageList('delete')">삭제</button>
                    </td>
                </tr>
                <tr>
                    <th>게시물 복구</th>
                    <td>선택한 게시물 복구[<span class="color-red" style="color: red;">삭제</span> -&gt; <span class="color-blue" style="color: blue;">복구</span>]</td>
                    <td>
                        <button class="btn btn-warning" type="button" onclick="procManageList('restore')">복구</button>
                    </td>
                </tr>
                <tr>
                    <th>게시물 영구삭제</th>
                    <td>선택한 게시물 영구삭제 <span class="color-red" style="color: red;">(DB에서 영구삭제되며 복구가 불가능합니다)</span></td>
                    <td>
                        <button class="btn btn-warning" type="button" onclick="procManageList('deleteReal')">삭제</button>
                    </td>
                </tr>
                <tr>
                    <th>게시물 복사</th>
                    <td>선택한 게시물 복사(게시물,첨부파일,확장필드,확장필드 값)</td>
                    <td>
                        <button class="btn btn-warning" type="button" onclick="procManageList('copy')">복사</button>
                        <select name="copyBoardId" title="<spring:message code="title.searchCondition" />">
                            <option selected value=''>게시판선택</option>
                            <c:forEach var="item" items="${board}">
                                <option value="${item.boardId}">${item.boardNm}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>게시물 이동</th>
                    <td>선택한 게시물 이동(게시물,첨부파일,확장필드,확장필드 값,코멘트)</td>
                    <td>
                        <button class="btn btn-warning" type="button" onclick="procManageList('move')">이동</button>
                        <select name="moveBoardId" title="<spring:message code="title.searchCondition" />">
                            <option selected value=''>게시판선택</option>
                            <c:forEach var="item" items="${board}">
                                <option value="${item.boardId}">${item.boardNm}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

</div>
