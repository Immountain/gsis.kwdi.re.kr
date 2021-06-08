<%
	/**
	 * @Class Name : EgovCcmCmmnDetailCodeRegist.jsp
	 * @Description : 공통상세코드 등록 화면
	 * @Modification Information
	 * @
	 * @  수정일             수정자                   수정내용
	 * @ -------    --------    ---------------------------
	 * @ 2009.02.01   박정규              최초 생성
	 *   2017.09.04   이정은              표준프레임워크 v3.7 개선
	 *
	 *  @author 공통서비스팀
	 *  @since 2009.02.01
	 *  @version 1.0
	 *  @see
	 *
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="info" uri="http://infomind.com/info" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<c:set var="pageTitle"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.title"/></c:set>
<script type="text/javascript" src="<c:url value="/validator.do"/>"></script>
<validator:javascript formName="cmmnDetailCodeVO" staticJavascript="false" xhtml="true" cdata="false"/>


<script src="<c:url value='/js/infomind/com/jquery-3.4.1.js' />"></script>
<script src="<c:url value="/js/infomind/com/common-ui/jquery-ui-1.12.1/jquery-ui.js"/>"></script>
<script src="<c:url value="/js/infomind/com/jquery.ext.js"/>"></script>

<script type="text/javascript">



	$(document).ready(function() {

		fn_egov_init();
		fncShowMessg();


		selectBoxInit();

		$('#clCode').change(function() {

			var API_SERVER = "<c:url value='/cms/code/search/selectComtccmmncodeList.do' />";
			ajaxLoadSelect({
				url: API_SERVER,
				params: [
					{name: 'clCode', value:this.value}

				],
				selectboxNm: 'codeId'
			});

		});



	});

	/* ********************************************************
     * 초기화
     ******************************************************** */
	function fn_egov_init(){

		// 첫 입력란에 포커스
		document.getElementById("cmmnDetailCodeVO").clCode.focus();

	}
	/* ********************************************************
     * 목록 으로 가기
     ******************************************************** */
	function fn_egov_list_code(){
		location.href = "<c:url value='/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do' />";
	}
	/* ********************************************************
     * 저장처리화면
     ******************************************************** */
	function fn_egov_regist_code(form){
		//input item Client-Side validate
		if (!validateCmmnDetailCodeVO(form)) {
			return false;
		} else {
			if(confirm("<spring:message code="common.regist.msg" />")){
				form.action="<c:url value='/cms/ccm/cde/RegistCcmCmmnDetailCode.do'/>";
				form.submit();
			}
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



	function selectBoxInit() {

		var API_SERVER = "<c:url value='/cms/code/search/selectComtccmmnclcodeList.do' />";
		ajaxLoadSelect({
			url: API_SERVER,
			params: [

			],
			selectboxNm: 'clCode'
		});


	}


</script>
<div class="sub subView">
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>등록 내역
	</h2>

	<form:form commandName="cmmnDetailCodeVO" method="post" onSubmit="fn_egov_regist_code(document.forms[0]); return false;">
		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<tbody>
				<!-- 입력/선택 -->
				<c:set var="inputTxt"><spring:message code="input.input" /></c:set>
				<c:set var="inputSelect"><spring:message code="input.select"/></c:set>
				<c:set var="inputYes"><spring:message code="input.yes" /></c:set>
				<c:set var="inputNo"><spring:message code="input.no" /></c:set>
				<!-- 코드ID -->
				<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeId"/> </c:set>
				<tr>
					<th><label for="codeId">${title} <span class="pilsu">*</span></label></th>
					<td class="left">

							<span class="select-outline">
								 <info:select name="clCode" id="clCode"  width="150" first="선택해주세요" val="${cmmnDetailCodeVO.clCode}"/>
							</span>
							<%--<form:select path="clCode" title="${title} ${inputSelect}" onChange="fn_egov_get_CodeId(cmmnDetailCodeVO);">--%>
							<%--<form:option value="" label="${inputSelect}"/>--%>
							<%--<form:options items="${clCodeList}"  itemValue="clCode" itemLabel="clCodeNm"/>--%>
							<%--</form:select>--%>

							<%--<form:select path="codeId" title="${title} ${inputSelect}" >--%>
							<%--<form:option value="" label="${inputSelect}"/>--%>
							<%--<form:options items="${codeList}"  itemValue="codeId" itemLabel="codeIdNm"/>--%>
							<%--</form:select>--%>
						<span class="select-outline">
								 <info:select name="codeId" id="codeId"  width="200" first="선택해주세요" val="${cmmnDetailCodeVO.codeId}"/>
							</span>



						<div><form:errors path="codeId" cssClass="error" /></div>

					</td>
				</tr>

				<!-- 상세코드 -->
				<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.code"/> </c:set>
				<tr>
					<th><label for="code">${title} <span class="pilsu">*</span></label></th>
					<td class="left">
						<form:input path="code" title="${title} ${inputTxt}" size="70" maxlength="70" />
						<div><form:errors path="code" cssClass="error" /></div>
					</td>
				</tr>

				<!-- 상세코드명 -->
				<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeNm"/> </c:set>
				<tr>
					<th><label for="codeNm">${title} <span class="pilsu">*</span></label></th>
					<td class="left">
						<form:input path="codeNm" title="${title} ${inputTxt}" size="70" maxlength="70" />
						<div><form:errors path="codeNm" cssClass="error" /></div>
					</td>
				</tr>

				<!-- 상세코드설명 -->
				<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.codeDc"/> </c:set>
				<tr>
					<th><label for="codeDc">${title } <span class="pilsu">*</span></label></th>
					<td class="nopd">
						<form:textarea path="codeDc" title="${title} ${inputTxt}" cols="300" rows="10" />
						<div><form:errors path="codeDc" cssClass="error" /></div>
					</td>
				</tr>

				<!-- 사용여부 -->
				<c:set var="title"><spring:message code="comSymCcmCde.cmmnDetailCodeVO.useAt"/> </c:set>
				<tr>
					<th>${title } <span class="pilsu">*</span></th>
					<td class="left">
						<form:select path="useAt" title="${title} ${inputTxt }" cssClass="txt">
							<form:option value="Y"  label=" ${inputYes}"/>
							<form:option value="N" label=" ${inputNo}"/>
						</form:select>
						<div><form:errors path="useAt" cssClass="error" /></div>
					</td>
				</tr>

				</tbody>
			</table>

			<!-- 하단 버튼 -->
			<div class="btn-set right">
				<input type="submit" class="button" value="<spring:message code="button.create" />" title="<spring:message code="button.create" /> <spring:message code="input.button" />" />
				<button type="button" class="button" onclick="location.href='<c:url value='/cms/ccm/cde/SelectCcmCmmnDetailCodeList.do?menuTargetNo=${menuInfo.menuTargetNo}' />'"  title="<spring:message code="title.list" /> <spring:message code="input.button" />" ><spring:message code="button.list" /></button>

			</div>

		</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<%-- <input name="cmd" type="hidden" value="<c:out value='save'/>"> --%>
	</form:form>

</div>