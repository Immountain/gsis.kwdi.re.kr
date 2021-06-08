<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<div class="board notice">
    <h3><spring:message code="메인.공지사항"/></h3>

    <c:if test="${fn:length(latestBoardItems)>0}">


        <div class="headline">
            <a href="<info:url value='/board/notic/view.do?itemId=${latestBoardItems[0].itemId}'/>">
                <p class="date">
                        ${latestBoardItems[0].regDtYyyy}<strong>${latestBoardItems[0].regDtMm}.${latestBoardItems[0].regDtDd}</strong>
                </p>
                <p class="text">
                    <strong>${latestBoardItems[0].title}</strong>
                    <span>${latestBoardItems[0].memo}</span>
                </p>
            </a>
        </div>
    <ul>
        <c:forEach items="${latestBoardItems}" var="item" varStatus="status">
            <c:if test="${status.index !=0}">
                <li>
                      <a href="<info:url value='/board/notic/view.do?itemId=${item.itemId}'/>">${item.title}</a>
                </li>

            </c:if>
        </c:forEach>
        </ul>
    </c:if>
    <a class="more" href="<info:url value="/board/notic/list.do"/>">more</a>
</div>


