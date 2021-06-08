<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<!-- 테스트용 임시 -->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>




<c:set var="pageTitle">공지 게시판</c:set>
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


			 var url ="<c:url value='/cms/info/file/ImageView.do?atchFileId=' />"+data.atchFileId+"&fileSn="+data.fileSn;


			 var testVal =data.orignlFileNm+"("+getSizeStr(data.fileSize)+")</br>"+url;


			pd.filename.html(testVal)

			$("#boardFile").val(data.atchFileId);
		},
		afterUploadAll:function(obj) {
		},
		dynamicFormData: function() {
			var data ={atchFileId:$("#boardFile").val(),prixFixe:'BOAD_'}
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
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_regist_page(form){
	//input item Client-Side validate
	if(confirm("<spring:message code="common.regist.msg" />")){
		form.submit();
	}
}


function fn_list() {


	document.resultVO.action = "<c:url value='/cms/info/board/boardItemList.do'/>";
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
		<i class='bx bxs-dashboard' ></i>상세
	</h2>
	<form:form commandName="resultVO" id="resultVO" name="resultVO" action="${pageContext.request.contextPath}/cms/info/board/insertBoardItem.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">${pageTitle} 등록</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							제목
						</th>
						<td class="left">
								${resultVO.title}
						</td>
					</tr><tr>
						<th style="width: 9%;">
							메모
						</th>
						<td class="left">
								${resultVO.memo}
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="nopd">

								${resultVO.contentInfoDecode}

							<%--<textarea name="boardContent" id="boardContent" rows="30" cols="80">--%>
								<%--<c:out value="${resultVO.contentInfoDecode} "></c:out>--%>
                                <%--</textarea>--%>
						</td>
					</tr>


				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<button type="button" class="button main" onclick="fn_list()"  title="목록"  >목록</button>


		</div>
		<input name="boardId" id="boardId" type="hidden" value="${resultVO.boardId}">
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="boardFile" id="boardFile" type="hidden" value="${resultVO.boardFile}">
		<input name="cmd" type="hidden" value="<c:out value='view'/>">
	</form:form>
</div>