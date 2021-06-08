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


<c:set var="pageTitle">아이피 관리</c:set>
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


</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="resultVO" action="${pageContext.request.contextPath}/cms/info/hurt635/insertHurt635.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">등록 내역</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->

					<tr>
						<th style="width: 9%;">
							<label for="keySeq">시퀀스<span class="pilsu">*</span></label>
                        </th>
						<td class="left" >
							<form:input path="keySeq"  size="50" maxlength="70" readonly="true"/>
							<div><form:errors path="keySeq" cssClass="error" /></div>
						</td>
						<!-- 허용여부 -->
						<th>허용여부<span class="pilsu">*</span></th>
						<td class="left" >
							<form:select path="allwYn" cssClass="txt">
								<form:option value=""  label="선택하세요"/>
								<form:option value="Y"  label="허용"/>
								<form:option value="N" label="허용안함"/>
							</form:select>
							<div><form:errors path="allwYn" cssClass="error" /></div>
						</td>
					</tr>

					<tr><!-- IP 주소 -->
						<th> IP <span class="pilsu">*</span></th>
						<td class="left" colspan="4">
							  <form:input path="ip1" id="ip1" cssClass="txaIpUmt"  maxlength="3" style="width:60px;" onKeyup="this.value=this.value.replace(/[^0-9*]/g,'');"/>
							. <form:input path="ip2" id="ip2" cssClass="txaIpUmt"  maxlength="3" style="width:60px;" onKeyup="this.value=this.value.replace(/[^0-9*]/g,'');"/>
							. <form:input path="ip3" id="ip3" cssClass="txaIpUmt"  maxlength="3" style="width:60px;" onKeyup="this.value=this.value.replace(/[^0-9*]/g,'');"/>
							. <form:input path="ip4" id="ip4" cssClass="txaIpUmt"  maxlength="3" style="width:60px;" onKeyup="this.value=this.value.replace(/[^0-9*]/g,'');"/>
							숫자 또는 *만 입력 가능합니다.
							<div><form:errors path="ip1" cssClass="error" /></div>
							<div><form:errors path="ip2" cssClass="error" /></div>
							<div><form:errors path="ip3" cssClass="error" /></div>
							<div><form:errors path="ip4" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="remk">비고<span class="pilsu">*</span></label></th>
						<td class="left" colspan="4">
							<form:input path="remk" size="70" maxlength="200" />
							<div><form:errors path="remk" cssClass="error" /></div>
						</td>
					</tr>

				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
			<span class="button"><a href="<c:url value='/cms/info/hurt635/hurt635List.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>

		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>