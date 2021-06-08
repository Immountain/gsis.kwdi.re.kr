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


<c:set var="pageTitle">이미지 관리</c:set>
<script type="text/javascript">

	// http://hayageek.com/docs/jquery-upload-file.php
	// 업로드 참고
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
function fn_regist(form){
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
		<spring:hasBindErrors name="infoPageImageGroupVO">
		<c:forEach items="${errors.fieldErrors}" var="error">
		msg =msg+'${error.defaultMessage}'+"\n";
		</c:forEach>
		</spring:hasBindErrors>
		if(msg){		alert(msg);
		}
	}


</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="infoPageImageGroupVO" action="${pageContext.request.contextPath}/cms/info/pageimage/insertPageImage.do" method="post">

		<form:hidden path="atchFileId" />

		<h3 class="btitle">
			등록 내역
		</h3>



		<div class="rows white-box">
				<!-- 등록폼 -->
				<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							<label for="imageId">이미지 아이디<span class="pilsu">*</span></label>
                        </th>
						<td class="left">
							<form:input path="imageId"  size="70" maxlength="10" />
							<div><form:errors path="imageId" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="imageNm">이미지 명칭  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="imageNm" size="70" maxlength="250" />
							<div><form:errors path="imageNm" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<!-- 이미지 -->
						<th>이미지<span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="imageGb" cssClass="txt">
								<form:option value="PAGE"  label="페이지 이미지"/>
								<form:option value="BOARD" label="게시판 이미지"/>
							</form:select>
							<div><form:errors path="imageGb" cssClass="error" /></div>
						</td>
					</tr>


					<tr>
					<!-- 사용여부 -->
					<th>사용여부<span class="pilsu">*</span></th>
					<td class="left">
						<form:select path="useYn" cssClass="txt">
							<form:option value="Y"  label="사용"/>
							<form:option value="N" label="사용안함"/>
						</form:select>
						<div><form:errors path="useYn" cssClass="error" /></div>
					</td>
					</tr>
					<tr>
						<th><label for="imageDc">이미지 설명  <span class="pilsu">*</span></label></th>
						<td class="left">

							<form:textarea path="imageDc" rows="5" maxlength="250"/>
							<div><form:errors path="imageDc" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label >파일 업로드<span class="pilsu">*</span></label></th>
						<td class="left">
							<div id="fileuploader">Upload</div>

						</td>
					</tr>

				</tbody>
				</table>
		</div>
				<!-- 하단 버튼 -->
			<div class="btn-set right">
				<input class="button" type="submit" value='<spring:message code="button.save" />' onclick="fn_regist(document.forms[0]); return false;" /><!-- 저장 -->
				<span class="button"><a href="<c:url value='/cms/info/pageimage/selectPageImageList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
			</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">


	</form:form>
</div>

<script type="text/javascript">

</script>