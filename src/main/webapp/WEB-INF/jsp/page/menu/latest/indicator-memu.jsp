<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- S:main_menu -->

<%--${depth1Title}->${depth2Title}->${depth3Title}--%>


<%--<c:if test="${fn:length(depth1List) != 0}">--%>
<%--<ul class="submenu subdepth01">--%>

         <%--<li>--%>
            <%--<input id="subdepth01" type="checkbox"><label for="subdepth01">${depth1Title}</label>--%>
            <%--<ul>--%>
               <%--<c:forEach items="${depth1List}" var="item1">--%>
                     <%--<li><a href="#">${item1.siteMemuNm}</a></li>--%>
               <%--</c:forEach>--%>
            <%--</ul>--%>
         <%--</li>--%>
<%--</ul>--%>

<%--</c:if>--%>
<c:if test="${fn:length(depth1List) != 0}">

   <p>${depth1Title}</p>



</c:if>

<c:if test="${fn:length(depth2List) != 0}">
   <p>${depth2Title}</p>


   <%--<ul class="submenu subdepth02">--%>

      <%--<li>--%>
         <%--<input id="subdepth02" type="checkbox"><label for="subdepth02">${depth2Title}</label>--%>
         <%--<ul>--%>
            <%--<c:forEach items="${depth2List}" var="item1">--%>

               <%--<c:if test="${item1.viewYn=='Y'}">--%>
                  <%--<li><a  <c:if test="${item1.checkYn == 'Y'}">class="on"</c:if> href="${item1.url}">${item1.siteMemuNm}</a></li>--%>
               <%--</c:if>--%>
            <%--</c:forEach>--%>
         <%--</ul>--%>
      <%--</li>--%>
   <%--</ul>--%>

</c:if>







