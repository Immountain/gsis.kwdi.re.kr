<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="slider">

        <c:forEach items="${bannerList}" var="item">
            <div class="slide">
                <a href="">
                    <img src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${item.bannerImage}&fileSn=${item.fileSn}">
                    <p class="text"> ${item.bannerNm}</p>
                </a>
            </div>

        </c:forEach>
</div>
<button class="pause"><i class='bx bx-pause'></i></button>
<button class="play none"><i class='bx bx-play'></i></button>
<button class="arrow left"></button>
<p class="pagingInfo"></p>
<button class="arrow right"></button>





