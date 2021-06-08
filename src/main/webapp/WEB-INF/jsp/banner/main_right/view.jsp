<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<ul class="slider slider1 right">
    <c:forEach items="${bannerList}" var="item">
        <li>
            <img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.bannerImage}&fileSn=${item.fileSn}" />
        </li>
    </c:forEach>

</ul>

