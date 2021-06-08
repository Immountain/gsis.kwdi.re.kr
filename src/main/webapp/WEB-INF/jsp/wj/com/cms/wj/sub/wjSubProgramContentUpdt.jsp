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
<jsp:include page="/WEB-INF/jsp/infomind/com/module/CodeMirrorEditor.jsp"/>

<c:set var="pageTitle">주요프로그램</c:set>
<script type="text/javascript">

	var editors = [];

	var editorRefresh = function() {
		editors.forEach(function(editor, idx) {
			editor.refresh();
		});
	}
	var editorSave = function() {
		editors.forEach(function(editor, idx) {
			editor.save();
		});
	}

	$(document).ready(function() {

		fncShowMessg();
		fieldErrors();

		<c:forEach var="result" items="${resultVO.listLang}" varStatus="status">
			editors.push($ifx.generateCodeEditor(resultVO['listLang[${status.index}].contentInfo'],{
				useThemeSelector: true
			}));
		</c:forEach>

		$('input[name=__langSelector__]').on('click', function (e) {
			$('[data-incms-id]').hide();
			$('[data-incms-id=' + e.target.value + ']').show();
			editorRefresh();
		})

		$('input[name=__langSelector__]').eq(0).trigger('click');


	});

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

		var	API_SERVER = "<c:url value='/cms/wj/sub/WjSubProgramContentUpdt.do'/>";

		editorSave();
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

<form:form commandName="resultVO" name="resultVO">
	<h3 class="btitle">
		등록 내역
	</h3>

	<div class="rows white-box">
		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
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
								<th width="10%"><label for="listLang[${status.index}].subProgramTitle">프로그램명칭  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].subProgramTitle" size="20" readonly="true"/>
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].contentInfo">내용<span class="pilsu">*</span></label></th>
								<td class="left" colspan="3">
									<textarea name="listLang[${status.index}].contentInfo" size="40">${result.contentInfoDecode}</textarea>
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
	<form:hidden path="festivityProgramSno" id="festivityProgramSno" />
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>
