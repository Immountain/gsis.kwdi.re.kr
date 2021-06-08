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

		$('#btn_update_save').click(function(){

			if(submitFlag){
				alert('등록중입니다')
				return;
			}

			var festivityId = $('#festivityId').val();
			var applicationId = $('#applicationId').val();
			var applicationGb = $('#applicationGb').val();
			var applicationNm = $('#applicationNm').val();
			var applicationContent = $('#applicationContent').val();
			var applicationMemo = $('#applicationMemo').val();
			var applicationStrDt = $('#applicationStrDt').val();
			var useYn = $('#useYn').val();

			if(!festivityId){
				alert('대회아이디 입력하세요')
				return;
			}

			if(!applicationId){
				alert('참가신청아이디 입력하세요')
				return;
			}

			if(!applicationGb){
				alert('참가신청구분 입력하세요')
				return;
			}

			if(!applicationNm){
				alert('참가신청명 입력하세요')
				return;
			}

			if(!applicationContent){
				alert('참가신청내용 입력하세요')
				return;
			}

			if(!applicationMemo){
				alert('참가신청메모 입력하세요')
				return;
			}

			if(!applicationStrDt){
				alert('시작일 입력하세요')
				return;
			}

			if(!applicationEntDt){
				alert('종료일 입력하세요')
				return;
			}

			if($('#applicationStrDt').val() > $('#applicationEntDt').val()){
				alert("시작일이 종료일보다 빠를수 없습니다.");
				return;
			};

			submitFlag= true;


			var	API_SERVER = "<c:url value='/cms/wj/festivity/wjFestivityApplicationUpdt.do'/>";
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
					<form:input path="festivityNm" readonly="true"/>
					<form:hidden path="festivityId"/>
					<input type="button" class="button main" value="대회찾기" id="fn_search_festivityId_button"/>
				</td>
			</tr>
			<tr>
				<th width="10%">참가신청아이디<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="applicationId" maxlength="10"/>
					<div><form:errors path="applicationId" cssClass="error" /></div>
				</td>
				<th>참가신청명<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="applicationNm"/>
					<div><form:errors path="applicationNm" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="10%">참가신청구분<span class="pilsu">*</span></th>
				<td>
					<form:select path="applicationGb">
						<form:option value="1" label="1"/>
						<form:option value="2" label="2"/>
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
				<th width="10%">참가신청내용<span class="pilsu">*</span></th>
				<td class="left">
					<form:textarea path="applicationContent"  maxlength="250" />
					<div><form:errors path="applicationContent" cssClass="error" /></div>
				</td>
				<th width="10%">참가신청메모<span class="pilsu">*</span></th>
				<td class="left" >
					<form:textarea path="applicationMemo"  maxlength="10"/>
					<div><form:errors path="applicationMemo" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="10%">시작일<span class="pilsu">*</span></th>
				<td class="left">
					<form:input type="date" path="applicationStrDt"/>
					<div><form:errors path="applicationStrDt" cssClass="error"/></div>
				</td>
				<th width="10%">종료일<span class="pilsu">*</span></th>
				<td>
					<form:input type="date" path="applicationEntDt"/>
					<div><form:errors path="applicationEntDt" cssClass="error"/></div>
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
								<th width="10%"><label for="listLang[${status.index}].applicationTitle">참가신청제목<span class="pilsu">*</span></label></th>
								<td class="left" colspan="3">
									<form:input path="listLang[${status.index}].applicationTitle"/>
									<div><form:errors path="listLang[${status.index}].applicationTitle" cssClass="error" /></div>
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].contentInfo">참가신청내용<span class="pilsu">*</span></label></th>
								<td>
									<form:input path="listLang[${status.index}].contentInfo" size="40" maxlength="250"/>
								</td>
								<th width="10%"><label for="listLang[${status.index}].memo">참가신청메모 <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].memo" size="40" maxlength="250"/>
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
		<button type="button" id="btn_update_save" name="btn_update_save" class="btn main" name="btn_save">수정</button>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</div>
</form:form>
</div>

