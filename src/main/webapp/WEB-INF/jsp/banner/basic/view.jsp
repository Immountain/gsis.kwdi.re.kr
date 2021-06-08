<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


배너그룹명 :${bannerGrp.bannerGroupNm}<br>
배너 그룹 설명 :${bannerGrp.bannerGroupTxt}<br>


배너리트
<c:forEach items="${bannerList}" var="item">
    <li>

           배너 타이틀: ${item.bannerNm}<br>
            배너 내용: ${item.bannerTxt}
    </li>
</c:forEach>



