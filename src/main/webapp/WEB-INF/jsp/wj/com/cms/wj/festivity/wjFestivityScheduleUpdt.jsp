<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<!-- javaScript -->
<script src="<c:url value='/js/infomind/com/jquery-3.1.0.min.js'/>" ></script>

<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>


<c:set var="pageTitle">대회일정</c:set>
<script type="text/javascript">

	var submitFlag = false;

	$(document).ready(function() {
		fncShowMessg();
		fieldErrors();



		$('#btn_regist_save').click(function(){

			var title = $('#title').val();
			var meo = $('#meo').val();
			var orderCnt = $('#orderCnt').val();
			var useYn = $('#useYn').val();
			var scheduleStrTime = $('#scheduleStrTime').val();
			var scheduleEndTime = $('#scheduleEndTime').val();

			if(submitFlag){
				alert('등록중입니다')
				return;
			}

			if(!scheduleStrTime){
				alert('시작시간 입력하세요')
				return;
			}

			if(scheduleEndTime){

				if(scheduleStrTime > scheduleEndTime){
					alert('시작시간이 종료시간보다 빠를수 없습니다')
					return;
				}
			}

			if(!title){
				alert('제목 입력하세요')
				return;
			}

			if(!meo){
				alert('메모 입력하세요')
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

			var	API_SERVER = "<c:url value='/cms/wj/festivity/UpdateWjFestivityScheduleLang.do'/>";
			var formData = $("#resultVO").serialize();
			$.ajax({
				type : 'post',
				url : API_SERVER,
				data : formData,
				dataType : 'json',
				success : function(res){

					submitFlag = true;
					alert(res.message);
					ax5modal.close();

				},
				error(res){
					alert("error");
				}

			})
		});

		$('input[name=__langSelector__]').on('click', function(e) {
			$('[data-incms-id]').hide();
			$('[data-incms-id=' + e.target.value + ']').show();
		})

		$('input[name=__langSelector__]').eq(0).trigger('click');

		$('#map').show();

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
			<form:hidden path="festivityId"/>
			<form:hidden path="scheduleSno"/>
			<tr>
				<th width="5%">제목<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="title" maxlength="100"/>
					<form:errors path="" cssClass="error"/>
				</td>
				<th width="5%">사용여부<span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<form:select path="useYn" >
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="사용안함"/>
					</form:select>
					<div><form:errors path="useYn" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th>일짜<span class="pilsu">*</span></th>
				<td class="left" colspan="3">
					<form:input type="date" path="scheduleDay"/>
					<div><form:errors path="scheduleDay" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="5%">시작시간<span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<form:input type="time" path="scheduleStrTime"/>
					<div><form:errors path="scheduleStrTime" cssClass="error"/></div>
				</td>
				<th width="5%">종료시간<span class="pilsu">*</span></th>
				<td class="left" width="20%">
					<form:input type="time" path="scheduleEndTime"/>
					<div><form:errors path="scheduleEndTime" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="5"><label for="meo">대회메모<span class="pilsu">*</span></label></th>
				<td class="left">
					<form:input path="meo" maxlength="100"/>
					<div><form:errors path="meo" cssClass="error"/></div>
				</td>
				<th width="5"><lavel for="orderCnt">정렬순번<span class="pilsu">*</span></lavel></th>
				<td class="left">
					<form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="10" placeholder="숫자만입력가능합니다."/>
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
								<th><label for="listLang[${status.index}].scheduleTitle">일정제목<span class="pulsu">*</span></label></th>
								<td colspan="3">
									<form:input path="listLang[${status.index}].scheduleTitle" maxlength="100"/>
									<div><form:errors path="listLang[${status.index}].scheduleTitle" cssClass="error"/></div>
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].scheduleProgram">프로그램  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].scheduleProgram" size="40" maxlength="100" />
									<div><form:errors path="listLang[${status.index}].scheduleProgram" cssClass="error" /></div>
								</td>
								<th width="10%"><label for="listLang[${status.index}].schedulePlace">장소  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].schedulePlace" size="40" maxlength="100" />
									<div><form:errors path="listLang[${status.index}].schedulePlace" cssClass="error"/> </div>
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
		<button type="button" id="btn_regist_save" name="btn_regist_save" class="btn main" name="btn_save" >수정</button>
	</div>
	<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
</form:form>
</div>
