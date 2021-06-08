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


<c:set var="pageTitle">배너 관리</c:set>
<script type="text/javascript">
$(document).ready(function() {
	fncShowMessg();

	$('input[name=__langSelector__]').on('click', function(e) {
		$('[data-incms-id]').hide();
		$('[data-incms-id=' + e.target.value + ']').show();
	})

	$('input[name=__langSelector__]').eq(0).trigger('click');


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

	var result = {};
	// result['siteMemuId'] = form.siteMemuId.value;
	// result['festivityId'] = form.festivityId.value;
	result['titme'] = form.titme.value;
	result['memo'] = form.memo.value;
	result['atchFileId'] = form.atchFileId.value;
	result['pageBannerUrl'] = form.pageBannerUrl.value;
	result['useYn'] = form.useYn.value;

	var list = [];
	$('[data-incms-id]').each(function(i, e) {
		list.push({
			'title': $(e).find('[name$=title]').val(),
			'memo': $(e).find('[name$=memo]').val(),
			'contentInfo': $(e).find('[name$=contentInfo]').val()
		})
	})

	result['list'] = list;
	// result['deleteYn'] = form.deleteYn.value;


	console.log(result);


	// return false;

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
	<form:form commandName="resultVO" action="${pageContext.request.contextPath}/cms/info/banner/insertPageBanner.do" method="post" onSubmit="fn_regist_page(document.forms[0]); return false;">
		<h3 class="btitle">등록 내역</h3>
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
					<tr>
						<th><label for="titme">배너 명칭  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:input path="titme" size="70" maxlength="100" />
							<div><form:errors path="titme" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="memo">배너설명 <span class="pilsu">*</span></label></th>
						<td class="nopd">
								<%--<form:textarea path="contentInfo"  cols="300" rows="10" />--%>
								<%----%>
							<div>
								<form:textarea path="memo" id="memo" rows="5" cols="80" maxlength="500"></form:textarea>
							</div>
							<div><form:errors path="memo" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label >배너 이미지<span class="pilsu">*</span></label></th>
						<td class="left">
							<div id="fileuploader">Upload</div>
						</td>
					</tr>

					<tr>
						<th><label >다국어 설정<span class="pilsu">*</span></label></th>
						<td class="left">

							<div class="radio-field">
								<c:forEach items="${lang}" var="item" varStatus="status">
									<input type="radio" name="__langSelector__" id="langSelector_${status.index}" value="${item.langCode}"/>
									<label for="langSelector_${status.index}">${item.langNm}</label>
								</c:forEach>
							</div>

							<c:forEach items="${lang}" var="item" varStatus="status">
								<form:hidden path="bannerLangList[${status.index}].langCode" value="${item.langCode}"/>
								<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />" style="display: none;" data-incms-id="${item.langCode}">
									<tbody>
										<tr>
											<th><label for="bannerLangList[${status.index}].title">제목  <span class="pilsu">*</span></label></th>
											<td class="left">
												<form:input path="bannerLangList[${status.index}].title" size="70" maxlength="100" />
												<div><form:errors path="bannerLangList[${status.index}].title" cssClass="error" /></div>
											</td>
										</tr>
										<tr>
											<th><label for="bannerLangList[${status.index}].memo">메모 <span class="pilsu">*</span></label></th>
											<td class="nopd">
													<%--<form:textarea path="contentInfo"  cols="300" rows="10" />--%>
													<%----%>
												<div>
													<form:textarea path="bannerLangList[${status.index}].memo" rows="2" cols="80" maxlength="500"></form:textarea>
												</div>
												<div><form:errors path="bannerLangList[${status.index}].memo" cssClass="error" /></div>
											</td>
										</tr>
										<tr>
											<th><label for="bannerLangList[${status.index}].contentInfo">내용 <span class="pilsu">*</span></label></th>
											<td class="nopd">
													<%--<form:textarea path="contentInfo"  cols="300" rows="10" />--%>
													<%----%>
												<div>
													<form:textarea path="bannerLangList[${status.index}].contentInfo" rows="4" cols="80" maxlength="500"></form:textarea>
												</div>
												<div><form:errors path="bannerLangList[${status.index}].contentInfo" cssClass="error" /></div>
											</td>
										</tr>
									</tbody>
								</table>
							</c:forEach>

						</td>
					</tr>


					<tr>
						<th><label for="pageBannerUrl">링크URL </label></th>
						<td class="left">
							<form:input path="pageBannerUrl" size="70" maxlength="25" />
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
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
			<span class="button"><a href="<c:url value='/cms/info/banner/bannerList.do?menuTargetNo=${menuInfo.menuTargetNo}' />" title="<spring:message code="button.list" />  <spring:message code="input.button" />"><spring:message code="button.list" /></a></span>
		</div>

		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<form:hidden path="atchFileId" id="atchFileId" />
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>