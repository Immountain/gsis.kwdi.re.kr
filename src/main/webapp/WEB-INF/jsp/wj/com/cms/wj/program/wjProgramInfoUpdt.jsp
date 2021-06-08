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


		$('#fn_search_festivityId_button').click(function () {
			var p = {};

			var API_SERVER = "<c:url value='/cms/wj/festivity/popWjFestivityInfoList.do'/>";

			ax5modal.open({
				theme: "primary",
				height: 477,
				width: 837,
				header: {
					title: '대회정보찾기',
					btns: {
						close: {
							label: '<i class="bx bx-x" aria-hidden="true"></i>', onClick: function () {
								ax5modal.close();
							}
						}
					}
				},
				iframe: {
					method: "get",
					url: API_SERVER,
					param: p
				},

			}, function (data) {
				$('#festivityId').val(data.festivityId);
				$('#festivityNm').val(data.festivityNm);
			});
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



	function fnUpdt(){

		var programTitle = $('#programTitle').val();
		var programDay = $('#programDay').val();
		var programStrTime = $('#programStrTime').val();
		var programEndTime = $('#programEndTime').val();
		var programPlace = $('#programPlace').val();
		var programEtc = $('#programEtc').val();
		var pageType = $('#pageType').val();
		var orderCnt = $('#orderCnt').val();

		if(!programTitle){
			alert('프로그램명칭 입력하세요')
			return;
		}

		if(!programDay){
			alert('날짜 입력하세요')
			return;
		}

		if(!programStrTime){
			alert('시작시간 입력하세요')
			return;
		}
		if(programEndTime){
			if(programStrTime > programEndTime){

				alert('시작시간이 종료시간보다 빠를수 없습니다')
				return;
			}
		}

		if(!programPlace){
			alert('장소 입력하세요')
			return;
		}

		if(!orderCnt){
			alert('정렬순번 입력하세요')
			return;
		}

		var	API_SERVER = "<c:url value='/cms/wj/program/wjProgramInfoUpdate.do'/>";
		var formData = $("#resultVO").serialize();
		$.ajax({
			type : 'post',
			url : API_SERVER,
			data : formData,
			dataType : 'json',
			success : function(res){

				ax5modal.close();
				alert('수정 되었습니다')
			},
			error(res){
				alert("error");
			}

		})

	};

</script>



<div class="sub subView">

<form:form commandName="resultVO">
	<h3 class="btitle">
		등록 내역
	</h3>

	<div class="rows white-box">
		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<input type="hidden" id="festivityId" name="festivityId" value="<c:out value="${resultVO.festivityId}"/>">
			<tr>
				<th width="20">대회아이디<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="festivityNm" readonly="true"/>
					<input type="button" class="button main" value="대회찾기" id="fn_search_festivityId_button"/>
				</td>
				<th width="10%">프로그램명칭<span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<form:input path="programTitle" maxlength="250"/>
				</td>
			</tr>
			<tr>
				<th>장소<span class="pilsu">*</span></th>
				<td>
					<form:input path="programPlace" maxlength="250"/>
				</td>

				<th>날짜<span class="pilsu">*</span></th>
				<td>
					<form:input path="programDay" type="date"/>
				</td>
			</tr>
			<tr>
				<th>시작시간<sapan class="pilsu">*</sapan></th>
				<td class="left" width="20%">
					<form:input path="programStrTime" type="time"/>
				</td>
				<th width="7%">종료시간<span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<form:input path="programEndTime" type="time"/>
				</td>
			</tr>

			<tr>
				<th width="20"><label for="${resultVO.programEtc}">기타정보<span class="pilsu">*</span></label></th>
				<td class="left">
					<form:input path="programEtc" maxlength=""/>
				</td>
				<th>페이지타입<span class="pilsu">*</span></th>
				<td>
					<form:select path="pageType" maxlength="10">
						<form:option value="NONO" label="사용안함"/>
						<form:option value="INCLUDE" label="INCLUDE"/>
						<form:option value="CONTENT" label="내용타입"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th><label >이미지리스트<span class="pilsu">*</span></label></th>
				<td class="left" colspan="3">
					<div id="fileuploader">Upload</div>
				</td>
			</tr>
			<tr>
				<th>정렬순번<span class="pilsu">*</span></th>
				<td class="left" colspan="3">
					<form:input path="orderCnt" maxlength="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다"/>
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
								<th width="10%"><label for="listLang[${status.index}].programNm">프로그램명칭  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].programNm" size="40" maxlength="100" />
								</td>
								<th width="10%"><label for="listLang[${status.index}].programPlace">프로그램장소<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].programPlace" size="40" maxlength="100" />
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].programEtc">프로그램 기타 <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].programEtc" size="40" maxlength="100"/>
								</td>
								<th width="10%"><label for="listLang[${status.index}].pageId">페이지아이디<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].pageId" size="40" />
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
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="fnUpdt()">수정</button>
	</div>
	<form:hidden path="atchFileId" id="atchFileId" />
	<form:hidden path="programSno" id="programSno" />
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>

`