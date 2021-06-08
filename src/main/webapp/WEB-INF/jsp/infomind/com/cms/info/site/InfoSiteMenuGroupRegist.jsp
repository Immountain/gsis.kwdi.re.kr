<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<c:set var="pageTitle">사이트 메뉴 그룹 관리</c:set>
<script type="text/javascript">
$(document).ready(function() {
	fncShowMessg();
	fieldErrors();

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
function fn_regist_page(form){
	//input item Client-Side validate
	if(confirm("<spring:message code="common.regist.msg" />")){
		form.submit();
	}
}

/* ********************************************************
* 서버 처리 후 메세지 화면에 보여주기
******************************************************** */
function fncShowMessg(){
	if("<c:out value='${message}'/>" != ''){
		alert("<c:out value='${message}'/>");
	}
}
function fieldErrors() {
	var msg ="";
	<spring:hasBindErrors name="resultVO">
	<c:forEach items="${errors.fieldErrors}" var="error">
	msg =msg+'${error.defaultMessage}'+"\n";
	</c:forEach>
	</spring:hasBindErrors>
	if(msg){

		alert(msg);
	}
}



</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="resultVO" action="${pageContext.request.contextPath}/cms/info/site/insertSiteMenuGroup.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">등록 내역</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 15%;"><label for="menuGroupNm">사이트 <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:select path="siteId" cssClass="w400">
								<form:options items="${infoSite}" itemLabel="fullPath" itemValue="siteId"/>
							</form:select>
							<div><form:errors path="menuGroupNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th >
							<label for="menuGroupId">사이트 메뉴그룹 아이디<span class="pilsu">*</span></label>
                        </th>
						<td class="left">
							<form:input path="menuGroupId"  size="50" maxlength="50" />
							<div><form:errors path="menuGroupId" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="menuGroupNm">사이트 메뉴그룹 명칭  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="menuGroupNm" size="50" maxlength="50" />
							<div><form:errors path="menuGroupNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<!-- 언어 -->
						<th>언어<span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="menuGroupLocale" cssClass="w400">
								<form:options items="${langCd}" itemLabel="codeNm" itemValue="code"/>
							</form:select>
							<div><form:errors path="menuGroupLocale" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<!-- 사용여부 -->
						<th>사용여부<span class="pilsu">*</span></th>
						<td class="left">
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
			<input type="submit" class="button" value="등록" title="등록버튼" />
			<span class="button"><a href="<c:url value='/cms/info/site/InfoSiteMenuGroupList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>

		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">


		<input name="cmd" type="hidden" value="<c:out value='update'/>">
	</form:form>
</div>