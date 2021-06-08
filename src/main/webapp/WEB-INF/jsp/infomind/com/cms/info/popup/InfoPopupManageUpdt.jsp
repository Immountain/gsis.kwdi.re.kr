<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<!-- datePicker -->
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/pickadate/lib/themes/classic.css"/>">
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/pickadate/lib/themes/classic.date.css"/>">
<link rel="stylesheet" href="<c:url value="/js/infomind/com/common-ui/pickadate/lib/themes/classic.time.css"/>">
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/picker.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/picker.date.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/picker.time.js"/>"></script>
<script src="<c:url value="/js/infomind/com/common-ui/pickadate/lib/translations/ko_KR.js"/>"></script>
<script src="<c:url value="/js/infomind/com/moment.js"/>"></script>

<c:set var="pageTitle">팝업</c:set>
<script type="text/javascript">
$(document).ready(function() {

	$('#ntceBgnde').pickadate({
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyymmdd'
	});
	$('#ntceEndde').pickadate({
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyymmdd'
	});

	fncShowMessg();

	$("#fileuploader").uploadFile({
		url: "<c:url value="/"/>cms/info/file/upload.do",
		atchFileId:$("#atchFileId").val(),
		viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
		multiple:true,
		dragDrop:true,
		fileName:"uploadfile",
		maxFileCount:1,
		returnType:"json",
		showPreview:true,
		previewHeight: "100px",
		previewWidth: "100px",
		showDelete: true,
		showDownload:true,
		sequential:true,
		sequentialCount:1,
		onLoad:function(obj) {
			$.ajax({
				cache: false,
				url: "<c:url value="/"/>cms/info/file/tempList.do",
				dataType: "json",
				data:{atchFileId:$("#atchFileId").val()},
				success: function(data) {
					for(var i=0;i<data.length;i++) {
						obj.createProgress(data[i]);
					}
				}
			});
		},
		onSubmit:function(files) {
		},
		onSuccess:function(files,data,xhr,pd) {
			$("#atchFileId").val(data.atchFileId);
		},
		afterUploadAll:function(obj) {
		},
		dynamicFormData: function() {
			var data ={atchFileId:$("#atchFileId").val(),prixFixe:'PAGE_'}
			return data;
		},
		onError: function(files,status,errMsg,pd) {
		},
		deleteCallback: function (data, pd) {
			$.ajax({
				cache: false,
				url: "<c:url value="/"/>cms/info/file/delete.do",
				dataType: "json",
				data:{atchFileId:data.atchFileId,fileSn:data.fileSn},
				success: function(data) {
					pd.statusbar.hide(); //You choice.
				}
			});
		},
		downloadCallback:function(data,pd) {
			location.href="/cms/info/file/fileDown.do?atchFileId="+data.atchFileId+"&fileSn="+data.fileSn;
		}
	});
});

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_update_page(form){
	//input item Client-Side validate
	if(confirm("<spring:message code="common.update.msg" />")){
		form.action="<c:url value='/cms/info/popup/updatePopupManage.do' />";
		form.submit();
	}
}

function fncGoBack(){
	document.resultVO.action="<c:url value='/cms/info/popup/popupManageList.do' />";
	document.resultVO.submit();
}

