<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%@ taglib prefix="info" uri="http://infomind.com/info" %>

<c:set var="pageTitle">게시판</c:set>
<script type="text/javascript">
$(document).ready(function() {
	fncShowMessg();
	fieldErrors();
});

/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_update_page(form){
	//input item Client-Side validate
	if(confirm("<spring:message code="common.update.msg" />")){
		form.action="<c:url value='/cms/info/board/updateBoard.do' />";
		form.submit();
	}
}

function fncGoBack(){
	document.resultVO.action="<c:url value='/cms/info/board/boardList.do' />";
	document.resultVO.submit();
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
	<h2 class="stitle">
		<i class='bx bxs-dashboard' ></i>${pageTitle} <spring:message code="title.detail" />
	</h2>

	<form:form commandName="resultVO" name="resultVO" id="resultVO" action="${pageContext.request.contextPath}/cms/info/board/updateBoard.do" method="post" onSubmit="fn_update_page(document.forms[0]); return false;">
		<%--hidden text--%>
		<%--<info:hiddenField searchHiddenField="${searchVO}" type="text"/>--%>
		<h3 class="btitle">
			수정 내역
		</h3>

		<div class="rows white-box">
			<!-- 등록폼 -->
			<table class="landscape" summary="<spring:message code="common.summary.list" arguments="${pageTitle}" />">
				<colgroup>
					<col width="9%"/>
					<col width="41%"/>
					<col width="9%"/>
					<col width="41%"/>
				</colgroup>
				<tbody>
					<!-- 입력/선택 -->
					<tr>
						<th style="width: 9%;">
							<label for="boardId">게시판 아이디<span class="pilsu">*</span></label>
						</th>
						<td class="left" colspan="3">
							${resultVO.boardId}
						<div><form:errors path="boardId" cssClass="error" /></div>
						</td>


					</tr>
					<tr>
						<th><label for="boardNm">게시판 명  <span class="pilsu">*</span></label></th>
						<td class="left" colspan="3">
							<form:input path="boardNm" size="70" maxlength="25" />
							<div><form:errors path="boardNm" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="boardSkinId">게시판 스킨 </label></th>
						<td class="left" colspan="3">
							<form:select path="boardSkinId">
								<form:option value="">선택</form:option>
								<c:forEach var="item" items="${boardSkinList}">
									<form:option value="${item.boardSkinId}">${item.boardSkinNm}</form:option>
								</c:forEach>
							</form:select>
							<div><form:errors path="boardSkinId" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>리스트 형태 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="boardListType" cssClass="txt">
								<form:option value="LIST"  label="리스트"/>

							</form:select>
							<div><form:errors path="boardListType" cssClass="error" /></div>
						</td>

						<th>리스트 로우 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="listRow" cssClass="txt">
								<form:option value="10" label="10"/>
								<form:option value="9"  label="9"/>
								<form:option value="8"  label="8"/>
								<form:option value="7"  label="7"/>
								<form:option value="6"  label="6"/>
								<form:option value="5"  label="5"/>
								<form:option value="4"  label="4"/>
								<form:option value="3"  label="3"/>
								<form:option value="2"  label="2"/>
								<form:option value="1"  label="1"/>
								<form:option value="11"  label="11"/>
								<form:option value="12"  label="12"/>
								<form:option value="13"  label="13"/>
								<form:option value="14"  label="14"/>
								<form:option value="15"  label="15"/>
							</form:select>
							<div><form:errors path="listRow" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="subKeyYn">사용자키 여부 </label></th>
						<td class="left" colspan="3">
							<form:select path="subKeyYn" cssClass="txt">
								<form:option value="N"  label="사용안함"/>
								<form:option value="Y"  label="사용"/>

							</form:select>

							<div><form:errors path="subKeyYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="boardTxt">게시판 내용 <span class="pilsu">*</span></label></th>
						<td class="nopd" colspan="3">
							<div>
								<form>
									<form:textarea path="boardTxt" id="boardTxt" rows="5" cols="80" maxlength="250"></form:textarea>
								</form>
							</div>
							<div><form:errors path="boardTxt" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>게시판 사용여부 <span class="pilsu">*</span></th>
						<td class="left" colspan="3">
							<form:select path="useYn" cssClass="txt">
								<form:option value="Y"  label="사용"/>
								<form:option value="N" label="사용안함"/>
							</form:select>
							<div><form:errors path="useYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>댓글 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="useComment" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="useComment" cssClass="error" /></div>
						</td>

						<th>답글 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="useReply" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="useReply" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>공지 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="useNotice" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="useNotice" cssClass="error" /></div>
						</td>

						<th>본인 글만 보기 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="loginUserListYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="loginUserListYn" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th>만족도 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="stsfdgAt" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="stsfdgAt" cssClass="error" /></div>
						</td>

						<th>문의 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="inquireYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="inquireYn" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th>비밀글 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="wrSecretYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="wrSecretYn" cssClass="error" /></div>
						</td>

						<th>비밀번호 입력 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="secretPwYn" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="secretPwYn" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th>첨부파일 사용여부 <span class="pilsu">*</span></th>
						<td class="left">
							<form:select path="fileAtchPosblAt" cssClass="txt">
								<form:option value="N" label="사용안함"/>
								<form:option value="Y"  label="사용"/>
							</form:select>
							<div><form:errors path="fileAtchPosblAt" cssClass="error" /></div>
						</td>
						<th><label for="atchPosblFileNumber">첨부파일 가능 갯수  <span class="pilsu">*</span></label></th>
						<td class="left">
							<form:select path="atchPosblFileNumber" cssClass="txt">
								<form:option value="1"  label="1"/>
								<form:option value="2"  label="2"/>
								<form:option value="3"  label="3"/>
								<form:option value="4"  label="4"/>
								<form:option value="5"  label="5"/>

							</form:select>
							<div><form:errors path="atchPosblFileNumber" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="atchPosblFileSize">첨부파일 가능 사이즈  <span class="pilsu">*</span></label></th>
						<td class="left" colspan="3">
							<form:input path="atchPosblFileSize" size="70" maxlength="10" />
							<div><form:errors path="atchPosblFileSize" cssClass="error" /></div>
						</td>
					</tr>

					<tr>
						<th><label for="temp1">임시필드1 </label></th>
						<td class="left" colspan="3">
							<form:input path="temp1" size="70" maxlength="250" />
							<div><form:errors path="temp1" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp2">임시필드2 </label></th>
						<td class="left" colspan="3">
							<form:input path="temp2" size="70" maxlength="250" />
							<div><form:errors path="temp2" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp3">임시필드3 </label></th>
						<td class="left" colspan="3">
							<form:input path="temp3" size="70" maxlength="250" />
							<div><form:errors path="temp3" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp4">임시필드4 </label></th>
						<td class="left" colspan="3">
							<form:input path="temp4" size="70" maxlength="250" />
							<div><form:errors path="temp4" cssClass="error" /></div>
						</td>
					</tr>
					<tr>
						<th><label for="temp5">임시필드5 </label></th>
						<td class="left" colspan="3">
							<form:input path="temp5" size="70" maxlength="250" />
							<div><form:errors path="temp5" cssClass="error" /></div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 하단 버튼 -->
		<div class="btn-set right">
			<input type="submit" class="button" value="<spring:message code="button.update" />" title="<spring:message code="button.update" /> <spring:message code="input.button" />" />
			<button class="button" type="button" onclick="fncGoBack()"> <spring:message code="button.list" /></button>
		</div>
		<input name="menuTargetNo" id="menuTargetNo" type="hidden" value="${menuInfo.menuTargetNo}">
		<input name="boardId" id="boardId" type="hidden" value="${resultVO.boardId}">
		<input name="cmd" type="hidden" value="<c:out value='save'/>">
	</form:form>
</div>