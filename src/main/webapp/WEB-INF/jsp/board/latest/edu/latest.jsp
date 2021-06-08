<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<div class="program">
    <c:if test="${fn:length(latestBoardItems) == 0}">
        <h3>현재 교육이 없습니다.</h3>
    </c:if>
    <c:forEach items="${latestBoardItems}" var="item">
        <h3>${item.title}</h3>
        <p class="term">교육일정: ${item.noticeStartYyyy}.${item.noticeStartMm}.${item.noticeStartDd}~${item.noticeEndYyyy}.${item.noticeEndMm}.${item.noticeEndDd}</p>
        <p class="status status01">접수중</p>
    </c:forEach>
</div>