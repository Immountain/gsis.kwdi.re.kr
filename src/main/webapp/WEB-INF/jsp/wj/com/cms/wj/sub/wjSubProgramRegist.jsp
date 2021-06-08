<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<link rel="stylesheet" type="text/css" href="<c:url value='/file/css/uploadfile.css' />">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d7e450a15d62a7f83b1336b8aca4cf1c&libraries=services"></script>
<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>



<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

<!-- 다음 주소 찾기 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src='https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js'></script>
<script src="<c:url value='/file/js/jquery.uploadfile.js'/>" ></script>


<c:set var="pageTitle">대회정보</c:set>
<script type="text/javascript">

	var mapContainer;
	var kakaomap;
	var marker;

	var submitFlag = false;

	$(document).ready(function() {

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
		$('#btn_regist_save').click(function(){

			if(submitFlag){
				alert('등록중입니다')
				return;
			}

			var festivityId = $('#festivityId').val()
			var title = $('#title').val()
			var pageType = $('#pageType').val()
			var orderCnt = $('#orderCnt').val()
			var useYn = $('#useYn').val()

			if(!festivityId){
				alert('대회명 입력하세요');
				return;
			}

			if(!title){
				alert('제목 입력하세요');
				return;
			}

			if(!pageType){
				alert('페이지타입 입력하세요');
				return;
			}

			if(!orderCnt){
				alert('정렬순서 입력하세요')
				return;
			}

			if(!useYn){
				alert('사용여부 입력하세요')
				return;
			}

			submitFlag= true;


			var	API_SERVER = "<c:url value='/cms/wj/sub/RegistWjSubProgram.do'/>";
			var formData = $("#resultVO").serialize();
			$.ajax({
				type : 'post',
				url : API_SERVER,
				data : formData,
				dataType : 'json',
				success : function(res){

					alert(res.message);
					ax5modal.close();

				},
				error(){
					submitFlag = false;

					alert("error");
				}

			})

		});

		$('input[name=__langSelector__]').on('click', function(e) {
			$('[data-incms-id]').hide();
			$('[data-incms-id=' + e.target.value + ']').show();
		})

		$('input[name=__langSelector__]').eq(0).trigger('click');
	});




</script>



<div class="sub subView">
<form:form commandName="resultVO">
	<h3 class="btitle">
		등록 내역
	</h3>

	<div class="rows white-box">
		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<tr>
				<th width="20">대회아이디<span class="pilsu">*</span></th>
				<td class="left" colspan="3">
					<input id="festivityNm" type="text" readonly="true"/>
					<form:hidden path="festivityId"/>
					<input type="button" class="button main" value="대회찾기" id="fn_search_festivityId_button"/>
				</td>
			</tr>
			<tr>
				<th width="10%">제목<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="title" maxlength="100"/>
					<div><form:errors path="title" cssClass="error" /></div>
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
				<th width="10%">정렬순서<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="orderCnt"  maxlength="10" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다."/>
					<div><form:errors path="orderCnt" cssClass="error" /></div>
				</td>
				<th width="10%">사용여부<span class="pilsu">*</span></th>
				<td class="left" >
					<form:select path="useYn">
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="사용안함"/>
					</form:select>
					<div><form:errors path="useYn" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th><label >파일<span class="pilsu">*</span></label></th>
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
								<th width="10%"><label for="listLang[${status.index}].subProgramTitle">제목<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].subProgramTitle" maxlength="100"/>
									<div><form:errors path="listLang[${status.index}].subProgramTitle" cssClass="error" /></div>
								</td>
								<th width="10%"><label for="listLang[${status.index}].pageId">페이지아이디 <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].pageId" size="40" maxlength="10"/>
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
		<button type="button" id="btn_regist_save" name="btn_regist_save" class="btn main" name="btn_save">등록</button>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<form:hidden path="atchFileId" id="atchFileId" />
	</div>
</form:form>
</div>

