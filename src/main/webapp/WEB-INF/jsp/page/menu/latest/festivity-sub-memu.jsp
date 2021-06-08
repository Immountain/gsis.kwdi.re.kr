<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<c:if test="${fn:length(depth2List) != 0}">

   <%--<a class="active" href="<info:url value="/festivity/info.do"/>">대회소개</a>--%>
   <%--<a href="<info:url value="/festivity/program.do"/>">주요프로그램</a>--%>
   <%--<a href="">부대프로그램</a>--%>


   <c:forEach items="${depth2List}" var="item1">

      <c:if test="${item1.viewYn=='Y'}">
         <%--<a class="active" href="<info:url value="/festivity/info.do"/>">대회소개</a>--%>
        <a  <c:if test="${menuInfo.siteMemuId ==item1.siteMemuId}">class="active"</c:if> href="<info:url value="${item1.url}?festivityId=${view.festivityId}"/>">${item1.siteMemuNm}</a>
      </c:if>
   </c:forEach>



</c:if>


