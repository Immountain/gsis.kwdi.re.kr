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
<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>

<c:set var="pageTitle">주요프로그램</c:set>
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
			maxFileCount:10,
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

		$('input[name=__langSelector__]').on('click', function (e) {
			$('[data-incms-id]').hide();
			$('[data-incms-id=' + e.target.value + ']').show();
		})

		$('input[name=__langSelector__]').eq(0).trigger('click');


		$('#btn_modify_save').click(function () {

			var title = $('#title').val();
			var orderCnt = $('#orderCnt').val();

			if(!orderCnt){
				alert('정렬순서 입력하세요');
				return;
			}

			if(!title){
				alert('컨텐츠타이틀 입력하세요')
				return;
			}

			var	API_SERVER = "<c:url value='/cms/wj/program/insertWjProgramSub.do'/>";
			var formData = $("#resultVO").serialize();
			$.ajax({
				type : 'post',
				url : API_SERVER,
				data : formData,
				dataType : 'json',
				success : function(){

					ax5modal.close();
					alert('등록 되었습니다')
				},
				error(){
					alert("error");
				}

			})

		});
	});
	/* ********************************************************
     * 저장처리화면
     ******************************************************** */
	function fn_update_page(form){

		if(confirm("<spring:message code="common.update.msg" />")){
			//$("#resultVO").find("input,select").disableObj(false);
			$("input").disableObj(false);
			$("select").disableObj(false);

			form.action="<c:url value='/jtp/cms/organ/updateOrganMst.do' />";
			form.submit();

			$("input").disableObj(true);
			$("select").disableObj(true);

			//$("#resultVO").find("input,select").disableObj(true);
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

<form:form commandName="resultVO">
		<h3 class="btitle">
			등록 내역
		</h3>
	<div class="rows white-box">
		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<input type="hidden" id="programSno" name="programSno" value="<c:out value="${resultVO.programSno}"/>">
			<input type="hidden" id="festivityId" name="festivityId" value="<c:out value="${resultVO.festivityId}"/>">
			<tr>
				<th width="20">컨텐츠타이틀<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="title" />
				</td>

				<th width="10%">메모<span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<form:textarea path="memo" maxlength="250"/>
				</td>
			<tr>
				<th>대표여부<span class="pilsu">*</span></th>
				<td>
					<form:select path="leaderYn">
						<form:option value="N" label="사용안함"/>
						<form:option value="Y" label="사용"/>
					</form:select>
				</td>
				<th>사용여부<span class="pilsu">*</span></th>
				<td>
					<form:select path="useYn">
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="사용안함"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th>정렬순서<span class="pilsu">*</span></th>
				<td>
					<form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" placeholder="숫자만입력가능합니다."/>
				</td>
			</tr>
			<tr>
				<th><label >이미지정보<span class="pilsu">*</span></label></th>
				<td class="left" colspan="3">
					<div id="fileuploader">Upload</div>
				</td>
			</tr>
			<tr>
				<th width="10%"><label >다국어 설정<span class="pilsu">*</span></label></th>
				<td class="left" colspan="3">

					<div class="radio-field">
						<c:forEach items="${resultVO.listLang}" var="item" varStatus="status">
							<input type="radio" name="__langSelector__" id="langSelector_${status.index}" value="${item.langCode}"/>
							<label for="langSelector_${status.index}">${item.langCodeNm}</label>
						</c:forEach>
					</div>

					<c:forEach var="result" items="${resultVO.listLang}" varStatus="status">
						<form:hidden path="listLang[${status.index}].langCode" value="${result.langCode}"/>
						<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />" style="display: none;" data-incms-id="${result.langCode}">
							<tr>
								<th width="10%"><label for="listLang[${status.index}].contTitle">컨텐츠타이틀  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].contTitle" size="40" maxlength="100" />
								</td>
								<th width="10%"><label for="listLang[${status.index}].mainTitle">메인타이틀<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:textarea path="listLang[${status.index}].mainTitle" size="40" maxlength="100" />
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].mainName">이름<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].mainName" size="40" maxlength="100"/>
								</td>
								<th width="10%"><label for="listLang[${status.index}].mainEtc">기타<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:textarea path="listLang[${status.index}].mainEtc" size="40" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th><label for="listLang[${status.index}].mainInfo">정보<span class="pilsu">*</span></label></th>
								<td class="left" colspan="3">
									<form:textarea path="listLang[${status.index}].mainInfo"/>
								</td>
							</tr>
						</table>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
	</div>

	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save">등록</button>
	</div>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	<form:hidden path="atchFileId" id="atchFileId" />
</form:form>
</div>

`