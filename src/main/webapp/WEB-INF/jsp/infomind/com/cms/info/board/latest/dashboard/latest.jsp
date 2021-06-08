<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>








<ul>
    <c:forEach items="${latestBoardItems}" var="item">
        <li>
            <a href="javascript:gotoPop('${item.boardId}','${item.itemId}');">
                ${item.title}
                <i>${item.regDt}</i>
            </a>
        </li>
    </c:forEach>
</ul>