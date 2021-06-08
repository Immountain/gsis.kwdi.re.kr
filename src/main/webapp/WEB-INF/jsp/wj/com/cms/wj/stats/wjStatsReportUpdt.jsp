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


<c:set var="pageTitle">제주도민현황</c:set>
<script type="text/javascript">

	var mapContainer;
	var kakaomap;
	var marker;

	var submitFlag = false;

	$(document).ready(function() {

		$('#btn_updt_save').click(function(){

			if(submitFlag){
				alert('수정중입니다')
				return;
			}

			var countryGb = $('#countryGb').val();
			var countryCode = $('#countryCode').val();
			var areaCode = $('#areaCode').val();
			var title = $('#title').val();
			var establishYear = $('#establishYear').val();
			var membership = $('#membership').val();

			if(!countryCode){
				alert('국가코드 입력하세요')
				return;
			}

			if(!countryGb){
				alert('국내/국외구분 입력하세요')
				return;
			}

			if(!areaCode){
				alert('지역코드 입력하세요')
				return;
			}

			if(!title){
				alert('제목 입력하세요')
				return;
			}

			if(!establishYear){
				alert('창립연도 입력하세요')
				return;
			}

			if(!membership){
				alert('창립연도 입력하세요')
				return;
			}




			submitFlag= true;


			var	API_SERVER = "<c:url value='/cms/wj/stats/wjStatsReportUpdt.do'/>";
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
		수정 내역
	</h3>

	<div class="rows white-box">
		<!-- 수정폼 -->
		<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
			<tr>
				<th width="10%">제목<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="title" maxlength="25"/>
					<div><form:errors path="title" cssClass="error" /></div>
				</td>
				<th width="10%">국내/국외구분<span class="pilsu">*</span></th>
				<td>
					<form:select path="countryGb">
						<form:option value="1" label="국내"/>
						<form:option value="2" label="국외"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<th width="10%">국가코드<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="countryCode"  maxlength="10" />
					<div><form:errors path="countryCode" cssClass="error" /></div>
				</td>
				<th width="10%">지역코드<span class="pilsu">*</span></th>
				<td class="left" >
					<form:input path="areaCode"  maxlength="5"/>
					<div><form:errors path="areaCode" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="10%">창립연도<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="establishYear" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다." maxlength="4"/>
					<div><form:errors path="establishYear" cssClass="error"/></div>
				</td>
				<th width="10%">회원수<span class="pilsu">*</span></th>
				<td>
					<form:input path="membership" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="5" placeholder="숫자만입력가능합니다."/>
					<div><form:errors path="membership" cssClass="error"/></div>
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
								<th width="10%"><label for="listLang[${status.index}].countryGb">국내/국외구분<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:select path="listLang[${status.index}].countryGb">
										<form:option value="1" label="1"/>
										<form:option value="2" label="2"/>
									</form:select>
									<div><form:errors path="listLang[${status.index}].countryGb" cssClass="error" /></div>
								</td>
								<th width="10%"><label for="listLang[${status.index}].areaCode">지역코드<span class="pilsu">*</span></label></th>
								<td>
									<form:input path="listLang[${status.index}].areaCode" size="40" maxlength="5"/>
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].countryCode">국가코드 <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].countryCode" size="40" maxlength="10"/>
								</td>
								<th width="10%"><label for="listLang[${status.index}].associationName">협회명<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].associationName" size="40" maxlength="25"/>
								</td>
							</tr>
							<tr>
								<th><label for="listLang[${status.index}].memo">메모<span class="pilsu">*</span></label></th>
								<td class="left" colspan="3">
									<form:textarea path="listLang[${status.index}].memo" size="40" maxlangth="50"/>
								</td>
							</tr>
						</table>
					</c:forEach>
				</td>
			</tr>
		</table>
	</div>

	<!-- 하단 버튼 -->
	<div class="btn-set right">
		<button type="button" id="btn_updt_save" name="btn_updt_save" class="btn main" name="btn_save">수정</button>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="reportSno" id="reportSno" type="hidden" value="${resultVO.reportSno}"/>
	</div>
</form:form>
</div>

