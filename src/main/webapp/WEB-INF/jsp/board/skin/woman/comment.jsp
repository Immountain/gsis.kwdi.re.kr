<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>





<c:if test="${fn:length(commentList) != 0}">
  <c:forEach items="${commentList}" var="item" varStatus="status">

            <li>
                <h5>${item.title}</h5>
                <div class="text">
                        ${item.txtContent}
                </div>


                <%--<ul class="file">--%>
                    <%--<li>--%>
                        <%--<i class='bx bx-file'></i>--%>
                        <%--<a href="" title="야옹야옹.hwp">야옹야옹.hwp</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<i class='bx bx-file'></i>--%>
                        <%--<a href="" title="야옹야옹.hwp">야옹야옹.hwp</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>

                <%--<ul class="link">--%>
                    <%--<li>--%>
                        <%--<i class='bx bx-link'></i>--%>
                        <%--<a href="" title="http://daum.net">http://daum.net</a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<i class='bx bx-link'></i>--%>
                        <%--<a href="" title="http://daum.net">http://daum.net</a>--%>
                    <%--</li>--%>
                <%--</ul>--%>
                <div class="btn-set right">
                    <%--<button class="btn">수정</button>--%>
                    <button class="btn" type="button" onclick="commentDelete()">삭제</button>
                </div>

            </li>

        </c:forEach>


</c:if>