/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
function fncShowMessg(){
	if("<c:out value='${message}'/>" != ''){
		alert("<c:out value='${message}'/>");
	}
}
</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.detail" />
	</h2>

	<form:form commandName="resultVO" name="resultVO" id="resultVO" action="${pageContext.request.contextPath}/cms/info/popup/updatePopupManage.do" method="post" onSubmit="fn_update_page(document.forms[0]); return false;">
		<%--hidden text--%>
		<info:hiddenField searchHiddenField="${resultVO}" type="hidden"/>
		<h3 class="btitle">
			수정 내역
		</h3>

		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<tr>
						<th style="width: 9%;">
							<label for="popupId">팝업 아이디<span class="pilsu">*</span></label>
						</th>
						<td class="left">
							${resultVO.popupId}
						</td>
					</tr>
					<tr>
						<th><label for="popupNm">팝업 명  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="popupNm" size="70" maxlength="100" />
							<div><form:errors path="popupNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="popupTxt">팝업 내용 <span class="pilsu">*</span></label></th>
						<td class="nopd">
								<%--<form:textarea path="contentInfo"  cols="300" rows="10" />--%>
								<%----%>
							<div>
								<form>
									<form:textarea path="popupTxt" id="popupTxt" rows="5" cols="80" maxlength="500"></form:textarea>
								</form>
							</div>
							<div><form:errors path="popupTxt" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label >배너 이미지<span class="pilsu">*</span></label></th>
						<td class="left">
							<div id="fileuploader">Upload</div>
						</td>
					</tr>

					<tr>
						<th><label for="linkType">링크타입 </label></th>
						<td class="left">
							<form:select path="linkType">
								<form:option value="_self">현재창</form:option>
								<form:option value="_black">새창</form:option>
							</form:select>
							<div><form:errors path="linkType" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="linkUrl">링크URL </label></th>
						<td class="left">
							<form:input path="linkUrl" size="70" maxlength="25" />
							<div><form:errors path="linkType" cssClass="error" /></div>
						</td>
					</tr>


					<tr>
						<th><label for="popupWidthLc">팝업 위치 </label></th>
						<td class="left">
							x: <form:input path="popupWidthLc" size="10" maxlength="10" />
							y: <form:input path="popupVrticlLc" size="10" maxlength="10" />
						</td>
					</tr>
					<tr>
						<th><label for="popupWidthSize">팝업 사이즈 </label></th>
						<td class="left">
							width: <form:input path="popupWidthSize" size="10" maxlength="10" />
							height: <form:input path="popupVrticlSize" size="10" maxlength="10" />
						</td>
					</tr>

					<tr>
						<th><label for="styleClass">스타일 </label></th>
						<td class="left">
							<form:input path="styleClass" size="70" maxlength="10" />
							<div><form:errors path="styleClass" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label for="ntceBgnde">게시시작일자 </label></th>
						<td class="left">
							<input type="date" id="ntceBgnde" name="ntceBgnde" value="${resultVO.ntceBgnde}">
							<select id="strHour" name="strHour">
								<c:forEach items="${resultVO.hourList}" var="item" varStatus="status">
									<option value="${item}" <c:if test="${resultVO.strHour==item}">selected</c:if> >${item}</option>
								</c:forEach>
							</select>
							<select id="strMin" name="strMin">
								<c:forEach items="${resultVO.minList}" var="item" varStatus="status">
									<option value="${item}" <c:if test="${resultVO.strMin==item}">selected</c:if> >${item}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="ntceBgnde">게시종료일자 </label></th>
						<td class="left">

							<input type="date" id="ntceEndde" name="ntceEndde" value="${resultVO.ntceEndde}">
							<select id="endHour" name="endHour">
								<c:forEach items="${resultVO.hourList}" var="item" varStatus="status">
									<option value="${item}" <c:if test="${resultVO.endHour==item}">selected</c:if> >${item}</option>
								</c:forEach>
							</select>
							<select id="endMin" name="endMin">
								<c:forEach items="${resultVO.minList}" var="item" varStatus="status">
									<option value="${item}" <c:if test="${resultVO.endMin==item}">selected</c:if> >${item}</option>
								</c:forEach>
							</select>

						</td>
					</tr>

					<tr>
						<!-- 사용여부 -->
						<th>사용여부<span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="ntceAt" cssClass="txt">
								<form:option value="Y"  label="사용"/>
								<form:option value="N" label="사용안함"/>
							</form:select>
							<div><form:errors path="ntceAt" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label for="temp1">임시필드1 </label></th>
						<td class="left">
							<form:input path="temp1" size="70" maxlength="250" />
							<div><form:errors path="temp1" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp2">임시필드2 </label></th>
						<td class="left">
							<form:input path="temp2" size="70" maxlength="250" />
							<div><form:errors path="temp2" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp3">임시필드3 </label></th>
						<td class="left">
							<form:input path="temp3" size="70" maxlength="250" />
							<div><form:errors path="temp3" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp4">임시필드4 </label></th>
						<td class="left">
							<form:input path="temp4" size="70" maxlength="250" />
							<div><form:errors path="temp4" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp5">임시필드5 </label></th>
						<td class="left">
							<form:input path="temp5" size="70" maxlength="250" />
							<div><form:errors path="temp5" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
			<button class="button" type="button" onclick="fncGoBack()"> <spring:message code="button.list" /></button>
		</div>
		<input name="popupId" id="popupId" type="hidden" value="${resultVO.popupId}">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<form:hidden path="popupImage" id="atchFileId" />
		<input name="cmd" type="hidden" value="<c:out value='update'/>">
	</form:form>
</div>