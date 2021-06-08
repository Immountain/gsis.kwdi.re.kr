<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="container">

    <div class="slider">
        <c:forEach items="${bannerList}" var="item">

            <div class="slides">

                <a href="${item.linkUrl}" target="${item.linkType}"><img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.bannerImage}&fileSn=${item.fileSn}" alt="${item.bannerNm}">
                        ${item.bannerNm}
                </a>

            </div>
        </c:forEach>

    </div>

    <button class="arrow left">이전<i class='bx bx-chevron-left'></i></button>
    <button class="arrow right">다음<i class='bx bx-chevron-right'></i></button>
</div>


