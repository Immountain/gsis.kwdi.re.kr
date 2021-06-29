<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<!-- 테스트용 임시 media-->
<script src="<c:url value='/source/temp_editor/ckeditor.js'/>" ></script>

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


<c:set var="pageTitle">보고서 게시판</c:set>
<script type="text/javascript">
$(document).ready(function() {

	$('#strDay').pickadate({
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyymmdd'
	});
	$('#endDay').pickadate({
		format: 'yyyy-mm-dd',
		formatSubmit: 'yyyymmdd'
	});

	fncShowMessg();

	$("#fileuploader").uploadFile({
		url: "<c:url value="/"/>cms/info/file/upload.do",
		atchFileId:$("#boardFile").val(),
		viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
		multiple:true,
		dragDrop:true,
		fileName:"uploadfile",
		maxFileCount:$("#maxFileCount").val(),
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
				data:{atchFileId:$("#boardFile").val()},
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



	$("#fileuploader2").uploadFile({
		url: "<c:url value="/"/>cms/info/file/upload.do",
		atchFileId:$("#file01").val(),
		viewUrl:"<c:url value='/cms/info/file/ImageView.do' />",
		multiple:true,
		dragDrop:true,
		fileName:"uploadfile",
		maxFileCount:$("#maxFileCount").val(),
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
				data:{atchFileId:$("#file01").val()},
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
			$("#file01").val(data.atchFileId);
		},
		afterUploadAll:function(obj) {
		},
		dynamicFormData: function() {
			var data ={atchFileId:$("#file01").val(),prixFixe:'BOAD_'}
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


	var strDay =$('#strDay').val().replaceAll("-","")+$("#strHour option:selected").val() +$("#strMin option:selected").val();
	var endDay =$('#endDay').val().replaceAll("-","")+$("#endHour option:selected").val() +$("#endMin option:selected").val();

	$('#noticeStartDate').val(strDay);
	$('#noticeEndDate').val(endDay)


	if(!$('#title').val()){
		alert("제목을 입력하세요.");
		return;

	}


	if(confirm("<spring:message code="common.regist.msg" />")){
		form.submit();
	}
}


function fn_list() {


	document.resultVO.action = "<c:url value='/cms/info/board/c/boardItemList.do'/>";
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
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<form:form commandName="resultVO" id="resultVO" name="resultVO" action="${pageContext.request.contextPath}/cms/info/board/c/insertBoardItem.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<input id="noticeStartDate"  name="noticeStartDate" type="hidden" value="">
		<input id="noticeEndDate" name="noticeEndDate"  type="hidden" value="">
        <input name="maxFileCount" id="maxFileCount" type="hidden" value="${boardVO.atchPosblFileNumber}">


		<h3 class="btitle">${pageTitle} 등록</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							<label for="boardId">게시판 아이디<span class="pilsu">*</span></label>
                        </th>
						<td class="left">
							<form:input path="boardId"  size="70" maxlength="20" readonly="true"/>
							<div><form:errors path="boardId" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th style="width: 9%;">
							<label for="title">제목<span class="pilsu">*</span></label>
						</th>
						<td class="left">
							<form:input path="title"  size="70" maxlength="100" />
							<div><form:errors path="title" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th style="width: 9%;">
							<label for="memo">메모<span class="pilsu">*</span></label>
						</th>
						<td class="left">
							<form:input path="memo"  size="70" maxlength="20" />
							<div><form:errors path="memo" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>공지여부<span class="pilsu">*</span></th>
						<td class="left">
						<form:select path="noticeYn" cssClass="txt">
							<form:option value="N" label="사용안함"/>
							<form:option value="Y"  label="사용"/>

						</form:select>
						<div><form:errors path="noticeYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="strDay">공지 게시일자 </label></th>
						<td class="left">
							<input class="date" type="date" id="strDay" name="strDay" max="9999-12-31" value="${boardVO.tempStrDay}">
						<span>~</span>
							<input class="date" type="date" id="endDay" name="endDay" max="9999-12-31" value="${boardVO.tempEndDay}">
						</td>
					</tr>
					<tr>
						<th><label for="strDay">공지 게시시작 시간 </label></th>
						<td class="left">
							<select id="strHour" name="strHour">
								<c:forEach var="hour" begin="0" end="23">
									<option value="<c:if test="${hour<10}">0</c:if>${hour}"><c:if test="${hour<10}">0</c:if>${hour}</option>
								</c:forEach>

							</select>
							<span>:</span>
							<select id="strMin" name="strMin">
								<c:forEach var="min" begin="0" end="59">
									<option value="<c:if test="${min<10}">0</c:if>${min}"><c:if test="${min<10}">0</c:if>${min}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th><label for="strDay">공지 게시종료 시간 </label></th>
						<td class="left">
							<select id="endHour" name="endHour">
								<c:forEach var="hour" begin="0" end="23">
									<option value="<c:if test="${hour<10}">0</c:if>${hour}"><c:if test="${hour<10}">0</c:if>${hour}</option>
								</c:forEach>

							</select><span>:</span>
							<select id="endMin" name="endMin">
								<c:forEach var="min" begin="0" end="59">
									<option value="<c:if test="${min<10}">0</c:if>${min}"><c:if test="${min<10}">0</c:if>${min}</option>
								</c:forEach>
							</select>
						</td>
					</tr>





					<tr>
						<th><label for="boardContent">내용 <span class="pilsu">*</span></label></th>
						<td class="nopd">
							<div>
								<form>
                                <textarea name="boardContent" id="boardContent" rows="10" cols="80"></textarea>

									<%--<script>--%>
										<%--CKEDITOR.replace('boardContent');--%>
									<%--</script>--%>
								</form>
							</div>
							<div><form:errors path="boardContent" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label >파일 업로드<span class="pilsu">*</span></label></th>
						<td class="left">
							<div id="fileuploader">Upload</div>

						</td>
					</tr>

					<tr>
						<th><label >이미지 업로드<span class="pilsu">*</span></label></th>
						<td class="left">
							<div id="fileuploader2">Upload</div>

						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<c:if test="${menuInfo.authVO.writeYn >0}">
				<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
			</c:if>
			<button type="button" class="button main" onclick="fn_list()"  title="목록"  >목록</button>


		</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="boardFile" id="boardFile" type="hidden" value="${resultVO.boardFile}">
		<input name="file01" id="file01" type="hidden" value="${resultVO.file01}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>