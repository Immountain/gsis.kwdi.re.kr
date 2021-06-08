<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="frame-area">
	<div class="frame-child" frame-id="0">

		<c:if test="${loginVO.userSe=='ENT'}">
			<iframe  id='content_frame' src="<c:url value="/cms/dashboard2.do"/>" />
		</c:if>
		<c:if test="${loginVO.userSe=='USR'}">
			<iframe  id='content_frame' src="<c:url value="/cms/dashboard.do"/>" />
		</c:if>


	</div>

</div>
