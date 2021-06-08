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

			var festivityId = $('#festivityId').val();
			var festivityNm = $('#festivityNm').val();
			var festivityNemo = $('#festivityNemo').val();
			var festivityStrDay = $('#festivityStrDay').val();
			var festivityEndDay = $('#festivityEndDay').val();
			var orderCnt = $('#orderCnt').val();
			var useYn = $('#useYn').val();


			if(!festivityId){
				alert('대회아이디 입력하세요')
				return;
			};

			if(!festivityNm){
				alert('대회명 입력하세요')
				return;
			};

			if(!festivityNemo){
				alert('대회메모 입력하세요')
				return;
			};

			if(!festivityStrDay){
				alert('시작일 입력하세요')
				return;
			};

			if(!festivityEndDay){
				alert('종료일 입력하세요')
				return;
			};

			if(!orderCnt){
				alert('정렬순서 입력하세요')
				return;
			};

			if(!useYn){
				alert('사용여부 입력하세요')
				return;
			};

			if($('#festivityStrDay').val() > $('#festivityEndDay').val()){
				alert("시작일이 종료일보다 빠를수 없습니다.");
				return;
			};

			submitFlag= true;


			var	API_SERVER = "<c:url value='/cms/wj/festivity/RegistWjFestivityInfo.do'/>";
			var formData = $("#resultVO").serialize();
			$.ajax({
				type : 'post',
				url : API_SERVER,
				data : formData,
				dataType : 'json',
				success : function(res){

					alert('등록되었습니다');
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

		$('#map').hide();

		mapContainer = document.getElementById('map'), // 지도를 표시할 div
				mapOption = {
					center: new kakao.maps.LatLng(33.500312, 126.530103), //지도의 중심좌표.
					level: 3 // 지도의 확대 레벨
				};

		kakaomap = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

		// 마커가 표시될 위치입니다
		var markerPosition  = new kakao.maps.LatLng(33.500312, 126.530103);

		// 마커를 생성합니다
		marker = new kakao.maps.Marker({
			position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(kakaomap);

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);

		// 지도에 클릭 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(kakaomap, 'click', function(mouseEvent) {

			// 클릭한 위도, 경도 정보를 가져옵니다
			var latlng = mouseEvent.latLng;

			// 마커 위치를 클릭한 위치로 옮깁니다
			marker.setPosition(latlng);

			document.getElementById("latitude").value = latlng.getLat();
			document.getElementById("longitude").value = latlng.getLng();
		});
	});


	/* ********************************************************
        //도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
        ******************************************************** */
	function sample4_execDaumPostcode() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var roadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 참고 항목 변수

				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
					extraRoadAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if(data.buildingName !== '' && data.apartment === 'Y'){
					extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				}
				// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if(extraRoadAddr !== ''){
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				document.getElementById('zipNo').value = data.zonecode;
				document.getElementById("address1").value = roadAddr;
				// document.getElementById("address2").value = data.jibunAddress;

				//	mapContainer.style.display = "block";
				$('#map').show();

				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();

				geocoder.addressSearch(data.address, function (result,status) {
					// 정상적으로 검색이 완료됐으면
					//
					// console.log(status);
					// console.log(result);
					if (status === daum.maps.services.Status.OK) {
						// 해당 주소에 대한 좌표를 받아서
						//
						// console.log(result[0].x);

						var coords = new daum.maps.LatLng(result[0].y, result[0].x);
						// 지도를 보여준다.
						//mapContainer.style.display = "block";
						kakaomap.relayout();
						// 지도 중심을 변경한다.
						kakaomap.setCenter(coords);
						// 마커를 결과값으로 받은 위치로 옮긴다.

						marker.setPosition(coords)

						document.getElementById("longitude").value = result[0].x;
						document.getElementById("latitude").value = result[0].y;
					}
				});
			}
		}).open();
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
			<tr>
				<th width="10%">대회아이디<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="festivityId" maxlength="10"/>
					<div><form:errors path="festivityId" cssClass="error" /></div>
				</td>
				<th width="10%">대회년도<span class="pilsu">*</span></th>
				<td class="left" colspan="3">
					<form:select path="festivityYear">
						<form:option value="2019" label="2019"/>
						<form:option value="2020" label="2020"/>
						<form:option value="2021" label="2021"/>
						<form:option value="2022" label="2022"/>
						<form:option value="2023" label="2023"/>
						<form:option value="2024" label="2024"/>
						<form:option value="2025" label="2025"/>
					</form:select>
					<div><form:errors path="festivityId" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<th width="10%">대회명<span class="pilsu">*</span></th>
				<td class="left">
					<form:input path="festivityNm"  maxlength="250" />
					<div><form:errors path="festivityNm" cssClass="error" /></div>
				</td>
				<th width="10%">정렬순서<span class="pilsu">*</span></th>
				<td class="left" >
					<form:input path="orderCnt" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="숫자만입력가능합니다." maxlength="10"/>
					<div><form:errors path="orderCnt" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="10%">페이지오픈일<span class="pilsu">*</span></th>
				<td class="left">
					<form:input type="date" path="pageStrDay"/>
					<div><form:errors path="pageStrDay" cssClass="error"/></div>
				</td>
				<th width="10%">페이지종료일<span class="pilsu">*</span></th>
				<td>
					<form:input type="date" path="pageEndDay"/>
					<div><form:errors path="pageEndDay" cssClass="error"/></div>
				</td>
			</tr>
			<tr>
				<th width="10%">시작일<span class="pilsu">*</span></th>
				<td class="left">
					<form:input type="date" path="festivityStrDay"/>
					<div><form:errors path="festivityStrDay" cssClass="error"/></div>
				</td>
				<th width="10%">종료일<span class="pilsu">*</span></th>
				<td>
					<form:input type="date" path="festivityEndDay"/>
					<div><form:errors path="festivityEndDay" cssClass="error"/></div>
				</td>
			</tr>
			<tr >
				<th width="10%">위도/경도주소찾기</th>
				<td class="left" colspan="3">
					<input type="text" id="zipNo" disabled="true"/>
					<input type="text" id="address1" disabled="true"/>
					<button class="button sub btn-ms" data="modal01" type="button" onclick="sample4_execDaumPostcode()">찾기</button>
				</td>
			</tr>
			<tr>
				<th width="10%"><label for="latitude">위도  <span class="pilsu">*</span></label></th>
				<td class="left">
					<form:input path="latitude" readonly="true" size="40" />
					<div><form:errors path="latitude" cssClass="error" /></div>
				</td>
				<th width="10%"><label for="longitude">경도  <span class="pilsu">*</span></label></th>
				<td class="left" width="10">
					<form:input path="longitude" readonly="true" size="40" />
					<div><form:errors path="longitude" cssClass="error" /></div>
				</td>
			</tr>
			<tr>
				<th width="10%">지도영역</th>
				<td colspan="3">
					<div id="map" name="map" style="width:100%;height:200px;margin-top:10px;"></div>
				</td>
			</tr>
			<tr>
				<th width="10%"><label for="festivityNemo">대회메모<span class="pilsu">*</span></label></th>
				<td class="left" width="10">
					<form:textarea path="festivityNemo" maxlength="250" size="40"/>
					<div><form:errors path="festivityNemo" cssClass="error"/></div>
				</td>
				<th width="10%"><label for="useYn">사용여부<span class="pilsu">*</span></label></th>
				<td class="left" width="10">
					<form:select path="useYn" >
						<form:option value="Y" label="사용"/>
						<form:option value="N" label="사용안함"/>
					</form:select>
					<div><form:errors path="useYn" cssClass="error"/></div>
					<form:hidden path="deleteYn"  value="N"/>
				</td>
			</tr>
			<tr>
				<th><label >대회이미지<span class="pilsu">*</span></label></th>
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
								<th width="10%"><label for="listLang[${status.index}].festivityPlace">장소  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:textarea path="listLang[${status.index}].festivityPlace" size="40" maxlength="250" />
									<div><form:errors path="listLang[${status.index}].festivityPlace" cssClass="error" /></div>
								</td>
								<th width="10%"><label for="listLang[${status.index}].festivitySituation">주요추진상황<span class="pilsu">*</span></label></th>
								<td>
									<form:textarea path="listLang[${status.index}].festivitySituation" size="40" maxlength="1000"/>
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].festivitySubject">주제 <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].festivitySubject" size="40" maxlength="250"/>
								</td>
								<th width="10%"><label for="listLang[${status.index}].festivityHost">주최<span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].festivityHost" size="40" maxlength="250"/>
								</td>
							</tr>
							<tr>
								<th width="10%"><label for="listLang[${status.index}].festivitySupervises">주관<span class="pilsu">*</span></label></th>
								<td>
									<form:input path="listLang[${status.index}].festivitySupervises" size="40" maxlength="250" />
								</td>
								<th width="10%"><label for="listLang[${status.index}].festivityScale">참가규모  <span class="pilsu">*</span></label></th>
								<td class="left">
									<form:input path="listLang[${status.index}].festivityScale" size="40" maxlength="250" />
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
		<form:hidden path="festivityImage" id="atchFileId" />
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
	</div>
</form:form>
</div>

