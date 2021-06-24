<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- S:main_menu -->


<c:if test="${fn:length(depth1List) != 0}">
   <a href="">${depth1Title}<i class='bx bx-chevron-right' ></i></a>
</c:if>
<c:if test="${fn:length(depth2List) != 0}">
   <strong>${depth2Title}</strong>
</c:if>










