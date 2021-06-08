<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="pageTitle">아이피 관리</c:set>

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
        document.searchVO.action = "<c:url value='/cms/info/hurt635/hurt635List.do'/>";
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
    function fn_pagedetail(keySeq) {
        // 사이트 키값(siteId) 셋팅.
        document.searchVO.keySeq.value = keySeq;
        document.searchVO.action = "<c:url value='/cms/info/hurt635/UpdateHurt635View.do'/>";
        document.searchVO.submit();
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

    <form name="searchVO" action="<c:url value='/cms/info/hurt635/hurt635List.do'/>" method="post" onSubmit="fn_info_search_page(); return false;">
        <!-- 검색영역 -->
        <!-- 검색조건선택 -->
        <div class="white-box">
            <div class="rows">
				<span class="select-outline">
					 <select name="searchUseYn" id="searchUseYn" title="사용여부">
                       <option value="" <c:if test="${searchVO.searchUseYn == ''}">selected="selected"</c:if> >허용여부선택</option><!-- 선택하세요 -->
                       <option value="Y" <c:if test="${searchVO.searchUseYn == 'Y'}">selected="selected"</c:if> >허용</option><!-- 코드ID -->
                       <option value="N" <c:if test="${searchVO.searchUseYn == 'N'}">selected="selected"</c:if> >허용안함</option><!-- 코드ID -->
               		</select>
				</span>

                <input type="text" class="w100" class="main" name="searchKeyword" size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />"  value='<c:out value="${searchVO.searchKeyword}"/>'  maxlength="155" >
                <button type="submit" class="button"  value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" ><i class='bx bx-slider-alt'></i><spring:message code="title.inquire" /></button>
                <button type="button" class="button main" onclick="location.href='<c:url value='/cms/info/hurt635/InfoHurt635View.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="button.create" /> <spring:message code="input.button" />"  ><spring:message code="button.create" /></button>
            </div>
        </div>

        <h3 class="btitle"><spring:message code="title.list" /></h3>

        <div class="rows white-box">
            <table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
                <thead>
                <tr>

                    <th>번호</th>
                    <th>허용여부</th>
                    <th>아이피</th>
                    <th>비고</th>


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
                    <tr style="cursor:pointer;cursor:hand;" onclick="fn_pagedetail('<c:out value="${item.keySeq}"/>');">
                        <td class="center"><c:out value="${(searchVO.pageIndex-1) * searchVO.pageSize + status.count}"/></td>

                        <td class="center"><c:out value="${item.allwYn}"/></td>
                        <td class="center"><c:out value="${item.ip1}"/>-<c:out value="${item.ip2}"/>-<c:out value="${item.ip3}"/>-<c:out value="${item.ip4}"/></td>
                        <td class="center"><c:out value="${item.remk}"/></td>



                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- paging navigation -->
            <article class="pagenation">
                <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="fn_info_select_linkPage"/>
            </article>

        </div>
        <input name="keySeq" type="hidden" value="">
        <input name="pageIndex" type="hidden" value="<c:out value='${searchVO.pageIndex}'/>">
        <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
    </form>
</div>
