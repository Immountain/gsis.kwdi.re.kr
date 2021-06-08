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

<c:set var="pageTitle">사이트</c:set>
<script type="text/javascript">
$(document).ready(function() {
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
		form.action="<c:url value='/cms/info/site/updateSite.do' />";

		if(form.subPath.value.indexOf('/') > -1) {
			alert('서브패스 "/" 는 자동 입력 됩니다.');
			return false;
		}
		form.submit();
	}
}

function fncGoBack(){
	document.resultVO.action="<c:url value='/cms/info/site/InfoSiteList.do' />";
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

	<form:form commandName="resultVO" name="resultVO" id="resultVO" action="${pageContext.request.contextPath}/cms/info/site/updateSite.do" method="post" onSubmit="fn_update_page(document.forms[0]); return false;">
		<%--hidden text--%>
		<info:hiddenField searchHiddenField="${searchVO}" type="hidden"/>
		<h3 class="btitle">
			수정 내역
		</h3>

		<div class="rows white-box">
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							<label for="siteId">사이트 아이디<span class="pilsu">*</span></label>
						</th>
						<td class="left">
							<form:input path="siteId"  size="70" maxlength="10" />
							<div><form:errors path="siteId" cssClass="error" /></div>
						</td>
						<th style="width: 9%;">
							<label for="siteId">사이트 대표 언어<span class="pilsu">*</span></label>
						</th>
						<td class="left" >
							<form:select path="langCd" cssClass="w400">
								<form:options items="${langCd}" itemLabel="codeNm" itemValue="code"/>
							</form:select>
							<div><form:errors path="langCd" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label for="host">호스트 명  <span class="pilsu">*</span></label></th>
						<td class="left">
							<label class="before" for="host">운영</label>
							<form:input path="host" size="60" maxlength="100" />
							<div><form:errors path="host" cssClass="error" /></div>
							<label class="before" for="debugHost">개발</label>
							<form:input path="debugHost" size="60" maxlength="100" />
							<div><form:errors path="debugHost" cssClass="error" /></div>
						</td>
						<th><label for="subPath">서브패스  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="subPath" size="70" maxlength="25" placeholder="SitePath"/>
							<div><form:errors path="subPath" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="layout">레이아웃  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="layout" size="70" maxlength="25" />
							<div><form:errors path="layout" cssClass="error" /></div>
						</td>
						<th><label for="theme">테마  <span class="pilsu">*</span></label></th>
						<td class="left" >
							<form:input path="theme" size="70" maxlength="25" />
							<div><form:errors path="theme" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="indexPage">인덱스페이지  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="indexPage" size="70" maxlength="25" />
							<div><form:errors path="indexPage" cssClass="error" /></div>
						</td>
						<th>메인페이지여부<span class="pilsu">*</span></th>
						<td class="left">
							<form:radiobutton path="mainSiteYn" id="mainSiteYn_Y" value="Y"/>
							<label for="mainSiteYn_Y"><i class="bx bx-radio-circle-marked"></i>사용</label>
							<form:radiobutton path="mainSiteYn" id="mainSiteYn_N" value="N"/>
							<label for="mainSiteYn_N"><i class="bx bx-radio-circle-marked"></i>사용안함</label>

							<div><form:errors path="mainSiteYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<!-- 사용여부 -->
						<th>사용여부<span class="pilsu">*</span></th>
						<td class="left" colspan="4">
							<form:radiobutton path="useYn" id="useYn_Y" value="Y"/>
							<label for="useYn_Y"><i class="bx bx-radio-circle-marked"></i>사용</label>
							<form:radiobutton path="useYn" id="useYn_N" value="N"/>
							<label for="useYn_N"><i class="bx bx-radio-circle-marked"></i>사용안함</label>

							<div><form:errors path="useYn" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
			<span class="button"><a href="<c:url value='/cms/info/site/InfoSiteList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">

<%--		<form:hidden path="menuGroupNm" id="atchFileId" />--%>
<%--		<input name="cmd" type="hidden" value="<c:out value='save'/>">--%>
	</form:form>
</div>