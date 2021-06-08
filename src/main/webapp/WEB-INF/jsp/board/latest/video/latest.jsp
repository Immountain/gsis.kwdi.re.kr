<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<div class="gallery mov">
    <h3><spring:message code="메인.영상자료"/></h3>
    <ul>
        <c:forEach items="${latestBoardItems}" var="item" varStatus="status">

            <li>
                    <a href="${item.linkUrl}" target="${item.linkType}">
                    <p class="image">
                            <img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.boardFile}">
                    </p>
                    </a>
            </li>


        </c:forEach>

    </ul>
    <a class="more" href="<info:url value="/board/video/list.do"/>">more</a>

</div>


