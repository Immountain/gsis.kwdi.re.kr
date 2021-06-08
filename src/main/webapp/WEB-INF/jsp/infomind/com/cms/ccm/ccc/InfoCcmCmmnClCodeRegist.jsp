<%
 /**
  * @Class Name : InfoCcmCmmnClCodeRegist.jsp
  * @Description : 공통분류코드 등록 화면
  * @Modification Information
  * @
  * @  수정일             수정자                   수정내용
  * @ -------    --------    ---------------------------
  * @ 2020.10.12   양진혁              최초 생성
  *
  *
  *  @author 인포개발팀
  *  @since 2020.10.12
  *  @version 1.0
  *  @see
  *  
  */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comSymCcmCcc.cmmnClCodeVO.title"/></c:set>


<script type="text/javascript">


	$(document.body).ready(function () {

		fn_egov_init();


		//닫기
		$('#modal-close').click(function () {
			ax5modal.close();
		});


		$('#btn_save').click(function() {


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




			var formData = $("#editVO").serializeObject();
            // formData = $.extend(true, {
            //     a: 1
            // }, formData)


			//console.log(formData)
			//console.log(JSON.stringify(formData))

			var API_SERVER = "<c:url value='/cms/ccm/ccc/RegistCcmCmmnClCode.do' />";
			var saveQuestion = confirm("저장 하시겠습니까?");
			if (saveQuestion) {
				$.ajax({
					url : API_SERVER,
					type : 'post',
					data : formData,
					dateType:'json',
					//contentType: "application/json",  // ajax 통신으로 보내는 타입

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
								alert("저장처리 완료했습니다.");

							}else{
								alert(data.message);

							}
						}else{

							alert("처리중 오류가 발생했습니다.");

						}
					}, // success
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						//console.log("data=1=>"+JSON.stringify(XMLHttpRequest));
						//console.log("data=2=>"+XMLHttpRequest);
						//console.log("data=3=>"+XMLHttpRequest.status);
					}
				});

			}
		});

	});


/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init(){

	// 첫 입력란에 포커스
//	document.getElementById("cmmnClCodeVO").clCode.focus();

}

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
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.create" />
	</h2>
	<!-- 등록폼 -->
		<form:form commandName="editVO">

		<h3 class="btitle">
			등록 내역
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
						<form:input path="clCode" title="${title} ${inputTxt}" maxlength="3" />
						<div><form:errors path="clCode" cssClass="error" /></div>
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
			<button type="button" id="btn_save" class="btn main" name="btn_save">등록</button>

	</div>

</div>

