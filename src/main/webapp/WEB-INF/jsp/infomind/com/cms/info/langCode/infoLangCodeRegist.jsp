<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d7e450a15d62a7f83b1336b8aca4cf1c&libraries=services"></script>


<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>



<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

<c:set var="pageTitle">코드언어팩</c:set>
<script type="text/javascript">



	$(document).ready(function() {
		fncShowMessg();
		fieldErrors();

		$('input[name=__langSelector__]').on('click', function(e) {
			$('[data-incms-id]').hide();
			$('[data-incms-id=' + e.target.value + ']').show();
		})

		$('input[name=__langSelector__]').eq(0).trigger('click');

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



	function fnInsert(){

		var	API_SERVER = "<c:url value='/cms/info/langCode/RegistLangCode.do'/>";
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
			error(res){
				alert("error");
			}

		})

	}



</script>



<div class="sub subView">
<h2 class="stitle">
	<i class='bx bxs-dashboard' ></i>${pageTitle}
</h2>

<form:form commandName="resultVO">
	<h3 class="btitle">
		등록 내역
	</h3>
	<div class="rows white-box">
		<!-- 등록폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<!-- 코트ID명 -->
			<input type="hidden" name="codeId" value="<c:out value="${result.codeId}"/>">
			<input type="hidden" name="code" value="<c:out value="${result.code}"/>">

			<tr>
				<th width="20"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeIdNm" /><span class="pilsu">*</span></th>
				<td class="left" colspan="3">
					<c:out value="${result.codeIdNm}"/>
				</td>
			</tr>
			<!-- 상세코드 -->
			<tr>
				<th width="10%"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code" /><span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<c:out value="${result.code}"/>
				</td>
				<!-- 상세코드명 -->
				<th width="7%"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm" /><span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<c:out value="${result.codeNm}"/>
				</td>
			</tr>
			<!-- 상세코드설명 -->
			<tr>
				<th width="20"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeDc" /><span class="pilsu">*</span></th>
				<td class="left">
					<c:out value="${result.codeDc}"/>
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
								<th width="10%"><label for="listLang[${status.index}].codeNm">코드명  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].codeNm" size="40" maxlength="100" />
									<div><form:errors path="listLang[${status.index}].codeNm" cssClass="error" /></div>
								</td>
						</table>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>
	</div>





	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save" onclick="fnInsert()">등록</button>
	</div>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>

