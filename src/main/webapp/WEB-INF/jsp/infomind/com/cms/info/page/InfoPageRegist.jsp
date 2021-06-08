<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!-- 테스트용 임시 -->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

<!-- 테스트용 임시 -->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>


<script src="<c:url value='/js/infomind/com/incms.polyfill.js'/>" ></script>
<script src="<c:url value='/js/infomind/com/incms.core.js'/>" ></script>
<script>
$ifx.contextPath='<c:url value="/"/>';
</script>
<script src="<c:url value="/js/infomind/com/common-ui/Highcharts/code/highcharts.src.js"/>"></script>
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<c:set var="pageTitle">페이지 관리</c:set>
<script type="text/javascript">
	$(document).ready(function() {
		fieldErrors();
		fncShowMessg();

		var codeEditor = $ifx.generateCodeEditor(infoPageInfoVO.contentInfo, {
			useThemeSelector: true
		});

		$("#fileuploader").uploadFile({
			url: "<c:url value="/"/>cms/info/file/upload.do",
			atchFileId:$("#pageImage").val(),
			viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
			multiple:true,
			dragDrop:true,
			fileName:"uploadfile",
			maxFileCount:5,
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
					data:{atchFileId:$("#pageImage").val()},
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


				var url ="<c:url value='/site/info/file/ImageView.do?atchFileId=' />"+data.atchFileId+"&fileSn="+data.fileSn;


				var testVal =data.orignlFileNm+"("+getSizeStr(data.fileSize)+")</br>"+url;


				pd.filename.html(testVal)

				$("#pageImage").val(data.atchFileId);
			},
			afterUploadAll:function(obj) {
			},
			dynamicFormData: function() {
				var data ={atchFileId:$("#pageImage").val(),prixFixe:'PAGE_'}
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

	function getSizeStr(size) {
		var sizeStr = "";
		var sizeKB = size / 1024;
		if(parseInt(sizeKB) > 1024) {
			var sizeMB = sizeKB / 1024;
			sizeStr = sizeMB.toFixed(2) + " MB";
		} else {
			sizeStr = sizeKB.toFixed(2) + " KB";
		}
		return sizeStr;
	}




	function fieldErrors() {
		var msg ="";
		<spring:hasBindErrors name="infoPageInfoVO">
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
	<form:form commandName="infoPageInfoVO" action="${pageContext.request.contextPath}/cms/info/page/insertPageInfo.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
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
							<label for="pageNm">페이지 그룹<span class="pilsu">*</span></label>
						</th>
						<td class="left">
							<form:select path="pageGroupId">
								<c:forEach var="item" items="${list_group}">
									<form:option value="${item.pageGroupId}">${item.pageGroupNm}</form:option>
								</c:forEach>
							</form:select>


						</td>
					</tr>
					<tr>
						<th style="width: 9%;">
							<label for="pageNm">페이지 아이디<span class="pilsu">*</span></label>
                        </th>
						<td class="left">
							<form:input path="pageId"  size="70" maxlength="100" />
							<div><form:errors path="pageId" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th style="width: 9%;">
							<label for="pageNm">페이지 타입<span class="pilsu">*</span></label>
						</th>
						<td class="left">
							<form:select path="pageType" >
								<form:option value="page" label="page"/>
								<form:option value="include" label="include"/>
							</form:select>
						</td>
					</tr>

					<tr>
						<th><label for="pageNm">페이지 명칭  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="pageNm" size="70" maxlength="100" />
							<div><form:errors path="pageNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="pageNm">레이아웃 선택  <span class="pilsu">*</span></label></th>
						<td class="left">
								<select name="layoutId" id="layoutId">

									<option value="page">페이지 레이아웃</option>
									<option value="hompage">메인페이지 레이아웃</option>

								</select>
						</td>
					</tr>
					<tr>
						<th><label for="modType">환경 <span class="pilsu">*</span></label></th>
						<td class="left">
							<select name="modType" id="modType">
								<option value="JSP" <c:if test="${infoPageInfoVO.modType == 'JSP'}">selected</c:if> >JSP 실행</option>
								<option value="DB"  <c:if test="${infoPageInfoVO.modType == 'DB'}">selected</c:if>>디비 실행</option>
								<option value="FOLDER" <c:if test="${infoPageInfoVO.modType == 'FOLDER'}">selected</c:if>>사용자정의</option>
							</select>
						</td>
					</tr>
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

					<!-- 내용 -->
					<tr>
						<th><label for="contentInfo">내용 <span class="pilsu">*</span></label></th>
						<td class="nopd">

							<%--<form:textarea path="contentInfo"  cols="300" rows="10" />--%>
						<%----%>
								<div>
								<form>
									<h3 class="btitle ">
										<input type="radio" name="editorTheme" id="editorTheme1" value="eclipse" checked>
										<label for="editorTheme1"><i class="bx bx-radio-circle-marked"></i>Light</label>
										<input type="radio" name="editorTheme" id="editorTheme2" value="vscode-dark">
										<label for="editorTheme2"><i class="bx bx-radio-circle-marked"></i>Dark</label>
									</h3>
                                	<textarea name="contentInfo" id="contentInfo" rows="30" cols="80"></textarea>

									<%--<script>--%>
										<%--CKEDITOR.replace('contentInfo');--%>
									<%--</script>--%>
									</form>
								</div>
							<div><form:errors path="contentInfo" cssClass="error" /></div>


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
				<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
				<span class="button"><a href="<c:url value='/cms/info/page/selectPageInfoList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
			</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
		<input name="pageImage" id="pageImage" type="hidden" value="${infoPageInfoVO.pageImage}">
	</form:form>
</div>