<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<ul>

    <c:forEach items="${userMenuList}" var="item">

        <c:if test="${item.viewYn=='Y'}">

            <li>
                <h3><a href="${item.url}">${item.siteMemuNm}</a></h3>

                <c:if test="${fn:length(item.childMenu) != 0}">

                    <ul>
                        <c:forEach items="${item.childMenu}" var="item1">
                            <c:if test="${item1.viewYn=='Y'}">
                                <li>
                                    <a href="${item1.url}">${item1.siteMemuNm}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </c:if>
            </li>


        </c:if>

    </c:forEach>

</ul>


