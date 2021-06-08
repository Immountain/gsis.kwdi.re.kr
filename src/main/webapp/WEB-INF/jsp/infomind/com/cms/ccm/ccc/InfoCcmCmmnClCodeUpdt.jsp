<%
 /**
  * @Class Name :  InfoCcmCmmnClCodeUpdt.jsp
  * @Description :  공통분류코드 수정하는 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.10.15   양진혁              최초 생성
  *  @author 공통서비스팀
  *  @since 2020.10.15
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="pageTitle"><spring:message code="comSymCcmCcc.cmmnClCodeVO.title"/></c:set>

<script type="text/javascript">


	$(document.body).ready(function () {

		//닫기
		$('#modal-close').click(function () {
			ax5modal.close();
		});


		$('#btn_modify_save').click(function() {


			//ajax 통신은 jsp 에서 체크
			var clCode =$('#clCode').val();
			var clCodeNm =$('#clCodeNm').val();
			var clCodeDc =$('#clCodeDc').val();


			if(!clCode){
				alert("분류코드 입력하세요");
				return;
			}

			if(!clCodeNm){
				alert("분류코드명 입력하세요");
				return;
			}
			if(!clCodeDc){
				alert("분류코드 상세 입력하세요");
				return;
			}



			var formData = $("#cmmnClCodeVO").serializeObject();
			// formData = $.extend(true, {
			//     a: 1
			// }, formData)
			var API_SERVER = "<c:url value='/cms/ccm/ccc/InfoUpdateCcmCmmnClCode.do' />";
			var saveQuestion = confirm("수정 하시겠습니까?");
			if (saveQuestion) {
				$.ajax({
					url : API_SERVER,
					type : 'post',
					//data : JSON.stringify(formData),
					data : formData,
					dateType:'json',
				//	contentType: "application/json",  // ajax 통신으로 보내는 타입

					beforeSend: function(xhr) {

						xhr.setRequestHeader("AJAX", "true");

					},
					success : function(data) {

						var jsonObj = JSON.stringify(data);
						//console.log("data==>"+data)
						//console.log("jsonObj==>"+jsonObj)
						if(data.status=="0"){
							if(data.message=="SUCCESS"){

								ax5modal.callback('OK');
								ax5modal.close();
								alert("수정처리 완료했습니다.");

							}else{
								alert(data.message);

							}
						}else{

							alert(data.error.message);

						}
					}, // success
					error: function(XMLHttpRequest, textStatus, errorThrown) {
					//	console.log("data=1=>"+JSON.stringify(XMLHttpRequest));
					//	console.log("data=2=>"+XMLHttpRequest);
					//	console.log("data=3=>"+XMLHttpRequest.status);
					}
				});

			}
		});

	});

	jQuery.fn.serializeObject = function() {
		var obj = null;
		try {
			if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
				var arr = this.serializeArray();
				if (arr) {
					obj = {};
					jQuery.each(arr, function() {
						obj[this.name] = this.value;
					});
				}//if ( arr ) {
			}
		} catch (e) {
			alert(e.message);
		} finally {
		}

		return obj;
	};
</script>
<div class="sub subView">
	<!-- 타이틀 -->
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>수정 내역
	</h2>
	<!-- 등록폼 -->
	<form:form commandName="cmmnClCodeVO">
	<input name="clCode" id="clCode" type="hidden" value="<c:out value="${cmmnClCodeVO.clCode}" />">
		<h3 class="btitle">
			수정 내역
		</h3>
		<div class="rows white-box">
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
				<!-- 입력 -->
				<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
				<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
				<c:set var="inputNo"><spring:message code="input.no" /></c:set>

				<!-- 분류코드 -->
				<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCode"/> </c:set>
				<tr>
					<th style="width:100px"  ><label for="clCode">${title} <span class="pilsu">*</span></label></th>
					<td class="left">
						<c:out value="${cmmnClCodeVO.clCode}"/>
					</td>
				</tr>

				<!-- 분류코드명 -->
				<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCodeNm"/> </c:set>
				<tr>
					<th><label for="clCodeNm">${title} <span class="pilsu">*</span></label></th>
					<td class="left">
						<form:input path="clCodeNm" title="${title} ${inputTxt}" maxlength="70" />
						<div><form:errors path="clCodeNm" cssClass="error" /></div>
					</td>
				</tr>

				<!-- 분류코드설명 -->
				<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.clCodeDc"/> </c:set>
				<tr>
					<th><label for="clCodeDc">${title } <span class="pilsu">*</span></label></th>
					<td class="nopd">
						<form:textarea path="clCodeDc" title="${title} ${inputTxt}" rows="5" />
						<div><form:errors path="clCodeDc" cssClass="error" /></div>
					</td>
				</tr>

				<!-- 사용여부 -->
				<c:set var="title"><spring:message code="comSymCcmCcc.cmmnClCodeVO.useAt"/> </c:set>
				<tr>
					<th>${title } <span class="pilsu">*</span></th>
					<td class="left">
						<form:select path="useAt" title="${title} ${inputTxt}" cssClass="txt">
							<form:option value="Y"  label=" ${inputYes}"/>
							<form:option value="N" label=" ${inputNo}"/>
						</form:select>
						<div><form:errors path="useAt" cssClass="error" /></div>
					</td>
				</tr>

				</tbody>
			</table>
		</div>
	</form:form>



	<!-- 하단 버튼 -->
	<div class="btn-set center">

		<button type="button" id="btn_modify_save" name="btn_modify_save" class="btn main" name="btn_save">수정</button>
		<%--<button type="button" class="button sub" id="modal-close"  /> 닫기</button>--%>
	</div>
</div>