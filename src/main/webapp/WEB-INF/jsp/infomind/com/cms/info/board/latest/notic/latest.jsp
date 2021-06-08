<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ul>
    <c:forEach items="${latestBoardItems}" var="item">
        <li>
            <i>${item.title}</i>
            <i>${item.regDt}</i>
        </li>
    </c:forEach>


</ul>