<%
    /**
     * @Class Name : EgovAuthorManage.java
     * @Description : EgovAuthorManage List 화면
     * @Modification Information
     * @
     * @  수정일                     수정자                    수정내용
     * @ -------       --------    ---------------------------
     * @ 2009.03.01    Lee.m.j       최초 생성
     *   2016.06.13    장동한          표준프레임워크 v3.6 개선
     *
     *  @author 실행환경 개발팀 홍길동
     *  @since 2009.02.01
     *  @version 1.0
     *  @see
     *
     */
%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle"><spring:message code="comCopSecRam.title"/></c:set>

    <title>${pageTitle} <spring:message code="title.list" /></title><!-- 권한관리 목록 -->
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <script type="text/javaScript" language="javascript" defer="defer">
        function fncCheckAll() {
            var checkField = document.listForm.delYn;
            if(document.listForm.checkAll.checked) {
                if(checkField) {
                    if(checkField.length > 1) {
                        for(var i=0; i < checkField.length; i++) {
                            checkField[i].checked = true;
                        }
                    } else {
                        checkField.checked = true;
                    }
                }
            } else {
                if(checkField) {
                    if(checkField.length > 1) {
                        for(var j=0; j < checkField.length; j++) {
                            checkField[j].checked = false;
                        }
                    } else {
                        checkField.checked = false;
                    }
                }
            }
        }

        function fncManageChecked() {

            var checkField = document.listForm.delYn;
            var checkId = document.listForm.checkId;
            var returnValue = "";

            var returnBoolean = false;
            var checkCount = 0;

            if(checkField) {
                if(checkField.length > 1) {
                    for(var i=0; i<checkField.length; i++) {
                        if(checkField[i].checked) {
                            checkField[i].value = checkId[i].value;
                            if(returnValue == "")
                                returnValue = checkField[i].value;
                            else
                                returnValue = returnValue + ";" + checkField[i].value;
                            checkCount++;
                        }
                    }
                    if(checkCount > 0)
                        returnBoolean = true;
                    else {
                        alert("<spring:message code="comCopSecRam.validate.authorSelect" />"); //선택된 권한이 없습니다."
                        returnBoolean = false;
                    }
                } else {
                    if(document.listForm.delYn.checked == false) {
                        alert("<spring:message code="comCopSecRam.validate.authorSelect" />"); //선택된 권한이 없습니다."
                        returnBoolean = false;
                    }
                    else {
                        returnValue = checkId.value;
                        returnBoolean = true;
                    }
                }
            } else {
                alert("<spring:message code="comCopSecRam.validate.authorSelectResult" />"); //조회된 결과가 없습니다.
            }

            document.listForm.authorCodes.value = returnValue;

            return returnBoolean;
        }

        function fncSelectAuthorList(pageNo){
            document.listForm.searchCondition.value = "1";
            document.listForm.pageIndex.value = pageNo;
            document.listForm.action = "<c:url value='/cms/sec/ram/InfoAuthorList.do'/>";
            document.listForm.submit();
        }

        function fncSelectAuthor(author) {
            document.listForm.authorCode.value = author;
            document.listForm.action = "<c:url value='/cms/sec/ram/InfoAuthor.do'/>";
            document.listForm.submit();
        }

        function fncAddAuthorInsert() {
            location.replace("<c:url value='/cms/sec/ram/InfoAuthorInsertView.do'/>");
        }

        function fncAuthorDeleteList() {

            if(fncManageChecked()) {
                if(confirm("<spring:message code="common.delete.msg" />")){	//삭제하시겠습니까?
                    document.listForm.action = "<c:url value='/cms/sec/ram/InfoAuthorListDelete.do'/>";
                    document.listForm.submit();
                }
            }
        }

        function fncAddAuthorView() {
            document.listForm.action = "<c:url value='/cms/sec/ram/InfoAuthorUpdate.do'/>";
            document.listForm.submit();
        }

        function fncSelectAuthorRole(author) {
            document.listForm.searchKeyword.value = author;
            document.listForm.action = "<c:url value='/cms/sec/ram/InfoAuthorRoleList.do'/>";
            document.listForm.submit();
        }

        function linkPage(pageNo){
            document.listForm.searchCondition.value = "1";
            document.listForm.pageIndex.value = pageNo;
            document.listForm.action = "<c:url value='/cms/sec/ram/InfoAuthorList.do'/>";
            document.listForm.submit();
        }


        function press() {

            if (event.keyCode==13) {
                fncSelectAuthorList('1');
            }
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
    <form:form name="listForm" action="${pageContext.request.contextPath}/cms/sec/ram/InfoAuthorList.do" method="post">
    <div class="white-box">
        <!-- 검색영역 -->
        <div class="rows">
                <!-- 검색키워드 및 조회버튼 -->
                    <input type="text" value="권한명&nbsp;&nbsp;:" size="1" class="w100"  onkeypress="press();" readonly="readonly" /><!-- 부서코드 -->
                    <input class="s_input" name="searchKeyword" type="text"  size="35" title="<spring:message code="title.search" /> <spring:message code="input.input" />" value='<c:out value="${infoAuthorManageVO.searchKeyword}"/>'  maxlength="155" >
                    <input type="submit" class="button" value="<spring:message code="button.inquire" />" title="<spring:message code="title.inquire" /> <spring:message code="input.button" />" />
                    <button type="button" class="button main" onclick="location.href='<c:url value='/cms/sec/ram/InfoAuthorInsertView.do?menuTargetNo=${menuInfo.menuTargetNo}'/>'"title="등록버튼">등록</button>

        </div>
    </div>

        <h3 class="btitle"><spring:message code="title.list" /></h3>

        <!-- 목록영역 -->
        <div class="rows white-box">
            <table class="basic" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
            <thead>
            <tr>
                <%--<th><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="<spring:message code="input.selectAll.title" />"></th><!-- 번호 -->--%>
                <th class="board_th_link"><spring:message code="comCopSecRam.list.authorRollId" /></th><!-- 권한 ID -->
                <th><spring:message code="comCopSecRam.list.authorNm" /></th><!-- 권한 명 -->
                <th><spring:message code="comCopSecRam.list.authorDc" /></th><!-- 설명 -->
                <th><spring:message code="table.regdate" /></th><!-- 등록일자 -->
                <th><spring:message code="comCopSecRam.list.authorRoll" /></th><!-- 롤 정보 -->
            </tr>
            </thead>
            <tbody>
            <c:if test="${fn:length(authorList) == 0}">
                <tr>
                    <td><spring:message code="common.nodata.msg" /></td>
                </tr>
            </c:if>
            <c:forEach var="author" items="${authorList}" varStatus="status">
                <tr>
                    <%--<td class="center"><input type="checkbox" name="delYn" class="check2" title="선택"><input type="hidden" name="checkId" value="<c:out value="${author.authorCode}"/>" /></td>--%>
                    <td class="center"><a href="#LINK" onclick="javascript:fncSelectAuthor('<c:out value="${author.authorCode}"/>')"><c:out value="${author.authorCode}"/></a></td>
                    <td class="center"><c:out value="${author.authorNm}"/></td>
                    <td class="center"><c:out value="${author.authorDc}"/></td>
                    <td class="center"><c:out value="${fn:substring(author.authorCreatDe,0,10)}"/></td>
                    <td class="center"><a href="<c:url value='/cms/sec/ram/InfoAuthorRoleList.do?menuTargetNo=${menuInfo.menuTargetNo}'/>&searchKeyword=<c:out value="${author.authorCode}"/>" onclick="javascript:fncSelectAuthorRole('<c:out value="${author.authorCode}"/>')"><img src="<c:url value='/images/egovframework/com/cmm/btn/btn_search.gif'/>" width="15" height="15" align="middle" alt="롤 정보"></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>


            <!-- paging navigation -->
            <article class="pagenation">
                <ui:pagination paginationInfo="${paginationInfo}" type="cmm" jsFunction="linkPage"/>
            </article>


    </div><!-- end div board -->


        <input type="hidden" name="authorCode"/>
        <input type="hidden" name="authorCodes"/>
        <input type="hidden" name="pageIndex" value="<c:out value='${infoAuthorManageVO.pageIndex}'/>"/>
        <input type="hidden" name="searchCondition" value="1"/>
        <input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">

</form:form>
</div>
