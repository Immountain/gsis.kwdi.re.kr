<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%pageContext.setAttribute("crlf", "\r\n"); %>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">

<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value='/source/js/common.js'/>" ></script>

<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<script src="<c:url value='/js/infomind/com/incms.polyfill.js' />"></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
$ifx.contextPath='<c:url value="/"/>';
</script>


<script type="text/javascript">


    $(document).ready(function() {





    })



</script>
    <section class="${pageCss}">
        <div class="container">
            <h3 class="subtitle">연구시설∙장비 매뉴얼</h3>

            <div class="docs-frame">
                <h4>연구장비공동활용지원사업 관리지침</h4>
                <p>
                    발행기관(출처) : ${boardItem.temp1} / 발행일 : ${boardItem.temp2}/ 분류 : ${boardItem.temp3} / 조회 : ${boardItem.readCnt}
                </p>


                <c:if test="${boardItem.fileCnt >0}">
                    <a class="download" href="/site/info/file/fileDown.do?atchFileId=${boardItem.boardFile}">
                        <i class="bx bx-download"></i>
                        다운로드
                    </a>
                    <div class="docs-outline">

                        <iframe src = "/site/info/file/ImageView.do?atchFileId=${boardItem.boardFile}" title="PDF 자료" width="100%" height="562px" scrolling="yes" ></iframe>
                        <object data="/site/info/file/ImageView.do?atchFileId=${boardItem.boardFile}" type="application/pdf" width="100%" height="562px" style="display:none;">
                            <embed src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${boardItem.boardFile}" type="application/pdf" />
                        </object>

                        <%--<iframe src="<c:url value="/"/>site/info/file/ImageView.do?atchFileId=${boardItem.boardFile}" width="100%" height="100%"></iframe>--%>
                    </div>

                </c:if>
            </div>
        </div>
    </section>

    <article class="docs-text">
        <div class="container">
            <h5>메뉴얼 정보</h5>

            <div class="text">
                <c:out value="${fn:replace(boardItem.contentInfoDecode , crlf , '<br/>') }" escapeXml="false"/>
            </div>
            <p class="info">[최종수정일: ${boardItem.modDt}]</p>
            <div class="btn-set center big">
                <button class="btn" type="button" onclick="history.back()">목록으로</button>
            </div>
        </div>
    </article>


